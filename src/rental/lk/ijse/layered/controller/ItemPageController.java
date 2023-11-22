package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import rental.lk.ijse.layered.bo.custom.CategoryBO;
import rental.lk.ijse.layered.bo.custom.ItemBO;
import rental.lk.ijse.layered.dto.ItemDTO;
import rental.lk.ijse.layered.sheared.ItemShearedData;
import rental.lk.ijse.layered.util.Constants;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemPageController implements Initializable {

    @FXML
    public JFXTextField txtItemSearch;

    @FXML
    public JFXComboBox<String> cmbItemStatus1;

    @FXML
    private Label txtItemTitle;

    @FXML
    private Label txtItemTitleParameter;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtItemPreDayPrice;

    @FXML
    private JFXTextField txtItemExtraPrice;

    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXTextField txtItemStatus;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

//    @FXML
//    private JFXTextField txtItemSearch;

//    @FXML
//    private JFXComboBox<String> cmbItemStatus;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnClearSearch;

    @FXML
    private TableView<ItemDTO> tblItem;

    @FXML
    private TableColumn<ItemDTO, Integer> colId;

    @FXML
    private TableColumn<ItemDTO, String> colName;

    @FXML
    private TableColumn<ItemDTO, Double> colPerDayPrice;

    @FXML
    private TableColumn<ItemDTO, Double> colExtraDayPrice;

    @FXML
    private TableColumn<ItemDTO, String> colCategory;

    @FXML
    private TableColumn<ItemDTO, String> colStatus;

    @FXML
    private JFXComboBox<String> cmbSearchCategory;

    @FXML
    private JFXComboBox<String> cmbItemStatus;

    @FXML
    private JFXButton btnSearch;

    private CategoryBO categoryBO;

    private ItemBO itemBO;

    private ArrayList<String> categoryNameList;

    public ItemPageController() {
        categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactoryType.CATEGORY);
        itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactoryType.ITEM);
    }

    @FXML
    void addItem(ActionEvent event) throws Exception {
        String name = txtItemName.getText();
        double preDayPrice = Double.parseDouble(txtItemPreDayPrice.getText());
        double extraPrice = Double.parseDouble(txtItemExtraPrice.getText());
        String categoryName = cmbCategory.getValue();
        String itemStatus = cmbItemStatus.getValue();

        if (validateCategory()) {
            int categoryId = categoryBO.getCategoryIdByCategoryName(categoryName);
            System.out.println("categoryId = " + categoryId);
            int itemLastId = itemBO.getItemLastId();
            System.out.println("itemLastId = " + itemLastId);

            ItemDTO itemDTO = new ItemDTO(itemLastId, name, preDayPrice, extraPrice, categoryName, categoryId, itemStatus);
            boolean b = itemBO.saveItem(itemDTO);
            if (b) {
                loadTableData();
                clearTextField();
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Validation Fail");
        }
    }

    private void clearTextField() {
        txtItemName.setText("");
        txtItemPreDayPrice.setText("");
        txtItemExtraPrice.setText("");
        cmbItemStatus.setValue(null);
        cmbCategory.setValue(null);
        tblItem.getSelectionModel().clearSelection();
        btnDelete.setVisible(false);
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        btnAdd.setDisable(true);
        btnClear.setDisable(true);
    }

    private boolean validateCategory() {
        String name = txtItemName.getText();
        double preDayPrice = Double.parseDouble(txtItemPreDayPrice.getText());
        double extraPrice = Double.parseDouble(txtItemExtraPrice.getText());
        String categoryName = cmbCategory.getValue();
        return !name.isEmpty() && preDayPrice!= 0 && extraPrice !=0 && !categoryName.isEmpty();
    }

    @FXML
    void clearSearch(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        txtItemSearch.setText("");
        cmbSearchCategory.setValue(null);
        btnClearSearch.setDisable(true);
        btnSearch.setDisable(true);
        loadTableData();
    }

    @FXML
    void clearText(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        clearTextField();
    }



    @FXML
    void deleteItem(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        boolean b = itemBO.deleteItem(ItemShearedData.itemId);
        if (b) {
            loadTableData();
            clearTextField();
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
    }

    @FXML
    void search(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String searchValue = null;
        Long categoryId = null;

        if (!txtItemSearch.getText().trim().isEmpty()) {
            searchValue = txtItemSearch.getText().trim();
        }

        if (cmbSearchCategory.getValue() != null) {
            categoryId = Long.parseLong(categoryBO.getCategoryIdByCategoryName(cmbSearchCategory.getValue())+"");
        }

        System.out.println("categoryId = " + categoryId);
        System.out.println("searchValue = " + searchValue);

        List<ItemDTO> itemDTOS = itemBO.searchItem(searchValue, categoryId);
        tblItem.setItems(null);
        if (itemDTOS != null) {
//            colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colPerDayPrice.setCellValueFactory(new PropertyValueFactory<>("preDayPrice"));
//            colExtraDayPrice.setCellValueFactory(new PropertyValueFactory<>("extraDayPrice"));
//            colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
//            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//            ObservableList<ItemDTO> itemDTOS1 = FXCollections.observableArrayList(itemDTOS);
//            tblItem.setItems(itemDTOS1);
            mapTableData(itemDTOS);
        }
    }

    private void mapTableData(List<ItemDTO> itemDTOS)  {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPerDayPrice.setCellValueFactory(new PropertyValueFactory<>("preDayPrice"));
        colExtraDayPrice.setCellValueFactory(new PropertyValueFactory<>("extraDayPrice"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        ObservableList<ItemDTO> itemDTOS1 = FXCollections.observableArrayList(itemDTOS);
        tblItem.setItems(itemDTOS1);
    }

    @FXML
    void selectCategorySearch(ActionEvent event) {
        if (!cmbSearchCategory.getValue().isEmpty()) {
            btnSearch.setDisable(false);
            btnClearSearch.setDisable(false);
        }
    }

    @FXML
    void keyUpSearch(KeyEvent event) {
        if (!txtItemSearch.getText().isEmpty()) {
            btnSearch.setDisable(false);
            btnClearSearch.setDisable(false);
        }
    }

    @FXML
    void updateItem(MouseEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (validateCategory()) {
            try {

                int categoryId = categoryBO.getCategoryIdByCategoryName(cmbCategory.getValue());
                ItemDTO itemDTO = new ItemDTO(ItemShearedData.itemId, txtItemName.getText(), Double.parseDouble(txtItemPreDayPrice.getText()), Double.parseDouble(txtItemExtraPrice.getText()), cmbCategory.getValue(), categoryId, cmbItemStatus.getValue());
                System.out.println("itemDTO = " + itemDTO);
                boolean b = itemBO.updateItem(itemDTO);

                if (b) {
                    loadTableData();
                    clearTextField();
                    System.out.println("Success");
                } else {
                    System.out.println("Fail");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Validation Fail");
        }
    }

    @FXML
    void keyUpExtraDay(KeyEvent event) {
        btnAdd.setDisable(false);
        btnClear.setDisable(false);
    }

    @FXML
    void keyUpName(KeyEvent event) {
        btnAdd.setDisable(false);
        btnClear.setDisable(false);
    }

    @FXML
    void keyUpPreDay(KeyEvent event) {
        btnAdd.setDisable(false);
        btnClear.setDisable(false);
    }

    private void modifyPageController() {
        btnAdd.setVisible(false);
        btnClear.setVisible(true);
        btnClear.setDisable(false);
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
        btnSearch.setVisible(true);
        btnClearSearch.setDisable(true);
        cmbItemStatus.setDisable(true);
        cmbSearchCategory.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbItemStatus1.setVisible(false);
        setCategoryNameList();
        try {
            initiatePageController();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setCategoryNameList() {
        try {
            categoryNameList = categoryBO.getAllActiveCategoryNameList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initiatePageController() throws SQLException, IOException, ClassNotFoundException {
        loadTableData();
        cmbItemStatus.getItems().addAll(Constants.FREE,Constants.INACTIVE_STATUS, Constants.RESERVED, Constants.DAMAGE);
        cmbItemStatus.getSelectionModel().clearSelection();
        cmbCategory.getItems().addAll(categoryNameList);
        cmbSearchCategory.getItems().addAll(categoryNameList);
        cmbItemStatus.setEditable(false);
        btnAdd.setVisible(true);
        btnAdd.setDisable(true);
        btnClear.setVisible(true);
        btnClear.setDisable(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnSearch.setDisable(true);
        btnClearSearch.setDisable(true);
    }

    private void modifyFunctionPageController() {
        btnAdd.setVisible(false);
        btnClear.setVisible(true);
        btnClear.setDisable(false);
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
        btnSearch.setDisable(true);
        btnClearSearch.setDisable(true);
        cmbItemStatus.setDisable(true);
        cmbCategory.setDisable(true);
    }

    private void loadTableData() throws SQLException, IOException, ClassNotFoundException {
        List<ItemDTO> allItem = itemBO.getAllItem();
        if (!allItem.isEmpty()) {
//            colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
//            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colPerDayPrice.setCellValueFactory(new PropertyValueFactory<>("preDayPrice"));
//            colExtraDayPrice.setCellValueFactory(new PropertyValueFactory<>("extraDayPrice"));
//            colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
//            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//            ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList(allItem);
//            tblItem.setItems(itemDTOS);

            mapTableData(allItem);
        }
    }

    @FXML
    void getItem(MouseEvent event) {
        if (!tblItem.getSelectionModel().isEmpty()) {
            modifyFunctionPageController();
            ItemDTO selectedItem = tblItem.getSelectionModel().getSelectedItem();
            ItemShearedData.itemId = selectedItem.getItemId();
            ItemShearedData.categoryId = selectedItem.getCategoryId();
            ItemShearedData.name = selectedItem.getName();
            txtItemName.setText(selectedItem.getName());
            ItemShearedData.preDayPrice = selectedItem.getPreDayPrice();
            txtItemPreDayPrice.setText(selectedItem.getPreDayPrice()+"");
            ItemShearedData.extraDayPrice = selectedItem.getExtraDayPrice();
            txtItemExtraPrice.setText(selectedItem.getExtraDayPrice()+"");
            ItemShearedData.categoryName = selectedItem.getCategoryName();
            cmbCategory.setValue(selectedItem.getCategoryName());
            ItemShearedData.categoryId = selectedItem.getCategoryId();
            ItemShearedData.status = selectedItem.getStatus();
            cmbItemStatus.setValue(selectedItem.getStatus());
            cmbCategory.setDisable(false);
            cmbItemStatus.setDisable(false);
        }

    }
}
