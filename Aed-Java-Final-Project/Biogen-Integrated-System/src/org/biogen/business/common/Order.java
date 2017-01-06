/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.common;

import org.biogen.ecosystem.model.Organization;
import org.biogen.organization.model.ChemicalManufacturer;

/**
 *
 * @author deveshkandpal
 */
public abstract class Order {

    private int quantity;
    private Organization synthesizingManufacturerId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Organization getSynthesizingManufacturerId() {
        return synthesizingManufacturerId;
    }

    public void setSynthesizingManufacturerId(Organization synthesizingManufacturerId) {
        this.synthesizingManufacturerId = synthesizingManufacturerId;
    }

}
