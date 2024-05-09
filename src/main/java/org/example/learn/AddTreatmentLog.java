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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private int patientId;
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("equipment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TreatmentLog.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PatientLog.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToAddPatient(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewPatient.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToAddLaboratory(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addLaboratory.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Cancel(ActionEvent event) {
        resetPage();
    }
}