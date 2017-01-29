/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.ecosystem.model.Organization;

/**
 *
 * @author deveshkandpal
 */
public class DrugManufacturerDirectory {

    private List<Organization> drugManfacturerList;

    public DrugManufacturerDirectory() {

        this.drugManfacturerList = new ArrayList<>();

    }

    public List<Organization> getDrugManfacturerList() {
        return drugManfacturerList;
    }

    public void setDrugManfacturerList(List<Organization> drugManfacturerList) {
        this.drugManfacturerList = drugManfacturerList;
    }

    @Override
    public String toString() {
        return "DrugManufacturerDirectory{" + "drugManfacturerList=" + drugManfacturerList + '}';
    }

    
    
}
