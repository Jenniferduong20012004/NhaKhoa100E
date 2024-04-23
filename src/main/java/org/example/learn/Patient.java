package org.example.learn;

import java.util.Date;

public class Patient {
    private int TrackingId;
    private String name;
    private Date date;
    private String additionalNote;
    private String description;
    private int MoneyPay;
    public Patient (int TrackingId, String name, Date date,String additionalNote,  String description, int MoneyPay){
        this.TrackingId= TrackingId;
        this.name = name;
        this.date = date;
        this.additionalNote = additionalNote;
        this.description= description;
        this.MoneyPay = MoneyPay;
    }
}
