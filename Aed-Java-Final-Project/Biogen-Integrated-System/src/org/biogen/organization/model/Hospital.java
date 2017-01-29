/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.medicare.model.ClinicalTrial;
import org.biogen.business.medicare.model.Patient;
import org.biogen.ecosystem.model.Organization;

/**
 *
 * @author deveshkandpal
 */
public class Hospital extends Organization {

    private List<Patient> patientList;
    private List<ClinicalTrial> clinicalTrialList;

    public Hospital(String name, int regionId, BusinessUnitType type) {
        super(name, regionId, type);
        this.patientList = new ArrayList<>();
        this.clinicalTrialList = new ArrayList<>();
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<ClinicalTrial> getClinicalTrialList() {
        return clinicalTrialList;
    }

    public void setClinicalTrialList(List<ClinicalTrial> clinicalTrialList) {
        this.clinicalTrialList = clinicalTrialList;
    }

}
