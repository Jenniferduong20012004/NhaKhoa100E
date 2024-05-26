package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BaseAddTreatmentImage {
    private ViewHandler viewHandler;

    @FXML
    private AnchorPane mainPane;
    @FXML
    void switchToTreatment(ActionEvent event) {
        mainPane.getChildren().setAll(viewHandler.openAddTreatment());
    }

    @FXML
    private void switchToNewImage(ActionEvent event) {
        mainPane.getChildren().setAll(viewHandler.openAddImage());
    }

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        mainPane.getChildren().setAll(viewHandler.openAddTreatment());
    }
}
