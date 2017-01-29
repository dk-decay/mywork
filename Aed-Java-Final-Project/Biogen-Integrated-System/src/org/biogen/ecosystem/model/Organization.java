/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.model;

import java.util.ArrayList;
import java.util.List;
import org.biogen.business.common.Order;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public abstract class Organization {

    private String name;
    private static int id = 1;
    private int orgId;
    private int enterpriseId;
    private int networkId;
    private BusinessUnitType buType;
    private WorkRequest workRequest;
    private List<User> userList;

    public Organization(String name, int enterpriseId, BusinessUnitType type) {
        this.name = name;
        this.orgId = id++;
        this.enterpriseId = enterpriseId;
        this.userList = new ArrayList<>();
        this.buType = type;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public WorkRequest getWorkRequest() {
        return workRequest;
    }

    public void setWorkRequest(WorkRequest workRequest) {
        this.workRequest = workRequest;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BusinessUnitType getBuType() {
        return buType;
    }

    public void setBuType(BusinessUnitType buType) {
        this.buType = buType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {

        return this.getName();
    }
}
