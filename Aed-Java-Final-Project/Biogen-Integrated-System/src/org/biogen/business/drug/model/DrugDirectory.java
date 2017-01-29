/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.drug.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class DrugDirectory {

    private List<Drug> drugList;

    public DrugDirectory() {
        this.drugList = new ArrayList<>();
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public Drug addNewDrug(int orgId) {
        Drug drug = new Drug(orgId);
        this.drugList.add(drug);
        return drug;

    }

    public void removeDrug(Drug drug) {
        this.getDrugList().remove(drug);

    }

}
