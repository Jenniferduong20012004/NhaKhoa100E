package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addTreatmentlogVM {
    private StringProperty name, description, saveResponse, criteria, quantity, laboName;
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private boolean visibleButton = false;
    private boolean checkedData = false;
    private String Date;
    private Date dateToday;
    private Patient patient;

    private int patientId;
    private boolean clicked;// laboratory click
    private PatientPageVM patientPageVM;

    public StringProperty laboNameProperty() {
        return laboName;
    }

    public StringProperty quantityProperty() {
        return quantity;
    }

    public String getCriteria() {
        return criteria.get();
    }

    public StringProperty criteriaProperty() {
        return criteria;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public void setDate(String date) {
        Date = date;
    }
    public void setVisibleButton(boolean visibleButton) {
        this.visibleButton = visibleButton;
    }
    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }

    public addTreatmentlogVM(){
        name = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        criteria = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        laboName = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        description = new SimpleStringProperty();
        description.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }

    private void onSavingChange() {
        boolean disable = description.get()== null || description.get().equals("");
        saveButtonDisabled.set(disable);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void addLaboratory(){
        LocalDate localDate = LocalDate.now();
        dateToday = java.sql.Date.valueOf(localDate);
        try {
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call addLaboratory(?,?,?,?,?)}");
            call.setInt(1, patient.getId());
            call.setDate(2, dateToday);
            call.setNString(3,  criteria.get());
            call.setInt(4, Integer.valueOf(quantity.get()));
            call.setNString(5, laboName.get());
            call.execute();
            rs = call.getResultSet();
            JOptionPane.showMessageDialog(null, "Save laboratory data successfully");
            connection.close();
            call.close();
            rs.close();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    public void getInformation(){
        LocalDate localDate = LocalDate.now();
        dateToday = java.sql.Date.valueOf(localDate);
        connection = JDBConnection.NhaKhoa100eConnect();
            try{
                call = connection.prepareCall("{call insertToTreatment(?, ?, ?)}");
                call.setInt(1, patient.getId());
                call.setString(2,description.get());
                call.setDate(3, dateToday);
                call.execute();
                JOptionPane.showMessageDialog(null, patient.getId());
                JOptionPane.showMessageDialog(null, "Save data successfully");
                connection.close();
                call.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
            }
    }
    public void clear(){
        name.set("");
        criteria.set("");
        saveResponse.set("");
        quantity.set("");
        laboName.set("");
    }

    public void addTreatmentWithPatientInformation(Patient c) {
        name.set(c.getName());
        this.patient = c;
    }
}
