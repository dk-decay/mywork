/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.admin.drug.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.common.Status;
import org.biogen.business.drug.model.Drug;
import org.biogen.business.drug.model.DrugComposition;
import org.biogen.business.drug.model.Item;
import org.biogen.business.drug.model.ItemOrder;
import org.biogen.business.drug.model.ItemStatus;
import org.biogen.business.drug.model.RouteType;
import org.biogen.business.enterprise.model.DrugManufacturingBusiness;
import org.biogen.business.medicare.model.Disease;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.organization.admin.chem.view.ManageNewCompoundRequestPanel;
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.organization.work.model.WorkRequestStatus;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.BusinessUserRole;
import org.biogen.user.model.RoleType;
import org.biogen.user.model.UserType;
import org.biogen.work.attachment.model.AttachmentType;
import org.biogen.work.attachment.model.NewDrugApprovalAttachment;

/**
 *
 * @author deveshkandpal
 */
public class CreateNewDrugPanel extends javax.swing.JPanel {

    /**
     * Creates new form CreateNewDrugPanel
     */
    private JPanel container;
    private Ecosystem system;
    private DrugManufacturingBusiness enterprise;
    private DrugManufactuer organization;
    private BusinessUser user;

    public CreateNewDrugPanel(
            JPanel container,
            Ecosystem system,
            DrugManufacturingBusiness enterprise,
            DrugManufactuer organization,
            BusinessUser user
    ) {
        initComponents();
        this.container = container;
        this.system = system;
        this.enterprise = enterprise;
        this.organization = organization;
        this.user = user;
        populateTargetedDiseaseDropDown();
        populateCompoundTable();
    }

    public void populateCompoundTable() {

        DefaultTableModel model = (DefaultTableModel) compoundTable.getModel();
        model.setRowCount(0);
        model.addTableModelListener(a -> {
            System.out.println("Event fired");
            int row = a.getFirstRow();

            int col = a.getColumn();
            if (col == 2) {
                System.out.println("Event fired for checkbox >" + col);
                Compound compound = (Compound) model.getValueAt(row, 0);
                int total = this.organization.getInventory().getItemList().stream()
                        .filter(i -> i.getCompound() == compound).findFirst().get()
                        .getItemOrderList()
                        .stream()
                        .mapToInt(io -> io.getQuantity())
                        .sum();

                int needed = Integer.valueOf(String.valueOf(model.getValueAt(row, col)));
                model.setValueAt(total - needed, row, col - 1);
            } else {
                System.out.println("Event fired for checkbox >>" + col);
            }

        });

        this.organization.getInventory().getItemList()
                .stream()
                .collect(Collectors.toMap(Item::getCompound,
                        it -> it.getItemOrderList().stream()
                                .filter(inter -> inter.getItemStatus() == ItemStatus.APPROVED)
                                .mapToInt(q -> q.getQuantity()).sum()))
                .forEach((k, v) -> {
                    if (v != 0) {
                        Object[] arr = new Object[4];
                        arr[0] = k;
                        arr[1] = v;
                        arr[2] = 0;
                        arr[3] = false;

                        model.addRow(arr);
                    }
                });
        if (model.getRowCount() == 0) {
            submitBtn.setEnabled(false);

        }
    }

    public void populateTargetedDiseaseDropDown() {
        diseaseField.removeAllItems();
        this.system.getDiseaseStudyDirectory().getDiseaseList()
                .stream()
                .forEach(d -> {
                    diseaseField.addItem(d);
                });

    }

    public void populateTargedGeneTable() {

        DefaultTableModel model = (DefaultTableModel) targetedGeneField.getModel();

        model.setRowCount(0);
        Disease d = (Disease) diseaseField.getSelectedItem();
        d.getAffectedGenes()
                .stream()
                .forEach(g -> {
                    Object[] arr = new Object[1];
                    arr[0] = g;
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        drugNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        diseaseField = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        strengthField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        routeField = new javax.swing.JComboBox<>();
        submitBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        targetedGeneField = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        compoundTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Create New Drug");

        jLabel2.setText("Drug Name");

        jLabel3.setText("Targeted Disease");

        diseaseField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseaseFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Targeted Gene");

        jLabel5.setText("Strength");

        strengthField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strengthFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("Route");

        routeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ORAL", "INJECTION" }));

        submitBtn.setText("submit");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        backBtn.setText("back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        targetedGeneField.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Genes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(targetedGeneField);

        compoundTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Compound", "Quantity", "Selected Quantity", "Needed"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(compoundTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(drugNameField)
                    .addComponent(diseaseField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(strengthField)
                    .addComponent(routeField, 0, 123, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(backBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitBtn)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(drugNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(diseaseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(routeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(submitBtn)
                .addGap(7, 7, 7)
                .addComponent(backBtn)
                .addContainerGap(417, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:

        String drugName = drugNameField.getText();
        Disease targetedDisease = (Disease) diseaseField.getSelectedItem();
        int[] selectedGeneIndexes = targetedGeneField.getSelectedRows();
        List<String> targedGenes = IntStream.of(selectedGeneIndexes)
                .mapToObj(idx -> (String) targetedGeneField.getValueAt(idx, 0))
                .collect(Collectors.toList());
        int drugStrength = Integer.valueOf(strengthField.getText());
        String drugRoute = (String) routeField.getSelectedItem();

        if (!drugName.isEmpty() && targetedDisease != null && selectedGeneIndexes.length > 0
                && drugStrength != 0 && !drugRoute.isEmpty()) {

            createDrug(drugName, targetedDisease, targedGenes, drugStrength, drugRoute);
            redirectUser();

        } else {

            JOptionPane.showMessageDialog(null, "all fields are mandatory");
        }


    }//GEN-LAST:event_submitBtnActionPerformed

    public void createDrug(String drugName, Disease targetedDisease, List<String> targetedGenes,
            int drugStrength, String drugRoute) {
        Drug drug = this.system.getDrugDirectory().addNewDrug(this.organization.getOrgId());
        drug.setStatus(Status.NEW);
        drug.setDisease(targetedDisease);
        drug.setCreatedOn(new Date());
        drug.setName(drugName);
        drug.setRouteType(drugRoute.equals("ORAL") ? RouteType.ORAL : RouteType.INJECTION);
        drug.setStrength(drugStrength);
        drug.getTargetedGenes().addAll(targetedGenes);
        this.organization.getDrugList().add(drug);
        this.enterprise.getDrugList().add(drug);
        int[] selectedCompounds = compoundTable.getSelectedRows();

        DefaultTableModel model = (DefaultTableModel) compoundTable.getModel();

        IntStream.range(0, model.getRowCount())
                .forEach(r -> {
                    boolean checked = (boolean) model.getValueAt(r, 3);
                    if (checked) {

                        // figure this out, if you want to remove all and then
                        // create new.
                        Compound c = (Compound) model.getValueAt(r, 0);
                        int newQuantity = Integer.valueOf(String.valueOf(model.getValueAt(r, 1)));
                        int selectedQuantity = Integer.valueOf(String.valueOf(model.getValueAt(r, 2)));
                        // update the amount of chemical units you have

                        DrugComposition dc = drug.getDrugCompositionCatalog().addDrugComposition();
                        dc.setCompound(c);
                        dc.setUnit(selectedQuantity);

                        Item item = this.organization.getInventory()
                                .getItemList()
                                .stream()
                                .filter(it -> it.getCompound() == c)
                                .findFirst()
                                .get();

                        for (ItemOrder io : item.getItemOrderList()) {
                            if (io.getQuantity() < selectedQuantity) {
                                selectedQuantity -= io.getQuantity();
                                io.setQuantity(0);
                            } else if (io.getQuantity() == selectedQuantity) {
                                selectedQuantity -= io.getQuantity();
                                io.setQuantity(0);
                                break;
                            } else {
                                io.setQuantity(io.getQuantity() - selectedQuantity);
                                selectedQuantity = 0;
                                break;
                            }
                        }

                    }

                });

        WorkRequest workRequest = this.enterprise.getWorkQueue().addWorkRequest();
        NewDrugApprovalAttachment attachment = (NewDrugApprovalAttachment) workRequest.addAttachment(AttachmentType.NewDrugApprovalAttachment);
        attachment.setDrug(drug);
        attachment.setWorkRequestId(workRequest.getWorkRequestId());

        workRequest.setComments("Seeking Approval for New Drug");
        workRequest.setSender(user);

        BusinessUser receiver = this.enterprise.getUserList().stream().filter(u -> u.getType() == UserType.BU)
                .map(us -> (BusinessUser) us)
                .filter(use -> use.getRole().getType() == RoleType.ENTERPRISE_ADMIN)
                .findFirst()
                .get();

        workRequest.setReceiver(receiver);
        workRequest.setStartDate(new Date());
        workRequest.setStatus(WorkRequestStatus.NEW);

    }


    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        redirectUser();
    }//GEN-LAST:event_backBtnActionPerformed

    public void redirectUser() {

        container.remove(this);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        ManageDrugCreationPanel previousPanel = (ManageDrugCreationPanel) component;
        previousPanel.populateApprovedOrDeniedCompoundRequest();
        previousPanel.populateNewDrugTable();
        //previousPanel.populateNewCompoundRequest();
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);
    }
    private void diseaseFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseaseFieldActionPerformed
        // TODO add your handling code here:
        populateTargedGeneTable();
    }//GEN-LAST:event_diseaseFieldActionPerformed

    private void strengthFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strengthFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_strengthFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTable compoundTable;
    private javax.swing.JComboBox diseaseField;
    private javax.swing.JTextField drugNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> routeField;
    private javax.swing.JTextField strengthField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JTable targetedGeneField;
    // End of variables declaration//GEN-END:variables
}
