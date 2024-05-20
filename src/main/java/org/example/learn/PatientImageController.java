package org.example.learn;

import ViewModel.PatientImageConVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientImageController {
    private PatientImageConVM viewVM;
    @FXML
    private ImageView imgProd;

    @FXML
    private AnchorPane productInfoPane;
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
    }

    @FXML
    public void onViewButtonClicked(ActionEvent event) {
    }

    public void initImageInfo(int imageId) throws SQLException {
        viewVM = new PatientImageConVM(imageId);
        viewVM.loadDataFromImage(imgProd);
    }

}
