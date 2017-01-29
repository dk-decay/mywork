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
public class ChemicalManufactDirectory {

    private List<Organization> chemManList;
    

    public ChemicalManufactDirectory() {
        this.chemManList = new ArrayList<>();
    
    }

    public List<Organization> getChemManList() {
        return chemManList;
    }

    public void setChemManList(List<Organization> chemManList) {
        this.chemManList = chemManList;
    }



}
