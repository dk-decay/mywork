/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import org.biogen.business.drug.model.Drug;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalStudy {

    private ClinicalTrialDirectory clinicalTrialDirectory;
    private String studyName;
    private boolean studyStatus;
    private Drug drug;
    private boolean resultStatus;
    private int clinicalStudyId;
    private static int id = 1;
    private TrialType trialType;
    private ClinicalState state;
    private int participatingCandidates;
    private int initialDosage;

    public ClinicalStudy() {
        this.clinicalStudyId = id++;
        this.clinicalTrialDirectory = new ClinicalTrialDirectory();
        //  this.orgId = orgId;

    }

    public int getInitialDosage() {
        return initialDosage;
    }

    public void setInitialDosage(int initialDosage) {
        this.initialDosage = initialDosage;
    }

    public int getParticipatingCandidates() {
        return participatingCandidates;
    }

    public void setParticipatingCandidates(int participatingCandidates) {
        this.participatingCandidates = participatingCandidates;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public ClinicalState getState() {
        return state;
    }

    public void setState(ClinicalState state) {
        this.state = state;
    }

    public TrialType getTrialType() {
        return trialType;
    }

    public void setTrialType(TrialType trialType) {
        this.trialType = trialType;
    }
//
//    public int getOrgId() {
//        return orgId;
//    }
//
//    public void setOrgId(int orgId) {
//        this.orgId = orgId;
//    }

    public ClinicalTrialDirectory getClinicalTrialDirectory() {
        return clinicalTrialDirectory;
    }

    public void setClinicalTrialDirectory(ClinicalTrialDirectory clinicalTrialDirectory) {
        this.clinicalTrialDirectory = clinicalTrialDirectory;
    }

    public boolean isStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(boolean studyStatus) {
        this.studyStatus = studyStatus;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

//    public String getTargetGene() {
//        return targetGene;
//    }
//
//    public void setTargetGene(String targetGene) {
//        this.targetGene = targetGene;
//    }
    public boolean isResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        this.resultStatus = resultStatus;
    }

    public int getClinicalStudyId() {
        return clinicalStudyId;
    }

    public void setClinicalStudyId(int clinicalStudyId) {
        this.clinicalStudyId = clinicalStudyId;
    }

    @Override
    public String toString() {

        return this.studyName;
    }

}
