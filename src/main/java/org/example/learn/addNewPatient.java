package org.example.learn;

import ViewModel.addNewPatientVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;

public class addNewPatient{
    @FXML
    private Button saveButton;
    @FXML
    TextField textField, textField1, textField2,textField3;
    private addNewPatientVM addNewpatientvm;
    private ViewHandler viewHandler;
    @FXML
    private void getInformation (ActionEvent event) throws SQLException {
        addNewpatientvm.saveInformation();
    }

    @FXML
    private void switchToEquipment(ActionEvent event) {
        addNewpatientvm.clear();
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        addNewpatientvm.clear();
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        addNewpatientvm.clear();
        viewHandler.openPatient();
    }
    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
        addNewpatientvm.clear();
        viewHandler.openAddEquipment();
    }
    @FXML
    private void Cancel(ActionEvent event){
        addNewpatientvm.clear();
    }

    public void init(addNewPatientVM addNewPatientVM, ViewHandler viewHandler) {
        this.addNewpatientvm = addNewPatientVM;
        this.viewHandler = viewHandler;
        textField.textProperty().bindBidirectional(addNewPatientVM.nameProperty());
        textField1.textProperty().bindBidirectional(addNewPatientVM.contactNumberProperty());
        textField2.textProperty().bindBidirectional(addNewPatientVM.addressProperty());
        textField3.textProperty().bindBidirectional(addNewPatientVM.dateOfBirthProperty());
        saveButton.disableProperty().bind(addNewPatientVM.saveButtonDisabledProperty());
        addNewPatientVM.saveResponseProperty().addListener((observableValue, oldValue, newValue) -> onSavingResult(newValue));
    }

    private void onSavingResult(String newValue) {
        if("OK".equals(newValue)) {
            JOptionPane.showMessageDialog(null, "Save data successfully");
            System.out.println("okok");
        }
    }
}
