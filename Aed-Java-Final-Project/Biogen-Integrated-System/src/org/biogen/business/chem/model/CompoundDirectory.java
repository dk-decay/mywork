/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.chem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author deveshkandpal
 */
public class CompoundDirectory {

    private List<Compound> compoundList;

    public CompoundDirectory() {

        this.compoundList = new ArrayList<>();

    }

    public List<Compound> getCompoundList() {
        return compoundList;
    }

    public void setCompoundList(List<Compound> compoundList) {
        this.compoundList = compoundList;
    }

    public Compound addNewCompound() {
        Compound compound = new Compound();
        this.compoundList.add(compound);
        return compound;
    }

    public void removeCompound(Compound compound) {
        this.compoundList = this.compoundList.stream().filter(c -> c != compound)
                .collect(Collectors.toList());

    }
}
