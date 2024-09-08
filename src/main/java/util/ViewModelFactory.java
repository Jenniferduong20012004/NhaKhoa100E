package util;

import Entity.Patient;
import ViewModel.*;
import org.example.learn.ViewPatientVM;

public class ViewModelFactory {
    private EditPatientVM editPVM;
    private ViewPatientVM viewPatientVM;
    private TreatmentLogVM treatmentLogVM;
    private PatientPageVM patientPageVM;
    private LaboratoryUseVM laboratoryUseVM;
    private addNewLaboratoryVM addNewLaboratoryVM;
    private addNewPatientVM addNewPatientVM;
    private addTreatmentlogVM addTreatmentlogVM;
    private addNewImageVM addNewImageVM;
    private ViewPatientDatecomeVM viewPatientDatecomeVM;
    private OpenImageVM openVM;
    private BaseVM baseVM;
    private EditTreatmentVM editTVM;
    public EditPatientVM getEditPVM(){
        if (editPVM == null){
            editPVM = new EditPatientVM();
        }
        return editPVM;
    }
    public OpenImageVM getOpenImageVM(){
        if (openVM == null){
            openVM = new OpenImageVM();
        }
        return openVM;
    }
    public ViewPatientDatecomeVM getViewPatientDatecomeVM(){
        if (viewPatientDatecomeVM == null){
            viewPatientDatecomeVM = new ViewPatientDatecomeVM();
        }
        return viewPatientDatecomeVM;
    }

    public ViewPatientVM getViewPatientVM() {
        if(viewPatientVM == null){
            viewPatientVM = new ViewPatientVM();
        }
        return viewPatientVM;
    }

    public addNewImageVM getAddNewImageVM(){
        if (addNewImageVM== null){
            addNewImageVM = new addNewImageVM();
        }
        return addNewImageVM;
    }

    public PatientPageVM getPatientPageVM(){
        if (patientPageVM == null){
            patientPageVM = new PatientPageVM(getAddNewTreatmentVM(), getAddNewImageVM(), getViewPatientVM(), getViewPatientDatecomeVM(), getEditPVM());
        }
        return patientPageVM;
    }
    public TreatmentLogVM getTreatmentLogVM(){
        if (treatmentLogVM == null){
            treatmentLogVM = new TreatmentLogVM(getEditTreatmentVM());
        }
        return treatmentLogVM;
    }

    public LaboratoryUseVM getEquipmentVM() {
        if (laboratoryUseVM == null){
            laboratoryUseVM = new LaboratoryUseVM();
        }
        return laboratoryUseVM;
    }

    public addTreatmentlogVM  getAddNewTreatmentVM() {
        if (addTreatmentlogVM== null){
            addTreatmentlogVM = new addTreatmentlogVM ();
        }
        return addTreatmentlogVM;
    }
    public addNewLaboratoryVM getAddNewLaboratoryVM(){
        if (addNewLaboratoryVM== null){
            addNewLaboratoryVM = new addNewLaboratoryVM ();
        }
        return addNewLaboratoryVM;
    }
    public addNewPatientVM getAddNewPatientVM(){
        if (addNewPatientVM== null){
            addNewPatientVM = new addNewPatientVM ();
        }
        return addNewPatientVM;
    }

    public BaseVM getBase() {
        if (baseVM== null){
            baseVM= new BaseVM ();
        }
        return baseVM;
    }

    public EditTreatmentVM getEditTreatmentVM() {
        if(editTVM == null){
            editTVM = new EditTreatmentVM();
        }
        return  editTVM;
    }
}
