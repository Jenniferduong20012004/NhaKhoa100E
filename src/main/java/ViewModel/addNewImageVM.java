package ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import java.io.*;

public class addNewImageVM {
    private StringProperty name, id;
    private byte[] imageData;

    public String getName() {
        return name.get();
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public addNewImageVM (){
        name = new SimpleStringProperty();
        id = new SimpleStringProperty();
    }
    public void clear() {
        name.set("");
        id.set("");
    }

    public void chooseFile() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        try{
            if (file != null) {
                String filePath = file.getAbsolutePath();
                byte[] imageData = readImageData(filePath);
                setImageData(imageData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    private byte[] readImageData(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
