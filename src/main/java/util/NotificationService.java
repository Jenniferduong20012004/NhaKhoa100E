package util;
import Entity.Patient;
import ViewModel.addTreatmentlogVM;
import ViewModel.addNewImageVM;

import java.util.ArrayList;

public class NotificationService {
    private addTreatmentlogVM add;
    private addNewImageVM addNewImageVM;
    public NotificationService(addTreatmentlogVM add, addNewImageVM image){
        this.add = add;
        this.addNewImageVM = image;
    }

    public void notify(Patient c) {
        add.addTreatmentWithPatientInformation(c);
        addNewImageVM.addImageWithPatientInformation (c);
    }
}
