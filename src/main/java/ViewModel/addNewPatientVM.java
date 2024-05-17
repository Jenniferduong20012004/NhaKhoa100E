package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;
import java.sql.*;

public class addNewPatientVM {
    private StringProperty name, address, contactNumber, dateOfBirth, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private ResultSet rs = null;

    private PreparedStatement pst = null;
    private boolean update = false;
    public Patient patient =null;
    private CallableStatement call = null;
    private int patientId;
    public addNewPatientVM(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        dateOfBirth = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        address.addListener((observableValue, oldValue, newValue)->onSavingChange());
        contactNumber.addListener((observableValue, oldValue, newValue)->onSavingChange());
        dateOfBirth.addListener((observableValue, oldValue, newValue)->onSavingChange());

    }

    public String getSaveResponse() {
        return saveResponse.get();
    }

    public StringProperty saveResponseProperty() {
        return saveResponse;
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().equals("")||address.get()== null ||address.get().equals("")|| contactNumber.get()== null || contactNumber.get().equals("")||dateOfBirth.get()== null || dateOfBirth.get().equals("");
        saveButtonDisabled.set(disable);
    }

    public void saveInformation(){
            try{
                String sql = getQuery();
                connection = JDBConnection.NhaKhoa100eConnect();
                call = connection.prepareCall("{call insertPatient(?,?,?,?)}");
                call.setString(1, name.get());
                call.setString(2, dateOfBirth.get());
                call.setString(3, contactNumber.get());
                call.setString(4, address.get());
                call.execute();
                rs = call.getResultSet();
                JOptionPane.showMessageDialog(null, "Save data successfully");
                clear();
                call.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public boolean isSaveButtonDisabled() {
        return saveButtonDisabled.get();
    }

    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    private String getQuery() {
        if (update == true){
            return "update Patient set namePatient = ?, dateOfBirth = ?, contactNumber = ?, addressPatient = ? where patient_id ="+ patientId;
        }
        else{
            return "Insert into Patient (namePatient, dateOfBirth, contactNumber, addressPatient) Values (?,?,?,?)";
        }
    }
    public void clear(){
        name.set("");
        address.set("");
        contactNumber.set("");
        dateOfBirth.set("");
        saveResponse.set("");
    }

    public void update(int id){
        String sql = "Select * from Patient where patient_id = ?";
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            name.set(rs.getString("namePatient"));
            address.set(rs.getString("addressPatient"));
            contactNumber.set(rs.getString("contactNumber"));
            dateOfBirth.set(""+rs.getDate("dateOfBirth"));
            pst.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
