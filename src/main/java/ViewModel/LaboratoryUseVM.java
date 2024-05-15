package ViewModel;

import Entity.LaboratoryUse;
import SQL.JDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratoryUseVM {
    ObservableList<LaboratoryUse> laboUseList = FXCollections.observableArrayList();
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public ObservableList<LaboratoryUse> getLaboUseList() {
        return laboUseList;
    }

    public void init(){
        connection = JDBConnection.NhaKhoa100eConnect();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        try {
            laboUseList.clear();
            pst = connection.prepareStatement("Select Patient.namePatient, labUse.dateCome, labUse.Criteria, labUse.Quantity, labUse. laboName from Patient, labUse where Patient.patient_id = labUse.patient_id");
            rs = pst.executeQuery();
            while (rs.next()){
                laboUseList.add(new LaboratoryUse(rs.getString("laboName"),//tên cột trong sql
                        rs.getString("namePatient"),
                        rs.getString("Criteria"),
                        ""+rs.getInt("Quantity"),
                        "" +rs.getDate("dateCome")));

            }

        } catch (SQLException e) {
            Logger.getLogger(Equipment.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void removeRecord(LaboratoryUse c) {
    }
}
