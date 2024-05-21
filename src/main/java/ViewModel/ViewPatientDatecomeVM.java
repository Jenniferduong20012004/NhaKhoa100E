package ViewModel;

import Entity.Patient;
import Entity.Treatment;
import SQL.JDBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.PatientPageControl;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewPatientDatecomeVM {
    StringProperty name;
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private ObservableList<Treatment> list;
    private Patient patient;
    public ViewPatientDatecomeVM(){
        name = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void init(){
        list = FXCollections.observableArrayList();
        connection = JDBConnection.NhaKhoa100eConnect();
    }
    public void loadFromDatabase() {
        try {
            list.clear();
            call = connection.prepareCall("{call getTreatmentWithPatientID(?)}");
            call.setInt(1,patient.getId());
            call.execute();
            rs = call.getResultSet();
            while (rs.next()){
                list.add(new Treatment(
                        rs.getInt("patient_id"),
                        patient.getName(), //tên cột trong sql
                        rs.getString("detail"),
                        rs.getString("dateCome")));
            }
            //JOptionPane.showMessageDialog(null,list.size());
            call.close();
            rs.close();

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    public ObservableList<Treatment> getList() {
        return list;
    }

    public void viewWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
    }
}
