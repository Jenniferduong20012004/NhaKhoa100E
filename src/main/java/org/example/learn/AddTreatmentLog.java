package org.example.learn;

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
    @FXML
    private Button checkButton;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    @FXML
    private Label amountlabel;

    @FXML
    private Label criterialabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private DatePicker datePickerdob;

    @FXML
    private Label labolabel;
    @FXML
    private RadioButton radioButtonNo;

    @FXML
    private RadioButton radioButton;

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
    private TextField textField5;
    @FXML
    private Label Date;
    private boolean clicked= false;
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private boolean checkedData = false;
    private String patientId;
    @FXML
    void CheckData(ActionEvent event) {
            String name = textField1.getText();
            name = name.trim();
            System.out.println(name);
            LocalDate localDate = datePickerdob.getValue();
            java.sql.Date dateSQL = java.sql.Date.valueOf(localDate);
            String pattern = "yyyy-MMMM-dd";
            String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
            if (name.isEmpty() ||  datePattern.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in missing data");
        }
            String sql = "DECLARE @class nvarchar(50) = N'"+name+"'"+ " Select patient_id from Patient where namePatient = @class and dateOfBirth = ?";
            System.out.println(sql);
            connection = JDBConnection.NhaKhoa100eConnect();
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, datePattern);
            rs = pst.executeQuery();
            if (rs.isBeforeFirst()){
                System.out.println(localDate);
                Date.setVisible(true);
                DescriptionLabel.setVisible(true);
                textArea.setVisible(true);
                datePicker.setVisible(true);
                radioButtonNo.setVisible(true);
                radioButton.setVisible(true);
                //patientId = rs.getString(1);
                System.out.println(patientId);
                checkedData = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Patient first come to clinic insert in New Patient first");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void clickYes(ActionEvent event){
        if (clicked == false){
            clicked = true;
            textField3.setVisible(true);
            textField4.setVisible(true);
            textField5.setVisible(true);
            amountlabel.setVisible(true);
            criterialabel.setVisible(true);
            labolabel.setVisible(true);
        }
        else{
           clicked = false;
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);
            amountlabel.setVisible(false);
            criterialabel.setVisible(false);
            labolabel.setVisible(false);
        }
    }

    @FXML
    public void collectInformation (ActionEvent event) throws SQLException {
        if (checkedData ==true) {
            String name = textField1.getText();
            LocalDate localDate = datePicker.getValue();
            java.sql.Date date = java.sql.Date.valueOf(localDate);
            String pattern = "MMMM dd, yyyy";
            String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
            System.out.println(name + datePattern);
            String Description = textArea.getText();
            String sql = "Insert into Treatment Values (?,?,?,?)";
            if (name.isEmpty() || datePattern.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill missing data");
                alert.showAndWait();
            } else {
                try {
                    connection = JDBConnection.NhaKhoa100eConnect();
                    pst = connection.prepareStatement(sql);
                    pst.setString(1, name);
                    pst.setDate(3, date);
                    pst.setString(4, Description);
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "Save data successfully");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    pst.close();
                }

            }
        }
    }
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

    public void switchToAddLaboratory(ActionEvent event) {
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
}