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
    private AnchorPane anchorPane;

    public void init(ViewPatientVM viewVM, ViewHandler viewHandler) {
        this.viewVM = viewVM;
        this.viewHandler = viewHandler;
        labelName.textProperty().bindBidirectional(viewVM.nameProperty());
        scrollPane.setStyle("-fx-background-color: white;");
        viewVM.loadDatabase();
        anchorPane = getAnchorPane();
        scrollPane.setContent(anchorPane);
        showPicture();
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
    private void showPicture() {
        int width = 300;
        int height = 250;
        int xIndex = 0;
        int yIndex = 0;
        for (AnchorPane pane : viewVM.getProductPaneList()) {
            pane.setLayoutX(xIndex * width);
            pane.setLayoutY(yIndex * height);
            pane.setPrefWidth(width);
            pane.setPrefHeight(height);

            anchorPane.getChildren().add(pane);
            xIndex++;
            if (xIndex % 2== 0) {
                xIndex = 0;
                yIndex++;
            }
        }
    }
    @FXML
    private void switchToDateCome(ActionEvent event) {
        viewHandler.openViewPatientDateCome();
    }
}
