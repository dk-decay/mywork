/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.work.attachment.model;

import org.biogen.business.chem.model.Compound;
import org.biogen.business.drug.model.Item;
import org.biogen.business.drug.model.ItemOrder;

/**
 *
 * @author deveshkandpal
 */
public class InventoryUpdateAttachment extends Attachment {

    private ItemOrder itemOrder;
    private Compound compound;

    public InventoryUpdateAttachment(AttachmentType type) {
        setAttachmentType(type);
    }

    public ItemOrder getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(ItemOrder itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }

}
