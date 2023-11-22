package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rental.lk.ijse.layered.bo.BOFactory;
import rental.lk.ijse.layered.bo.BOFactoryType;
import rental.lk.ijse.layered.bo.custom.UserBO;
import rental.lk.ijse.layered.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label logo;

    @FXML
    private Label companyName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private JFXButton btnLogin;

    private UserBO userBO;

    public LoginController() {
        userBO = (UserBO) BOFactory.getInstance().getBO(BOFactoryType.USER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DropShadow shadow = new DropShadow(60, Color.valueOf("#f2f2f2"));

        logo.setEffect(shadow);
        companyName.setEffect(shadow);

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void login(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            UserDTO user = userBO.getUserByUsername(username);
            System.out.println("user = " + user);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    btnLogin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    setAlertScreen();
                }
            } else {
                System.out.println("Call");
                setAlertScreen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAlertScreen() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/AlertView.fxml"));
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.show();
    }
}
