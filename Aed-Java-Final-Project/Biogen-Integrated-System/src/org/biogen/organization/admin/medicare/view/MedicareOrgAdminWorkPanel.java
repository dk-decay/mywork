/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.admin.medicare.view;

import javax.swing.JPanel;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.DrugManufacturingBusiness;
import org.biogen.business.enterprise.model.MedicareBusiness;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.network.model.OperationalRegion;
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.organization.model.Hospital;
import org.biogen.user.model.BusinessUser;

/**
 *
 * @author deveshkandpal
 */
public class MedicareOrgAdminWorkPanel extends javax.swing.JPanel {

    /**
     * Creates new form MedicareAdminWorkPanel
     */
    private Ecosystem system;
    private JPanel container;
    private BusinessUser user;
    private MedicareBusiness enterprise;
    private OperationalRegion network;
    private Hospital organization;

    public MedicareOrgAdminWorkPanel(Ecosystem system, JPanel container, BusinessUser user) {
        initComponents();
        this.system = system;
        this.container = container;
        this.user = user;
        populateUserDetails();

    }

    public void populateUserDetails() {

        this.network = this.system.getOpRegionDirectory().getOperationalRegionList()
                .stream().filter(op -> op.getRegionId() == user.getNetworkId())
                .findFirst()
                .get();

        this.enterprise = this.system.getOpRegionDirectory().getOperationalRegionList()
                .stream().filter(op -> op.getRegionId() == user.getNetworkId())
                .findFirst()
                .get()
                .getBuDir()
                .getBusinessUnitList()
                .stream()
                .filter(bu -> bu.getUnitType() == BusinessUnitType.MEDICARE)
                .map(unit -> (MedicareBusiness) unit)
                .filter(medicareBusiness -> medicareBusiness.getEnterpriseId() == user.getEnterpriseId())
                .findFirst()
                .get();

        this.organization = (Hospital) this.enterprise
                .getOrganizationList()
                .stream()
                .filter(hospitalOrg -> hospitalOrg.getOrgId() == user.getOrganizationId())
                .findFirst()
                .get();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        manageClinicalStudyBtn = new javax.swing.JButton();
        mgnDrugInvetoryBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Hospital Work Area");

        manageClinicalStudyBtn.setText("Manage Clinical Study");
        manageClinicalStudyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageClinicalStudyBtnActionPerformed(evt);
            }
        });

        mgnDrugInvetoryBtn.setText("Manage Drug Inventory");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mgnDrugInvetoryBtn)
                            .addComponent(manageClinicalStudyBtn)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel1)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(manageClinicalStudyBtn)
                .addGap(18, 18, 18)
                .addComponent(mgnDrugInvetoryBtn)
                .addContainerGap(295, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void manageClinicalStudyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageClinicalStudyBtnActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_manageClinicalStudyBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton manageClinicalStudyBtn;
    private javax.swing.JButton mgnDrugInvetoryBtn;
    // End of variables declaration//GEN-END:variables
}
