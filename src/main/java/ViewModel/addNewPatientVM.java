package ViewModel;

import javafx.scene.control.TextField;
import org.example.learn.JDBConnection;
import org.example.learn.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addNewPatientVM {
    private Connection connection;

    private PreparedStatement pst = null;
    private boolean update = false;
    public Patient patient =null;
    private int id;
    public void saveInformation(String name, String contactNumber, String address, String dob){
        if (name.isEmpty()||contactNumber.isEmpty()||address.isEmpty()||dob.isEmpty()){
            JOptionPane.showMessageDialog(null, "Please fill all data");
        }
        else {
            try{
                String sql = getQuery();
                connection = JDBConnection.NhaKhoa100eConnect();
                pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2,dob);
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
    private String getQuery() {
        if (update == true){
            return "";
        }
        else{
            return "Insert into Patient (namePatient, dateOfBirth, contactNumber, addressPatient) Values (?,?,?,?)";
        }
    }
    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

}
