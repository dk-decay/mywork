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
public class Disease {

    private static int id;
    private String name;
    private ClinicalStudyDirectory clinicalStudyDirectory;
    private List<Drug> acceptableDrugList;
    private String description;
    private List<String> affectedGenes;
    private int diseaseId;

    public Disease() {

        this.diseaseId = id++;
        this.clinicalStudyDirectory = new ClinicalStudyDirectory();
        this.acceptableDrugList = new ArrayList<>();
        this.affectedGenes = new ArrayList<>();

    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClinicalStudyDirectory getClinicalStudyDirectory() {
        return clinicalStudyDirectory;
    }

    public void setClinicalStudyDirectory(ClinicalStudyDirectory clinicalStudyDirectory) {
        this.clinicalStudyDirectory = clinicalStudyDirectory;
    }

    public List<Drug> getAcceptableDrugList() {
        return acceptableDrugList;
    }

    public void setAcceptableDrugList(List<Drug> acceptableDrugList) {
        this.acceptableDrugList = acceptableDrugList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAcceptableDrug(Drug d) {
        this.acceptableDrugList.add(d);

    }

    public List<String> getAffectedGenes() {
        return affectedGenes;
    }

    public void setAffectedGenes(List<String> affectedGenes) {
        this.affectedGenes = affectedGenes;
    }

//    public Drug addAcceptableDrug(Drug drug) {
//        Drug drug = new Drug(manufacturerId);
//        this.acceptableDrugList.add(drug);
//        return drug;
//    }

    public void addAffectedGenes(String gene) {

        this.affectedGenes.add(gene);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
