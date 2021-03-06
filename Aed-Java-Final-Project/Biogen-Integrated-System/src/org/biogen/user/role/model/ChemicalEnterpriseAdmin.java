/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.role.model;

import javax.swing.JPanel;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.enterprise.admin.chem.view.ChemEnterpriseAdminMainWorkPanel;
import org.biogen.organization.admin.chem.view.ManageNewCompoundRequestPanel;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.BusinessUserRole;
import org.biogen.user.model.RoleType;

/**
 *
 * @author deveshkandpal
 */
public class ChemicalEnterpriseAdmin extends BusinessUserRole {

    public ChemicalEnterpriseAdmin(RoleType type) {
        super(type);
    }

    @Override
    public JPanel retrieveWorkArea(Ecosystem system, JPanel container, BusinessUser user) {
        return new ChemEnterpriseAdminMainWorkPanel(system, container, user);
    }

}
