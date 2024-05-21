package org.example.learn;

import Entity.Patient;
import ViewModel.ViewPatientDatecomeVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewPatientDateCome {
    @FXML
    private TableView<Patient> laboTable;
    @FXML
    private TableColumn<Patient, String> DateLaboColumn;

    @FXML
    private TableColumn<Patient, String> QuantityColumn;
    private ViewPatientDatecomeVM viewPatientDatecomeVM;
    private ViewHandler viewHandler;
    public void init(ViewPatientDatecomeVM viewPatientDatecomeVM, ViewHandler viewHandler) {
        this.viewPatientDatecomeVM = viewPatientDatecomeVM;
        this.viewHandler = viewHandler;
        viewPatientDatecomeVM.loadFromDatabase();
        laboTable.setItems(viewPatientDatecomeVM.getList());
        setCellTable();
    }

    private void setCellTable() {
    }

    public void switchToDashboard(ActionEvent event) {
        viewHandler.openAddPatient();
    }

    public void switchToPatient(ActionEvent event) {
        viewHandler.openPatient();
    }

    public void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }

    public void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }

    public void switchToImage(ActionEvent event) {
        viewHandler.openViewPatient();
    }
}
