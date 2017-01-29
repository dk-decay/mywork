/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.order.model;

import org.biogen.business.common.Order;
import org.biogen.business.drug.model.Drug;

/**
 *
 * @author deveshkandpal
 */
public class DrugSynthesisOrder extends Order {

    private Drug drug;

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return this.drug.toString();
    }

}
