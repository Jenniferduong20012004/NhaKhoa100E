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
    private StringProperty name, address, contactNumber, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private ResultSet rs = null;
    public Patient patient =null;
    private CallableStatement call = null;
    private int patientId;
    public addNewPatientVM(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        address.addListener((observableValue, oldValue, newValue)->onSavingChange());
        contactNumber.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }

    public String getSaveResponse() {
        return saveResponse.get();
    }

    public StringProperty saveResponseProperty() {
        return saveResponse;
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().equals("")||address.get()== null ||address.get().equals("")|| contactNumber.get()== null || contactNumber.get().equals("");
        saveButtonDisabled.set(disable);
    }

    public void saveInformation(){
            try{
                connection = JDBConnection.NhaKhoa100eConnect();
                call = connection.prepareCall("{call insertPatient(?,?,?)}");
                call.setString(1, name.get());
                call.setString(2, contactNumber.get());
                call.setString(3, address.get());
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


    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public void clear(){
        name.set("");
        address.set("");
        contactNumber.set("");
        saveResponse.set("");
    }

}
