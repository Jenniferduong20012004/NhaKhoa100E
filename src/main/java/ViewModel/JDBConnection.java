package ViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBConnection {
    public  static Connection NhaKhoa100eConnect(){
        String string = "jdbc:sqlserver://DESKTOP-9TRAU8I:1433;" + "user=sa;password=Nhu1234@;databaseName=NhaKhoa100e;encrypt=false";
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(string);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
