package org.example.learn;

import Entity.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private Stage mainStage;
    private ViewModelFactory vmf;


    public ViewHandler(ViewModelFactory vmf){
        mainStage = new Stage();
        this.vmf = vmf;
    }
    public void start(){
        openPatient();
        mainStage.show();
    }

    public void openTreatmentlog() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TreatmentLog.fxml"));
        try{
            Parent root = (Parent) loader.load();
            Treatmentlog treatmentlog =  loader.getController();
            treatmentlog.init(vmf.getTreatmentLogVM(), this);
            Scene treatmentLogScnene = new Scene(root);
            mainStage.setScene(treatmentLogScnene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openPatient(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PatientLog.fxml"));
        try{
            Parent root = (Parent) loader.load();
            PatientPageControl patientPageControl =  loader.getController();
            patientPageControl.init(vmf.getPatientPageVM(), this);
            Scene patient = new Scene(root);
            mainStage.setScene(patient);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openEquipment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("equipment.fxml"));
        try{
            Parent root = (Parent) loader.load();
            Equipment equipment =  loader.getController();
            equipment.init(vmf.getEquipmentVM(), this);
            Scene equipmentScene = new Scene(root);
            mainStage.setScene(equipmentScene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openAddTreatment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewTreatment.fxml"));
        try{
            Parent root = (Parent) loader.load();
            AddTreatmentLog addTreatmentLog =  loader.getController();
            addTreatmentLog.init(vmf.getAddNewTreatmentVM(), this);
            Scene addTreatmentScene = new Scene(root);
            mainStage.setScene(addTreatmentScene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openAddEquipment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addLaboratory.fxml"));
        try{
            Parent root = (Parent) loader.load();
            addNewLaboratory addTreatmentLog =  loader.getController();
            addTreatmentLog.init(vmf.getAddNewLaboratoryVM(), this);
            Scene addLaboratory = new Scene(root);
            mainStage.setScene(addLaboratory);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openAddPatient() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewPatient.fxml"));
        try{
            Parent root = (Parent) loader.load();
            addNewPatient addNewPatient =  loader.getController();
            addNewPatient.init(vmf.getAddNewPatientVM(), this);
            Scene addNewPatientScene = new Scene(root);
            mainStage.setScene(addNewPatientScene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openAddImage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addImage.fxml"));
        try{
            Parent root = (Parent) loader.load();
            addNewImage addNewImage=  loader.getController();
            addNewImage.init(vmf.getAddNewImageVM(), this);
            Scene addImage = new Scene(root);
            mainStage.setScene(addImage);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void openViewPatient(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewPatient.fxml"));
        try{
            Parent root = (Parent) loader.load();
            ViewPatient view=  loader.getController();
            view.init(vmf.getViewPatientVM(), this);
            Scene viewScene = new Scene(root);
            mainStage.setScene(viewScene);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
