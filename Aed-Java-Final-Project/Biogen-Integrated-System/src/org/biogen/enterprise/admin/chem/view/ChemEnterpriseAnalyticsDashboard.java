/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.enterprise.admin.chem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.enterprise.model.ChemicalManufacturingBusiness;
import org.biogen.organization.model.ChemicalManufacturer;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.user.model.BusinessUser;
import org.biogen.work.attachment.model.AttachmentType;
import org.biogen.work.attachment.model.NewCompoundApprovalAttachment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author deveshkandpal
 */
public class ChemEnterpriseAnalyticsDashboard extends javax.swing.JPanel {

    /**
     * Creates new form ChemEnterpriseAnalyticsDashboard
     */
    private ChemicalManufacturingBusiness enterprise;
    private JPanel container;
    private Map<BusinessUser, List<NewCompoundApprovalAttachment>> orgCompMap;

    public ChemEnterpriseAnalyticsDashboard(ChemicalManufacturingBusiness enterprise,
            JPanel container) {
        initComponents();
        this.enterprise = enterprise;
        this.container = container;
        populatePanel();

    }

    public void populatePanel() {
        populateBarGraph();
        DefaultTableModel model = (DefaultTableModel) orgTable.getModel();
        model.setRowCount(0);

        this.orgCompMap.forEach((k, v) -> {

            ChemicalManufacturer org
                    = (ChemicalManufacturer) this.
                            enterprise.
                            getOrganizationList()
                            .stream()
                            .filter(o -> o.getOrgId() == k.getOrganizationId())
                            .findFirst()
                            .get();
            
            Object[] arr = new Object[2];
            arr[0] = org;
            arr[1] = v.size();
            model.addRow(arr);
        });
    }

    public void populateBarGraph() {
        this.orgCompMap = new HashMap<>();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<BusinessUser, List<WorkRequest>> dataMap = new HashMap<>();
        for (WorkRequest wr : this.enterprise.getWorkQueue().getWorkRequestList()) {

            if (wr.getAttachment().getAttachmentType() == AttachmentType.NewCompoundApprovalAttachment) {

                NewCompoundApprovalAttachment attach = (NewCompoundApprovalAttachment) wr.getAttachment();
                if (dataMap.containsKey(wr.getSender())) {

                    List<WorkRequest> values = dataMap.get(wr.getSender());
                    values.add(wr);
                    dataMap.put(wr.getSender(), values);

                    List<NewCompoundApprovalAttachment> attachList
                            = orgCompMap.get(wr.getSender());
                    attachList.add(attach);
                    orgCompMap.put(wr.getSender(), attachList);

                } else {
                    List<WorkRequest> values = new ArrayList<>();
                    values.add(wr);
                    dataMap.put(wr.getSender(), values);

                    List<NewCompoundApprovalAttachment> attachList = new ArrayList<>();
                    attachList.add(attach);
                    orgCompMap.put(wr.getSender(), attachList);

                }
            }

        }

        dataMap.forEach((k, v) -> {
            String orgName = this.enterprise.getOrganizationList().stream().filter(o -> o.getOrgId() == k.getOrganizationId()).findFirst().get().getName();
            dataset.setValue(v.size(), "Total Compounds", orgName);

        });

        JFreeChart chart = ChartFactory
                .createBarChart("No of Compounds",
                        "Organization Name",
                        "Total Compounds", dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);

        ChartPanel f = new ChartPanel(chart);

        barPanel.setLayout(new java.awt.BorderLayout());
        barPanel.removeAll();
        barPanel.add(f, BorderLayout.CENTER);
        barPanel.validate();
        barPanel.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orgTable = new javax.swing.JTable();
        barPanel = new javax.swing.JPanel();
        compoundChart = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        orgTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Organization", "Total Compounds"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orgTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orgTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orgTable);

        barPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout barPanelLayout = new javax.swing.GroupLayout(barPanel);
        barPanel.setLayout(barPanelLayout);
        barPanelLayout.setHorizontalGroup(
            barPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        barPanelLayout.setVerticalGroup(
            barPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        compoundChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout compoundChartLayout = new javax.swing.GroupLayout(compoundChart);
        compoundChart.setLayout(compoundChartLayout);
        compoundChartLayout.setHorizontalGroup(
            compoundChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        compoundChartLayout.setVerticalGroup(
            compoundChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(compoundChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(barPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(compoundChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(barPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void orgTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orgTableMouseClicked
        // TODO add your handling code here:
        
        
        int selectedRow = orgTable.getSelectedRow();
        if (selectedRow >= 0) {
            ChemicalManufacturer org = (ChemicalManufacturer) orgTable.getValueAt(selectedRow, 0);
            visualize(org);
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a compound request to be deleted");
        }
    }//GEN-LAST:event_orgTableMouseClicked


    public void visualize(ChemicalManufacturer org) {
        
         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         
         
         org.getChemicalSynthesisOrderList()
                 .stream()
                 .forEach(a -> {
                     dataset.setValue(a.getQuantity(), "Total Stock", a.getCompound().toString());
                 
                 });
          
         
         
            JFreeChart chart = ChartFactory
                .createBarChart("Total Stock",
                        "Compound Name",
                        "Total Stock", dataset,
                        PlotOrientation.VERTICAL,
                        false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLACK);

        ChartPanel f = new ChartPanel(chart);

        compoundChart.setLayout(new java.awt.BorderLayout());
        compoundChart.removeAll();
        compoundChart.add(f, BorderLayout.CENTER);
        compoundChart.validate();
        compoundChart.repaint();
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barPanel;
    private javax.swing.JPanel compoundChart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable orgTable;
    // End of variables declaration//GEN-END:variables
}
