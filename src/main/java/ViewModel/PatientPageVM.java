package ViewModel;

import util.NotificationService;
import Entity.Patient;
import SQL.JDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.PatientPageControl;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientPageVM {
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private ObservableList<Patient> list;
    private NotificationService notification;
    public PatientPageVM (addTreatmentlogVM add, addNewImageVM img, ViewPatientVM view){
        notification = new NotificationService(add, img, view);
    }
    public void init(){
        list = FXCollections.observableArrayList();
        connection = JDBConnection.NhaKhoa100eConnect();
    }

    public ObservableList<Patient> getList() {
        return list;
    }

    public void loadDataFromDatabase(){
        try {
            list.clear();
            call = connection.prepareCall("{call getpatient}");
            call.execute();
            rs = call.getResultSet();
            while (rs.next()){
                list.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("contactNumber"),
                        rs.getString("addressPatient")));
            }
            call.close();
            rs.close();

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void removeRecord(Patient c) {
        try {
            call = connection.prepareCall("{call delete_patient(?)}");
            call.setInt(1, c.getId());
            call.execute();
        }catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);
        }
        loadDataFromDatabase();
    }

    public void addTreatment(Patient c) {
        notification.notify (c);
    }

    public void view(Patient c) {
        notification.notify(c);
    }
}
