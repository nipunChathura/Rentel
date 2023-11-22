package rental.lk.ijse.layered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardController {
    @FXML
    public JFXButton btnNavHome;

    @FXML
    private BorderPane bp;

    @FXML
    private JFXButton btnNavCategory;

    @FXML
    private JFXButton btnNavItem;

    @FXML
    private JFXButton btnNavOrder;

    @FXML
    private AnchorPane ap;

    @FXML
    private Label txtDatetime;

    private String activePage = "../view/DefaultPage.fxml";

    @FXML
    public void initialize() throws IOException {
        loadPage(activePage);
        txtDatetime.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 0 20; -fx-background-color: #ffffff;");
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                txtDatetime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }

    @FXML
    void loadCategoryPage(MouseEvent event) throws IOException {
        activePage = "../view/CategoryPage.fxml";
        loadPage(activePage);
    }

    @FXML
    void loadItemPage(MouseEvent event) throws IOException {
        activePage = "../view/ItemPage.fxml";
        loadPage(activePage);
    }

    @FXML
    void loadOrderPage(MouseEvent event) throws IOException {
        activePage = "../view/OrderPage.fxml";
        loadPage(activePage);
    }

    private void loadPage(String page) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource(page));
        bp.setCenter(root);
    }

    public void loadHomePage(MouseEvent mouseEvent) throws IOException {
        activePage = "../view/DefaultPage.fxml";
        loadPage(activePage);
    }
}
