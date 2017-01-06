/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.model;

import java.util.HashMap;
import java.util.Map;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.organization.work.model.WorkQueue;
import org.biogen.user.role.model.BusinessAdmin;
import org.biogen.user.role.model.ChemicalEnterpriseAdmin;
import org.biogen.user.role.model.ChemicalNetworkAdmin;
import org.biogen.user.role.model.ChemicalOrganizationAdmin;
import org.biogen.user.role.model.DrugEnterpriseAdmin;
import org.biogen.user.role.model.DrugNetworkAdmin;
import org.biogen.user.role.model.DrugOrganizationAdmin;
import org.biogen.user.role.model.HospitalEnterpriseAdmin;
import org.biogen.user.role.model.HospitalNetworkAdmin;
import org.biogen.user.role.model.HospitalOrganizationAdmin;
import org.biogen.user.role.model.NetworkAdmin;

/**
 *
 * @author deveshkandpal
 */
public class BusinessUser extends User {

    private BusinessUserRole role;
    private WorkQueue workQueue;
    private int networkId;
    private int enterpriseId;
    private int organizationId;

    public BusinessUser(UserType type, BusinessUnitType buType,
            RoleType roleType, String username, String password) {
        super(type, username, password);
        this.role = role;
        this.workQueue = new WorkQueue();
        this.addBusinessUserRole(roleType, buType);
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public void addBusinessUserRole(RoleType roleType, BusinessUnitType buType) {

        Map<String, BusinessUserRole> values = new HashMap<String, BusinessUserRole>() {
            {
                put("BUS_ADMIN", new BusinessAdmin(roleType));
                put("NETWORK_ADMIN", new NetworkAdmin(roleType));

                //  put("NETWORK_ADMIN_CHEMICAL", new ChemicalNetworkAdmin(roleType));
                put("ENTERPRISE_ADMIN_CHEMICAL", new ChemicalEnterpriseAdmin(roleType));
                put("ORG_ADMIN_CHEMICAL", new ChemicalOrganizationAdmin(roleType));

                //  put("NETWORK_ADMIN_DRUG", new DrugNetworkAdmin(roleType));
                put("ENTERPRISE_ADMIN_DRUG", new DrugEnterpriseAdmin(roleType));
                put("ORG_ADMIN_DRUG", new DrugOrganizationAdmin(roleType));

                //  put("NETWORK_ADMIN_MEDICARE", new HospitalNetworkAdmin(roleType));
                put("ENTERPRISE_MEDICARE", new HospitalEnterpriseAdmin(roleType));
                put("ORG_ADMIN_MEDICARE", new HospitalOrganizationAdmin(roleType));

            }
        };
        String key;
        if (roleType == RoleType.NETWORK_ADMIN) {
            key = roleType.toString();
        } else if (roleType == RoleType.BUS_ADMIN) {
            key = roleType.toString();
        } else {
            key = roleType.toString() + "_" + buType.toString();
        }

        this.role = values.get(key);

    }

    public BusinessUserRole getRole() {
        return role;
    }

    public void setRole(BusinessUserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {

        return this.getFirstname() + " " + this.getLastname();
    }

}
