package org.example.learn;

import ViewModel.ViewPatientVM;
import ViewModel.addNewImageVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class ViewPatient {
    private ViewHandler viewHandler;
    private ViewPatientVM viewVM;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label labelName;


    public void init(ViewPatientVM viewVM, ViewHandler viewHandler) {
        this.viewVM = viewVM;
        this.viewHandler = viewHandler;
        labelName.textProperty().bindBidirectional(viewVM.nameProperty());
        AnchorPane anchorPane = viewVM.getAnchorPane();
        scrollPane.setContent(anchorPane);
        viewVM.showPicture(anchorPane);
    }
    @FXML
    private void switchToDashboard(ActionEvent event) {
        viewHandler.openAddPatient();
    }
    @FXML
    private void switchToPatient(ActionEvent event) {
        viewHandler.openPatient();
    }
    @FXML
    private void switchToEquipment(ActionEvent event) {
        viewHandler.openEquipment();
    }
    @FXML
    private void switchToTreatment(ActionEvent event) {
        viewHandler.openTreatmentlog();
    }
}
