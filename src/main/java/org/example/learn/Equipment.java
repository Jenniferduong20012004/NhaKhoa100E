package org.example.learn;

import Entity.LaboratoryUse;
import ViewModel.LaboratoryUseVM;
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

public class Equipment {
    private ViewHandler viewHandler;
    @FXML
    private TableView<LaboratoryUse> laboTable;
    @FXML
    private TableColumn<LaboratoryUse,String> laboratoryNameColumn;
    @FXML
    private TableColumn<LaboratoryUse,String> PatientName;
    @FXML
    private TableColumn<LaboratoryUse,String> CriteriaColumn;
    @FXML
    private TableColumn<LaboratoryUse, Integer> QuantityColumn;
    @FXML
    private TableColumn<LaboratoryUse,String> DateLaboColumn;
    @FXML
    private TableColumn<LaboratoryUse, String> Action;
    @FXML
    private TextField search;
    private LaboratoryUseVM laboVM;
     @FXML
    private void switchToPatient(ActionEvent event) {
        viewHandler.openPatient();
    }
    @FXML
    private void switchToTreatment(ActionEvent event){
        viewHandler.openTreatmentlog();
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        viewHandler.openAddEquipment();
    }
    @FXML
    private void switchToAddLaboratory(ActionEvent event) {
        viewHandler.openAddEquipment();
    }


    private void setCellTable() {
        laboratoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("Labname"));
        PatientName.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        CriteriaColumn.setCellValueFactory(new PropertyValueFactory<>("criteria"));//tên trong treatment class
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        DateLaboColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        Callback<TableColumn<LaboratoryUse, String>, TableCell<LaboratoryUse, String>> cellFactory = new Callback<TableColumn<LaboratoryUse, String>, TableCell<LaboratoryUse, String>>() {
            @Override
            public TableCell<LaboratoryUse, String> call(final TableColumn<LaboratoryUse, String> param) {
                final TableCell<LaboratoryUse, String> cell = new TableCell<LaboratoryUse, String>() {
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
                                LaboratoryUse c = getTableView().getItems().get(getIndex());
                                laboVM.removeRecord(c);
                                laboTable.setItems(laboVM.getLaboUseList());
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
        FilteredList<LaboratoryUse> filterLabo = new FilteredList<>(laboVM.getLaboUseList(), b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) ->{
            filterLabo.setPredicate(laboUseList ->{
                //no search value
                if (newValue.isEmpty() || newValue.isBlank() || newValue ==null){
                    return true;
                }
                String searchKeyWord=newValue.trim().toLowerCase();
                if (laboUseList.getLabname().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true; //  found match in product name
                }
                else if (laboUseList.getPatientName().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else if (laboUseList.getDate().toLowerCase().indexOf(searchKeyWord)>-1){
                    return true;
                }
                else{
                    return false;
                }
            });
        });
        SortedList<LaboratoryUse> sortedData = new SortedList<>(filterLabo);
        sortedData.comparatorProperty().bind(laboTable.comparatorProperty());
        laboTable.setItems(sortedData);
    }

    public void init(LaboratoryUseVM equipmentVM, ViewHandler viewHandler) {
         this.laboVM = equipmentVM;
         this.viewHandler = viewHandler;
        laboVM.init();
        laboTable.setItems(laboVM.getLaboUseList());
        setCellTable();
    }
}
