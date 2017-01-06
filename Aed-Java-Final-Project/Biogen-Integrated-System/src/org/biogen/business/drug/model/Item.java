/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.drug.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biogen.business.chem.model.Compound;
import org.biogen.network.model.OperationalRegionType;
import org.biogen.organization.model.ChemicalManufacturer;

/**
 *
 * @author deveshkandpal
 */
public class Item {

    private Compound compound;
    private List<ItemOrder> itemOrderList;

    public Item() {

        this.itemOrderList = new ArrayList<>();
    }

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

    public List<ItemOrder> getItemOrderList() {
        return itemOrderList;
    }

    public void setItemOrderList(List<ItemOrder> itemOrderList) {
        this.itemOrderList = itemOrderList;
    }

    public ItemOrder addItemOrder() {
        ItemOrder itemOrder = new ItemOrder();
        this.itemOrderList.add(itemOrder);
        return itemOrder;

    }

    public void removeItemOrder(ItemOrder itemOrder) {
        this.itemOrderList.remove(itemOrder);
    }

    @Override
    public String toString() {

        return this.compound.toString();
    }
}
