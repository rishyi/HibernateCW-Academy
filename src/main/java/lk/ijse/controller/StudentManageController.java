package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.tm.StudentTM;
import lk.ijse.util.Regex;

import java.io.IOException;
import java.util.ArrayList;

public class StudentManageController {



    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        setCellValueFactory();
        loadAllStudents();
    }


    private void loadAllStudents() {
        tblStudent.getItems().clear();
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();

        try {
            ArrayList<StudentDTO> loadAllStudents= studentBO.loadAllStudents();
            for(StudentDTO c : loadAllStudents){
                obList.add(new StudentTM(c.getStudentId(),c.getFirstname(),c.getLastname(),c.getAddress(),c.getContact()));
                tblStudent.setItems(obList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        colSecondName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        
    }

    @FXML
    private AnchorPane rootNode;

    @FXML
    private ComboBox<?> cmbCordinator;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colFirstName;

    @FXML
    private TableColumn<?, ?> colRegesterDate;

    @FXML
    private TableColumn<?, ?> colSecondName;

    @FXML
    private TableColumn<?, ?> colStudentId;



    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSecondName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
       String studentId= txtId.getText();

        try {

            if(isValid()){
                boolean isDelete = studentBO.deleteStudent(studentId);
                if(isDelete){
                    new Alert(Alert.AlertType.INFORMATION,"student deleted successfully").show();
                    loadAllStudents();
                    clearFields();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"student does not delete").show();
                }
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Please Fill all fields correctly").show();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
       String id = txtId.getText();
       String firstName = txtFirstName.getText();
       String lastNAme = txtSecondName.getText();
       String address = txtAddress.getText();
       String contact = txtContact.getText();


        boolean isSaved = false;
        try {
            if(isValid()){

                isSaved = studentBO.saveStudent(new StudentDTO(id, firstName, lastNAme, address, contact));
                if (!isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Student saved Succesfully").show();
                    loadAllStudents();

                    clearFields();

                }
                else {
                    new Alert(Alert.AlertType.ERROR,"student does not save").show();
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//            tblStudent.getItems().add(new StudentTM(id,firstName,lastNAme,address,contact));



    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws IOException {
        String id= txtId.getText();
        String fName = txtFirstName.getText();
        String lName =txtSecondName.getText();
        String address= txtAddress.getText();
        String contact = txtContact.getText();

    if(isValid()){
        boolean isUpdated= studentBO.updateStudent(new StudentDTO(id,fName,lName,address,contact));
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Student updated succesfully").show();
            loadAllStudents();
            clearFields();

        }
        else {
            new Alert(Alert.AlertType.ERROR,"Student does not update").show();
        }
           }
    else {
        new Alert(Alert.AlertType.ERROR,"please fill all fields correctly").show();

    }


    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id =txtId.getText();
        try {
            StudentDTO studentDTO=  studentBO.searchById(id);

            if(studentDTO != null){

                txtFirstName.setText(studentDTO.getFirstname());
                txtSecondName.setText(studentDTO.getLastname());
                txtAddress.setText(studentDTO.getAddress());
                txtContact.setText(studentDTO.getContact());
            }
            else {
                new Alert(Alert.AlertType.ERROR,"can not find the student").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 public void clearFields(){
    txtId.clear();
    txtFirstName.clear();
    txtSecondName.clear();
    txtAddress.clear();
    txtContact.clear();
 }


    @FXML
    void txtAddressOnKeyRealeased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.ADDRESS, txtAddress);

    }

    @FXML
    void txtConntcatOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.TEL, txtContact);

    }

    @FXML
    void txtFNameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME, txtFirstName);

    }



    @FXML
    void txtSnameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME, txtSecondName);

    }

    @FXML
    void txtStudentIdOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.STUDENTID, txtId);

    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.util.TextField.STUDENTID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME, txtFirstName)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME, txtSecondName)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.TEL, txtContact)) return false;

        return true;
    }

}