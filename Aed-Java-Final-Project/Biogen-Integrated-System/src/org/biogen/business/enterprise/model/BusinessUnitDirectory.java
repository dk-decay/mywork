/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.enterprise.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deveshkandpal
 */
public class BusinessUnitDirectory {

    private List<BusinessUnit> businessUnitList;

    public BusinessUnitDirectory() {

        this.businessUnitList = new ArrayList<>();
    }

    public List<BusinessUnit> getBusinessUnitList() {
        return businessUnitList;
    }

    public void setBusinessUnitList(List<BusinessUnit> businessUnitList) {
        this.businessUnitList = businessUnitList;
    }

    public BusinessUnit addBusinessUnit(BusinessUnitType unitType, int regionId) {
        BusinessUnit bu = null;
        switch (unitType) {
            case CHEMICAL: {
                bu = new ChemicalManufacturingBusiness(unitType, regionId);
                break;
            }
            case DRUG: {
                bu = new DrugManufacturingBusiness(unitType, regionId);
                break;
            }
            case MEDICARE: {
                bu = new MedicareBusiness(unitType, regionId);
                break;
            }

        }
        this.businessUnitList.add(bu);
        return bu;
    }
}
