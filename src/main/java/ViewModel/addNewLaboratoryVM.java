package ViewModel;

import SQL.JDBConnection;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;
import java.sql.*;

public class addNewLaboratoryVM {
    private Connection connection;
    private StringProperty name, address, contactNumber, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private PreparedStatement pst = null;
    private CallableStatement call = null;
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
        boolean disable = name.get()== null || name.get().isEmpty() ||address.get()== null || address.get().isEmpty() || contactNumber.get()== null || contactNumber.get().isEmpty();
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

    public void saveLaboratory () throws SQLException {
            try{
                connection = JDBConnection.NhaKhoa100eConnect();
                call = connection.prepareCall("{call insertLabo(?,?,?)}");
                call.setString(1, name.get());
                call.setString(2, address.get());
                call.setString(3,  contactNumber.get());
                call.execute();
                JOptionPane.showMessageDialog(null, "Save data successfully");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                connection.close();
                call.close();
            }
        }

}
