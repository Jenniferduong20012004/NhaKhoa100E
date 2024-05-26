package ViewModel;

import Entity.Patient;
import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
import util.HelpMethods;

import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Stack;

public class addNewImageVM {
    private Connection connection;
    private ResultSet rs = null;

    private PreparedStatement pst = null;
    private CallableStatement call = null;
    private Patient patient;
    private StringProperty name, id, image1, image2, image3, image4, image5, image6, image7;
    private Date dateToday;
    private BooleanProperty chooseFile2, chooseFile3, chooseFile4, chooseFile5, chooseFile6, chooseFile7;
    private Stack<byte[]> filePaths;

    public String getName() {
        return name.get();
    }

    public String getId() {
        return id.get();
    }


    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty image1Property() {
        return image1;
    }

    public StringProperty image7Property() {
        return image7;
    }

    public StringProperty image6Property() {
        return image6;
    }

    public StringProperty image5Property() {
        return image5;
    }

    public StringProperty image4Property() {
        return image4;
    }

    public BooleanProperty chooseFile6Property() {
        return chooseFile6;
    }

    public BooleanProperty chooseFile7Property() {
        return chooseFile7;
    }

    public BooleanProperty chooseFile2Property() {
        return chooseFile2;
    }

    public BooleanProperty chooseFile5Property() {
        return chooseFile5;
    }

    public StringProperty image2Property() {
        return image2;
    }


    public BooleanProperty chooseFile4Property() {
        return chooseFile4;
    }

    public BooleanProperty chooseFile3Property() {
        return chooseFile3;
    }

    public StringProperty image3Property() {
        return image3;
    }


    public addNewImageVM (){
        filePaths = new Stack<>();
        name = new SimpleStringProperty();
        id = new SimpleStringProperty();
        image1 = new SimpleStringProperty();
        image2 = new SimpleStringProperty();
        image3 = new SimpleStringProperty();
        image4 = new SimpleStringProperty();
        image5 = new SimpleStringProperty();
        image6 = new SimpleStringProperty();
        image7 = new SimpleStringProperty();
        chooseFile2 = new SimpleBooleanProperty(true);
        chooseFile3 = new SimpleBooleanProperty(true);
        chooseFile4 = new SimpleBooleanProperty(true);
        chooseFile5 = new SimpleBooleanProperty(true);
        chooseFile6 = new SimpleBooleanProperty(true);
        chooseFile7 = new SimpleBooleanProperty(true);
        image1.addListener((observableValue, oldValue, newValue) -> onAllowSecondFile());
        image2.addListener((observableValue, oldValue, newValue) -> onAllowThirdFile());
        image1.addListener((observableValue, oldValue, newValue) -> onAllowThirdFile());
        image1.addListener((observableValue, oldValue, newValue) -> onAllowFourthFile());
        image2.addListener((observableValue, oldValue, newValue) -> onAllowFourthFile());
        image3.addListener((observableValue, oldValue, newValue) -> onAllowFourthFile());
        image1.addListener((observableValue, oldValue, newValue) -> onAllowFifthFile());
        image2.addListener((observableValue, oldValue, newValue) -> onAllowFifthFile());
        image3.addListener((observableValue, oldValue, newValue) -> onAllowFifthFile());
        image4.addListener((observableValue, oldValue, newValue) -> onAllowFifthFile());
        image1.addListener((observableValue, oldValue, newValue) -> onAllowSixthFile());
        image2.addListener((observableValue, oldValue, newValue) -> onAllowSixthFile());
        image3.addListener((observableValue, oldValue, newValue) -> onAllowSixthFile());
        image4.addListener((observableValue, oldValue, newValue) -> onAllowSixthFile());
        image5.addListener((observableValue, oldValue, newValue) -> onAllowSixthFile());
        image1.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
        image2.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
        image3.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
        image4.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
        image5.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
        image6.addListener((observableValue, oldValue, newValue) -> onAllowSeventhFile());
    }

    private void onAllowSeventhFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ||image2.get() == null      || image2.get().isEmpty()||image3.get() == null      || image3.get().isEmpty()||image4.get() == null      || image4.get().isEmpty()||image5.get() == null      || image5.get().isEmpty()||image6.get() == null      || image6.get().isEmpty();
        chooseFile7.set(disabled);
    }

    private void onAllowSixthFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ||image2.get() == null      || image2.get().isEmpty()||image3.get() == null      || image3.get().isEmpty()||image4.get() == null      || image4.get().isEmpty()||image5.get() == null      || image5.get().isEmpty();
        chooseFile6.set(disabled);
    }

    private void onAllowFifthFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ||image2.get() == null      || image2.get().isEmpty()||image3.get() == null      || image3.get().isEmpty()||image4.get() == null      || image4.get().isEmpty();
        chooseFile5.set(disabled);
    }

    private void onAllowFourthFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ||image2.get() == null      || image2.get().isEmpty()||image3.get() == null      || image3.get().isEmpty();
        chooseFile4.set(disabled);
    }

    private void onAllowThirdFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ||image2.get() == null      || image2.get().isEmpty();
        chooseFile3.set(disabled);
    }

    private void onAllowSecondFile() {
        boolean disabled =  image1.get() == null      || image1.get().isEmpty() ;
        chooseFile2.set(disabled);
    }

    public void clear() {
        patient = null;
        name.set("");
        id.set("");
        image1.set("");
        image2.set("");
        image3.set("");
        image4.set("");
        image5.set("");
        image6.set("");
        image7.set("");
    }

    public void chooseFile() {
        filePaths.push(HelpMethods.chooseFile(image1));
    }
    public void chooseFile2() {
        filePaths.push(HelpMethods.chooseFile(image2));
    }
    public void chooseFile3() {
        filePaths.push(HelpMethods.chooseFile(image3));
    }
    public void chooseFile4() {
        filePaths.push(HelpMethods.chooseFile(image4));
    }
    public void chooseFile5() {
        filePaths.push(HelpMethods.chooseFile(image5));
    }
    public void chooseFile6() {
        filePaths.push(HelpMethods.chooseFile(image6));
    }
    public void chooseFile7() {
        filePaths.push(HelpMethods.chooseFile(image7));
    }

    public void addImageWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(patient.getName());
    }

    public void getInformation() {
        LocalDate localDate = LocalDate.now();
        dateToday = java.sql.Date.valueOf(localDate);
        while (!filePaths.isEmpty()) {
            try {
                connection = JDBConnection.NhaKhoa100eConnect();
                call = connection.prepareCall("{call insertImage(?,?,?)}");
                call.setInt(1, patient.getId());
                JOptionPane.showMessageDialog(null,filePaths.size());
                call.setDate(2, dateToday);
                call.setBytes(3, filePaths.pop());
                JOptionPane.showMessageDialog(null,filePaths.size());
                call.execute();
                rs = call.getResultSet();
                clear();
                call.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
