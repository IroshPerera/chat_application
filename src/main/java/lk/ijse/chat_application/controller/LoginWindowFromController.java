package lk.ijse.chat_application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.chat_application.bo.custom.UserBO;
import lk.ijse.chat_application.bo.custom.impl.UserBOImpl;
import lk.ijse.chat_application.client.Client;
import lk.ijse.chat_application.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginWindowFromController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;

    UserBO userBO = new UserBOImpl();

    List<String> user_list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void btnJoinOnAction(ActionEvent event) {
        String user_name = txtUser.getText();
        String password = txtPassword.getText();
        try {
            if (userBO.chekValidUser(new UserDTO(user_name, password))) {



                if (checkDuplicate(user_name)) {
                    new Alert(Alert.AlertType.ERROR, user_name + " is Already join this chat !!!").show();
                } else {
                    user_list.add(user_name);
                    Client client = new Client(user_name);

                    Thread thread = new Thread(client);
                    thread.start();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Incorrect User name or Password :(").show();
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean checkDuplicate(String user_name) {
        for (String name : user_list) {
            if (name.equals(user_name)) {
                return true;
            }
        }
        return false;
    }
    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            root.getChildren().clear();
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/user_list_window_form.fxml"))));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
