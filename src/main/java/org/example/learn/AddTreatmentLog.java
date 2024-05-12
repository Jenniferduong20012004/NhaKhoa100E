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
    private Button saveButton1;

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
    private addTreatmentlogVM addTreatmentlogVM = new addTreatmentlogVM();
    @FXML
    private void CheckData(ActionEvent event) {
            String name = textField1.getText().trim().toLowerCase();
            String datePattern = textField6.getText().trim().toLowerCase();
            addTreatmentlogVM.checkData(name, datePattern);
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
        if (addTreatmentlogVM.isClicked()==false){
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
            String Description = textArea.getText().trim().toLowerCase();
            addTreatmentlogVM.getInformation(Description);
            if (addTreatmentlogVM.isClicked()){
                String criteria = textField4.getText().trim().toLowerCase();
                String quantity = textField5.getText().trim().toLowerCase();
                String laboName = textField3.getText().trim().toLowerCase();
                addTreatmentlogVM.addLaboratory(criteria, quantity, laboName);
            }
            resetPage();
        }
    }

    private void resetPage() {
        textArea.setText(null);
        textField1.setText(null);
        textField3.setText(null);
        textField4.setText(null);
        textField4.setText(null);
        textField5.setText(null);
        textField6.setText(null);
        addTreatmentlogVM.setClicked(false);
        addTreatmentlogVM.setVisibleButton(false);

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
    private void switchToAddPatient(ActionEvent event) {
        viewHandler.openAddPatient();
    }
    @FXML
    private void switchToAddLaboratory(ActionEvent event) {
        viewHandler.openAddEquipment();
    }

    @FXML
    private void Cancel(ActionEvent event) {
        resetPage();
    }

    public void init(addTreatmentlogVM addTreatmentlogVM, ViewHandler viewHandler) {
        this.addTreatmentlogVM = addTreatmentlogVM;
        this.viewHandler = viewHandler;
    }
}