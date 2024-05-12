package org.example.learn;

import ViewModel.Patient;
import ViewModel.PatientPageVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PatientPageControl {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField search;
    private FXMLLoader fxmlLoader;
    @FXML
    private TableView<Patient> patients;
    @FXML
    private TableColumn<Patient,Integer> id;
    @FXML
    private TableColumn<Patient,String> PatientName;
    @FXML
    private TableColumn<Patient,String> PatientContactNumber;
    @FXML
    private TableColumn<Patient,String> AddressPatient;
    @FXML
    private TableColumn<Patient, String> DateColumn;
    private PatientPageVM patientpagevm;
    ObservableList<Patient> list = FXCollections.observableArrayList();
    private ViewHandler viewHandler;
    public void init (PatientPageVM patientpagevm, ViewHandler viewHandler){
        this.patientpagevm = patientpagevm;
        this.viewHandler = viewHandler;
        patientpagevm.init();
        list = patientpagevm.loadDataFromDatabase();
        patients.setItems(list);
        setCellTable();
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        viewHandler.openPatient();
    }
    @FXML

    private void switchToAddPatient(ActionEvent event) {
        viewHandler.openAddPatient();
    }

    @FXML
    private void setCellTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        PatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PatientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        AddressPatient.setCellValueFactory(new PropertyValueFactory<>("address"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));//tên trong patient class
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
                else if (list.getDateOfBirth().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<Patient> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(patients.comparatorProperty());
        patients.setItems(sortedData);
        }
    @FXML
    public void update(ActionEvent event) {
        Patient patient = patients.getSelectionModel().getSelectedItem();
        try {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addNewPatient.fxml"));
            addNewPatient addnewpatient = fxmlLoader.getController();
            patientpagevm.update(patient.getId(), addnewpatient.getAddNewpatientvm());
            addnewpatient.setTextField(patient.getName(), patient.getContactNumber(), patient.getAddress(), patient.getDateOfBirth());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    public void delete(ActionEvent event) {
    }

}
