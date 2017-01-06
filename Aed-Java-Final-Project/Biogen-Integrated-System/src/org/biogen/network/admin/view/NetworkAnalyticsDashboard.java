/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.network.admin.view;

import com.orsoncharts.data.xyz.XYZDataset;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.ChemicalManufacturingBusiness;
import org.biogen.business.medicare.model.ClinicalStudy;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.network.model.OperationalRegion;
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.organization.model.Hospital;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 *
 * @author deveshkandpal
 */
public class NetworkAnalyticsDashboard extends javax.swing.JPanel {

    /**
     * Creates new form NetworkAnalyticsDashboard
     */
    private OperationalRegion network;
    private Ecosystem system;

    public NetworkAnalyticsDashboard(OperationalRegion network, Ecosystem system) {
        initComponents();
        this.network = network;
        this.system = system;
        createDataSet();
    }

    

    public void createDataSet() {

        List<Drug> drugList = getDrugList();
        Map<Drug, Integer> drugStudyMap = getClinicalStudies(drugList);
        Map<String, Integer> drugCompoundMap = getDrugCompoundList(drugList);
        populateCompountToDrugChart(drugCompoundMap);
        populateDrugToStudyChart(drugStudyMap);
        
    }

    public void populateDrugToStudyChart(Map<Drug, Integer> compoundDrugMap) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        compoundDrugMap.forEach((k, v) -> {
            dataset.setValue(v, "Total Studies", k.getName());

        });

        JFreeChart chart = ChartFactory
                .createBarChart("No of Studies",
                        "Drug Name",
                        "Drug Quantity", dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartPanel f = new ChartPanel(chart);
        drugToStudy.setLayout(new java.awt.BorderLayout());
        drugToStudy.removeAll();
        drugToStudy.add(f, BorderLayout.CENTER);
        drugToStudy.validate();
        drugToStudy.repaint();

    }

    public void populateCompountToDrugChart(Map<String, Integer> compoundDrugMap) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        compoundDrugMap.forEach((k, v) -> {
            dataset.setValue(v, "Total Quantity", k);

        });

        JFreeChart chart = ChartFactory
                .createBarChart("No of Drugs",
                        "Compound Name",
                        "Drug Quantity", dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);
        ChartPanel f = new ChartPanel(chart);
        drugToCompound.setLayout(new java.awt.BorderLayout());
        drugToCompound.removeAll();
        drugToCompound.add(f, BorderLayout.CENTER);
        drugToCompound.validate();
        drugToCompound.repaint();

    }

    public Map<Drug, Integer> getClinicalStudies(List<Drug> drugList) {

        Map<Drug, Integer> drugToStudyMapping = new HashMap<>();

        this.system
                .getDiseaseStudyDirectory()
                .getDiseaseList()
                .stream()
                .map(m -> m.getClinicalStudyDirectory().getClinicalStudyList())
                .flatMap(l -> l.stream())
                .forEach(cs -> {
                    Drug d = cs.getDrug();
                    if (drugToStudyMapping.containsKey(d)) {
                        Integer studyList = drugToStudyMapping.get(d);
                        drugToStudyMapping.put(d, studyList + 1);
                    } else {
                        drugToStudyMapping.put(d, 1);
                    }
                });
        return drugToStudyMapping;
    }

    public List<Drug> getDrugList() {

        return this.network.getOrganizationList()
                .stream()
                .filter(o -> o.getBuType() == BusinessUnitType.DRUG)
                .map(m -> (DrugManufactuer) m)
                .map(d -> d.getDrugList())
                .flatMap(l -> l.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getDrugCompoundList(List<Drug> drugList) {

        List<Compound> cList = this.network.getBuDir()
                .getBusinessUnitList()
                .stream()
                .filter(bu -> bu.getUnitType() == BusinessUnitType.CHEMICAL)
                .map(m -> (ChemicalManufacturingBusiness) m)
                .map(m2 -> m2.getCompoundDirectory().getCompoundList())
                .flatMap(l -> l.stream())
                .distinct()
                .collect(Collectors.toList());

        Map<String, Integer> compoundDrugList = new HashMap<>();

        cList.forEach((c) -> {
            Integer count = drugList.stream()
                    .map(d -> d.getDrugCompositionCatalog().getDrugCompositionList())
                    .flatMap(f -> f.stream())
                    .filter(f -> f.getCompound() == c)
                    .collect(Collectors.toList()).size();

            compoundDrugList.put(c.getName(), count);
        });
        return compoundDrugList;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drugToCompound = new javax.swing.JPanel();
        drugToStudy = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        drugToCompound.setBackground(new java.awt.Color(255, 255, 255));
        drugToCompound.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout drugToCompoundLayout = new javax.swing.GroupLayout(drugToCompound);
        drugToCompound.setLayout(drugToCompoundLayout);
        drugToCompoundLayout.setHorizontalGroup(
            drugToCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );
        drugToCompoundLayout.setVerticalGroup(
            drugToCompoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        drugToStudy.setBackground(new java.awt.Color(255, 255, 255));
        drugToStudy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout drugToStudyLayout = new javax.swing.GroupLayout(drugToStudy);
        drugToStudy.setLayout(drugToStudyLayout);
        drugToStudyLayout.setHorizontalGroup(
            drugToStudyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );
        drugToStudyLayout.setVerticalGroup(
            drugToStudyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(drugToCompound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(drugToStudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(drugToStudy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drugToCompound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel drugToCompound;
    private javax.swing.JPanel drugToStudy;
    // End of variables declaration//GEN-END:variables
}
