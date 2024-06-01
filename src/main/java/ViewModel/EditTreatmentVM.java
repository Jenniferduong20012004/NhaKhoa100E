package ViewModel;

import javafx.beans.property.StringProperty;

public class EditTreatmentVM {
    private StringProperty name, description, saveResponse, criteria, quantity, laboName;
    private boolean clicked;
    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean b) {
        this.clicked = b;
    }
}
