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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.sql.CallableStatement;

public class PatientPageControl {
    @FXML
    private TextField search;
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
    private TableColumn<Patient, String> Action;
    private PatientPageVM patientpagevm;
    private ViewHandler viewHandler;
    private CallableStatement call = null;
    public void init (PatientPageVM patientpagevm, ViewHandler viewHandler){
        this.patientpagevm = patientpagevm;
        this.viewHandler = viewHandler;
        patientpagevm.init();
        patientpagevm.loadDataFromDatabase();
        patients.setItems(patientpagevm.getList());
        setCellTable();
    }

    @FXML
    private void setCellTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        PatientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PatientContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        AddressPatient.setCellValueFactory(new PropertyValueFactory<>("address"));//tên trong patient class
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
                            FXMLLoader addloader = new FXMLLoader(getClass().getResource("AddButton.fxml"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("button.fxml"));
                            FXMLLoader viewloader = new FXMLLoader(getClass().getResource("ViewButton.fxml"));
                            FXMLLoader editloader = new FXMLLoader(getClass().getResource("EditButton.fxml"));
                            final Button btn;
                            final Button add;
                            final Button view;
                            final Button edit;
                            try {
                                btn = loader.load();
                                add = addloader.load();
                                view = viewloader.load();
                                edit = editloader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            add.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            +"-fx-background-color: transparent"
                            );
                            btn.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                        + "-fx-background-color: transparent"
                            );
                            view.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-background-color: transparent"
                            );
                            edit.setStyle( " -fx-cursor: hand ;"
                                    + "-glyph-size:28px;"
                                    + "-fx-background-color: transparent");
                            add.setOnAction((ActionEvent event) -> {
                                Patient c = getTableView().getItems().get(getIndex());
                                addTreatment(c);
                            });
                            btn.setOnAction((ActionEvent event) -> {
                                Patient c = getTableView().getItems().get(getIndex());
                                patientpagevm.removeRecord(c);
                                patients.setItems(patientpagevm.getList());
                            });
                            view.setOnAction((ActionEvent event) -> {
                                Patient c = getTableView().getItems().get(getIndex());
                                view(c);
                            });
                            edit.setOnAction((ActionEvent) ->{
                                Patient c = getTableView().getItems().get(getIndex());
                                editPatient(c);
                            });
                            HBox managebtn = new HBox(add, btn, view, edit);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(add, new Insets(2, 2, 0, 3));
                            HBox.setMargin(btn, new Insets(2, 3, 0, 2));
                            HBox.setMargin(view, new Insets(2, 4, 0, 2));
                            HBox.setMargin(edit, new Insets(2, 5, 0, 3));
                            setGraphic(managebtn);
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
                else{
                    return false;
                }
            });
        });
        SortedList<Patient> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(patients.comparatorProperty());
        patients.setItems(sortedData);
        }

    private void editPatient(Patient c) {
        patientpagevm.edit(c);
        viewHandler.openEditPatient();
    }

    private void view(Patient c) {
        patientpagevm.view(c);
        viewHandler.openBaseViewPatient();
    }

    private void addTreatment(Patient c) {
        patientpagevm.addTreatment(c);
        viewHandler.openBaseAddTreatmentImage();
    }

}
