/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.admin.view;

import java.awt.CardLayout;
import javax.swing.JPanel;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.user.model.BusinessUser;


/**
 *
 * @author deveshkandpal
 */
public class BiogenAdminWorkPanel extends javax.swing.JPanel {

    /**
     * Creates new form BiogenAdminWorkPanel
     */
    private Ecosystem system;
    private JPanel container;
    private BusinessUser user;

    public BiogenAdminWorkPanel(Ecosystem system, JPanel container, BusinessUser user) {
        initComponents();
        this.system = system;
        this.container = container;
        this.user = user;
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
        mngClinicalTrialsBtn = new javax.swing.JButton();
        analyticDashboardBtn = new javax.swing.JButton();

        jLabel1.setText("Admin Work Area");

        mngClinicalTrialsBtn.setText("Manage Clinical Trials");
        mngClinicalTrialsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngClinicalTrialsBtnActionPerformed(evt);
            }
        });

        analyticDashboardBtn.setText("Analytic Dashboard");
        analyticDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyticDashboardBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mngClinicalTrialsBtn)
                            .addComponent(analyticDashboardBtn))))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(mngClinicalTrialsBtn)
                .addGap(18, 18, 18)
                .addComponent(analyticDashboardBtn)
                .addContainerGap(318, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mngClinicalTrialsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngClinicalTrialsBtnActionPerformed
        // TODO add your handling code here:
        ManageClinicalTrialPanel panel = new ManageClinicalTrialPanel(container, system, user);
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("ManageClinicalTrialPanel", panel);
        layout.next(container);


    }//GEN-LAST:event_mngClinicalTrialsBtnActionPerformed

    private void analyticDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyticDashboardBtnActionPerformed
        // TODO add your handling code here:
        AnalyticsDashboard panel = new AnalyticsDashboard();
        CardLayout layout = (CardLayout) container.getLayout();
        container.add("AnalyticsDashboard", panel);
        layout.next(container);
    }//GEN-LAST:event_analyticDashboardBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyticDashboardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mngClinicalTrialsBtn;
    // End of variables declaration//GEN-END:variables
}
