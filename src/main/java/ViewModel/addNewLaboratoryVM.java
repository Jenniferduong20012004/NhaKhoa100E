package ViewModel;

import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addNewLaboratoryVM {
    private Connection connection;
    private StringProperty name, address, contactNumber, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    public addNewLaboratoryVM(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        address.addListener((observableValue, oldValue, newValue)->onSavingChange());
        contactNumber.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().equals("")||address.get()== null ||address.get().equals("")|| contactNumber.get()== null || contactNumber.get().equals("");
        saveButtonDisabled.set(disable);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public String getSaveResponse() {
        return saveResponse.get();
    }

    public StringProperty saveResponseProperty() {
        return saveResponse;
    }

    public boolean isSaveButtonDisabled() {
        return saveButtonDisabled.get();
    }

    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }
    public void clear(){
        name.set("");
        address.set("");
        contactNumber.set("");
        saveResponse.set("");
    }

    public void saveLaboratory (){
        String sql = "Insert into Laboratory (laboName, contactNumber, addressLaboratory) Values (?,?,?)";
            try{
                connection = JDBConnection.NhaKhoa100eConnect();
                pst = connection.prepareStatement(sql);
                pst.setString(1, name.get());
                pst.setString(2, contactNumber.get());
                pst.setString(3, address.get());
                int i =pst.executeUpdate();
                if (i==1){
                    JOptionPane.showMessageDialog(null, "Save data successfully");
                }
                pst.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

}
