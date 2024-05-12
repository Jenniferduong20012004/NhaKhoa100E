package org.example.learn;

import ViewModel.addNewPatientVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class addNewPatient{
    private Stage stage;
    @FXML
    TextField textField, textField1, textField2,textField3;
    private Scene scene;

    private addNewPatientVM addNewpatientvm;

    public addNewPatientVM getAddNewpatientvm() {
        return addNewpatientvm;
    }
    private ViewHandler viewHandler;
    @FXML
    private void getInformation (ActionEvent event) throws SQLException {
        String name = textField.getText().trim().toLowerCase();
        String contactNumber = textField1.getText().trim().toLowerCase();
        String address= textField2.getText().trim().toLowerCase();
        String dob = textField3.getText().trim().toLowerCase();
        addNewpatientvm.saveInformation(name, contactNumber, address, dob);
        resetText();
    }

    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
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
    private void switchToAddNewTreatmentLog(ActionEvent event) {
        viewHandler.openAddTreatment();
    }
    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
        viewHandler.openAddEquipment();
    }
    @FXML
    private void Cancel(ActionEvent event){
        resetText();
    }

    public void setTextField(String patientName, String contactNumber, String address, String dob){
        textField.setText(patientName);
        textField1.setText(contactNumber);
        textField2.setText(address);
        textField3.setText(dob);
    }
    public void resetText() {
        textField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
        textField3.setText(null);
    }

    public void init(addNewPatientVM addNewPatientVM, ViewHandler viewHandler) {
        this.addNewpatientvm = addNewPatientVM;
        this.viewHandler = viewHandler;
    }
}
