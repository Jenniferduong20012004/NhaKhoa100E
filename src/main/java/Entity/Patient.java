package Entity;


import SQL.JDBConnection;
import javafx.scene.control.Button;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private int id;
    private String name;


    private String address;
    private String contactNumber;
    public Patient (String name, String contactNumber,String address){
        this.name = name;
        this.contactNumber = contactNumber;
        this.address= address;
    }

    public Patient (int id, String name, String contactNumber, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void saveInformation(){
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call insertPatient(?,?,?)}");
            call.setString(1, name);
            call.setString(2, contactNumber);
            call.setString(3, address);
            call.execute();
            rs = call.getResultSet();
            call.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
