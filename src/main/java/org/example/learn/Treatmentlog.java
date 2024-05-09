package org.example.learn;

import ViewModel.Patient;
import ViewModel.Treatment;
import ViewModel.TreatmentLogVM;
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
import java.util.Date;
import java.util.ResourceBundle;

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
    private TreatmentLogVM treatmentLogVm = new TreatmentLogVM();
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
