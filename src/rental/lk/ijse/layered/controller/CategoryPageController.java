package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import rental.lk.ijse.layered.bo.BOFactory;
import rental.lk.ijse.layered.bo.BOFactoryType;
import rental.lk.ijse.layered.bo.custom.CategoryBO;
import rental.lk.ijse.layered.dto.CategoryDTO;
import rental.lk.ijse.layered.sheared.CategoryShearedData;
import rental.lk.ijse.layered.util.Constants;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryPageController implements Initializable {
    @FXML
    private Label txtCategoryTitle;

    @FXML
    private TableView<CategoryDTO> tblCategory;

    @FXML
    private TableColumn<CategoryDTO, Integer> colId;

    @FXML
    private TableColumn<CategoryDTO, String> colName;

    @FXML
    private TableColumn<CategoryDTO, String> colDesc;

    @FXML
    private TableColumn<CategoryDTO, String> colStatus;

    @FXML
    private JFXComboBox<String> cmbCategoryStatus;

    @FXML
    private JFXTextField txtCategorySearch;

    @FXML
    private JFXTextField txtCategoryName;

    @FXML
    private JFXTextField txtCategoryDesc;

    @FXML
    private JFXComboBox<String> cmbCategoryStatusField;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClearText;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnClearSearch;

    private final CategoryBO categoryBO;

    private ObservableList<String> searchCategoryStatus;

    private ObservableList<String> textCategoryStatus;

    @FXML
    void updateCategory(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        if (validationCategory()) {
            boolean b = categoryBO.updateCategory(
                    new CategoryDTO(CategoryShearedData.categoryId, txtCategoryName.getText(), txtCategoryDesc.getText(), cmbCategoryStatusField.getValue()));
            if (b) {
                loadTableDate();
                clearTextFields();
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Validation Fail");
        }
    }

    @FXML
    void deleteCategory(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        boolean b = categoryBO.deleteCategory(CategoryShearedData.categoryId);
        if (b) {
            loadTableDate();
            clearTextFields();
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }

    }

    @FXML
    void addCategory(ActionEvent event) throws Exception {
        String name = txtCategoryName.getText();
        String desc = txtCategoryDesc.getText();
        String status = cmbCategoryStatusField.getValue();

        if (validationCategory()) {
            int categoryLastId = categoryBO.getCategoryLastId();

            CategoryDTO categoryDTO = new CategoryDTO(categoryLastId, name, desc, status);

            boolean b = categoryBO.saveCategory(categoryDTO);
            if (b) {
                loadTableDate();
                clearTextFields();
                System.out.println("Success");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Validation Fail");
        }
    }

    private void initiatePageController() throws SQLException, IOException, ClassNotFoundException {
        loadTableDate();
        cmbCategoryStatus.getItems().addAll(Constants.ALL_STATUS,Constants.ACTIVE_STATUS, Constants.INACTIVE_STATUS);
        cmbCategoryStatusField.getItems().addAll(Constants.ACTIVE_STATUS, Constants.INACTIVE_STATUS);
        cmbCategoryStatus.getSelectionModel().selectFirst();
        cmbCategoryStatusField.setEditable(false);
        btnAdd.setVisible(true);
        btnAdd.setDisable(true);
        btnClearText.setVisible(true);
        btnClearText.setDisable(true);
        btnUpdate.setVisible(false);
        btnDelete.setVisible(false);
        btnSearch.setDisable(true);
        btnClearSearch.setDisable(true);
    }

    private void modifyFunctionPageController() {
        btnAdd.setVisible(false);
        btnClearText.setVisible(true);
        btnClearText.setDisable(false);
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);
        btnSearch.setDisable(true);
        btnClearSearch.setDisable(true);
        txtCategorySearch.setDisable(true);
        cmbCategoryStatus.setDisable(true);
    }

    @FXML
    void clearSearch(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        clearSearchFields();
    }

    @FXML
    void clearText(ActionEvent event) {
        btnAdd.setDisable(true);
        clearTextFields();
    }

    private void clearTextFields() {
        txtCategoryName.setText("");
        txtCategoryDesc.setText("");
        cmbCategoryStatusField.setValue(null);
        tblCategory.getSelectionModel().clearSelection();
        btnDelete.setVisible(false);
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        btnAdd.setDisable(true);
        btnClearText.setDisable(true);
    }

    private void clearSearchFields() throws SQLException, IOException, ClassNotFoundException {
        txtCategorySearch.setText("");
        cmbCategoryStatus.setValue(null);
        btnSearch.setDisable(true);
        loadTableDate();
    }

    @FXML
    void search(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String searchValue = null;
        if (!txtCategorySearch.getText().trim().isEmpty()) {
            searchValue = txtCategorySearch.getText().trim();
        }

        tblCategory.setItems(null);
        List<CategoryDTO> categoryDTOS = categoryBO.searchCategory(searchValue);
        if (categoryDTOS != null) {
            colId.setCellValueFactory(new PropertyValueFactory<CategoryDTO, Integer>("categoryId"));
            colName.setCellValueFactory(new PropertyValueFactory<CategoryDTO, String>("name"));
            colDesc.setCellValueFactory(new PropertyValueFactory<CategoryDTO, String>("description"));
            colStatus.setCellValueFactory(new PropertyValueFactory<CategoryDTO, String>("status"));
            ObservableList<CategoryDTO> searchData = FXCollections.observableArrayList(categoryDTOS);
            tblCategory.setItems(searchData);
        }
    }

    @FXML
    void keyUpDesc(KeyEvent event) {
        btnClearText.setDisable(false);
        btnAdd.setDisable(false);
    }

    @FXML
    void keyUpName(KeyEvent event) {
        btnClearText.setDisable(false);
        btnAdd.setDisable(false);
    }

    @FXML
    void keyUpSearch(KeyEvent event) {
        if (txtCategorySearch.getText().isEmpty()) {
            btnSearch.setDisable(false);
            btnClearSearch.setDisable(false);
        }
    }

    @FXML
    void selectStatus(ActionEvent event) {
        if (!cmbCategoryStatus.getSelectionModel().isEmpty()) {
            btnSearch.setDisable(false);
            btnClearSearch.setDisable(false);
        }
    }

    private boolean validationCategory() {
        String name = txtCategoryName.getText();
        String desc = txtCategoryDesc.getText();
        String status = cmbCategoryStatusField.getValue();
        return !name.isEmpty() && !desc.isEmpty() && name.length() >= 2 && desc.length() >= 2 && !status.isEmpty();
    }


    public CategoryPageController() {
        categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactoryType.CATEGORY);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initiatePageController();
        } catch (SQLException | IOException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadTableDate() throws SQLException, IOException, ClassNotFoundException {
        List<CategoryDTO> allCategory = categoryBO.getAllCategory();
        if (allCategory != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            ObservableList<CategoryDTO> categoryDTOS = FXCollections.observableArrayList(allCategory);
            tblCategory.setItems(categoryDTOS);
        }
    }

    @FXML
    void getItem(MouseEvent event) {
        if (!tblCategory.getSelectionModel().isEmpty()) {
            modifyFunctionPageController();
            CategoryDTO selectedItem = tblCategory.getSelectionModel().getSelectedItem();
            CategoryShearedData.categoryId = selectedItem.getCategoryId();
            CategoryShearedData.name = selectedItem.getName();
            txtCategoryName.setText(selectedItem.getName());
            CategoryShearedData.description = selectedItem.getDescription();
            txtCategoryDesc.setText(selectedItem.getDescription());
            CategoryShearedData.status = selectedItem.getStatus();
            cmbCategoryStatusField.setValue(selectedItem.getStatus());
        }
    }

}
