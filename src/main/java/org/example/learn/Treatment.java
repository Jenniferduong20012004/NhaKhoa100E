package org.example.learn;

public class Treatment {
    private String patientName;
    private String description;
    private String Date;
    public Treatment (String patientName, String description, String Date){
        this.patientName = patientName;
        this.description = description;
        this.Date = Date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
