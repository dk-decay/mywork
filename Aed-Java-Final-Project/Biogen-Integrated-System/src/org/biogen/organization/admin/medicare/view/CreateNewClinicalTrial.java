/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.admin.medicare.view;

import javax.swing.JPanel;
import org.biogen.business.enterprise.model.MedicareBusiness;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.network.model.OperationalRegion;
import org.biogen.organization.model.Hospital;
import org.biogen.user.model.BusinessUser;

/**
 *
 * @author deveshkandpal
 */
public class CreateNewClinicalTrial extends javax.swing.JPanel {

    /**
     * Creates new form CreateNewClinicalTrial
     */
    private Ecosystem system;
    private JPanel container;
    private BusinessUser user;
    private MedicareBusiness enterprise;
    private OperationalRegion network;
    private Hospital organization;

    public CreateNewClinicalTrial(
            Ecosystem system,
            JPanel container,
            BusinessUser user,
            MedicareBusiness enterprise,
            OperationalRegion network,
            Hospital organization
    ) {
        initComponents();
        this.system = system;
        this.container = container;
        this.user = user;
        this.enterprise = enterprise;
        this.network = network;
        this.organization = organization;
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
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Create New Clinical Trial");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(191, 191, 191))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jLabel2)
                .addContainerGap(382, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
