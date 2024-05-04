package org.example.learn;

public class Patient {
    private String name;


    private String address;
    private String contactNumber;
    private String date;
    private String description;

    public Patient (String name, String contactNumber, String address, String description, String dateLast){
        this.name = name;
        this.contactNumber= contactNumber;
        this.address = address;
        this.description = description;
        this.date = dateLast;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
