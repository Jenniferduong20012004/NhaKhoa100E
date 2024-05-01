package org.example.learn;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.Date;

public class Patient {
    private String name;
    private String address;
    private String contactNumber;

    public Patient (String name, String contactNumber, String address){
        this.name = name;
        this.contactNumber= contactNumber;
        this.address = address;
    }
}
