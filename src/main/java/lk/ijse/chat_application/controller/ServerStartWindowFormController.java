package lk.ijse.chat_application.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.chat_application.dto.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStartWindowFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgLogo;

    @FXML
    private JFXButton btnLogin;


    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {


            Server server = Server.getServerSocket();
            Thread thread = new Thread(server);
            thread.start();

            root.getChildren().clear();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_window_from.fxml"))));
            stage.show();
            stage.setOnCloseRequest(e-> {
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
