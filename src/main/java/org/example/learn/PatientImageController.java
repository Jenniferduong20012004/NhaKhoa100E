package org.example.learn;

import ViewModel.PatientImageConVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.*;

public class PatientImageController {
    @FXML
    private TextField txt_pName;
    private PatientImageConVM viewVM;
    @FXML
    private ImageView imgProd;

    @FXML
    private AnchorPane productInfoPane;
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        viewVM.removeImage();
    }

    @FXML
    public void onViewButtonClicked(ActionEvent event) {
    }

    public void initImageInfo(int imageId, ViewPatientVM vm) throws SQLException {
        viewVM = new PatientImageConVM(imageId, vm);
        txt_pName.textProperty().bindBidirectional(viewVM.dateTakeProperty());
        viewVM.loadDataFromImage(imgProd);
    }

}
