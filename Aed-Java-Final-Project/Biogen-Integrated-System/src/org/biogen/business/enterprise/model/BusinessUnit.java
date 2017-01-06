/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.enterprise.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.ecosystem.model.Organization;
import org.biogen.organization.work.model.WorkQueue;
import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public abstract class BusinessUnit {

    private BusinessUnitType unitType;
    private List<Organization> organizationList;
    private WorkQueue workQueue;
    private List<User> userList;
    private int regionId;
    private static int id = 1;
    private int enterpriseId;
    
    public BusinessUnit(BusinessUnitType unitType, int regionId) {
        this.unitType = unitType;
        this.organizationList = new ArrayList<>();
        this.regionId = regionId;
        this.enterpriseId = id++;
        this.userList = new ArrayList<>();
        this.workQueue = new WorkQueue();
        
        
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }
    
    

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    
    

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public BusinessUnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(BusinessUnitType unitType) {
        this.unitType = unitType;
    }

}
