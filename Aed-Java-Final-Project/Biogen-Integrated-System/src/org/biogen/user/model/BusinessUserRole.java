/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.model;

import javax.swing.JPanel;
import org.biogen.ecosystem.model.Ecosystem;

/**
 *
 * @author deveshkandpal
 */
public abstract class BusinessUserRole {

    private RoleType type;

    public BusinessUserRole(RoleType type) {
        this.type = type;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
    
    public abstract JPanel retrieveWorkArea(Ecosystem system, JPanel container,
            BusinessUser user);

}
