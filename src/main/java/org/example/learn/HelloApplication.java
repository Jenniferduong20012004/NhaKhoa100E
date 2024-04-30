package org.example.learn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("equipment.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String string = "jdbc:sqlserver://DESKTOP-9TRAU8I:1433;" +
                "user=sa;password=Nhu1234@;databaseName=NhaKhoa100e;encrypt=false";
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(string);
            System.out.println ("Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //launch();
    }
}