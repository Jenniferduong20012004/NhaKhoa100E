package util;

import org.example.learn.ViewPatient;

public class NotiImage {
    private ViewPatient view;
    public NotiImage (ViewPatient view){
        this.view = view;
    }
    public void notifyImage(){
        view.refreshImage();
    }
}
