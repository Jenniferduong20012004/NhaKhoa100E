package org.example.learn;

import ViewModel.addNewLaboratoryVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addNewLaboratory {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField textField;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField1;
    @FXML
    private Button saveButton;
    private ViewHandler viewHandler;
    private addNewLaboratoryVM addnewlaboratoryvm;
    @FXML
    private void saveLaboratory (ActionEvent event){
        String name = textField.getText().trim().toLowerCase();
        String contactNumber = textField1.getText().trim().toLowerCase();
        String address= textField2.getText();
        addnewlaboratoryvm.saveLaboratory(name, contactNumber, address);
        resetText();
    }

    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToAddTreatmentLog(ActionEvent event) {
        viewHandler.openAddTreatment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        viewHandler.openPatient();
    }
    @FXML
    private void switchToAddPatient(ActionEvent event) {
        viewHandler.openAddPatient();
    }
    @FXML
    private void Cancel (ActionEvent event){resetText();
    }
    public void resetText() {
        textField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
    }

    public void init(addNewLaboratoryVM addNewLaboratoryVM, ViewHandler viewHandler) {
        this.addnewlaboratoryvm = addNewLaboratoryVM;
        this.viewHandler=viewHandler;
    }
}
