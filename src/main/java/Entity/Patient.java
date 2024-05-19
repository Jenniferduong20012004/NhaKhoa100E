package Entity;


import javafx.scene.control.Button;

public class Patient {
    private int id;
    private String name;


    private String address;
    private String contactNumber;
    private String dateOfBirth;
    private Button removeButton;
    private Button addButton;

    public Patient (int id, String name, String contactNumber, String address, String dateOfBirth){
        this.id = id;
        this.name = name;
        this.contactNumber= contactNumber;
        this.address = address;
        this.dateOfBirth=dateOfBirth;
        removeButton = new Button();
        addButton = new Button();
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
