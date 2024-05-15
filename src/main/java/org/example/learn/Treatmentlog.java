package org.example.learn;

import Entity.Patient;
import Entity.Treatment;
import ViewModel.TreatmentLogVM;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class Treatmentlog {
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
    private TreatmentLogVM treatmentLogVm;
    private ViewHandler viewHandler;
    @FXML
    private void switchToEquipment(ActionEvent event){
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToAddTreatment(ActionEvent event){
        viewHandler.openAddTreatment();
    }
    @FXML
    private void switchToDashboard(ActionEvent event){
        viewHandler.openAddTreatment();
    }
    @FXML
    private void switchToPatient(ActionEvent event){
        viewHandler.openPatient();
    }

    public void init(TreatmentLogVM treatmentLogVm, ViewHandler viewHandler){
        this.treatmentLogVm = treatmentLogVm;
        this.viewHandler = viewHandler;
        treatmentLogVm.init();
        treatmentTable.setItems(treatmentLogVm.getTreatmentList());
        setCellTable();
    }



    private void setCellTable() {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));//tên trong treatment class
        FilteredList<Treatment> filter = new FilteredList<>(treatmentLogVm.getTreatmentList(), b -> true);
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
