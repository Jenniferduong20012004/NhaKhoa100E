package ViewModel;

import Entity.Treatment;
import SQL.JDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.Treatmentlog;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreatmentLogVM {
    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private ObservableList<Treatment> treatmentList;
    private CallableStatement call = null;
    public void init(){
        treatmentList = FXCollections.observableArrayList();
        connection = JDBConnection.NhaKhoa100eConnect();
        loadDataFromDatabase();
    }
    private void loadDataFromDatabase() {
        try {
            treatmentList.clear();
            call = connection.prepareCall("{call showTreatment}");
            call.execute();
            rs = call.getResultSet();
            while (rs.next()){
                treatmentList.add(new Treatment(rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("detail"),
                        "" +rs.getDate("dateCome")));
            }
            call.close();
            connection.close();
            rs.close();

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
