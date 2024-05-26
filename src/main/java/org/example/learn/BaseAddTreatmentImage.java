package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;

public class BaseAddTreatmentImage {
    private ViewHandler viewHandler;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button newImagebutton;

    @FXML
    private Button newTLbutton;
    @FXML
    void switchToTreatment(ActionEvent event) {
        mainPane.getChildren().setAll(viewHandler.openAddTreatment());
        newTLbutton.setStyle("-fx-background-color:#4C7D77 ");
        newImagebutton.setStyle("-fx-background-color: #489E98;");
    }

    @FXML
    private void switchToNewImage(ActionEvent event) {
        mainPane.getChildren().setAll(viewHandler.openAddImage());
        newImagebutton.setStyle("-fx-background-color:#4C7D77 ");
        newTLbutton.setStyle("-fx-background-color: #489E98;");
    }

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        mainPane.getChildren().setAll(viewHandler.openAddTreatment());
        newTLbutton.setStyle("-fx-background-color:#4C7D77 ");
        newImagebutton.setStyle("-fx-background-color: #489E98;");
    }
}
