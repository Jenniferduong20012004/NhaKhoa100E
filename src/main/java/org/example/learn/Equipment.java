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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Equipment implements Initializable {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
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
    private TextField search;
    ObservableList<LaboratoryUse> laboUseList = FXCollections.observableArrayList();
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
     @FXML
    private void switchToPatient(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PatientLog.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToTreatment(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TreatmentLog.fxml"));
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
    private void switchToDashboard(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addLaboratory.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToAddLaboratory(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addLaboratory.fxml"));
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

    private void loadDataFromDatabase() {
        try {
            laboUseList.clear();
            pst = connection.prepareStatement("Select Patient.namePatient, labUse.dateCome, labUse.Criteria, labUse.Quantity, labUse. laboName from Patient, labUse where Patient.patient_id = labUse.patient_id");
            rs = pst.executeQuery();
            while (rs.next()){
                laboUseList.add(new LaboratoryUse(rs.getString("laboName"),//tên cột trong sql
                        rs.getString("namePatient"),
                        rs.getString("Criteria"),
                        ""+rs.getInt("Quantity"),
                        "" +rs.getDate("dateCome")));
                laboTable.setItems(laboUseList);
            }
            setCellTable();

        } catch (SQLException e) {
            Logger.getLogger(Equipment.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    private void setCellTable() {
        laboratoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("Labname"));
        PatientName.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        CriteriaColumn.setCellValueFactory(new PropertyValueFactory<>("criteria"));//tên trong treatment class
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        DateLaboColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        FilteredList<LaboratoryUse> filterLabo = new FilteredList<>(laboUseList, b -> true);
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

}
