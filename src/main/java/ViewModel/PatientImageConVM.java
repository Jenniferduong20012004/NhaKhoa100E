package ViewModel;

import SQL.JDBConnection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientImageConVM {
    private int id;
    private Connection connection;
    private ResultSet rs = null;
    private CallableStatement call = null;
    public PatientImageConVM (int i){
        this.id =i;
    }
    public ResultSet loadDataFromImage(ImageView imgProd) {
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call getImageWithId(?)}");
            call.setInt(1,id);
            call.execute();
            rs = call.getResultSet();
            while (rs.next()) {
                showImage(imgProd, rs);
            }
            connection.close();
            call.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private void showImage(ImageView imgProd, ResultSet resultSet) throws SQLException {
        byte[] imgData = resultSet.getBytes("pImage");
        if(imgData != null){
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imgData);
            Image image = new Image(inputStream);
            imgProd.setImage(image);
            imgProd.setPreserveRatio(false);
        } else {
            imgProd.setImage(null);
        }
    }
    public void removeImage(){
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call deleteImage(?)}");
            call.setInt(1,id);
            call.execute();
            connection.close();
            call.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
