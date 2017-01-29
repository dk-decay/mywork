/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.enterprise.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.drug.model.DrugDirectory;
import org.biogen.organization.model.DrugManufacturerDirectory;

/**
 *
 * @author deveshkandpal
 */
public class DrugManufacturingBusiness extends BusinessUnit {

    //private DrugManufacturerDirectory drugManuFactDirectory;
    private List<Drug> drugList;

    public DrugManufacturingBusiness(BusinessUnitType unitType,
             int regionId) {
        super(unitType, regionId);
        //  this.drugManuFactDirectory = new DrugManufacturerDirectory();
        this.drugList = new ArrayList<>();
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

}
