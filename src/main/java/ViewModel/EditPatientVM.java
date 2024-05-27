package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.learn.EditPatient;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditPatientVM {
    private CallableStatement call = null;
    private StringProperty name, address, contactNumber, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private ResultSet rs = null;

    private Patient patient;
    public EditPatientVM(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        address.addListener((observableValue, oldValue, newValue)->onSavingChange());
        contactNumber.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }
    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }

    public StringProperty nameProperty() {
        return name;
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().isEmpty() ||address.get()== null || address.get().isEmpty() || contactNumber.get()== null || contactNumber.get().isEmpty();
        saveButtonDisabled.set(disable);
    }

    public String getAddress() {
        return address.get();
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void editWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
        address.set(patient.getAddress());
        contactNumber.set(patient.getContactNumber());
    }

    public void clear() {
        name.set(patient.getName());
        address.set(patient.getAddress());
        contactNumber.set(patient.getContactNumber());
    }

    public void edit() {
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call updatePatient(?,?,?, ?)}");
            call.setInt(1, patient.getId());
            call.setNString(2, name.get());
            call.setNString(3, contactNumber.get());
            call.setNString(4, address.get());
            call.execute();
            rs = call.getResultSet();
            JOptionPane.showMessageDialog(null, "Update successfully");
            clear();
            call.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
