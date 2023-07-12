package lk.ijse.chat_application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.chat_application.bo.custom.UserBO;
import lk.ijse.chat_application.bo.custom.impl.UserBOImpl;
import lk.ijse.chat_application.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserListWindowFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgLogo;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;


    UserBO userBO = new UserBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsers();
    }

    private void loadUsers() {
        try {
            List<UserDTO> allUsers = userBO.getAllUsers();

            for (UserDTO userDTO : allUsers){
                HBox hBox = new HBox();
                hBox.setStyle("-fx-alignment: center;-fx-fill-height: true;-fx-min-height: 50;-fx-pref-width: 520;-fx-max-width: 520;-fx-padding: 10");
                Label messageLbl = new Label("\uD83D\uDE06 "+userDTO.getName());
                messageLbl.setStyle("-fx-background-color:   #6480d5;-fx-background-radius:15;-fx-font-size: 18;-fx-font-weight: normal;-fx-text-fill: white;-fx-wrap-text: true;-fx-alignment: center;-fx-content-display: center;-fx-padding: 10;-fx-max-width: 350;");
                hBox.getChildren().add(messageLbl);
                Platform.runLater(() -> {

                    vBox.getChildren().add(hBox);
                });
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnAddOnAction(ActionEvent actionEvent) {
        String user_name = txtUserName.getText();
        String password = txtPassword.getText();


        if (user_name.equals("") || password.equals("")) {
            new Alert(Alert.AlertType.WARNING, "Please enter user name and password !!!").show();

        } else {
            try {

                boolean existUser = userBO.isExistUser(user_name);
                if (!existUser) {
                    boolean isAdded = userBO.addUser(new UserDTO(user_name, password));
                    if (isAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User Added :)").show();
                        vBox.getChildren().clear();
                        loadUsers();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "User not Added :(").show();
                    }

                } else {
                    new Alert(Alert.AlertType.ERROR, "User is Already Exist :(").show();
                }


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


        }


    }

    @FXML
    public void btnSkipOnAction(ActionEvent actionEvent) {

        try {
            root.getChildren().clear();
            Stage stage = (Stage) root.getScene().getWindow();

            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_window_from.fxml"))));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
