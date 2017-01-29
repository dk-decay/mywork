/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.ecosystem.model.Organization;

/**
 *
 * @author deveshkandpal
 */
public class HospitalDirectory {

    private List<Organization> hospitalList;

    public HospitalDirectory() {
        this.hospitalList = new ArrayList<>();
    }

    public List<Organization> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<Organization> hospitalList) {
        this.hospitalList = hospitalList;
    }

    
}
