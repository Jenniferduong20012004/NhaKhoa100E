package org.example.learn;

import ViewModel.addNewImageVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addNewImage {
    @FXML
    private TextField textField;

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private TextField image3;

    private ViewHandler viewHandler;
    private addNewImageVM addNewImageVM;
    @FXML
    private Button chooseFile2;
    @FXML
    private Button chooseFile3;
    @FXML
    private Button chooseFile4;

    @FXML
    private Button chooseFile5;

    @FXML
    private Button chooseFile6;

    @FXML
    private Button chooseFile7;
    @FXML
    private TextField image4;

    @FXML
    private TextField image5;

    @FXML
    private TextField image6;

    @FXML
    private TextField image7;

    public void init(addNewImageVM addNewImageVM, ViewHandler viewHandler) {
        this.addNewImageVM = addNewImageVM;
        this.viewHandler=viewHandler;
        textField.textProperty().bindBidirectional(addNewImageVM.nameProperty());
        textField1.textProperty().bindBidirectional(addNewImageVM.image1Property());
        textField2.textProperty().bindBidirectional(addNewImageVM.image2Property());
        image3.textProperty().bindBidirectional(addNewImageVM.image3Property());
        image4.textProperty().bindBidirectional(addNewImageVM.image4Property());
        image5.textProperty().bindBidirectional(addNewImageVM.image5Property());
        image6.textProperty().bindBidirectional(addNewImageVM.image6Property());
        image7.textProperty().bindBidirectional(addNewImageVM.image7Property());
        chooseFile2.disableProperty().bind(addNewImageVM.chooseFile2Property());
        chooseFile3.disableProperty().bind(addNewImageVM.chooseFile3Property());
        chooseFile4.disableProperty().bind(addNewImageVM.chooseFile4Property());
        chooseFile5.disableProperty().bind(addNewImageVM.chooseFile5Property());
        chooseFile6.disableProperty().bind(addNewImageVM.chooseFile6Property());
        chooseFile7.disableProperty().bind(addNewImageVM.chooseFile7Property());
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
