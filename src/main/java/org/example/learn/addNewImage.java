package org.example.learn;

import ViewModel.addNewImageVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class addNewImage {
    @FXML
    private TextField textField;

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    private ViewHandler viewHandler;
    private addNewImageVM addNewImageVM;
    public void init(addNewImageVM addNewImageVM, ViewHandler viewHandler) {
        this.addNewImageVM = addNewImageVM;
        this.viewHandler=viewHandler;
        textField.textProperty().bindBidirectional(addNewImageVM.nameProperty());
        textField1.textProperty().bindBidirectional(addNewImageVM.idProperty());
        textField2.textProperty().bindBidirectional(addNewImageVM.image1Property());
    }
    @FXML
    private void Cancel(ActionEvent event) {
        addNewImageVM.clear();
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        addNewImageVM.chooseFile();
    }

    @FXML
    private void getInformation(ActionEvent event) {
        addNewImageVM.getInformation();
    }

    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
        addNewImageVM.clear();
        viewHandler.openAddEquipment();
    }

    @FXML
    private void switchToAddNewTreatmentLog(ActionEvent event) {
        viewHandler.openAddTreatment();
    }

    @FXML
    private void switchToEquipment(ActionEvent event) {
        addNewImageVM.clear();
        viewHandler.openEquipment();
    }

    @FXML
    private void switchToPatient(ActionEvent event) {
        addNewImageVM.clear();
        viewHandler.openPatient();
    }

    @FXML
    private void switchToTreatment(ActionEvent event) {
        addNewImageVM.clear();
        viewHandler.openTreatmentlog();
    }
}
