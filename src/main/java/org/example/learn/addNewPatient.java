package org.example.learn;

import ViewModel.addNewPatientVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class addNewPatient implements Initializable {
    private Stage stage;
    @FXML
    TextField textField, textField1, textField2,textField3;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Connection connection;

    private PreparedStatement pst = null;
    private boolean update = false;
    public Patient patient =null;
    private int id;
    private addNewPatientVM addNewpatientvm = new addNewPatientVM();

    @FXML
    private void getInformation (ActionEvent event) throws SQLException {
        String name = textField.getText().trim().toLowerCase();
        String contactNumber = textField1.getText().trim().toLowerCase();
        String address= textField2.getText().trim().toLowerCase();
        String dob = textField3.getText().trim().toLowerCase();
        addNewpatientvm.saveInformation(name, contactNumber, address, dob);
        resetText();
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
    private void switchToAddNewTreatmentLog(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewTreatment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToAddNewLaboratory(ActionEvent event) {
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
    private void Cancel(ActionEvent event){
        resetText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setTextField(String patientName, String contactNumber, String address, String dob){
        textField.setText(patientName);
        textField1.setText(contactNumber);
        textField2.setText(address);
        textField3.setText(dob);
    }
    public void resetText() {
        textField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
        textField3.setText(null);
    }
}
