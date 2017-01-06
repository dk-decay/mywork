/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.admin.chem.view;

import java.awt.CardLayout;
import javax.swing.JPanel;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.ChemicalManufacturingBusiness;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.organization.model.ChemicalManufacturer;
import org.biogen.organization.work.model.WorkQueue;
import org.biogen.user.model.BusinessUser;

/**
 *
 * @author deveshkandpal
 */
public class ChemOrgAdminMainWorkPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChemOrgAdminMainWorkPanel
     */
    private Ecosystem system;
    private JPanel container;
    private BusinessUser user;
    private ChemicalManufacturingBusiness enterprise;
    private ChemicalManufacturer organization;

    public ChemOrgAdminMainWorkPanel(Ecosystem system, JPanel container, BusinessUser user) {
        initComponents();
        this.system = system;
        this.container = container;
        this.user = user;
        populateUserDetails();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void populateUserDetails() {

        this.enterprise = system.getOpRegionDirectory().getOperationalRegionList()
                .stream().filter(op -> op.getRegionId() == user.getNetworkId())
                .findFirst()
                .get()
                .getBuDir()
                .getBusinessUnitList()
                .stream()
                .filter(bu -> bu.getUnitType() == BusinessUnitType.CHEMICAL)
                .map(unit -> (ChemicalManufacturingBusiness) unit)
                .filter(chemBusiness -> chemBusiness.getEnterpriseId() == user.getEnterpriseId())
                .findFirst()
                .get();

        this.organization = (ChemicalManufacturer) enterprise
                .getOrganizationList().stream()
                .filter(o -> o.getOrgId() == user.getOrganizationId())
                .findFirst()
                .get();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mngNewCompoundCreationRequestBtn = new javax.swing.JButton();
        mngCompoundSynthesisBtn = new javax.swing.JButton();
        mngDashboardBtn = new javax.swing.JButton();
        updateCompoundListBtn = new javax.swing.JButton();
        analyticsDashboardBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        mngNewCompoundCreationRequestBtn.setText("Manage New Compound Creation Request");
        mngNewCompoundCreationRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngNewCompoundCreationRequestBtnActionPerformed(evt);
            }
        });

        mngCompoundSynthesisBtn.setText("Manage Compound Synthesis");
        mngCompoundSynthesisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngCompoundSynthesisBtnActionPerformed(evt);
            }
        });

        mngDashboardBtn.setText("Manage Vendor Requests");
        mngDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngDashboardBtnActionPerformed(evt);
            }
        });

        updateCompoundListBtn.setText("Manage Compound Inventory");
        updateCompoundListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCompoundListBtnActionPerformed(evt);
            }
        });

        analyticsDashboardBtn.setText("Analytics Dashboard");
        analyticsDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyticsDashboardBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mngDashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateCompoundListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mngNewCompoundCreationRequestBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mngCompoundSynthesisBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(analyticsDashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(mngNewCompoundCreationRequestBtn)
                .addGap(18, 18, 18)
                .addComponent(mngCompoundSynthesisBtn)
                .addGap(18, 18, 18)
                .addComponent(updateCompoundListBtn)
                .addGap(18, 18, 18)
                .addComponent(mngDashboardBtn)
                .addGap(18, 18, 18)
                .addComponent(analyticsDashboardBtn)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mngCompoundSynthesisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngCompoundSynthesisBtnActionPerformed
        // TODO add your handling code here:
        ManageCompoundSynthesis panel = new ManageCompoundSynthesis(organization, container);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("ManageCompoundSynthesis", panel);
        layout.next(container);
    }//GEN-LAST:event_mngCompoundSynthesisBtnActionPerformed

    private void mngNewCompoundCreationRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngNewCompoundCreationRequestBtnActionPerformed
        // TODO add your handling code here:
        ManageNewCompoundRequestPanel panel = new ManageNewCompoundRequestPanel(system, container, user, enterprise, organization);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("ManageNewCompoundRequestPanel", panel);
        layout.next(container);
    }//GEN-LAST:event_mngNewCompoundCreationRequestBtnActionPerformed

    private void mngDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngDashboardBtnActionPerformed
        // TODO add your handling code here:
        ManageVendorRequest panel = new ManageVendorRequest(container,
                system, organization, enterprise, user);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("ManageVendorRequest", panel);
        layout.next(container);
    }//GEN-LAST:event_mngDashboardBtnActionPerformed

    private void updateCompoundListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCompoundListBtnActionPerformed
        // TODO add your handling code here:
        UpdateCompoundPanel panel = new UpdateCompoundPanel(container, system, organization);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("UpdateCompoundPanel", panel);
        layout.next(container);
    }//GEN-LAST:event_updateCompoundListBtnActionPerformed

    private void analyticsDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyticsDashboardBtnActionPerformed
        // TODO add your handling code here:
        WorkQueue queue = this.user.getWorkQueue();
        ChemicalOrgAnalyticsDashboard panel = new ChemicalOrgAnalyticsDashboard(queue, container);
         CardLayout layout = (CardLayout) container.getLayout();
        container.add("ChemicalOrgAnalyticsDashboard", panel);
        layout.next(container);
        
    }//GEN-LAST:event_analyticsDashboardBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyticsDashboardBtn;
    private javax.swing.JButton mngCompoundSynthesisBtn;
    private javax.swing.JButton mngDashboardBtn;
    private javax.swing.JButton mngNewCompoundCreationRequestBtn;
    private javax.swing.JButton updateCompoundListBtn;
    // End of variables declaration//GEN-END:variables
}