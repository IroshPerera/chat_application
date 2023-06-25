package lk.ijse.chat_application.controller;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingWindowFormController implements Initializable {
    @FXML
    private ImageView imgLogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScaleTransition zoomIn = new ScaleTransition(Duration.seconds(1.5), imgLogo);
        zoomIn.setFromX(1.0);
        zoomIn.setFromY(1.0);
        zoomIn.setToX(1.5);
        zoomIn.setToY(1.5);
        zoomIn.play();
    }
}
