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
    private TextArea textArea;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;
    @FXML
    private TextField textField5;
    private ViewHandler viewHandler;
    private addTreatmentlogVM addTreatmentlogVM;

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
    private void collectInformation(ActionEvent event) throws SQLException {
            addTreatmentlogVM.getInformation();
            if (addTreatmentlogVM.isClicked()){
                addTreatmentlogVM.addLaboratory();
            }
            addTreatmentlogVM.clear();
            resetPage();
    }

    private void resetPage() {
        addTreatmentlogVM.setPatient(null);
        addTreatmentlogVM.setClicked(false);
        textArea.setText("");
    }


    public void init(addTreatmentlogVM addTreatmentlogVM, ViewHandler viewHandler) {
        this.addTreatmentlogVM = addTreatmentlogVM;
        this.viewHandler = viewHandler;
        textField1.textProperty().bindBidirectional(addTreatmentlogVM.nameProperty());
        textArea.textProperty().bindBidirectional(addTreatmentlogVM.descriptionProperty());
        saveButton.disableProperty().bind(addTreatmentlogVM.saveButtonDisabledProperty());
        textField4.textProperty().bindBidirectional(addTreatmentlogVM.criteriaProperty());
        textField3.textProperty().bindBidirectional(addTreatmentlogVM.laboNameProperty());
        textField5.textProperty().bindBidirectional(addTreatmentlogVM.quantityProperty());
    }

    public void Cancel(ActionEvent event) {
        addTreatmentlogVM.clear();
    }
}