package ViewModel;

import org.example.learn.JDBConnection;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class addTreatmentlogVM {
    private Connection connection;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private boolean visibleButton = false;
    private boolean checkedData = false;
    private String patientName;
    private String Description;
    private String Date;
    private Date date;

    private int patientId;
    private boolean clicked;// laboratory click

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return Date;
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

    public void checkData(String name, String datePattern){
        if (name.isEmpty() ||  datePattern.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in missing data");
        }
        else {
            patientName = name;
            Date = datePattern;
            String sql = "DECLARE @class nvarchar(50) = N'" + name + "'" + " Select patient_id from Patient where namePatient = @class and dateOfBirth = ?";
            connection = JDBConnection.NhaKhoa100eConnect();
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, datePattern);
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
    public void getInformation(String Description){
        if (checkedData ==true) {
            LocalDate localDate = LocalDate.now();
            date = java.sql.Date.valueOf(localDate);
            String pattern = "yyyy-MM-dd";
            String datePattern = localDate.format(DateTimeFormatter.ofPattern(pattern));
            if (Description.isEmpty() ||  datePattern.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in missing data");
            }
            else {
                String sql = "Insert into Treatment (patient_id, detail, dateCome) Values (?,?,?)";
                System.out.println(sql);
                connection = JDBConnection.NhaKhoa100eConnect();
                try{
                    connection =JDBConnection.NhaKhoa100eConnect();
                    pst = connection.prepareStatement(sql);
                    pst.setInt(1, patientId);
                    pst.setString(2,Description);
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
}
