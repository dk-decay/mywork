/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.user.model;

/**
 *
 * @author deveshkandpal
 */
public class PatientUser extends User {

    private Sex sex;
    private Ethinicity ethinicity;

    public PatientUser(UserType type) {
        super(type, null, null);
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    

    public Ethinicity getEthinicity() {
        return ethinicity;
    }

    public void setEthinicity(Ethinicity ethinicity) {
        this.ethinicity = ethinicity;
    }
    
    
}
