package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rental.lk.ijse.layered.bo.BOFactory;
import rental.lk.ijse.layered.bo.BOFactoryType;
import rental.lk.ijse.layered.bo.custom.OrderBO;
import rental.lk.ijse.layered.dto.CategoryDTO;
import rental.lk.ijse.layered.dto.OrderDTO;
import rental.lk.ijse.layered.util.Utils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderPageController implements Initializable {

    @FXML
    private Label txtCategoryTitle;

    @FXML
    private JFXButton btnClearSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXComboBox<String> cmbOrderStatus;

    @FXML
    private JFXTextField txtOrderSearch;

    @FXML
    private JFXDatePicker txtOrderStartDate;

    @FXML
    private JFXDatePicker txtOrderEndDate;

    @FXML
    private TableView<OrderDTO> tblOrder;

    @FXML
    private TableColumn<OrderDTO, Date> colDate;

    @FXML
    private TableColumn<OrderDTO, String> colName;

    @FXML
    private TableColumn<OrderDTO, String> colAddress;

    @FXML
    private TableColumn<OrderDTO, String> colPhoneNumber;

    @FXML
    private TableColumn<OrderDTO, String> colNic;

    @FXML
    private TableColumn<OrderDTO, Double> colTotalPrice;

    @FXML
    private TableColumn<OrderDTO, Double> colExtraPrice;

    @FXML
    private TableColumn<OrderDTO, String> colStatus;

    @FXML
    private TableColumn<OrderDTO, Integer> colId;

    @FXML
    private JFXButton btnAdd;

    private OrderBO orderBO;


    private Parent root = null;
    private Stage stage = null;

    public OrderPageController() {
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactoryType.ODER);
    }

    @FXML
    void AddOrder(ActionEvent event) throws IOException {
        if (root == null) {
            root = FXMLLoader.load(getClass().getResource("../view/OrderAddPage.fxml"));
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
        } else {
            Scene scene1 = root.getScene();
            stage.close();
            stage = new Stage();
            stage.setScene(scene1);
        }
//        btnAdd.getScene().getWindow().hide();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void clearSearch(ActionEvent event) {
        txtOrderSearch.setText("");
        txtOrderStartDate.setValue(null);
        txtOrderEndDate.setValue(null);
        cmbOrderStatus.setValue(null);
        btnAdd.setDisable(false);
        btnClearSearch.setDisable(true);
        btnSearch.setDisable(true);
    }

    @FXML
    void keyUpSearch(KeyEvent event) {
        enableSearchController();
    }

    @FXML
    void search(ActionEvent event) throws SQLException, IOException, ClassNotFoundException, ParseException {
        String orderValue = null;
        if (!txtOrderSearch.getText().trim().isEmpty()) {
            orderValue = txtOrderSearch.getText();;
        }
        String status = null;
        if (!cmbOrderStatus.getItems().isEmpty()) {
            status =  cmbOrderStatus.getValue();
        }
        Date startDate = null;
        if (txtOrderStartDate.getValue() != null) {
            startDate = Utils.localDateToDate(txtOrderStartDate.getValue());
        }
        Date endDate = null;
        if (txtOrderEndDate.getValue() != null) {
            endDate = Utils.localDateToDate(txtOrderEndDate.getValue());
        }
        btnClearSearch.setDisable(false);
        List<OrderDTO> orderDTOS = orderBO.searchOrder(orderValue, status, startDate, endDate);
        if (!orderDTOS.isEmpty()) {

            setTableDate(orderDTOS);
        }
    }

    private void enableSearchController() {
        btnSearch.setDisable(false);
        btnClearSearch.setDisable(false);
        btnAdd.setDisable(true);
    }

    @FXML
    void selectStatus(ActionEvent event) {
        enableSearchController();
    }

    @FXML
    void setDate(ActionEvent event) {
        enableSearchController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initiatePageController();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initiatePageController() throws SQLException, IOException, ClassNotFoundException {
        btnClearSearch.setDisable(true);
        btnSearch.setDisable(true);
        loadTableData();
    }

    private void loadTableData() throws SQLException, IOException, ClassNotFoundException {
        try {
            List<OrderDTO> allOrder = orderBO.getAllOrder();
            if (allOrder != null) {
                setTableDate(allOrder);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void setTableDate(List<OrderDTO> allOrder) {
        colId.setCellValueFactory(new PropertyValueFactory<OrderDTO, Integer>("orderId"));
        colDate.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("orderDate"));
        colName.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("customerAddress"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("customerPhoneNumber"));
        colNic.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("customerNic"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<OrderDTO, Double>("totalAmount"));
        colExtraPrice.setCellValueFactory(new PropertyValueFactory<OrderDTO, Double>("totalExtraAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("status"));
        ObservableList<OrderDTO> orderDTOS = FXCollections.observableArrayList(allOrder);
        tblOrder.setItems(orderDTOS);
    }
}
