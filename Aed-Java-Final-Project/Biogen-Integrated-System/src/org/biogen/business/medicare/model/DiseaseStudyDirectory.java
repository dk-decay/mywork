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
public class DiseaseStudyDirectory {

    private List<Disease> diseaseList;

    public DiseaseStudyDirectory() {

        this.diseaseList = new ArrayList<>();
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public Disease addNewDisease() {
        Disease d = new Disease();
        this.diseaseList.add(d);
        return d;

    }

}
