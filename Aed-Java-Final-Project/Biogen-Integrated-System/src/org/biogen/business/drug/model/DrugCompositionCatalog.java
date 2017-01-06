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
public class DrugCompositionCatalog {

    private List<DrugComposition> drugCompositionList;

    public DrugCompositionCatalog() {
        this.drugCompositionList = new ArrayList<>();

    }

    public List<DrugComposition> getDrugCompositionList() {
        return drugCompositionList;
    }

    public void setDrugCompositionList(List<DrugComposition> drugCompositionList) {
        this.drugCompositionList = drugCompositionList;
    }

    public DrugComposition addDrugComposition() {
        DrugComposition dco = new DrugComposition();
        this.drugCompositionList.add(dco);
        return dco;

    }

}
