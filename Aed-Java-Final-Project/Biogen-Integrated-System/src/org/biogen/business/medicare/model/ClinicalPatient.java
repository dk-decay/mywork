/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalPatient extends Patient {
    
    private ClinicalTrialResultDirectory clinicalTrialResultDirectory;
    private int age;

    public ClinicalPatient(int hospitalId, User user) {
        super(hospitalId, user);
        this.clinicalTrialResultDirectory = new ClinicalTrialResultDirectory();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    

    public ClinicalTrialResultDirectory getClinicalTrialResultDirectory() {
        return clinicalTrialResultDirectory;
    }

    public void setClinicalTrialResultDirectory(ClinicalTrialResultDirectory clinicalTrialResultDirectory) {
        this.clinicalTrialResultDirectory = clinicalTrialResultDirectory;
    }
    
    
    
    
}
