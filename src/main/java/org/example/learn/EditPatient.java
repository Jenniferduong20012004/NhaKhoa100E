package org.example.learn;

import ViewModel.EditPatientVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPatient {
    @FXML
    private Button saveButton;

    @FXML
    private Button saveButton1;

    private ViewHandler viewHandler;
    private EditPatientVM editPatientVM;
    @FXML
    private TextField textField;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private void getInformation(ActionEvent event) {
        editPatientVM.edit();
        // Get the current stage
        Stage currentStage = (Stage) textField.getScene().getWindow();
        currentStage.close();
    }
    @FXML
    private void Cancel(ActionEvent event) {
        editPatientVM.clear();
    }

    public void init(EditPatientVM editPVM, ViewHandler viewHandler) {
        this.editPatientVM = editPVM;
        this.viewHandler = viewHandler;
        textField.textProperty().bindBidirectional(editPatientVM.nameProperty());
        textField1.textProperty().bindBidirectional(editPatientVM.contactNumberProperty());
        textField2.textProperty().bindBidirectional(editPatientVM.addressProperty());
        saveButton.disableProperty().bind(editPatientVM.saveButtonDisabledProperty());
    }
}
