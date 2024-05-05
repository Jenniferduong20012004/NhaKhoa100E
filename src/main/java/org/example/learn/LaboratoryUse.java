package org.example.learn;

public class LaboratoryUse {
    private String Labname;
    private String PatientName;
    private String criteria;
    private int quantity;
    private String date;
    public LaboratoryUse (String Labname, String PatientName, String criteria, int quantity){
        this.Labname =Labname;
        this.PatientName=PatientName;
        this.criteria =criteria;
        this.quantity = quantity;
    }

    public String getLabname() {
        return Labname;
    }

    public void setLabname(String labname) {
        Labname = labname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
