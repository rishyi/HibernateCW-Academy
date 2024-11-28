


package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.dto.CourseDTO;
import lk.ijse.dto.EnrollmentDTO;
import lk.ijse.entity.Enrollment;
import lk.ijse.tm.EnrollmentTM;
import lk.ijse.tm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EnrollmentController {
    private ObservableList<EnrollmentTM> obList = FXCollections.observableArrayList();



    EnrollmentBO enrollmentBO = (EnrollmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENROLLMENT);
    CourseBO courseBO= (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() throws SQLException, IOException, ClassNotFoundException {

            getCurrentRegisterId();
        getCourseIds();
        setCellValueFactory();
//        configureMultiSelectComboBox();


    }

    private void setCellValueFactory() {

        colRegId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseIds.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("courseFEE"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

    }

    private void getCurrentRegisterId() throws SQLException, ClassNotFoundException, IOException {
        String currentId = enrollmentBO.getCurrentId();

        String nextRegisterId = generateNextRegisterId(currentId);
        lblRegesterId.setText(nextRegisterId);
    }


    private String generateNextRegisterId(String currentId) {

        enrollmentBO.generateNewRegisterId();
        return currentId;
    }
    @FXML
    private ComboBox<String> cmbCourses;
    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCourseIds;

    @FXML
    private TableColumn<?, ?> colDownPayment;

    @FXML
    private TableColumn<?, ?> colRegId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTotPayment;
    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private DatePicker dateRegister;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblRegesterId;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblTotalPayment;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<EnrollmentTM> tblRegistration;

    @FXML
    private TextField txtDownpayment;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblCourseFee;

    @FXML
    private Label lblCourseName;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {



    }

    private void clearFeilds() {
//        lblRegesterId.setText("");
        dateRegister.setValue(null);
        txtStudentId.setText("");
        lblStudentName.setText("");
        cmbCourses.getItems().clear();
        lblCourseName.setText("");
        lblTotalPayment.setText("");
        lblBalance.setText("");
        txtDownpayment.setText("");
        lblCourseFee.setText("");
       lblRegesterId.setText(generateNextRegisterId(lblRegesterId.getText()));


//        tblRegistration.refresh();



    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentCalOnAction(ActionEvent event) {
        double totalPay= Double.parseDouble(lblTotalPayment.getText());
        double downPayment = Double.parseDouble(txtDownpayment.getText());
        double balance = totalPay- downPayment;
        lblBalance.setText(String.valueOf(balance));

    }

    @FXML
    void txtStudentDetailOnAction(ActionEvent event) {
       String studentId= txtStudentId.getText();
        String studentName = null;
        try {
            studentName = studentBO.getStudentName(studentId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(studentName!=null){
            lblStudentName.setText(studentName);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Student Id not found").show();
        }

    }

    @FXML
    void cmbCourseIdsOnAction(ActionEvent event) {
     String courseId = cmbCourses.getValue();

        try {
            CourseDTO courseDTO = courseBO.searchCourse(courseId);
            lblCourseName.setText(courseDTO.getProgramName());
            lblCourseFee.setText(String.valueOf(courseDTO.getFee()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void getCourseIds() {
        ObservableList<String> courseIds = FXCollections.observableArrayList();

        ArrayList<String> codeList = null;
        try {
            codeList = courseBO.getCourseIds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String code : codeList) {
            courseIds.add(code);
        }
        cmbCourses.setItems(courseIds);

    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {

        String registrationId = lblRegesterId.getText();
        String registrationDate = String.valueOf(dateRegister.getValue());
        String studentId = txtStudentId.getText();
        String studentName = lblStudentName.getText();
        String courseId = cmbCourses.getValue();
        String courseName = lblCourseName.getText();
        double courseFee= Double.parseDouble(lblCourseFee.getText());


        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblRegistration.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblRegistration.refresh();
                calculateNetTotal();
//                calculateNetTotal();
                return;
            }
        });

//        for (int i = 0; i < tblRegistration.getItems().size(); i++) {
//            if(registrationId.equals(colRegId.getCellData(i))) {
//
//
//            }
       // }

        EnrollmentTM tm = new EnrollmentTM(registrationId, studentId,studentName,courseId,courseName,courseFee,registrationDate, btnRemove);
        obList.add(tm);

        tblRegistration.setItems(obList);
        calculateNetTotal();



    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String registerId = lblRegesterId.getText();
        String registerDate= String.valueOf(dateRegister.getValue());
        double downPayment = Double.parseDouble(txtDownpayment.getText());
        double balance= Double.parseDouble(lblBalance.getText());
        String studentId = txtStudentId.getText();
        String courseId=cmbCourses.getValue();
        try {
            boolean isRegistered = enrollmentBO.registerStudent(new EnrollmentDTO(registerId,registerDate,downPayment,balance,studentId,courseId));
            if(isRegistered){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Registered").show();
                clearFeilds();
                generateNextRegisterId(lblRegesterId.getText());
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Registration declined. Try Again").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    private double calculateNetTotal() {
        double SubTotal = 0;
        for (int i = 0; i < tblRegistration.getItems().size(); i++) {
            SubTotal += (double) colFee.getCellData(i);
        }
        lblTotalPayment.setText(String.valueOf(SubTotal));
        //btnMoneyReceiving();
        System.out.println("hiiiiiiiii");

        return SubTotal;
    }
}

