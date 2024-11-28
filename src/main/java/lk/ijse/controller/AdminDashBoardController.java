package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashBoardController {

        @FXML
        private AnchorPane childRootNode;

        @FXML
        private Label lblTotCourses;

        @FXML
        private Label lblTotEmployees;

        @FXML
        private Label lblTotRevenue;

        @FXML
        private Label lblTotStudent;

        @FXML
        private AnchorPane rootNode;

        @FXML
        void btnCordinatorDetailOnAction(ActionEvent event) throws IOException {
                navigateToCordinatorPage();

        }

        private void navigateToCordinatorPage() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cordinator.fxml"));
                Parent PerenetRootNode = null;

                PerenetRootNode = loader.load();


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

        @FXML
        void btnCourseManageOnAction(ActionEvent event) throws IOException {
                navigagetToCoursePage();

        }

        private void navigagetToCoursePage() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Courses.fxml"));
                Parent PerenetRootNode = null;

                PerenetRootNode = loader.load();


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {

                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminDashBoard.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.rootNode.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("DashBoard Form");

        }

        @FXML
        void btnEnrollmentOnAction(ActionEvent event) {
           navigateToEnrollmentPage();

        }

        private void navigateToEnrollmentPage() {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/enrollment.fxml"));
                Parent PerenetRootNode = null;

                try {
                        PerenetRootNode = loader.load();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

        @FXML
        void btnLogOutOnAction(ActionEvent event) throws IOException {
               navigateToLoginPage();
        }

        private void navigateToLoginPage() throws IOException {

                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));

                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.rootNode.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Login Form");
        }

        @FXML
        void btnStudentManageOnAction(ActionEvent event) throws IOException {
            navigateToStudentManageForm();
        }

        private void navigateToStudentManageForm() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studentManage.fxml"));
                Parent PerenetRootNode = null;

                PerenetRootNode = loader.load();


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

        @FXML
        void btnUserManageOnAction(ActionEvent event) throws IOException {
                navigateToUserSettings();

        }

        private void navigateToUserSettings() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/settings.fxml"));
                Parent PerenetRootNode = null;

                PerenetRootNode = loader.load();


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

        @FXML
        void btnpaymentManageOnAction(ActionEvent event) throws IOException {
                navigateToPaymentForm();

        }

        private void navigateToPaymentForm() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/cordinator.fxml"));
                Parent PerenetRootNode = null;

                PerenetRootNode = loader.load();


                childRootNode.getChildren().clear();
                childRootNode.getChildren().add(PerenetRootNode);
        }

}