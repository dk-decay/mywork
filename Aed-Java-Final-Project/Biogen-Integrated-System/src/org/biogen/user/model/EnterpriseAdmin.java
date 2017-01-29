/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.model;

import javax.swing.JPanel;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.enterprise.admin.view.EnterpriseAdminMainWorkPanel;

/**
 *
 * @author deveshkandpal
 */
public class EnterpriseAdmin extends BusinessUserRole {

    public EnterpriseAdmin(RoleType type) {
        super(type);

    }

    @Override
    public JPanel retrieveWorkArea(Ecosystem system, JPanel container, BusinessUser user) {
        return new EnterpriseAdminMainWorkPanel(system, container, user);
    }

}
