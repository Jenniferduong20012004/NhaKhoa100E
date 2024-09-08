package util;

import Entity.Treatment;
import ViewModel.EditTreatmentVM;

public class NotiTreatmentFix {
    private EditTreatmentVM edit;
    public NotiTreatmentFix(EditTreatmentVM edit) {
        this.edit = edit;
    }
    public void notify(Treatment t){
        edit.editWithTreatmentInformation(t);
    }
}
