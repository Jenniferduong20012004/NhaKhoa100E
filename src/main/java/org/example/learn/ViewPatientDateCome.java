package org.example.learn;

import Entity.Patient;
import Entity.Treatment;
import ViewModel.ViewPatientDatecomeVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class ViewPatientDateCome {
    @FXML
    private Label labelName;
    @FXML
    private TableView<Treatment> laboTable;
    @FXML
    private TableColumn<Treatment, String> DateLaboColumn;

    @FXML
    private TableColumn<Treatment, String> QuantityColumn;
    private ViewPatientDatecomeVM viewPatientDatecomeVM;
    private ViewHandler viewHandler;
    public void init(ViewPatientDatecomeVM viewPatientDatecomeVM, ViewHandler viewHandler) {
        this.viewPatientDatecomeVM = viewPatientDatecomeVM;
        labelName.textProperty().bindBidirectional(viewPatientDatecomeVM.nameProperty());
        this.viewHandler = viewHandler;
        viewPatientDatecomeVM.init();
        viewPatientDatecomeVM.loadFromDatabase();
        laboTable.setItems(viewPatientDatecomeVM.getList());
        setCellTable();
    }

    private void setCellTable() {
        DateLaboColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("description"));//tên trong treatment class
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
    private void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToImage(ActionEvent event) {
        viewHandler.openViewPatient();
    }
}
