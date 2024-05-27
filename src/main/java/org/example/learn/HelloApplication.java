package org.example.learn;

import javafx.application.Application;
import javafx.stage.Stage;
import util.ViewModelFactory;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewModelFactory vmf = new ViewModelFactory();
        ViewHandler viewHandler = new ViewHandler(vmf);
        viewHandler.start();
    }

    public static void main(String[] args) {
        launch();
    }
}