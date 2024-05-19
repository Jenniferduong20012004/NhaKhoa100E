package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;

public class addNewImageVM {
    private Connection connection;
    private ResultSet rs = null;

    private PreparedStatement pst = null;
    private CallableStatement call = null;
    private Patient patient;
    private StringProperty name, id, image1;
    private byte[] imageData;
    private Date dateToday;

    public String getName() {
        return name.get();
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty image1Property() {
        return image1;
    }

    public addNewImageVM (){
        name = new SimpleStringProperty();
        id = new SimpleStringProperty();
        image1 = new SimpleStringProperty();
    }
    public void clear() {
        patient = null;
        name.set("");
        id.set("");
        image1.set("");
    }

    public void chooseFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        try{
            if (file != null) {
                String filePath = file.getAbsolutePath();
                image1.set(filePath);
                byte[] imageData = readImageData(filePath);
                setImageData(imageData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    private byte[] readImageData(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addImageWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
    }

    public void getInformation() {
        LocalDate localDate = LocalDate.now();
        dateToday = java.sql.Date.valueOf(localDate);
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call insertImage(?,?,?)}");
            call.setInt(1, patient.getId());
            call.setDate(2, dateToday);
            call.setBytes(3, getImageData());
            call.execute();
            rs = call.getResultSet();
            JOptionPane.showMessageDialog(null, "Save data successfully");
            clear();
            call.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getImageData() {
        return imageData;
    }
}
