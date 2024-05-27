package ViewModel;

import SQL.JDBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.learn.ViewPatient;
import org.example.learn.ViewPatientVM;
import util.NotiImage;

import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientImageConVM {
    private int id;
    private StringProperty dateTake;
    private Connection connection;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private NotiImage noti;
    public PatientImageConVM (int i, ViewPatientVM viewVM, ViewPatient view){
        this.id =i;
        dateTake = new SimpleStringProperty();
        noti = new NotiImage(view);
    }


    public StringProperty dateTakeProperty() {
        return dateTake;
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
                dateTake.set(rs.getString("dateTake"));
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
        noti.notifyImage();
    }

    public void view() {
    }
}
