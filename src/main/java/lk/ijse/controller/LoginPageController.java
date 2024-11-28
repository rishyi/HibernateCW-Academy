package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.util.PasswordUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    UserBO userBO =  (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserId;

    public static UserDTO userDTO;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String password = txtPassword.getText();

        String dbPassword =  getUserPassword();

        boolean isPasswordCorrect = BCrypt.checkpw(password,dbPassword);
        if (isPasswordCorrect) {
            openMainForm();
        } else {
            new Alert(Alert.AlertType.ERROR,"Invalid Password.Try Again").show();
        }





        }


    private void openMainForm(){
        try {
            Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/adminDashboard.fxml")));
            Stage stage = (Stage) rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserPassword(){
        String username = txtUserId.getText();
        String password = null;


        try {
            password = userBO.getUser(username);
            return password;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



    @FXML
    void btnRegesterOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtUserNameOnKeyReleased(KeyEvent event) {

    }

}
