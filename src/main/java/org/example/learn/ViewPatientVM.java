package org.example.learn;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.example.learn.PatientImageController;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewPatientVM {
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private Patient patient;
    private StringProperty name;
    private List<AnchorPane> productPaneList;
    public ViewPatientVM (){
        name = new SimpleStringProperty();
        productPaneList =  new ArrayList<AnchorPane>();
    }

    public List<AnchorPane> getProductPaneList() {
        return productPaneList;
    }

    void loadDatabase() {
        try {
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call getPatientImage(?)}");
            call.setInt(1, patient.getId());
            call.execute();
            rs = call.getResultSet();
            while (rs.next()) {
                int imageId = rs.getInt("iId");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientImage.fxml"));
                AnchorPane pane = loader.load();
                productPaneList.add(pane);
                PatientImageController controller = loader.getController();
                controller.initImageInfo(imageId, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringProperty nameProperty() {
        return name;
    }
    public void clear(){
        patient = null;
        name.set("");
        productPaneList = null;

    }


    public int getAnchorHeight() {
        int countPur = 0;
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call getPatientImage(?)}");
            call.setInt(1,patient.getId());
            call.execute();
            rs = call.getResultSet();
            while (rs.next()) {
                countPur++;
            }
            connection.close();
            call.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return (int) (Math.ceil((double) countPur / 2)) * 250;
    }

    public void viewImageWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
    }
    public void delete() {
        productPaneList = null;
        loadDatabase();
    }
}