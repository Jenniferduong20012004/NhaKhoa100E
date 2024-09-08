package org.example.learn;

import ViewModel.EditPatientVM;
import ViewModel.EditTreatmentVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EditTreatment {
    private ViewHandler viewHandler;
    @FXML
    private Label DescriptionLabel;

    @FXML
    private Label LaboratoryLabelCheck;

    @FXML
    private Label amountlabel;

    @FXML
    private Label criterialabel;

    @FXML
    private Label labolabel;

    @FXML
    private RadioButton radioButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button saveButton1;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField4;

    @FXML
    private TextField textField5;
    @FXML
    private DatePicker datePicler;
    private EditTreatmentVM editTreatmentVM;


    @FXML
    private void Cancel(ActionEvent event) {

    }

    @FXML
    private void clickYes(ActionEvent event) {
        if (!editTreatmentVM.isClicked()){
            editTreatmentVM.setClicked(true);
            textField3.setVisible(true);
            textField4.setVisible(true);
            textField5.setVisible(true);
            amountlabel.setVisible(true);
            criterialabel.setVisible(true);
            labolabel.setVisible(true);
        }
        else{
            editTreatmentVM.setClicked(false);
            textField3.setVisible(false);
            textField4.setVisible(false);
            textField5.setVisible(false);
            amountlabel.setVisible(false);
            criterialabel.setVisible(false);
            labolabel.setVisible(false);
        }
    }

    @FXML
    private void collectInformation(ActionEvent event) {

    }
    public void init(EditTreatmentVM editPVM, ViewHandler viewHandler) {
        this.editTreatmentVM = editPVM;
        this.viewHandler=viewHandler;
        textField1.textProperty().bindBidirectional(editTreatmentVM.nameProperty());
        textArea.textProperty().bindBidirectional(editTreatmentVM.descriptionProperty());
        datePicler.valueProperty().bindBidirectional(editTreatmentVM.dateProperty());
    }
}
