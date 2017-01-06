/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.drug.model.Drug;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalStudyDirectory {

    private List<ClinicalStudy> clinicalStudyList;

    public ClinicalStudyDirectory() {

        this.clinicalStudyList = new ArrayList<>();
    }

    public List<ClinicalStudy> getClinicalStudyList() {
        return clinicalStudyList;
    }

    public void setClinicalStudyList(List<ClinicalStudy> clinicalStudyList) {
        this.clinicalStudyList = clinicalStudyList;
    }

//    public ClinicalStudy addNewStudy(Drug drug, TrialPhaseType type,
//            int trialConductingOrgId) {
//        
//        // recruiting state -> don't allow
//        // if it's in pending state -> don't allow
//        // if it's approved -> create a new study
//        this.clinicalStudyList.stream()
//                .filter(cls -> cls.getDrug() == drug)
//                .map(d -> d.getClinicalTrialDirectory().getClinicalTrialList())
//                .flatMap(l -> l.stream())
//                .filter(p -> p.getTrialConductingOrgId() == trialConductingOrgId)
//                .filter(q -> q.getPhaseType() == type)
//                .filter(r -> r.getTrialState() == TrialState.RECRUITING || r.getTrialState() == TrialState.PENDING)
//                .findFirst()
//                .get();
//                
//                
//                
//        ClinicalStudy study = new ClinicalStudy();
//        this.clinicalStudyList.add(study);
//        return study;
//
//    }
    public ClinicalStudy addNewStudy() {
        ClinicalStudy study = new ClinicalStudy();
        this.clinicalStudyList.add(study);
        return study;
    }

    public void removeNewStudy(ClinicalStudy study) {
        this.clinicalStudyList.remove(study);

    }

}
