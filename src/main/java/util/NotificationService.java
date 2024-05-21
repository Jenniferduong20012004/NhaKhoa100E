package util;
import Entity.Patient;
import ViewModel.ViewPatientDatecomeVM;
import org.example.learn.ViewPatientVM;
import ViewModel.addTreatmentlogVM;
import ViewModel.addNewImageVM;

public class NotificationService {
    private addTreatmentlogVM add;
    private addNewImageVM addNewImageVM;
    private ViewPatientVM view;
    private ViewPatientDatecomeVM viewDatecome;
    public NotificationService(addTreatmentlogVM add, addNewImageVM image, ViewPatientVM view,ViewPatientDatecomeVM viewDatecom){
        this.add = add;
        this.addNewImageVM = image;
        this.view = view;
        this.viewDatecome = viewDatecom;
    }

    public void notify(Patient c) {
        add.addTreatmentWithPatientInformation(c);
        addNewImageVM.addImageWithPatientInformation (c);
        view.viewImageWithPatientInformation(c);
        viewDatecome.viewWithPatientInformation(c);
    }
}
