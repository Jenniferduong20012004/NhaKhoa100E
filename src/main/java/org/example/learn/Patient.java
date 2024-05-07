package org.example.learn;

public class Patient {
    private String name;


    private String address;
    private String contactNumber;
    private String dateOfBirth;

    public Patient (String name, String contactNumber, String address, String dateOfBirth){
        this.name = name;
        this.contactNumber= contactNumber;
        this.address = address;
        this.dateOfBirth=dateOfBirth;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
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
