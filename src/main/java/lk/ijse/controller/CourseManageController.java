package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.scene.input.KeyEvent;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.tm.CourseTM;
import lk.ijse.util.Regex;

import java.io.IOException;
import java.util.ArrayList;

public class CourseManageController {

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() {
        setCellValueFactory();
        loadAllCourses();
    }

    private void loadAllCourses() {
        tblProgram.getItems().clear();
        ObservableList<CourseTM> obList = FXCollections.observableArrayList();

        try {
            ArrayList<CourseDTO>loadAllCourses= courseBO.getAllCourses();
            for(CourseDTO c : loadAllCourses){
                obList.add(new CourseTM(c.getProgramId(),c.getProgramName(),c.getDuration(),c.getFee()));
                tblProgram.setItems(obList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }


    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colProgramFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CourseTM> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtProgramId;

    @FXML
    private TextField txtProgramName;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

       String id = txtProgramId.getText();

        try {
            if(isValid()){
                boolean isDelete = courseBO.deleteCourse(id);
                if(isDelete){
                    new Alert(Alert.AlertType.INFORMATION,"Program deleted successfully").show();
                    loadAllCourses();
                    clearFeilds();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"program does not deleted").show();
                }
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Please fill all Fields correctly").show();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtProgramId.getText();
        String programName=txtProgramName.getText();
        String duration=txtDuration.getText();
        Double fee = Double.valueOf(txtProgramFee.getText());

        boolean isSaved = false;
        try {
            if (isValid()) {
                isSaved = courseBO.saveCourse(new CourseDTO(id,programName,duration,fee));
                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Program Added Succesfully").show();
                    loadAllCourses();
                    clearFeilds();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Program Does not Added").show();
                }
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Please fill fields Correctly").show();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = txtProgramId.getText();
        String name = txtProgramName.getText();
        String duration=txtDuration.getText();
        Double fee = Double.valueOf(txtProgramFee.getText());

        try {
            if(isValid()){
                boolean isUpdate =courseBO.updateCourse(new CourseDTO(id,name,duration,fee));
                if(isUpdate){
                    new Alert(Alert.AlertType.INFORMATION,"Course Details Updated Successfully").show();
                    loadAllCourses();
                    clearFeilds();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Course details doesnot Updated").show();
                }
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Please fill fields Correctly").show();

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

      String id = txtProgramId.getText();

        try {
            CourseDTO courseDTO = courseBO.searchCourse(id);
            if(courseDTO != null){
//                txtProgramId.setText(courseDTO.getProgramId());
                txtProgramName.setText(courseDTO.getProgramName());
                txtDuration.setText(courseDTO.getDuration());
                txtProgramFee.setText(String.valueOf(courseDTO.getFee()));

            }
            else {
                new Alert(Alert.AlertType.ERROR,"Course id does not found").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Course ID Not Found!").show();

        }


    }

    public void clearFeilds(){
        txtProgramId.setText("");
        txtProgramName.setText("");
        txtDuration.setText("");
        txtProgramFee.setText("");
    }
    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.util.TextField.PROGRAMID, txtProgramId)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.NAME, txtProgramName)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.DURATION, txtDuration)) return false;
        if (!Regex.setTextColor(lk.ijse.util.TextField.FEE, txtProgramFee)) return false;
        return true;
    }


    @FXML
    void txtDurationOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.DURATION, txtDuration);

    }

    @FXML
    void txtFeeKeyAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.FEE, txtProgramFee);

    }

    @FXML
    void txtIdKeyAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.PROGRAMID, txtProgramId);

    }

    @FXML
    void txtNameKeyAction(KeyEvent event) {
        Regex.setTextColor(lk.ijse.util.TextField.NAME, txtProgramName);


    }

}


