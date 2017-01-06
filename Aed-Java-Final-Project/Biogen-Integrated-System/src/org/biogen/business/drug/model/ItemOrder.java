/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.drug.model;

import java.util.Date;
import org.biogen.business.chem.model.Compound;
import org.biogen.ecosystem.model.Organization;
import org.biogen.network.model.OperationalRegionType;
import org.biogen.organization.model.ChemicalManufacturer;

/**
 *
 * @author deveshkandpal
 */
public class ItemOrder {

    private int quantity;
    private ItemStatus itemStatus;
    private Organization organization;
    private OperationalRegionType region;
    private Date requestedOn;
    private int workRequestId;

    public int getWorkRequestId() {
        return workRequestId;
    }

    public void setWorkRequestId(int workRequestId) {
        this.workRequestId = workRequestId;
    }

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(ChemicalManufacturer organization) {
        this.organization = organization;
    }

    public OperationalRegionType getRegion() {
        return region;
    }

    public void setRegion(OperationalRegionType region) {
        this.region = region;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    @Override
    public String toString() {
        return String.valueOf(this.quantity);

    }
}
