package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BaseView {
    @FXML
    private AnchorPane focus;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        focus.getChildren().setAll(viewHandler.openViewPatient());
    }
    @FXML
    private void switchToImage(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openViewPatient());
    }
    @FXML
    private void switchToDateCome(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openViewPatientDateCome());
    }
}
