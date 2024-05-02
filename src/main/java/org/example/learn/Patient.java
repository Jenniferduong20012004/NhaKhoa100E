package org.example.learn;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.Date;

public class Patient {
    private String name;
    private String address;
    private String contactNumber;
    private String date;

    public Patient (String name, String contactNumber, String address, String date){
        this.name = name;
        this.contactNumber= contactNumber;
        this.address = address;
        this.date = date;
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

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
