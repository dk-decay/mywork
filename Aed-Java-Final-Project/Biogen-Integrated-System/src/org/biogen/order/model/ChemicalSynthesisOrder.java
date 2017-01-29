/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.order.model;

import org.biogen.business.chem.model.Compound;
import org.biogen.business.common.Order;

/**
 *
 * @author deveshkandpal
 */
public class ChemicalSynthesisOrder extends Order {

    private Compound compound;

    public Compound getCompound() {
        return compound;
    }

    public void setCompound(Compound compound) {
        this.compound = compound;
    }
    
    @Override
    public String toString() {
    
    return compound.toString();
    }

}
