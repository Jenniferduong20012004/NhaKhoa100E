package org.example.learn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon.*;

public class PatientPageControl implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField search;
    private FXMLLoader fxmlLoader;
    @FXML
    private TableView<Patient> patients;
    @FXML
    private TableColumn<Patient,String> PatientName;
    @FXML
    private TableColumn<Patient,String> PatientContactNumber;
    @FXML
    private TableColumn<Patient,String> AddressPatient;
    @FXML
    private TableColumn<Patient, String> DateColumn;
    @FXML
    private TableColumn<Patient, String> MoneyPayColumn;
    @FXML
    private TableColumn<Patient, String>  Action;
    Patient patient = null;

    ObservableList<Patient> list = FXCollections.observableArrayList();
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private void switchToEquipment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("equipment.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TreatmentLog.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewPatient.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML

    private void switchToAddPatient(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewPatient.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = JDBConnection.NhaKhoa100eConnect();
        loadDataFromDatabase();
    }
    @FXML
    private void setCellTable(){
        PatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PatientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        AddressPatient.setCellValueFactory(new PropertyValueFactory<>("address"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        MoneyPayColumn.setCellValueFactory(new PropertyValueFactory<>("description"));//tên trong patient class
        Callback<TableColumn<Patient,String>, TableCell<Patient, String>> cellFactory =(TableColumn<Patient, String> param) ->{
            final TableCell <Patient, String>cell = new TableCell<Patient,String>(){
                //overide update item method
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item,empty);
                    //ensure the cell is created only on non-empty rows
                    if (empty){
                        setGraphic(null);
                        setText(null);

                    }
                    else{
                        final Button editButton = new Button ("Edit");
                        editButton.setStyle(
                                "-fx-background-color: #489E98;"+
                                        "-fx-border-radius: 8px 8px 8px 8px;"+
                                        "-fx-font-family: 'Montserrat';"+
                                        "-fx-background-radius: 20;"+
                                        "-fx-text-fill: white;"+

                        );
                        final Button deleteButton = new Button ("Delete");
                        deleteButton.setStyle(
                                "-fx-background-color: #ff1744;"+
                                        "-fx-border-radius: 8px 8px 8px 8px;"+
                                        "-fx-font-family: 'Montserrat';"+
                                        "-fx-background-radius: 20;"+
                                        "-fx-text-fill: white;"
                        );
                        editButton.setOnAction(event->{
                            patient = patients.getSelectionModel().getSelectedItem();
                            FXMLLoader load = new FXMLLoader();
                            load.setLocation(getClass().getResource("addNewPatient.fxml"));
                            try {
                                load.load();
                            }
                            catch (IOException ex){
                                Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            addNewPatient addnewpatient = load.getController();
                            addnewpatient.setUpdate(true);
                        });
                        HBox managebtn = new HBox(editButton, deleteButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editButton, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                };
            };
            return cell;
        };
        FilteredList<Patient> filter = new FilteredList<>(list, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) ->{
            filter.setPredicate(list ->{
                //no search value
                if (newValue.isEmpty() || newValue.isBlank() || newValue ==null){
                    return true;
                }
                String searchKeyWord=newValue.trim().toLowerCase();
                if (list.getName().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true; //  found match in product name
                }
                else if (list.getContactNumber().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else if (list.getDate().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<Patient> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(patients.comparatorProperty());
        Action.setCellFactory(cellFactory);
        patients.setItems(sortedData);
        }
    private void loadDataFromDatabase(){
        try {
            list.clear();
            pst = connection.prepareStatement("Select Treatment.detail, newTable.namePatient, newTable.contactNumber, newTable.addressPatient, newTable.dateLast from (Select Patient.namePatient, Patient.patient_id, Patient.contactNumber, Patient.addressPatient, Max(dateCome) as dateLast from Treatment, Patient where Treatment.patient_id = Patient.patient_id group by Patient.patient_id, Patient.contactNumber, Patient.addressPatient,Patient.namePatient) as newTable left join Treatment on Treatment.dateCome = newTable.dateLast and Treatment.patient_id=newTable.patient_id");
            rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Patient(rs.getString("namePatient"), //tên cột trong sql
                        rs.getString("contactNumber"),
                        rs.getString("addressPatient"),
                       rs.getString("detail"),
                       "" +rs.getDate("dateLast")));
                patients.setItems(list);
            }
            setCellTable();

        } catch (SQLException e) {
            Logger.getLogger(PatientPageControl.class.getName()).log(Level.SEVERE, null, e);

        }
    }
}
