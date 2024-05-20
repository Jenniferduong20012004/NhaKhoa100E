package util;

import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.*;

public class HelpMethods {
    public static byte[] chooseFile(StringProperty image1) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        try{
            if (file != null) {
                String filePath = file.getAbsolutePath();
                image1.set(filePath);
                byte[] imageData = readImageData(filePath);
                return imageData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static byte[] readImageData(String filePath) {
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
