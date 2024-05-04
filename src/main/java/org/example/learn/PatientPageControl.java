package org.example.learn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import org.example.learn.HelloApplication;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientPageControl implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private TableView<Patient> patients;
    @FXML
    private TableColumn<Patient,String> PatientName;
    @FXML
    private TableColumn<Patient,String> PatientContactNumber;
    @FXML
    private TableColumn<Patient,String> AddressPatient;
    @FXML
    private TableColumn<Patient, String> DateColumn;
    @FXML
    private TableColumn<Patient, Integer> MoneyPayColumn;
    ObservableList<Patient> list = FXCollections.observableArrayList();
    Patient patient = null;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    public void switchToEquipment(ActionEvent event) {
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
    public void switchToTreatment(ActionEvent event) {
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
    public void switchToDashboard(ActionEvent event) {
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

    public void switchToAddPatient(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = JDBConnection.NhaKhoa100eConnect();
        setCellTable();
        loadDataFromDatabase();
        patients.setItems (list);
    }
    @FXML
    private void setCellTable(){
        /*patients = new TableView<>();
        PatientName = new TableColumn<>();
        PatientContactNumber = new TableColumn<>();
        AddressPatient = new TableColumn<>();
        DateColumn = new TableColumn<>();*/
        PatientName.setCellValueFactory(new PropertyValueFactory<>("name"));

        PatientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        AddressPatient.setCellValueFactory(new PropertyValueFactory<>("address"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    private void loadDataFromDatabase(){
        /*try {
            list.clear();
            pst = connection.prepareStatement("Select Treatment.patientName, Patient.contactnumber, Patient.addressPatient, Treatment.dateTreatment from Treatment, Patient where Patient.patientName = Treatment.patientName and Patient.contactnumber =Treatment.contactnumber");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Patient(rs.getString("patientName"),
                        rs.getString("contactnumber"),
                        rs.getString("addressPatient"),
                       "" +rs.getDate("dateTreatment")));
                patients.setItems(list);
            }

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }
        patients.setItems(list);*/
    }
}
