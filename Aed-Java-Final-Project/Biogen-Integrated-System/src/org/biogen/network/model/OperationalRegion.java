/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.network.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.enterprise.model.BusinessUnit;
import org.biogen.business.enterprise.model.BusinessUnitDirectory;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.ChemicalManufacturingBusiness;
import org.biogen.business.enterprise.model.DrugManufacturingBusiness;
import org.biogen.business.enterprise.model.MedicareBusiness;
import org.biogen.ecosystem.model.Organization;
import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public class OperationalRegion {

    private OperationalRegionType type;
    private static int id = 1;
    private int regionId;

    private List<User> userList;
    private BusinessUnitDirectory buDir;
    private List<Organization> organizationList;

    public OperationalRegion(OperationalRegionType type) {
        this.type = type;
        this.regionId = id++;
        this.userList = new ArrayList<>();
        this.buDir = new BusinessUnitDirectory();
        this.organizationList = new ArrayList<>();
    }

    public BusinessUnitDirectory getBuDir() {
        return buDir;
    }

    public void setBuDir(BusinessUnitDirectory buDir) {
        this.buDir = buDir;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        OperationalRegion.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public OperationalRegionType getType() {
        return type;
    }

    public void setType(OperationalRegionType type) {
        this.type = type;
    }

}
