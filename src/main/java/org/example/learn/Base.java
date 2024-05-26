package org.example.learn;

import ViewModel.BaseVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Base {
    @FXML
    private Label labelCategory;
    @FXML
    private AnchorPane sideMenu;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private HBox mainBox;
    @FXML
    private AnchorPane focus;
    @FXML
    private Button dashboardbutton;
    @FXML
    private Button labbutton;
    @FXML
    private Button patientbutton;
    @FXML
    private Button treatmentlogbutton;

    private boolean isSidebarVisible = true;
    private BaseVM base;
    private ViewHandler viewHandler;
    @FXML
    private void toggleDashboard(){;
        if (isSidebarVisible) {
            mainBox.getChildren().remove(sideMenu);

        } else {
            mainBox.getChildren().add(0, sideMenu);
        }
        isSidebarVisible = !isSidebarVisible;
    }

    public void init(BaseVM base, ViewHandler viewHandler) {
        HBox.setHgrow(mainPain, Priority.ALWAYS);
        this.base = base;
        this.viewHandler = viewHandler;
        focus.getChildren().setAll(viewHandler.openPatient());
        dashboardbutton.setStyle("-fx-background-color: #C9E7EE; ");
        labbutton.setStyle("-fx-background-color: #C9E7EE; ");
        patientbutton.setStyle("-fx-background-color: white; ");
        treatmentlogbutton.setStyle("-fx-background-color: #C9E7EE; ");
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openBaseAdd());
        labelCategory.setText("Dashboard");
        dashboardbutton.setStyle("-fx-background-color: white; ");
        labbutton.setStyle("-fx-background-color: #C9E7EE; ");
        patientbutton.setStyle("-fx-background-color: #C9E7EE; ");
        treatmentlogbutton.setStyle("-fx-background-color: #C9E7EE; ");

    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openPatient());
        labelCategory.setText("Patient");
        dashboardbutton.setStyle("-fx-background-color: #C9E7EE; ");
        labbutton.setStyle("-fx-background-color: #C9E7EE; ");
        patientbutton.setStyle("-fx-background-color: white; ");
        treatmentlogbutton.setStyle("-fx-background-color: #C9E7EE; ");
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openTreatmentlog());
        labelCategory.setText("Treatment Logs");
        dashboardbutton.setStyle("-fx-background-color: #C9E7EE; ");
        labbutton.setStyle("-fx-background-color: #C9E7EE; ");
        patientbutton.setStyle("-fx-background-color: #C9E7EE; ");
        treatmentlogbutton.setStyle("-fx-background-color: white; ");
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openEquipment());
        labelCategory.setText("Laboratory");
        dashboardbutton.setStyle("-fx-background-color: #C9E7EE; ");
        labbutton.setStyle("-fx-background-color: white; ");
        patientbutton.setStyle("-fx-background-color: #C9E7EE; ");
        treatmentlogbutton.setStyle("-fx-background-color: #C9E7EE; ");
    }
}
