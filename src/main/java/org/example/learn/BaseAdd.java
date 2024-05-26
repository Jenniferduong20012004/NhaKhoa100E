package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BaseAdd {
    private ViewHandler viewHandler;
    public void init (ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        anchorAdd.getChildren().setAll(viewHandler.openAddPatient());
    }
    @FXML
    private AnchorPane anchorAdd;
    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
        anchorAdd.getChildren().setAll(viewHandler.openAddEquipment());
    }

    public void switchToAddNewPatient(ActionEvent event) {
        anchorAdd.getChildren().setAll(viewHandler.openAddPatient());
    }
}
