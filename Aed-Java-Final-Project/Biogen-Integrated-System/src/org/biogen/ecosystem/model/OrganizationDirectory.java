/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.organization.model.ChemicalManufacturer;
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.organization.model.Hospital;
import org.biogen.business.enterprise.model.BusinessUnitType;

/**
 *
 * @author deveshkandpal
 */
public class OrganizationDirectory {

    private List<Organization> organizationList;

    public OrganizationDirectory() {

        this.organizationList = new ArrayList<>();

    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public Organization addOrganization(String name, int enterpriseId, BusinessUnitType type) {
        Organization org;
        switch (type) {
            case CHEMICAL: {
                org = new ChemicalManufacturer(name, enterpriseId, type);
                break;
            }
            case DRUG: {
                org = new DrugManufactuer(name, enterpriseId, type);
                break;
            }
            default: {
                org = new Hospital(name, enterpriseId, type);
                break;
            }

        }
        this.organizationList.add(org);
        return org;
    }

}
