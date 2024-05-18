package org.example.learn;

import ViewModel.addTreatmentlogVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class AddTreatmentLog {
    @FXML
    private Label DescriptionLabel;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label amountlabel;

    @FXML
    private Label criterialabel;

    @FXML
    private Label labolabel;

    @FXML
    private RadioButton radioButton;
    @FXML
    private Label LaboratoryLabelCheck;

    @FXML
    private Button saveButton;

    @FXML
    private Button checkButton;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5, textField6;
    private ViewHandler viewHandler;
    private addTreatmentlogVM addTreatmentlogVM;
    @FXML
    private void CheckData(ActionEvent event) {
            addTreatmentlogVM.checkData();
            if (addTreatmentlogVM.isVisibleButton()) {
                LaboratoryLabelCheck.setVisible(true);
                DescriptionLabel.setVisible(true);
                textArea.setVisible(true);
                radioButton.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Patient first come to clinic insert in New Patient first");
            }

    }
    @FXML
    private void clickYes(ActionEvent event){
        if (!addTreatmentlogVM.isClicked()){
            addTreatmentlogVM.setClicked(true);
            textField3.setVisible(true);
            textField4.setVisible(true);
            textField5.setVisible(true);
            amountlabel.setVisible(true);
            criterialabel.setVisible(true);
            labolabel.setVisible(true);
        }
        else{
            addTreatmentlogVM.setClicked(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);
            amountlabel.setVisible(false);
            criterialabel.setVisible(false);
            labolabel.setVisible(false);
        }
    }

    @FXML
    private void collectInformation (ActionEvent event) throws SQLException {
        if (addTreatmentlogVM.isCheckedData()){
            addTreatmentlogVM.getInformation();
            if (addTreatmentlogVM.isClicked()){
                addTreatmentlogVM.addLaboratory();
            }
            addTreatmentlogVM.clear();
            resetPage();
        }
    }

    private void resetPage() {
        addTreatmentlogVM.setClicked(false);
        addTreatmentlogVM.setVisibleButton(false);

    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openPatient();
    }
    @FXML
    private void switchToAddPatient(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openAddPatient();
    }
    @FXML
    private void switchToAddLaboratory(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openAddEquipment();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
    }
    @FXML
    private void switchToNewImage(ActionEvent event) {
        addTreatmentlogVM.clear();
        resetPage();
        viewHandler.openAddImage();
    }

    public void init(addTreatmentlogVM addTreatmentlogVM, ViewHandler viewHandler) {
        this.addTreatmentlogVM = addTreatmentlogVM;
        this.viewHandler = viewHandler;
        textField1.textProperty().bindBidirectional(addTreatmentlogVM.nameProperty());
        textField6.textProperty().bindBidirectional(addTreatmentlogVM.dateOfBirthProperty());
        checkButton.disableProperty().bind(addTreatmentlogVM.checkButtonDisabledProperty());
        textArea.textProperty().bindBidirectional(addTreatmentlogVM.descriptionProperty());
        saveButton.disableProperty().bind(addTreatmentlogVM.saveButtonDisabledProperty());
        textField4.textProperty().bindBidirectional(addTreatmentlogVM.criteriaProperty());
        textField3.textProperty().bindBidirectional(addTreatmentlogVM.laboNameProperty());
        textField5.textProperty().bindBidirectional(addTreatmentlogVM.quantityProperty());
    }
}