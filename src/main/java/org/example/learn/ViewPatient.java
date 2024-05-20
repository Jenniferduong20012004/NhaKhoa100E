package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import static java.awt.Color.white;

public class ViewPatient {
    private ViewHandler viewHandler;
    private ViewPatientVM viewVM;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label labelName;

    public void init(ViewPatientVM viewVM, ViewHandler viewHandler) {
        this.viewVM = viewVM;
        this.viewHandler = viewHandler;
        labelName.textProperty().bindBidirectional(viewVM.nameProperty());
        AnchorPane anchorPane = getAnchorPane();
        scrollPane.setContent(anchorPane);
        viewVM.showPicture(anchorPane);
    }
    public AnchorPane getAnchorPane(){

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(0);
        anchorPane.setLayoutY(0);
        anchorPane.setMinWidth(800);
        anchorPane.setPrefWidth(800);
        anchorPane.setMaxWidth(800);
        anchorPane.setStyle("-fx-background-color: white;");
        anchorPane.setPrefHeight(viewVM.getAnchorHeight());
        return anchorPane;
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        viewVM.clear();
        viewHandler.openAddPatient();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        viewVM.clear();
        viewHandler.openPatient();
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewVM.clear();
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        viewVM.clear();
        viewHandler.openTreatmentlog();
    }
}
