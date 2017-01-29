/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalTrialDirectory {

    private List<ClinicalTrial> clinicalTrialList;

    public ClinicalTrialDirectory() {

        this.clinicalTrialList = new ArrayList<>();
    }

    public List<ClinicalTrial> getClinicalTrialList() {
        return clinicalTrialList;
    }

    public void setClinicalTrialList(List<ClinicalTrial> clinicalTrialList) {
        this.clinicalTrialList = clinicalTrialList;
    }
    
    

    public ClinicalTrial addNewClinicalTrial(int orgId) {

        ClinicalTrial c = new ClinicalTrial(orgId);
        this.clinicalTrialList.add(c);
        return c;
    }

}
