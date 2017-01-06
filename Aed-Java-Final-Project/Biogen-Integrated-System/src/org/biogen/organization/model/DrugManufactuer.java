/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.drug.model.Inventory;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.ecosystem.model.Organization;
import org.biogen.order.model.ChemicalSynthesisOrder;
import org.biogen.order.model.DrugSynthesisOrder;

/**
 *
 * @author deveshkandpal
 */
public class DrugManufactuer extends Organization {

    private List<Drug> drugList;
    private Inventory inventory;
    private List<DrugSynthesisOrder> drugSynthesisOrderList;

    public DrugManufactuer(String name, int regionId, BusinessUnitType type) {
        super(name, regionId, type);
        this.drugList = new ArrayList<>();
        this.inventory = new Inventory();
        this.drugSynthesisOrderList = new ArrayList<>();
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<DrugSynthesisOrder> getDrugSynthesisOrderList() {
        return drugSynthesisOrderList;
    }

    public void setDrugSynthesisOrderList(List<DrugSynthesisOrder> drugSynthesisOrderList) {
        this.drugSynthesisOrderList = drugSynthesisOrderList;
    }

    public DrugSynthesisOrder createOrUpdateOrder(Drug drug, int quantity) {

        DrugSynthesisOrder dso = this.drugSynthesisOrderList
                .stream()
                .filter(o -> o.getDrug() == drug)
                .findFirst()
                .orElse(null);

        if (dso != null) {
            dso.setQuantity(dso.getQuantity() + quantity);
        } else {
            dso = createNewOrder();
            dso.setDrug(drug);
            dso.setQuantity(quantity);
        }
        return dso;
    }

    public DrugSynthesisOrder createNewOrder() {
        System.out.println("Added new chemical for synthesis");
        DrugSynthesisOrder dso = new DrugSynthesisOrder();
        this.drugSynthesisOrderList.add(dso);
        return dso;

    }
}
