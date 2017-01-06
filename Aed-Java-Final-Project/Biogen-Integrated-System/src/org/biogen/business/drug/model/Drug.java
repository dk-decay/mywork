/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.business.drug.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.common.Status;
import org.biogen.business.medicare.model.Disease;

/**
 *
 * @author deveshkandpal
 */
public class Drug {

    private static int id = 1;
    private String name;
    private Date createdOn;
    private DrugCompositionCatalog drugCompositionCatalog;
    private List<String> targetedGenes;
    private int strength;
    private RouteType routeType;
    private int drugManuFacturerId;
    private int drugId;
    private Status status;
    private Disease disease;

    public Drug(int drugManuFacturerId) {
        this.drugId = id++;
        this.drugManuFacturerId = drugManuFacturerId;
        this.drugCompositionCatalog = new DrugCompositionCatalog();
        this.targetedGenes = new ArrayList<>();
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Drug.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DrugCompositionCatalog getDrugCompositionCatalog() {
        return drugCompositionCatalog;
    }

    public void setDrugCompositionCatalog(DrugCompositionCatalog drugCompositionCatalog) {
        this.drugCompositionCatalog = drugCompositionCatalog;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public List<String> getTargetedGenes() {
        return targetedGenes;
    }

    public void setTargetedGenes(List<String> targetedGenes) {
        this.targetedGenes = targetedGenes;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public int getDrugManuFacturerId() {
        return drugManuFacturerId;
    }

    public void setDrugManuFacturerId(int drugManuFacturerId) {
        this.drugManuFacturerId = drugManuFacturerId;
    }

    @Override
    public String toString() {

        return this.name;
    }

}
