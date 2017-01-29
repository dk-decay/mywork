/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.model;

import org.biogen.network.model.OperationalRegionDirectory;
import org.biogen.business.drug.model.DrugDirectory;
import org.biogen.business.medicare.model.DiseaseStudyDirectory;
import org.biogen.business.medicare.model.PatientDirectory;
import org.biogen.organization.work.model.WorkQueue;
import org.biogen.user.model.UserDirectory;

/**
 *
 * @author deveshkandpal
 */
public class Ecosystem {

    private OperationalRegionDirectory opRegionDirectory;
    private UserDirectory userDirectory;
    private OrganizationDirectory organizationDirectory;
    private DrugDirectory drugDirectory;
    private DiseaseStudyDirectory diseaseStudyDirectory;
    private PatientDirectory patientDirectory;
    private WorkQueue workQueue;
    private static Ecosystem instance;

    private Ecosystem() {

        this.opRegionDirectory = new OperationalRegionDirectory();

        this.userDirectory = new UserDirectory();

        this.organizationDirectory = new OrganizationDirectory();

        this.patientDirectory = new PatientDirectory();

        this.diseaseStudyDirectory = new DiseaseStudyDirectory();

        this.drugDirectory = new DrugDirectory();

        this.workQueue = new WorkQueue();

    }

    public static Ecosystem getInstance() {
        if (instance == null) {

            instance = new Ecosystem();
        }

        return instance;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public OperationalRegionDirectory getOpRegionDirectory() {
        return opRegionDirectory;
    }

    public void setOpRegionDirectory(OperationalRegionDirectory opRegionDirectory) {
        this.opRegionDirectory = opRegionDirectory;
    }

    public UserDirectory getUserDirectory() {
        return userDirectory;
    }

    public void setUserDirectory(UserDirectory userDirectory) {
        this.userDirectory = userDirectory;
    }

    public OrganizationDirectory getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(OrganizationDirectory organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    public DrugDirectory getDrugDirectory() {
        return drugDirectory;
    }

    public void setDrugDirectory(DrugDirectory drugDirectory) {
        this.drugDirectory = drugDirectory;
    }

    public DiseaseStudyDirectory getDiseaseStudyDirectory() {
        return diseaseStudyDirectory;
    }

    public void setDiseaseStudyDirectory(DiseaseStudyDirectory diseaseStudyDirectory) {
        this.diseaseStudyDirectory = diseaseStudyDirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientDirectory;
    }

    public void setPatientDirectory(PatientDirectory patientDirectory) {
        this.patientDirectory = patientDirectory;
    }

}
