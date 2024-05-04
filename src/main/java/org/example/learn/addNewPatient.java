package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

public class addNewPatient implements Initializable {
    private Stage stage;
    @FXML
    TextField textField, textField1, textField2;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private Connection connection;
    @FXML
    private DatePicker datePicker;

    private PreparedStatement pst = null;
    @FXML
    public void getInformation (ActionEvent event) throws SQLException {
        String sql = "Insert into Patient (namePatient, dateOfBirth, contactNumber, addressPatient) Values (?,?,?,?)";
        String name = textField.getText().trim();
        String contactNumber = textField1.getText().trim();
        String address= textField2.getText().trim();
        LocalDate localDate = datePicker.getValue();
        Date date = Date.valueOf(localDate);
        String pattern = "MMMM dd, yyyy";
        String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
        if (name.isEmpty()||contactNumber.isEmpty()||address.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all data");
        }
        else {
            try{
                connection =JDBConnection.NhaKhoa100eConnect();
                pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setDate(2,date);
                pst.setString(3, contactNumber);
                pst.setString(4, address);
                int i =pst.executeUpdate();
                if (i==1){
                    JOptionPane.showMessageDialog(null, "Save data successfully");
                }
                pst.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
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
    public void switchToPatient(ActionEvent event) {
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
    public void switchToAddNewTreatmentLog(ActionEvent event) {
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
    public void switchToAddNewLaboratory(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
