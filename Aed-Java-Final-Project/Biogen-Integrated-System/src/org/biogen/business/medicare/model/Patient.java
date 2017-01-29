/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.medicare.model;

import org.biogen.user.model.User;

/**
 *
 * @author deveshkandpal
 */
public abstract class Patient {

    private static int id = 1;
    private User user;
    private Disease disease;
    private SocialHabits socialHabits;
    private int hospitalId;
    private int patientId;

    public Patient(int hospitalId, User user) {

        this.patientId = id++;
        this.hospitalId = hospitalId;
        this.user = user;

    }

    public SocialHabits addSocialHabits() {

        this.socialHabits = new SocialHabits();
        return this.socialHabits;

    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public SocialHabits getSocialHabits() {
        return socialHabits;
    }

    public void setSocialHabits(SocialHabits socialHabits) {
        this.socialHabits = socialHabits;
    }

}
