package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.tm.UserTM;

import java.io.IOException;
import java.util.ArrayList;

public class CordinatorController {

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    @FXML
    private TableColumn<User, String> colCoordinatorId;

    @FXML
    private TableColumn<User, String> colCordinatorName;

    @FXML
    private TableColumn<User, Void> colAction;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<UserTM> tblCordinator;

    // Data list for the TableView
    private ObservableList<User> cordinatorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the columns
        colCoordinatorId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colCordinatorName.setCellValueFactory(new PropertyValueFactory<>("userName"));

        // Add the delete button column
        colAction.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    try {
                        String userId = null;
                        UserTM selectedCordinator = tblCordinator.getSelectionModel().getSelectedItem();
                        if (selectedCordinator != null) {
                            // Get the userId value
                            userId = selectedCordinator.getUserId();


                            boolean isDeleted = userBO.deleteCordinator(userId);
                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Cordinator deleted Successfully").show();
                                tblCordinator.getItems().remove(selectedCordinator);


                            } else {
                                new Alert(Alert.AlertType.ERROR, "Cordinator Does not Deleted").show();
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });
        loadAllCordinators();



    }
    public void loadAllCordinators(){

        tblCordinator.getItems().clear();

        ObservableList<UserTM> obList = FXCollections.observableArrayList();

        ArrayList<UserDTO> loadAllCordinat= null;
        try {
            loadAllCordinat = userBO.loadAllCordinators();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(UserDTO c : loadAllCordinat){
            obList.add(new UserTM(c.getUserId(),c.getUserName()));
            tblCordinator.setItems(obList);
        }
    }


}

