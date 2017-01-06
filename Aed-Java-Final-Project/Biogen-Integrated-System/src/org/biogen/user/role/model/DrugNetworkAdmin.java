/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.role.model;

import javax.swing.JPanel;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.network.admin.drug.view.DrugNetworkAdminWorkPanel;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.BusinessUserRole;
import org.biogen.user.model.RoleType;

/**
 *
 * @author deveshkandpal
 */
public class DrugNetworkAdmin extends BusinessUserRole {

    public DrugNetworkAdmin(RoleType type) {
        super(type);
    }

    @Override
    public JPanel retrieveWorkArea(Ecosystem system, JPanel container,
            BusinessUser user) {
       return new DrugNetworkAdminWorkPanel(system, container, user);
    }

}
