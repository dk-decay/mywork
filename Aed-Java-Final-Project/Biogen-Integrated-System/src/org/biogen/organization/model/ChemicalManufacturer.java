/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.ecosystem.model.Organization;
import org.biogen.order.model.ChemicalSynthesisOrder;

/**
 *
 * @author deveshkandpal
 */
public class ChemicalManufacturer extends Organization {

    private List<Compound> compoundList;
    private List<ChemicalSynthesisOrder> chemicalSynthesisOrderList;

    public ChemicalManufacturer(String name, int regionId, BusinessUnitType type) {
        super(name, regionId, type);
        this.compoundList = new ArrayList<>();
        this.chemicalSynthesisOrderList = new ArrayList<>();
    }

    public List<Compound> getCompoundList() {
        return compoundList;
    }

    public void setCompoundList(List<Compound> compoundList) {
        this.compoundList = compoundList;
    }

    public ChemicalSynthesisOrder createOrUpdateOrder(Compound compound, int quantity) {

        ChemicalSynthesisOrder cso = this.chemicalSynthesisOrderList
                .stream()
                .filter(o -> o.getCompound() == compound)
                .findFirst()
                .orElse(null);

        if (cso != null) {
            cso.setQuantity(cso.getQuantity() + quantity);
        } else {
            cso = createNewOrder();
            cso.setCompound(compound);
            cso.setQuantity(quantity);
        }
        return cso;
    }

    public ChemicalSynthesisOrder createNewOrder() {
        System.out.println("Added new chemical for synthesis");
        ChemicalSynthesisOrder cso = new ChemicalSynthesisOrder();
        this.chemicalSynthesisOrderList.add(cso);
        return cso;

    }

    public List<ChemicalSynthesisOrder> getChemicalSynthesisOrderList() {
        return chemicalSynthesisOrderList;
    }

    public void setChemicalSynthesisOrderList(List<ChemicalSynthesisOrder> chemicalSynthesisOrderList) {
        this.chemicalSynthesisOrderList = chemicalSynthesisOrderList;
    }

    

}
