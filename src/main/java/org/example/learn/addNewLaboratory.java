package org.example.learn;

import ViewModel.addNewLaboratoryVM;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;

public class addNewLaboratory {
    @FXML
    private TextField textField;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField1;
    @FXML
    private Button saveButton;
    private ViewHandler viewHandler;
    private addNewLaboratoryVM addnewlaboratoryvm;
    @FXML
    private void saveLaboratory (ActionEvent event){
        addnewlaboratoryvm.clear();
        addnewlaboratoryvm.saveLaboratory();
    }
    @FXML
    private void Cancel (ActionEvent event){addnewlaboratoryvm.clear();
    }

    public void init(addNewLaboratoryVM addNewLaboratoryVM, ViewHandler viewHandler) {
        this.addnewlaboratoryvm = addNewLaboratoryVM;
        this.viewHandler=viewHandler;
        textField.textProperty().bindBidirectional(addnewlaboratoryvm.nameProperty());
        textField1.textProperty().bindBidirectional(addnewlaboratoryvm.contactNumberProperty());
        textField2.textProperty().bindBidirectional(addnewlaboratoryvm.addressProperty());
        saveButton.disableProperty().bind(addnewlaboratoryvm.saveButtonDisabledProperty());
        addnewlaboratoryvm.saveResponseProperty().addListener((observableValue, oldValue, newValue) -> onSavingResult(newValue));
    }

    private void onSavingResult(String newValue) {
        if("OK".equals(newValue)) {
            JOptionPane.showMessageDialog(null, "Save data successfully");
        }
    }
}
