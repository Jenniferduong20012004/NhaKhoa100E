package org.example.learn;

import ViewModel.ViewPatientVM;
import ViewModel.addNewImageVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ViewPatient {
    private ViewHandler viewHandler;
    private ViewPatientVM viewVM;

    public void init(ViewPatientVM viewVM, ViewHandler viewHandler) {
        this.viewVM = viewVM;
        this.viewHandler = viewHandler;
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        viewHandler.openAddPatient();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        viewHandler.openPatient();
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }
}
