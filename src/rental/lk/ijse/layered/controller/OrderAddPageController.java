package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import rental.lk.ijse.layered.bo.BOFactory;
import rental.lk.ijse.layered.bo.BOFactoryType;
import rental.lk.ijse.layered.bo.custom.ItemBO;
import rental.lk.ijse.layered.bo.custom.OrderBO;
import rental.lk.ijse.layered.dto.ItemDTO;
import rental.lk.ijse.layered.dto.OrderDTO;
import rental.lk.ijse.layered.dto.OrderDetailsDTO;
import rental.lk.ijse.layered.util.Constants;
import rental.lk.ijse.layered.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class OrderAddPageController implements Initializable {

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private Label txtCategoryTitle;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerPhoneNumber;

    @FXML
    private JFXTextField txtCustomerNic;

    @FXML
    private JFXComboBox<String> cmdItemName;

    @FXML
    private JFXDatePicker txtOrderReturnDate;

    @FXML
    private JFXTextField txtItemPricePreDay;

    @FXML
    private JFXTextField txtItemPriceExtraDay;

    @FXML
    private TableView<OrderDetailsDTO> tblOrderDetails;

    @FXML
    private TableColumn<OrderDetailsDTO, Integer> colItemId;

    @FXML
    private TableColumn<OrderDetailsDTO, String> colItemName;

    @FXML
    private TableColumn<OrderDetailsDTO, Date> colReturnDate;

    @FXML
    private TableColumn<OrderDetailsDTO, Double> colAmount;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXButton btnClearCustomerContent;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label txtTotalPrice;

    @FXML
    private JFXTextField txtItemPrice;

    private OrderBO orderBO;
    private ItemBO itemBO;

    private HashMap<String, ItemDTO> itemDTOHashMap;
    private HashMap<String, ItemDTO> removeItemDTOHashMap;

    private ArrayList<OrderDetailsDTO> orderDetails;

    private ObservableList<String> itemNameList;

    public OrderAddPageController() throws SQLException, IOException, ClassNotFoundException {
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactoryType.ODER);
        itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactoryType.ITEM);
        itemDTOHashMap = itemBO.getFreeItemList();
        itemNameList = FXCollections.observableArrayList();
        orderDetails = new ArrayList<>();
        removeItemDTOHashMap = new HashMap<>();
    }

    @FXML
    void addItem(ActionEvent event) throws Exception {
        double itemPrice = Double.parseDouble(txtItemPrice.getText());
        String itemName = cmdItemName.getValue();
        int itemId = itemDTOHashMap.get(itemName).getItemId();
        String returnDate = Utils.getStringDate(txtOrderReturnDate.getValue());

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        orderDetailsDTO.setItemName(itemName);
        orderDetailsDTO.setAmount(itemPrice);
        orderDetailsDTO.setItemId(itemId);
        orderDetailsDTO.setOrderReturnDate(returnDate);
        orderDetails.add(orderDetailsDTO);
        addItemTable(orderDetails);

        clearDetailsText();
        removeItemForMap(itemName);
        orderDetailsPageController();
        txtTotalPrice.setText(calculateTotalAmount()+"0");
        tblOrderDetails.setDisable(false);
    }

    private void removeItemForMap(String itemName) {
        ItemDTO remove = itemDTOHashMap.remove(itemName);
        itemNameList.clear();
        itemNameList.addAll(FXCollections.observableArrayList(itemDTOHashMap.keySet()));
        cmdItemName.setItems(FXCollections.observableArrayList(itemNameList));
        removeItemDTOHashMap.put(remove.getName(), remove);
    }

    @FXML
    void getItem(MouseEvent event) throws ParseException, SQLException, IOException, ClassNotFoundException {
        OrderDetailsDTO selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();
        getItemMapDataController(selectedItem.getItemName());
        cmdItemName.setValue(selectedItem.getItemName());
        txtItemPricePreDay.setText(itemDTOHashMap.get(selectedItem.getItemName()).getPreDayPrice()+"");
        txtOrderReturnDate.setValue(Utils.dateToLocalDate(Utils.getUtilDate(selectedItem.getOrderReturnDate())));
        txtItemPrice.setText(selectedItem.getAmount()+"");

        orderDetails.remove(selectedItem);
        addItemTable(orderDetails);
        txtTotalPrice.setText(calculateTotalAmount()+"0");
        tblOrderDetails.setEditable(true);
    }

    private void getItemMapDataController(String itemName) throws SQLException, IOException, ClassNotFoundException {
        cmdItemName.setItems(null);
        itemNameList.add(itemName);
        cmdItemName.setItems(FXCollections.observableArrayList(itemNameList));
        ItemDTO itemDTO = removeItemDTOHashMap.remove(itemName);
        itemDTOHashMap.put(itemName, itemDTO);
    }

    private double calculateItemAmount(int days, double price) {
        return days * price;
    }

    private double calculateTotalAmount() {
        return orderDetails.stream().mapToDouble(OrderDetailsDTO::getAmount).sum();
    }

    private void addItemTable(ArrayList<OrderDetailsDTO> orderDetailsDTO) {
        mapTableDate(orderDetailsDTO);
    }

    private void mapTableDate(ArrayList<OrderDetailsDTO> orderDetailsDTO) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("orderReturnDate"));
        ObservableList<OrderDetailsDTO> orderDetailsDTOS = FXCollections.observableArrayList(orderDetailsDTO);
        tblOrderDetails.setItems(orderDetailsDTOS);
    }

    private void removeItemTable() {

    }

    public boolean validateOrder() {
        return !txtCustomerName.getText().isEmpty() && txtCustomerAddress.getText().isEmpty()
                && !txtCustomerPhoneNumber.getText().isEmpty() && txtCustomerNic.getText().isEmpty()
                && !orderDetails.isEmpty();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderDetails = new ArrayList<>();
        txtItemPricePreDay.setEditable(false);
        Set<String> strings = itemDTOHashMap.keySet();
        cmdItemName.setItems(FXCollections.observableArrayList(strings));
        orderDetailsPageController();
        txtTotalPrice.setText("0.00");
    }


    @FXML
    void cancelOrder(ActionEvent event) {
        btnCancel.getScene().getWindow().setOnCloseRequest(event1 -> Platform.exit());
    }



    @FXML
    void clearCustomerContent(ActionEvent event) {

    }

    @FXML
    void keyUpAddress(KeyEvent event) {

    }

    @FXML
    void keyUpName(KeyEvent event) {

    }

    @FXML
    void keyUpNic(KeyEvent event) {

    }

    @FXML
    void keyUpPhoneNumber(KeyEvent event) {

    }

    @FXML
    void placeOrder(ActionEvent event) throws Exception {
        String customerName = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String phoneNumber = txtCustomerPhoneNumber.getText();
        String nic = txtCustomerNic.getText();

        Date currentDate = new Date();

        double totalAmount = orderDetails.stream().mapToDouble(OrderDetailsDTO::getAmount).sum();

        if (validateOrder()) {
            int orderLastId = orderBO.getOrderLastId();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(orderLastId);
            orderDTO.setOrderDate(Utils.getStringDate(Utils.dateToLocalDate(currentDate)));
            orderDTO.setCustomerName(customerName);
            orderDTO.setCustomerAddress(address);
            orderDTO.setCustomerPhoneNumber(phoneNumber);
            orderDTO.setCustomerNic(nic);
            orderDTO.setTotalAmount(totalAmount);
            orderDTO.setTotalExtraAmount(0);
            orderDTO.setStatus(Constants.NON_PAID_STATUS);
            orderDTO.setDetailsDTO(orderDetails);

            boolean b = orderBO.saveOrder(orderDTO);
            if (b) {
                System.out.println("Success");
            }else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Validation Fail");
        }
    }

    private void clearDetailsText() {
        cmdItemName.setValue(null);
        txtItemPricePreDay.setText("");
        txtOrderReturnDate.setValue(null);
        txtItemPrice.setText("");
    }

    @FXML
    void removeItem(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        cmdItemName.setValue(null);
        txtItemPricePreDay.setText("");
        txtOrderReturnDate.setValue(null);
        txtItemPrice.setText("0.00");

        if (!tblOrderDetails.getSelectionModel().isEmpty()) {
            orderDetails.remove(tblOrderDetails.getSelectionModel().getSelectedItem());
            txtTotalPrice.setText(calculateTotalAmount()+"");

            ItemDTO itemDTO = itemBO.getItemById(tblOrderDetails.getSelectionModel().getSelectedItem().getItemId());
            itemDTOHashMap.put(itemDTO.getName(), itemDTO);
            tblOrderDetails.setDisable(false);
        }
    }

    @FXML
    void setDate(ActionEvent event) {
        LocalDate value = txtOrderReturnDate.getValue();
        if (value != null) {
            String stringDate = Utils.getStringDate(value);
            if (!stringDate.trim().isEmpty()) {
                int dateDifference = Utils.getDateDifference(value);
                double itemPrice = Double.parseDouble(txtItemPricePreDay.getText());
                txtItemPrice.setText((dateDifference*itemPrice)+"");
            }
        }
    }

    @FXML
    void setItem(ActionEvent event) {
        String itemName = cmdItemName.getValue();
        if (itemName != null) {
            if (!itemName.trim().isEmpty()) {
                txtOrderReturnDate.setDisable(false);
                if (itemDTOHashMap.get(itemName) == null) {
                    txtItemPricePreDay.setText(removeItemDTOHashMap.get(itemName).getPreDayPrice()+"");
                } else {
                    txtItemPricePreDay.setText(itemDTOHashMap.get(itemName).getPreDayPrice()+"");
                }
            }
        }
    }

    private void orderDetailsPageController() {
        txtOrderReturnDate.setDisable(true);
        txtItemPrice.setEditable(false);
        txtItemPricePreDay.setEditable(false);
    }
}
