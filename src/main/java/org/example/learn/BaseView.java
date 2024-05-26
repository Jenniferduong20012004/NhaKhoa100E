package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BaseView {
    @FXML
    private AnchorPane focus;
    private ViewHandler viewHandler;
    @FXML
    private Button buttondatecome;

    @FXML
    private Button buttonimage;

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        focus.getChildren().setAll(viewHandler.openViewPatient());
        buttonimage.setStyle("-fx-background-color: #4C7D77;");
        buttondatecome.setStyle("-fx-background-color: #489E98;");
    }
    @FXML
    private void switchToImage(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openViewPatient());
        buttonimage.setStyle("-fx-background-color: #4C7D77;");
        buttondatecome.setStyle("-fx-background-color: #489E98;");
    }
    @FXML
    private void switchToDateCome(ActionEvent event) {
        focus.getChildren().setAll(viewHandler.openViewPatientDateCome());
        buttondatecome.setStyle("-fx-background-color: #4C7D77;");
        buttonimage.setStyle("-fx-background-color: #489E98;");
    }
}
