package ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.JDBConnection;
import org.example.learn.Patient;
import org.example.learn.PatientPageControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientPageVM {
    private addNewPatientVM addNewPatientVM;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Patient> list = FXCollections.observableArrayList();
    public void init(){
        connection = JDBConnection.NhaKhoa100eConnect();
    }
    public ObservableList<Patient> loadDataFromDatabase(){
        try {
            list.clear();
            pst = connection.prepareStatement("Select patient_id, namePatient, dateOfBirth, contactNumber, addressPatient\n" +
                    "from Patient");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("contactNumber"),
                        rs.getString("addressPatient"),
                        "" +rs.getDate("dateOfBirth")));
            }

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }
        return list;
    }
    public void update(){
        addNewPatientVM = new ViewModel.addNewPatientVM();
        addNewPatientVM.setUpdate(true);
    }
}