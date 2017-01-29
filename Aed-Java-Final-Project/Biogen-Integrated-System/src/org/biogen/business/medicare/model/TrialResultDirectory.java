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
public class TrialResultDirectory {

    private List<TrialResult> trialResultList;

    public TrialResultDirectory() {

        this.trialResultList = new ArrayList<>();
    }

    public List<TrialResult> getTrialResultList() {
        return trialResultList;
    }

    public void setTrialResultList(List<TrialResult> trialResultList) {
        this.trialResultList = trialResultList;
    }

    public TrialResult addTrialResult(int clinicalTrialId) {
        TrialResult tr = new TrialResult(clinicalTrialId);
        this.trialResultList.add(tr);
        return tr;
    }
}
