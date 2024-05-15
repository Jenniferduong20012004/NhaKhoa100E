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
    private StringProperty name, address, description, dateOfBirth, saveResponse;
    private BooleanProperty saveButtonDisabled, checkButtonDisabled;
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private boolean visibleButton = false;
    private boolean checkedData = false;
    private String patientName;
    private String Date;
    private Date date;

    private int patientId;
    private boolean clicked;// laboratory click

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

    public String getDateOfBirth() {
        return dateOfBirth.get();
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

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
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
            String sql = "DECLARE @class nvarchar(50) = N'" + name.get() + "'" + " Select patient_id from Patient where namePatient = @class and dateOfBirth = ?";
            connection = JDBConnection.NhaKhoa100eConnect();
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, dateOfBirth.get());
                rs = pst.executeQuery();
                if (rs.next()) {
                    visibleButton = true;
                    patientId = rs.getInt("patient_id");
                    checkedData = true;
                    rs.close();
                    connection.close();
                    pst.close();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient first come to clinic insert in New Patient first");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }
    public void addLaboratory(String criteria, String quantity, String laboName){
        try {
            connection = JDBConnection.NhaKhoa100eConnect();
            String sqlLaboratory = "Insert into labUse (patient_id, dateCome, Criteria, Quantity, laboName) Values (?,?,?,?,?)";
            PreparedStatement pst1 = connection.prepareStatement(sqlLaboratory);
            pst1.setInt(1, patientId);
            pst1.setDate(2, date);
            pst1.setString(3, criteria);
            pst1.setString(4, quantity);
            pst1.setString(5, laboName);
            int a = pst1.executeUpdate();
            if (a==1){
                JOptionPane.showMessageDialog(null, "Save laboratory data successfully");
            }
            pst1.close();
            connection.close();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    public void getInformation(){
        if (checkedData ==true) {
            LocalDate localDate = LocalDate.now();
            date = java.sql.Date.valueOf(localDate);
            String pattern = "yyyy-MM-dd";
            String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
                String sql = "Insert into Treatment (patient_id, detail, dateCome) Values (?,?,?)";
                System.out.println(sql);
                connection = JDBConnection.NhaKhoa100eConnect();
                try{
                    connection =JDBConnection.NhaKhoa100eConnect();
                    pst = connection.prepareStatement(sql);
                    pst.setInt(1, patientId);
                    pst.setString(2,description.get());
                    pst.setDate(3, date);
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
}
