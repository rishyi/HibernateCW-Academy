package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class UserSettingsController {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordUserId;

    @FXML
    private PasswordField txtRpeatePassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserRole;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtUserId.getText();
//        System.out.println("Searching for user with ID: " + id);


        try {
            UserDTO userDTO = userBO.searchById(id);
//            System.out.println("Searching for user with ID: " + id);


            if (userDTO != null) {
                System.out.println(userDTO.getUserId());

                txtUserId.setText(userDTO.getUserId());
                txtUserName.setText(userDTO.getUserName());
                txtUserRole.setText(userDTO.getUserRole());

            } else {
                new Alert(Alert.AlertType.ERROR, "can not find the User").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtPassword.setText("");
        txtNewPassword.setText("");
        txtRpeatePassword.setText("");
        txtPasswordUserId.setText("");
    }


    @FXML
    void txtOldPasswordOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        String dbPassword = getUserPassword();

        boolean isPasswordCorrect = BCrypt.checkpw(password,dbPassword);
        if (isPasswordCorrect) {

            txtNewPassword.requestFocus();
        } else {
           new Alert(Alert.AlertType.ERROR,"Password does not match").show();
        }

    }

    @FXML
    void txtPasswordSearchIDOnAction(ActionEvent event) {

    }

    @FXML
    void txtRepeatPasswordOnAction(ActionEvent event) {

    }


    @FXML
    void btnSavePasswordOnAction(ActionEvent event) {
     String userId= txtPasswordUserId.getText();
     String newPassword = txtNewPassword.getText();
     String repeatPassword = txtRpeatePassword.getText();
//        System.out.println(userId+ "connnnnnnnnn");
     if(newPassword.equals(repeatPassword)){
         try {
             boolean isChange= userBO.changePassword(newPassword,userId);
             if(isChange){
                 new Alert(Alert.AlertType.INFORMATION,"Password Changed Successfully").show();
                 clearFields();
             }
             else {
                 new Alert(Alert.AlertType.ERROR,"Password does not changed.Try again").show();
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }


    }

    public String getUserPassword() {
        String userId = txtPasswordUserId.getText();
        String password = null;


        try {
            password = userBO.getUser(userId);
            return password;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}



