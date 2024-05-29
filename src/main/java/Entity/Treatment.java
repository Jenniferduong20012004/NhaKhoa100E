package Entity;

import javafx.scene.control.Button;

public class Treatment {
    private String patientName;
    private String description;
    private String Date;
    private int patientId;
    public Treatment (int patientId, String patientName, String description, String Date){
        this.patientId = patientId;
        this.patientName = patientName;
        this.description = description;
        this.Date = Date;
    }


    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }


    public String getDescription() {
        return description;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
