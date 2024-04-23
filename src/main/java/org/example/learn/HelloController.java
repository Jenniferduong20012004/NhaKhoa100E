package org.example.learn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label myLabel;

    @FXML
    private TextArea textField1;
    @FXML
    private Button saveButton;
    String name;
    @FXML
    public void submit (ActionEvent event){
        name = textField1.getText();
        System.out.println (name);
    }

}