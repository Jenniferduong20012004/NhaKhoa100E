package org.example.learn;

import ViewModel.BaseVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openBaseAdd());
        labelCategory.setText("Dashboard");
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openPatient());
        labelCategory.setText("Patient");
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openTreatmentlog());
        labelCategory.setText("Treatment Logs");
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openEquipment());
        labelCategory.setText("Laboratory");
    }
}
