package org.example.learn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Treatmentlog implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    @FXML
    private TextField search;
    @FXML
    private TableView<Treatment> treatmentTable;
    @FXML
    private TableColumn<Treatment, String > DescriptionColumn;
    @FXML
    private TableColumn<Patient, String> NameColumn;
    @FXML
    private TableColumn<Patient, Date> DateColumn;
    ObservableList<Treatment> treatmentList = FXCollections.observableArrayList();
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private void switchToEquipment(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("equipment.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToAddTreatment(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewTreatment.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToDashboard(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewTreatment.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToPatient(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PatientLog.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = JDBConnection.NhaKhoa100eConnect();
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        try {
            treatmentList.clear();
            pst = connection.prepareStatement("Select Treatment.dateCome, Patient.namePatient, Treatment.detail from Treatment, Patient where Treatment.patient_id = Patient.patient_id order by dateCome desc");
            rs = pst.executeQuery();
            while (rs.next()){
                treatmentList.add(new Treatment(rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("detail"),
                        "" +rs.getDate("dateCome")));
                treatmentTable.setItems(treatmentList);
            }
            setCellTable();

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void setCellTable() {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));//tên trong treatment class
        FilteredList<Treatment> filter = new FilteredList<>(treatmentList, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) ->{
            filter.setPredicate(treatmentList ->{
                //no search value
                if (newValue.isEmpty() || newValue.isBlank() || newValue ==null){
                    return true;
                }
                String searchKeyWord=newValue.trim().toLowerCase();
                if (treatmentList.getPatientName().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true; //  found match in product name
                }
                else if (treatmentList.getDescription().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else if (treatmentList.getDate().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<Treatment> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(treatmentTable.comparatorProperty());
        treatmentTable.setItems(sortedData);
    }
}
