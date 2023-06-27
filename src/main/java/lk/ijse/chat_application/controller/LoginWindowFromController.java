package lk.ijse.chat_application.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.chat_application.dto.Client;
import lk.ijse.chat_application.dto.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginWindowFromController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField txtUser;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnJoinOnAction(ActionEvent event) {
        try {
        if (Pattern.matches("^[a-zA-Z\\s]+", txtUser.getText())) {
            Client client = new Client(txtUser.getText());

            Thread thread = new Thread(client);
            thread.start();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
