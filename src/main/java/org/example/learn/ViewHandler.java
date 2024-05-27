package org.example.learn;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class ViewHandler {
    private Stage mainStage;
    private ViewModelFactory vmf;


    public ViewHandler(ViewModelFactory vmf){
        mainStage = new Stage();
        this.vmf = vmf;
    }
    public void start(){
        openBase();
        mainStage.show();
    }
    public void openBase(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Base.fxml"));
        try{
            Parent root = (Parent) loader.load();
            Base treatmentlog =  loader.getController();
            treatmentlog.init(vmf.getBase(), this);
            Scene treatmentLogScnene = new Scene(root);
            mainStage.setScene(treatmentLogScnene);
            mainStage.setTitle("Nha Khoa 100E");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public AnchorPane openTreatmentlog() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TreatmentLog.fxml"));
        try{
            AnchorPane newContent = loader.load();
            Treatmentlog treatmentlog =  loader.getController();
            treatmentlog.init(vmf.getTreatmentLogVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openBaseAdd(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BaseAdd.fxml"));
        try{
            AnchorPane newContent = loader.load();
            BaseAdd addNewPatient =  loader.getController();
            addNewPatient.init(this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openPatient(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PatientLog.fxml"));
        try{
            AnchorPane newContent = loader.load();
            PatientPageControl addNewPatient =  loader.getController();
            addNewPatient.init(vmf.getPatientPageVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openEquipment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("equipment.fxml"));
        try{
            AnchorPane newContent = loader.load();
            Equipment equipment =  loader.getController();
            equipment.init(vmf.getEquipmentVM(), this);
            return  newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openAddTreatment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewTreatment.fxml"));
        try{
            AnchorPane newContent = loader.load();
            AddTreatmentLog addTreatmentLog =  loader.getController();
            addTreatmentLog.init(vmf.getAddNewTreatmentVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openAddEquipment(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addLaboratory.fxml"));
        try{
            AnchorPane newContent = loader.load();
            addNewLaboratory addTreatmentLog =  loader.getController();
            addTreatmentLog.init(vmf.getAddNewLaboratoryVM(), this);
            return  newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public AnchorPane openAddPatient() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addNewPatient.fxml"));
        try{
            AnchorPane newContent = loader.load();
            addNewPatient addNewPatient =  loader.getController();
            addNewPatient.init(vmf.getAddNewPatientVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public void openBaseViewPatient() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BaseView.fxml"));
        try {
            Parent root = loader.load();
            BaseView base = loader.getController();
            base.init(this);
            Stage stage = new Stage();
            stage.setTitle("View Patient");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void openBaseAddTreatmentImage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BaseAddTreatmentImage.fxml"));
        try {
            Parent root = loader.load();
            BaseAddTreatmentImage base = loader.getController();
            base.init(this);
            Stage stage = new Stage();
            stage.setTitle("Add Treatment");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AnchorPane openAddImage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addImage.fxml"));
        try{
            AnchorPane newContent = loader.load();
            addNewImage addNewImage=  loader.getController();
            addNewImage.init(vmf.getAddNewImageVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openViewPatient(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewPatient.fxml"));
        try{
            AnchorPane newContent = loader.load();
            ViewPatient view=  loader.getController();
            view.init(vmf.getViewPatientVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public AnchorPane openViewPatientDateCome(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewPatientDateCome.fxml"));
        try{
            AnchorPane newContent = loader.load();
            ViewPatientDateCome view=  loader.getController();
            view.init(vmf.getViewPatientDatecomeVM(), this);
            return newContent;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public void openOpenImage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OpenImage.fxml"));
        try {
            Parent root = loader.load();
            OpenImage base = loader.getController();
            base.init(vmf.getOpenImageVM(),this);
            Stage stage = new Stage();
            stage.setTitle("Add Treatment");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
