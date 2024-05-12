package org.example.learn;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*try {
            Parent parent = FXMLLoader.load(getClass().getResource("addNewTreatment.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);

            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        ViewModelFactory vmf = new ViewModelFactory();
        ViewHandler viewHandler = new ViewHandler(vmf);
        viewHandler.start();
    }

    public static void main(String[] args) {
        launch();
    }
}