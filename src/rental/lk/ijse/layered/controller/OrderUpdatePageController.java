package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderUpdatePageController implements Initializable {

    @FXML
    private Label txtCategoryTitle;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerPhoneNumber;

    @FXML
    private JFXTextField txtCustomerNic;

    @FXML
    private Label txtTotalPrice;

    @FXML
    private JFXTextField txtTotalExtraPrice;

    @FXML
    private JFXComboBox<?> cmdOrderStatus;

    @FXML
    private JFXComboBox<?> cmdItemName;

    @FXML
    private JFXDatePicker txtOrderReturnDate;

    @FXML
    private JFXTextField txtItemPricePreDay;

    @FXML
    private JFXTextField txtItemPriceExtraDay;

    @FXML
    private JFXButton btnModifyItem;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private JFXDatePicker txtOrderActualReturnDate;

    @FXML
    private JFXComboBox<?> cmdOrderDetailsStatus;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private TableColumn<?, ?> colOrderDetailsId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colActualReturnDate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colExtraAmount;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private Label txtExtraPrice;

    @FXML
    void UpdateOrder(ActionEvent event) {

    }

    @FXML
    void cancelOrder(ActionEvent event) {

    }

    @FXML
    void getItem(MouseEvent event) {

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
    void keyUpphoneNumber(KeyEvent event) {

    }

    @FXML
    void modifyItem(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    @FXML
    void setActualDate(ActionEvent event) {

    }

    @FXML
    void setDate(ActionEvent event) {

    }

    @FXML
    void setItem(ActionEvent event) {

    }

    @FXML
    void setODStatus(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
