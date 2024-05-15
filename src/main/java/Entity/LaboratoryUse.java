package Entity;

import javafx.scene.control.Button;

public class LaboratoryUse {
    private String Labname;
    private String PatientName;
    private String criteria;
    private String quantity;
    private String date;
    private Button removeButton;
    public LaboratoryUse (String Labname, String PatientName, String criteria, String quantity, String date){
        this.Labname =Labname;
        this.PatientName=PatientName;
        this.criteria =criteria;
        this.quantity = quantity;
        this.date = date;
        removeButton = new Button();
    }


    public String getLabname() {
        return Labname;
    }
    public String getQuantity() {
        return quantity;
    }
    public String getCriteria() {
        return criteria;
    }
    public String getPatientName() {
        return PatientName;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
