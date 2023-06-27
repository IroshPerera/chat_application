package lk.ijse.chat_application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import lk.ijse.chat_application.dto.Client;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientWindowFromController implements Initializable {


    @FXML
    private TextField txtMsgArea;
    private Client client;

    @FXML
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        try {
            String text = txtMsgArea.getText();
            if (text != null) {
                appendText(text);
                client.sendMessage(text);
                txtMsgArea.clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "message is empty").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong : server down").show();
        }
    }

    public void writeMessage(String message) {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-left;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label messageLbl = new Label(message);
        messageLbl.setStyle("-fx-background-color:   #2980b9;-fx-background-radius:15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
        hBox.getChildren().add(messageLbl);
        Platform.runLater(() -> {

            vBox.getChildren().add(hBox);
        });

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setImage(byte[] bytes, String utf) {

    }

    void appendText(String message) {
//        me
        HBox hBox = new HBox();
        hBox.setStyle("-fx-alignment: center-right;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
        Label messageLbl = new Label(message);
        messageLbl.setStyle("-fx-background-color:  purple;-fx-background-radius:15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center-left;-fx-content-display: left;-fx-padding: 10;-fx-max-width: 350;");
        hBox.getChildren().add(messageLbl);
        vBox.getChildren().add(hBox);
        new Thread(()->{
           playSound("view/meadia/messageSend.mp3");
        }).start();

    }
    private void playSound(String sound) {
        try {
            new Player(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(sound))).play();
        } catch (JavaLayerException e) {
            new Alert(Alert.AlertType.ERROR, "Audio not available").show();
        }
    }
}
