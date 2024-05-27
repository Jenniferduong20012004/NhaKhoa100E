package org.example.learn;

import ViewModel.PatientImageConVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.sql.SQLException;

public class PatientImageController {
    @FXML
    private TextField txt_pName;
    private PatientImageConVM viewVM;
    @FXML
    private ImageView imgProd;

    @FXML
    private AnchorPane productInfoPane;
    private ViewHandler viewHandler;
    @FXML
    private void onDeleteButtonClick(ActionEvent event) {
        viewVM.removeImage();
    }

    @FXML
    private void onViewButtonClicked(ActionEvent event) {
        viewVM.view();
        viewHandler.openOpenImage();

    }

    public void initImageInfo(int imageId, ViewPatientVM vm, ViewPatient view, ViewHandler viewHandler) throws SQLException {
        this.viewHandler = viewHandler;
        viewVM = new PatientImageConVM(imageId, vm, view);
        txt_pName.textProperty().bindBidirectional(viewVM.dateTakeProperty());
        viewVM.loadDataFromImage(imgProd);
    }

}
