/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public class PatientDirectory {

    private List<Patient> patientList;

    public PatientDirectory() {
        this.patientList = new ArrayList<>();
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public ClinicalPatient addNewClinicalPatient(int hospitalId, User user) {
        ClinicalPatient p = new ClinicalPatient(hospitalId, user);
        this.patientList.add(p);
        return p;
    }
}
