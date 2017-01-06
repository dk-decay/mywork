/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.drug.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.chem.model.Compound;

/**
 *
 * @author deveshkandpal
 */
public class Inventory {

    private List<Item> itemList;

    public Inventory() {
        this.itemList = new ArrayList<>();

    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item addOrReturnItem(Compound compound) {

        Item item = this.itemList.stream()
                .filter(it -> it.getCompound() == compound)
                .findFirst()
                .orElse(null);
        if (item == null) {

            item = new Item();
            item.setCompound(compound);
            this.itemList.add(item);
        }
        return item;
    }

}
