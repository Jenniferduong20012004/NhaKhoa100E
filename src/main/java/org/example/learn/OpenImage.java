package org.example.learn;

import ViewModel.OpenImageVM;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class OpenImage {
    @FXML
    private ImageView image;

    private ViewHandler viewHandler;
    private OpenImageVM openVM;
    public void init(OpenImageVM openVM, ViewHandler viewHandler) {
        this.openVM = openVM;
        this.viewHandler = viewHandler;
    }

}
