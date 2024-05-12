package ViewModel;

public class LaboratoryUse {
    private String Labname;
    private String PatientName;
    private String criteria;
    private String quantity;
    private String date;
    public LaboratoryUse (String Labname, String PatientName, String criteria, String quantity, String date){
        this.Labname =Labname;
        this.PatientName=PatientName;
        this.criteria =criteria;
        this.quantity = quantity;
        this.date = date;
    }

    public String getLabname() {
        return Labname;
    }

    public void setLabname(String labname) {
        Labname = labname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
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
