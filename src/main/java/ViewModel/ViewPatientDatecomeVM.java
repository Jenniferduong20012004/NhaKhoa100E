package ViewModel;

import Entity.Patient;
import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ViewPatientDatecomeVM {
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private ObservableList<Patient> list;
    private Patient patient;
    public void loadFromDatabase() {
    }

    public ObservableList<Patient> getList() {
        return list;
    }

    public void viewWithPatientInformation(Patient c) {
        this.patient = c;
    }
}
