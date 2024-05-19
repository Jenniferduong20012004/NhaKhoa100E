package org.example.learn;

import Entity.Patient;
import Entity.Treatment;
import ViewModel.TreatmentLogVM;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Date;

public class Treatmentlog {
    @FXML
    private TextField search;
    @FXML
    private TableView<Treatment> treatmentTable;
    @FXML
    private TableColumn<Treatment, String > DescriptionColumn;
    @FXML
    private TableColumn<Treatment, String> NameColumn;
    @FXML
    private TableColumn<Treatment, Date> DateColumn;
    @FXML
    private TableColumn<Treatment, String> Action;
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
        viewHandler.openAddPatient();
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
        Callback<TableColumn<Treatment, String>, TableCell<Treatment, String>> cellFactory = new Callback<TableColumn<Treatment, String>, TableCell<Treatment, String>>() {
            @Override
            public TableCell<Treatment, String> call(final TableColumn<Treatment, String> param) {
                final TableCell<Treatment, String> cell = new TableCell<Treatment, String>() {
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
                                Treatment c = getTableView().getItems().get(getIndex());
                                treatmentLogVm.removeRecord(c);
                                treatmentTable.setItems(treatmentLogVm.getTreatmentList());
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
