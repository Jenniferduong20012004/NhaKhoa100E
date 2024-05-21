package util;
import org.example.learn.ViewPatientVM;

public class NotificationImage {
    private ViewPatientVM viewVM;
    public NotificationImage (ViewPatientVM viewVM){
        this.viewVM = viewVM;
    }
    public void notifyToDelete(){
        viewVM.delete();
    }

}
