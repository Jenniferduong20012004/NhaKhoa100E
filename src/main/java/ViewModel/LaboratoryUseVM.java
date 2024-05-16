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
    private ObservableList<LaboratoryUse> laboUseList;
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call;

    public ObservableList<LaboratoryUse> getLaboUseList() {
        return laboUseList;
    }

    public void init(){
        laboUseList= FXCollections.observableArrayList();
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
            call.close();
            connection.close();
            rs.close();

        } catch (SQLException e) {
            Logger.getLogger(Equipment.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void removeRecord(LaboratoryUse c) {
    }
}
