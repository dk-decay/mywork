/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.enterprise.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.medicare.model.DiseaseStudyDirectory;
import org.biogen.organization.model.HospitalDirectory;
import org.biogen.business.medicare.model.PatientDirectory;
import org.biogen.business.enterprise.model.BusinessUnit;
import org.biogen.business.medicare.model.ClinicalPatient;
import org.biogen.business.medicare.model.Patient;

/**
 *
 * @author deveshkandpal
 */
public class MedicareBusiness extends BusinessUnit {
    // private HospitalDirectory hospitalDirectory;

    private DiseaseStudyDirectory diseaseDirectory;
    //private PatientDirectory patientDirectory;
    private List<ClinicalPatient> patientList;

    public MedicareBusiness(BusinessUnitType type, int regionId) {
        super(type, regionId);
        // this.hospitalDirectory = new HospitalDirectory();
        this.diseaseDirectory = new DiseaseStudyDirectory();
        //this.patientDirectory = new PatientDirectory();
        this.patientList = new ArrayList<>();
    }

//    public HospitalDirectory getHospitalDirectory() {
//        return hospitalDirectory;
//    }
//
//    public void setHospitalDirectory(HospitalDirectory hospitalDirectory) {
//        this.hospitalDirectory = hospitalDirectory;
//    }
    public DiseaseStudyDirectory getDiseaseDirectory() {
        return diseaseDirectory;
    }

    public void setDiseaseDirectory(DiseaseStudyDirectory diseaseDirectory) {
        this.diseaseDirectory = diseaseDirectory;
    }

    public List<ClinicalPatient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<ClinicalPatient> patientList) {
        this.patientList = patientList;
    }

}
