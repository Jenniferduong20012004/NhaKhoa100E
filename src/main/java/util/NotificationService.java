package util;
import Entity.Patient;
import org.example.learn.ViewPatientVM;
import ViewModel.addTreatmentlogVM;
import ViewModel.addNewImageVM;

public class NotificationService {
    private addTreatmentlogVM add;
    private addNewImageVM addNewImageVM;
    private ViewPatientVM view;
    public NotificationService(addTreatmentlogVM add, addNewImageVM image, ViewPatientVM view){
        this.add = add;
        this.addNewImageVM = image;
        this.view = view;
    }

    public void notify(Patient c) {
        add.addTreatmentWithPatientInformation(c);
        addNewImageVM.addImageWithPatientInformation (c);
        view.viewImageWithPatientInformation(c);
    }
}
