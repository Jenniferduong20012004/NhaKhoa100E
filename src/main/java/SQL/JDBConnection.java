package SQL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBConnection {
    public  static Connection NhaKhoa100eConnect(){
        String string = "jdbc:sqlserver://LAPTOP-7VIN5AEK:1433;" + "user=sa;password=Nhu1234@;databaseName=NHAKHOA;encrypt=false";
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(string);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
