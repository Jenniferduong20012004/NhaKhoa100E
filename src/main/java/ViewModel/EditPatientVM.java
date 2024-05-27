package ViewModel;

import Entity.Patient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.example.learn.EditPatient;

import java.sql.Connection;
import java.sql.ResultSet;

public class EditPatientVM {
    private StringProperty name, address, contactNumber, saveResponse;
    private BooleanProperty saveButtonDisabled;
    private Connection connection;
    private ResultSet rs = null;

    private Patient patient;
    public EditPatientVM(){
        name = new SimpleStringProperty();
        address = new SimpleStringProperty();
        contactNumber = new SimpleStringProperty();
        saveResponse = new SimpleStringProperty();
        saveButtonDisabled = new SimpleBooleanProperty();
        name.addListener((observableValue, oldValue, newValue)->onSavingChange());
        address.addListener((observableValue, oldValue, newValue)->onSavingChange());
        contactNumber.addListener((observableValue, oldValue, newValue)->onSavingChange());
    }
    public BooleanProperty saveButtonDisabledProperty() {
        return saveButtonDisabled;
    }

    public StringProperty nameProperty() {
        return name;
    }

    private void onSavingChange() {
        boolean disable = name.get()== null || name.get().isEmpty() ||address.get()== null || address.get().isEmpty() || contactNumber.get()== null || contactNumber.get().isEmpty();
        saveButtonDisabled.set(disable);
    }

    public String getAddress() {
        return address.get();
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void editWithPatientInformation(Patient c) {
        this.patient = c;
        name.set(c.getName());
        address.set(c.getAddress());
        contactNumber.set(c.getContactNumber());
    }
}
