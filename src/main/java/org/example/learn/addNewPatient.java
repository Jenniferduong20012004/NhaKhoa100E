package org.example.learn;

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

    @FXML
    private void getInformation (ActionEvent event) throws SQLException {
        String name = textField.getText().trim().toLowerCase();
        String contactNumber = textField1.getText().trim().toLowerCase();
        String address= textField2.getText().trim().toLowerCase();
        String dob = textField3.getText().trim().toLowerCase();
        if (name.isEmpty()||contactNumber.isEmpty()||address.isEmpty()||dob.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all data");
        }
        else {
            try{
                String sql = getQuery();
                connection =JDBConnection.NhaKhoa100eConnect();
                pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2,dob);
                pst.setString(3, contactNumber);
                pst.setString(4, address);
                int i =pst.executeUpdate();
                if (i==1){
                    JOptionPane.showMessageDialog(null, "Save data successfully");
                    resetText();
                }
                pst.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private String getQuery() {
        if (update == true){
            return "";
        }
        else{
            return "Insert into Patient (namePatient, dateOfBirth, contactNumber, addressPatient) Values (?,?,?,?)";
        }
    }

    private void resetText() {
        textField.setText(null);
        textField1.setText(null);
        textField2.setText(null);
        textField3.setText(null);
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
    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
