/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.enterprise.admin.drug.view;

import org.biogen.enterprise.admin.chem.view.*;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.enterprise.model.ChemicalManufacturingBusiness;
import org.biogen.business.enterprise.model.DrugManufacturingBusiness;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.organization.admin.chem.view.EditNewCompoundPanel;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.organization.work.model.WorkRequestStatus;
import org.biogen.user.model.BusinessUser;
import org.biogen.work.attachment.model.AttachmentType;

/**
 *
 * @author deveshkandpal
 */
public class ManageDrugWorkRequestPanel extends javax.swing.JPanel {
    
    private Ecosystem system;
    private BusinessUser user;
    private DrugManufacturingBusiness enterprise;
    private JPanel container;

    /**
     * Creates new form ManageWorkRequestPanel
     */
    public ManageDrugWorkRequestPanel(
            Ecosystem system,
            BusinessUser user,
            DrugManufacturingBusiness enterprise,
            JPanel container
    ) {
        initComponents();
        this.system = system;
        this.user = user;
        this.enterprise = enterprise;
        this.container = container;
        populateNewOrUpdatedWorkRequestTable();
        populateOtherWorkRequestTable();
    }
    
    public void populateNewOrUpdatedWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) newOrUpdatedWorkRequestTable.getModel();
        model.setRowCount(0);
        this.enterprise.getWorkQueue().getWorkRequestList()
                .stream()
                .filter(wr -> wr.getStatus() == WorkRequestStatus.NEW
                || wr.getStatus() == WorkRequestStatus.UPDATED)
                .forEach(r -> {
                    Object[] arr = new Object[4];
                    arr[0] = r;
                    arr[1] = r.getAttachment().getAttachmentType().toString();
                    arr[2] = r.getStartDate();
                    arr[3] = r.getStatus().toString();
                    model.addRow(arr);
                });
    }
    
    public void populateOtherWorkRequestTable() {
        DefaultTableModel model = (DefaultTableModel) otherWorkRequest.getModel();
        model.setRowCount(0);
        this.enterprise.getWorkQueue().getWorkRequestList()
                .stream()
                .filter(wr -> wr.getStatus() != WorkRequestStatus.NEW
                && wr.getStatus() != WorkRequestStatus.UPDATED)
                .forEach(r -> {
                    Object[] arr = new Object[5];
                    arr[0] = r;
                    arr[1] = r.getAttachment().getAttachmentType().toString();
                    arr[2] = r.getStartDate();
                    arr[3] = r.getEndDate();
                    arr[4] = r.getStatus().toString();
                    model.addRow(arr);
                });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        newOrUpdatedWorkRequestTable = new javax.swing.JTable();
        viewDetailsBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        otherWorkRequest = new javax.swing.JTable();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Manage Work Request");

        newOrUpdatedWorkRequestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Type", "Received On", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(newOrUpdatedWorkRequestTable);

        viewDetailsBtn.setText("View Details");
        viewDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsBtnActionPerformed(evt);
            }
        });

        otherWorkRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Type", "Start Date", "End Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(otherWorkRequest);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewDetailsBtn)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewDetailsBtn)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = newOrUpdatedWorkRequestTable.getSelectedRow();
        if (selectedRow >= 0) {
            WorkRequest workRequest = (WorkRequest) newOrUpdatedWorkRequestTable.getValueAt(selectedRow, 0);
            JPanel panel = determineJPanel(workRequest);
            CardLayout layout = (CardLayout) container.getLayout();
            container.add("ApproveOrDeclineWorkRequest", panel);
            layout.next(container);
            
        } else {
            JOptionPane.showMessageDialog(null, "Please select a work request!");
            
        }
    }//GEN-LAST:event_viewDetailsBtnActionPerformed
    
    private JPanel determineJPanel(WorkRequest request) {
        Map<AttachmentType, JPanel> values = new HashMap<AttachmentType, JPanel>() {
            {
                put(AttachmentType.NewDrugApprovalAttachment, new ApproveOrDeclineNewDrugWorkRequest(container, request));
            }
            
        };
        return values.get(request.getAttachment().getAttachmentType());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable newOrUpdatedWorkRequestTable;
    private javax.swing.JTable otherWorkRequest;
    private javax.swing.JButton viewDetailsBtn;
    // End of variables declaration//GEN-END:variables
}
