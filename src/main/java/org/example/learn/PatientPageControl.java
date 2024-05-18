package org.example.learn;

import Entity.Patient;
import Entity.Treatment;
import ViewModel.PatientPageVM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

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
    @FXML
    private TableColumn<Patient, String> Action;
    private PatientPageVM patientpagevm;
    private ViewHandler viewHandler;
    public void init (PatientPageVM patientpagevm, ViewHandler viewHandler){
        this.patientpagevm = patientpagevm;
        this.viewHandler = viewHandler;
        patientpagevm.init();
        patientpagevm.loadDataFromDatabase();
        patients.setItems(patientpagevm.getList());
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
        viewHandler.openAddPatient();
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
        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactory = new Callback<TableColumn<Patient, String>, TableCell<Patient, String>>() {
            @Override
            public TableCell<Patient, String> call(final TableColumn<Patient, String> param) {
                final TableCell<Patient, String> cell = new TableCell<Patient, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null); //empty rows do not get buttons
                            setText(null);
                        } else {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("button.fxml"));
                            final Button btn;
                            try {
                                btn = loader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            btn.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-alignment: BASELINE_CENTER;" +
                                            "-fx-background-color: transparent"
                            );
                            btn.setOnAction((ActionEvent event) -> {
                                Patient c = getTableView().getItems().get(getIndex());
                                patientpagevm.removeRecord(c);
                                patients.setItems(patientpagevm.getList());
                                setGraphic(btn);
                            });
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        Action.setCellFactory(cellFactory);
        FilteredList<Patient> filter = new FilteredList<>(patientpagevm.getList(), b -> true);
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

    }
    @FXML
    public void delete(ActionEvent event) {
    }

}
