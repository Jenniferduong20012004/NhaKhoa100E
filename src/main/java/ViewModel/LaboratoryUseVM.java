package ViewModel;

import Entity.LaboratoryUse;
import SQL.JDBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.learn.Equipment;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratoryUseVM {
    ObservableList<LaboratoryUse> laboUseList = FXCollections.observableArrayList();
    Connection connection = null;
    ResultSet rs = null;
    CallableStatement call;

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
            call = connection.prepareCall("{call getLaboratory}");
            call.execute();
            rs = call.getResultSet();
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
