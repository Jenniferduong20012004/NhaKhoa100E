package ViewModel;

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
    private StringProperty name, description, dateOfBirth, saveResponse, criteria, quantity, laboName;
    private BooleanProperty saveButtonDisabled, checkButtonDisabled;
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private boolean visibleButton = false;
    private boolean checkedData = false;
    private String Date;
    private Date dateToday;

    private int patientId;
    private boolean clicked;// laboratory click

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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public boolean isCheckButtonDisabled() {
        return checkButtonDisabled.get();
    }

    public BooleanProperty checkButtonDisabledProperty() {
        return checkButtonDisabled;
    }
    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }
    public void setDate(String date) {
        Date = date;
    }

    public boolean isVisibleButton() {
        return visibleButton;
    }

    public void setVisibleButton(boolean visibleButton) {
        this.visibleButton = visibleButton;
    }

    public boolean isCheckedData() {
        return checkedData;
    }

    public void setCheckedData(boolean checkedData) {
        this.checkedData = checkedData;
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

    public boolean isSaveButtonDisabled() {
        return saveButtonDisabled.get();
    }

    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }

    public addTreatmentlogVM(){
        name = new SimpleStringProperty();
        dateOfBirth = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        criteria = new SimpleStringProperty();
        quantity = new SimpleStringProperty();
        laboName = new SimpleStringProperty();
        checkButtonDisabled= new SimpleBooleanProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        description = new SimpleStringProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChangeCheck());
        dateOfBirth.addListener((observableValue, oldValue, newValue)->onSavingChangeCheck());
        description.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }

    private void onSavingChange() {
        boolean disable = description.get()== null || description.get().equals("");
        saveButtonDisabled.set(disable);
    }

    private void onSavingChangeCheck() {
        boolean disable = name.get()== null || name.get().equals("")||dateOfBirth.get()== null ||dateOfBirth.get().equals("");
        checkButtonDisabled.set(disable);
    }

    public void checkData(){
            connection = JDBConnection.NhaKhoa100eConnect();
            try {
                call = connection.prepareCall("{call checkData(?,?)}");
                call.setString(1, name.get());
                call.setDate(2, java.sql.Date.valueOf(dateOfBirth.get()));
                call.execute();
                rs = call.getResultSet();
                if (rs.next()) {
                    visibleButton = true;
                    patientId = rs.getInt("patient_id");
                    checkedData = true;
                    rs.close();
                    connection.close();
                    call.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient first come to clinic insert in New Patient first");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public void addLaboratory(){
        try {
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call addLaboratory(?,?,?,?,?)}");
            call.setInt(1, patientId);
            call.setDate(2, dateToday);
            call.setString(3,  criteria.get());
            call.setInt(4, Integer.valueOf(quantity.get()));
            call.setString(5, laboName.get());
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
        if (checkedData ==true) {
            LocalDate localDate = LocalDate.now();
            dateToday = java.sql.Date.valueOf(localDate);
            String pattern = "yyyy-MM-dd";
            String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
            String sql = "Insert into Treatment (patient_id, detail, dateCome) Values (?,?,?)";
            connection = JDBConnection.NhaKhoa100eConnect();
            try{
                    connection =JDBConnection.NhaKhoa100eConnect();
                    pst = connection.prepareStatement(sql);
                    pst.setInt(1, patientId);
                    pst.setString(2,description.get());
                    pst.setDate(3, dateToday);
                    int i =pst.executeUpdate();
                    if (i==1){
                        JOptionPane.showMessageDialog(null, "Save data successfully");
                    }
                    connection.close();
                    pst.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
            }
        }
    }
    public void clear(){
        name.set("");
        criteria.set("");
        dateOfBirth.set("");
        saveResponse.set("");
        quantity.set("");
        laboName.set("");
    }
}
