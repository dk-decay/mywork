/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author deveshkandpal
 */
public class TrialResult {

    private Map<String, Integer> scoreMap;
    private boolean improvement;
    private String findings;
    private int score;
    private TrialPhaseType phaseType;
    private int clinicalTrialId;

    public TrialResult(int id) {
        this.clinicalTrialId = id;
        this.scoreMap = new HashMap<>();

    }

    public Map<String, Integer> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Integer> scoreMap) {
        this.scoreMap = scoreMap;
    }

    public int getClinicalTrialId() {
        return clinicalTrialId;
    }

    public void setClinicalTrialId(int clinicalTrialId) {
        this.clinicalTrialId = clinicalTrialId;
    }

    public boolean isImprovement() {
        return improvement;
    }

    public void setImprovement(boolean improvement) {
        this.improvement = improvement;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public TrialPhaseType getPhaseType() {
        return phaseType;
    }

    public void setPhaseType(TrialPhaseType phaseType) {
        this.phaseType = phaseType;
    }

}
