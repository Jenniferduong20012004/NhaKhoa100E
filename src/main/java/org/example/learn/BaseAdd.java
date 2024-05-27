package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class BaseAdd {
    private ViewHandler viewHandler;
    @FXML
    private Button buttonpatient;

    @FXML
    private Button butttonlab;
    public void init (ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        anchorAdd.getChildren().setAll(viewHandler.openAddPatient());
        buttonpatient.setStyle("-fx-background-color: #4C7D77;");
        butttonlab.setStyle("-fx-background-color: #489E98;");
    }
    @FXML
    private AnchorPane anchorAdd;
    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
        anchorAdd.getChildren().setAll(viewHandler.openAddEquipment());
        butttonlab.setStyle("-fx-background-color: #4C7D77;");
        buttonpatient.setStyle("-fx-background-color: #489E98;");
    }

    public void switchToAddNewPatient(ActionEvent event) {
        anchorAdd.getChildren().setAll(viewHandler.openAddPatient());
        buttonpatient.setStyle("-fx-background-color: #4C7D77;");
        butttonlab.setStyle("-fx-background-color: #489E98;");
    }
}
