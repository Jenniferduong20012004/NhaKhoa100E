package ViewModel;

import Entity.Patient;
import Entity.Treatment;
import SQL.JDBConnection;
import javafx.beans.property.*;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditTreatmentVM {
    private StringProperty name, description, saveResponse, quantity, laboName;
    private ObjectProperty<LocalDate> date;
    private CallableStatement call = null;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private ResultSet rs = null;
    private boolean clicked;
    private Treatment treatment;
    private Patient patient;
    public boolean isClicked() {
        return clicked;
    }
    public EditTreatmentVM(){
        name = new SimpleStringProperty();
        description = new SimpleStringProperty();
        date  = new SimpleObjectProperty<>();
        saveResponse = new SimpleStringProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        description.addListener((observableValue, oldValue, newValue)->onSavingChange());
        date.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public ObjectProperty dateProperty() {
        return date;
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().isEmpty() ||description ==null || description.get().isEmpty()|| date == null;
        saveButtonDisabled.set(disable);
    }
    public void editWithTreatmentInformation(Treatment c) {
        this.treatment = c;
        name.set(treatment.getPatientName());
        description.set(treatment.getDescription());
        date.set(translateDate(treatment.getDate()));
    }
    private LocalDate translateDate (String s){
        return LocalDate.parse(s);
    }
    private String localDateToString (LocalDate s){
        return s.format (DATE_FORMATTER);
    }
    public void edit() {
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call updateTreatment(?,?,?)}");
            call.setInt(1, treatment.getPatientId());
            call.setNString(2, description.get());
            call.setNString(3, localDateToString(date.get()));
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
    public void clear() {
        name.set(treatment.getPatientName());
        description.set(treatment.getDescription());
        date.set(translateDate(treatment.getDate()));
    }
    public void setClicked(boolean b) {
        this.clicked = b;
    }

}
