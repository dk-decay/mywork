/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalTrialResultDirectory {
    private int clinicalStudyId;
    private TrialResultDirectory trialResultDirectory;
    
    public ClinicalTrialResultDirectory() {
        this.trialResultDirectory = new TrialResultDirectory();
    }

    public int getClinicalStudyId() {
        return clinicalStudyId;
    }

    public void setClinicalStudyId(int clinicalStudyId) {
        this.clinicalStudyId = clinicalStudyId;
    }

    
    public TrialResultDirectory getTrialResultDirectory() {
        return trialResultDirectory;
    }

    public void setTrialResultDirectory(TrialResultDirectory trialResultDirectory) {
        this.trialResultDirectory = trialResultDirectory;
    }
    
    
}
