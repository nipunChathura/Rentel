package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rental.lk.ijse.layered.util.Constants;

import java.io.IOException;

public class AlertController {

    @FXML
    private AnchorPane alertBackground;

    @FXML
    private FontAwesomeIconView txtFontAwesome;

    @FXML
    private Label txtMessageType;

    @FXML
    private Label txtMessage;

    public void sendAlert(String type, String message) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../view/AlertView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
