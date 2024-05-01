package org.example.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnection {
    public static Connection NhaKhoa100eConnect(){
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
