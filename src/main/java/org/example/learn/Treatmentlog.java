package org.example.learn;

import Entity.Patient;
import Entity.Treatment;
import ViewModel.TreatmentLogVM;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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
                            FXMLLoader editloader = new FXMLLoader(getClass().getResource("EditButton.fxml"));
                            final Button btn;
                            final Button editButton;
                            try {
                                btn = loader.load();
                                editButton = editloader.load();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            btn.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-alignment: BASELINE_CENTER;" +
                                            "-fx-background-color: transparent"
                            );
                            editButton.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-alignment: BASELINE_CENTER;" +
                                            "-fx-background-color: transparent"
                            );
                            editButton.setOnAction((ActionEvent event) -> {
                                Treatment c = getTableView().getItems().get(getIndex());
                                editTreat(c);
                            });
                            btn.setOnAction((ActionEvent event) -> {
                                Treatment c = getTableView().getItems().get(getIndex());
                                treatmentLogVm.removeRecord(c);
                                treatmentTable.setItems(treatmentLogVm.getTreatmentList());
                            });
                            HBox managebtn = new HBox(btn, editButton);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(btn, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editButton, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
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

    private void editTreat(Treatment c) {
    }
}
