package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.example.learn.PatientImageController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewPatientVM {
    private Connection connection = null;
    private ResultSet rs = null;
    private CallableStatement call = null;
    private Patient patient;
    private StringProperty name;
    public ViewPatientVM (){
        name = new SimpleStringProperty();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public AnchorPane getAnchorPane(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(0);
        anchorPane.setLayoutY(0);
        anchorPane.setPrefWidth(1200);
        anchorPane.setPrefHeight(getAnchorHeight());
        return anchorPane;
    }

    private double getAnchorHeight() {
        int countPur = 0;
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call getPatientImage(?)}");
            call.setInt(1,patient.getId());
            call.execute();
            rs = call.getResultSet();
            while (rs.next()) {
                countPur++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return (int) (Math.ceil((double) countPur / 4)) * 250;
    }

    public void viewImageWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
    }

    public void showPicture(AnchorPane anchorPane) {
        int width = 300;
        int height = 250;
        int xIndex = 0;
        int yIndex = 0;
        List<AnchorPane> productPaneList = new ArrayList<AnchorPane>();
        try{
            connection = JDBConnection.NhaKhoa100eConnect();
            call = connection.prepareCall("{call getPatientImage(?)}");
            call.setInt(1,patient.getId());
            call.execute();
            rs = call.getResultSet();
            while (rs.next()) {
                int imageId = rs.getInt("iId");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientImage.fxml"));
                AnchorPane pane = loader.load();
                productPaneList.add(pane);
                PatientImageController controller = loader.getController();
                controller.initImageInfo(imageId);
            }
        }
        catch (Exception e){

        }
    }
}
