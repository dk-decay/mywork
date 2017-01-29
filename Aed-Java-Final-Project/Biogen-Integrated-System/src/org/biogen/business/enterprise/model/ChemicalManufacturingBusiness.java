/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.enterprise.model;

import org.biogen.organization.model.ChemicalManufactDirectory;
import org.biogen.business.chem.model.CompoundDirectory;




/**
 *
 * @author deveshkandpal
 */
public class ChemicalManufacturingBusiness extends BusinessUnit {
  
    
    private CompoundDirectory compoundDirectory;
    
    
    public ChemicalManufacturingBusiness(BusinessUnitType type, int regionId) {
        super(type, regionId);
        this.compoundDirectory = new CompoundDirectory();
    }


    public CompoundDirectory getCompoundDirectory() {
        return compoundDirectory;
    }

    public void setCompoundDirectory(CompoundDirectory compoundDirectory) {
        this.compoundDirectory = compoundDirectory;
    }
    
    
    
}
