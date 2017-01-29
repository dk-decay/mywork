/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class ClinicalTrial {

    private int clinicalTrialId;
    private static int id = 1;
    private int trialConductingOrgId;
    private Date startDate;
    private Date endDate;
    private List<Patient> clinicalTrialPatientList;
    private TrialPhaseType phaseType;
    private boolean result;
    private TrialState trialState;
    private double averageScore;

    public ClinicalTrial(int trialConductingOrgId) {
        this.clinicalTrialId = id++;
        this.trialConductingOrgId = trialConductingOrgId;
        this.clinicalTrialPatientList = new ArrayList<>();

    }

    public TrialState getTrialState() {
        return trialState;
    }

    public void setTrialState(TrialState trialState) {
        this.trialState = trialState;
    }
    
    

    public int getClinicalTrialId() {
        return clinicalTrialId;
    }

    public void setClinicalTrialId(int clinicalTrialId) {
        this.clinicalTrialId = clinicalTrialId;
    }

    public int getTrialConductingOrgId() {
        return trialConductingOrgId;
    }

    public void setTrialConductingOrgId(int trialConductingOrgId) {
        this.trialConductingOrgId = trialConductingOrgId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Patient> getClinicalTrialPatientList() {
        return clinicalTrialPatientList;
    }

    public void setClinicalTrialPatientList(List<Patient> clinicalTrialPatientList) {
        this.clinicalTrialPatientList = clinicalTrialPatientList;
    }

    public TrialPhaseType getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(TrialPhaseType phaseType) {
        this.phaseType = phaseType;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
    
    
    
    @Override
    public String toString() {
    
    return phaseType.toString();
    }

}
