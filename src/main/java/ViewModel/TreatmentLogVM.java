package ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.PatientPageControl;
import org.example.learn.Treatmentlog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreatmentLogVM {
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<Treatment> treatmentList = FXCollections.observableArrayList();
    public void init(){
        connection = JDBConnection.NhaKhoa100eConnect();
        loadDataFromDatabase();
    }
    private void loadDataFromDatabase() {
        try {
            treatmentList.clear();
            pst = connection.prepareStatement("Select Treatment.dateCome, Patient.namePatient, Treatment.detail from Treatment, Patient where Treatment.patient_id = Patient.patient_id order by dateCome desc");
            rs = pst.executeQuery();
            while (rs.next()){
                treatmentList.add(new Treatment(rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("detail"),
                        "" +rs.getDate("dateCome")));
            }

        } catch (SQLException e) {
            Logger.getLogger(Treatmentlog.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public ObservableList<Treatment> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(ObservableList<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }
}
