package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.BO.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;

import java.io.IOException;

public class RegesterFormController {

    UserBO userBO =  (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserRole1;

    @FXML
    void btnRegesterOnAction(ActionEvent event) {
        String id= txtUserId.getText();
        String password =txtPassword.getText();
        String userName = txtUserName.getText();
        String userRole = txtUserRole1.getText();

        try {
            boolean isRegistered = userBO.saveUser(new UserDTO(id,password,userName,userRole));
            if(isRegistered){
                new Alert(Alert.AlertType.INFORMATION,"REGISTERED SUCCESSFULLY").show();
                clearFeilds();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"PLEASE TRY AGAIN").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFeilds(){
        txtUserId.setText("");
        txtPassword.setText("");
        txtUserName.setText("");
        txtUserRole1.setText("");
    }

}

