package ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.PatientPageControl;
import org.example.learn.addNewPatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientPageVM {
    private addNewPatientVM addNewPatientVM;
    private addNewPatient addNewPatient;
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
    public void update(int id, addNewPatientVM addNewPatientVM){
        this.addNewPatientVM = addNewPatientVM;
        addNewPatientVM.setPatientId(id);
        addNewPatientVM.setUpdate(true);
    }
}
