/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

/**
 *
 * @author deveshkandpal
 */
public class SocialHabits {
    private boolean smoke;
    private boolean alcoholic;
    private LifestyleType lifestyleType;

    public boolean isSmoke() {
        return smoke;
    }

    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public LifestyleType getLifestyleType() {
        return lifestyleType;
    }

    public void setLifestyleType(LifestyleType lifestyleType) {
        this.lifestyleType = lifestyleType;
    }
    
    
    
}
