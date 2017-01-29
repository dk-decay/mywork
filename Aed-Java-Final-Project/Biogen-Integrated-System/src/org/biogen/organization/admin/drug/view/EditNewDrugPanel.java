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
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.user.model.BusinessUser;

/**
 *
 * @author deveshkandpal
 */
public class EditNewDrugPanel extends javax.swing.JPanel {

    /**
     * Creates new form EditNewDrugPanel
     */
    private JPanel container;
    private Ecosystem system;
    private DrugManufacturingBusiness enterprise;
    private DrugManufactuer organization;
    private BusinessUser user;
    private Drug drug;

    public EditNewDrugPanel(
            JPanel container,
            Ecosystem system,
            DrugManufacturingBusiness enterprise,
            DrugManufactuer organization,
            BusinessUser user,
            Drug drug
    ) {
        initComponents();
        this.container = container;
        this.system = system;
        this.enterprise = enterprise;
        this.organization = organization;
        this.user = user;
        this.drug = drug;
        //rightTable.getModel().addTableModelListener(rightTable);
        populateForm();
    }

    public void populateForm() {
        drugNameField.setText(drug.getName());
        populateTargetedDiseaseDropDown(this.drug.getDisease());
        strengthField.setText(String.valueOf(drug.getStrength()));
        routeField.setSelectedItem(drug.getRouteType() == RouteType.ORAL ? "ORAL" : "INJECTION");
        populateTargedGeneTable(this.drug.getTargetedGenes());
        populateCompoundTable();

    }

    public void populateCompoundTable() {

        DefaultTableModel model = (DefaultTableModel) compoundTable.getModel();
        model.setRowCount(0);

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

                        DrugComposition d = drug.getDrugCompositionCatalog()
                                .getDrugCompositionList()
                                .stream()
                                .filter(c -> c.getCompound() == k)
                                .findFirst()
                                .orElse(null);

                        if (d != null) {
                            arr[2] = d.getUnit();
                            arr[3] = true;
                        } else {
                            arr[2] = 0;
                            arr[3] = false;
                        }
                        model.addRow(arr);
                    }
                });

        model.addTableModelListener(a -> {
            System.out.println("Event fired");
            int row = a.getFirstRow();

            int col = a.getColumn();
            if (col == 2) {
                Compound compound = (Compound) model.getValueAt(row, 0);
                int total = this.organization.getInventory().getItemList().stream()
                        .filter(i -> i.getCompound() == compound).findFirst().get()
                        .getItemOrderList()
                        .stream()
                        .mapToInt(io -> io.getQuantity())
                        .sum();
                int originalSelected = drug.getDrugCompositionCatalog().getDrugCompositionList()
                        .stream().filter(f -> f.getCompound() == compound)
                        .findFirst().get().getUnit();

                int needed = Integer.valueOf(String.valueOf(model.getValueAt(row, col)));
                int difference = originalSelected - needed;
                model.setValueAt(total + difference, row, col - 1);
            } else {
                System.out.println("Event fired for checkbox" + col);
            }

        });
    }

    public void populateTargetedDiseaseDropDown(Disease disease) {
        diseaseField.removeAllItems();
        this.system.getDiseaseStudyDirectory().getDiseaseList()
                .stream()
                .filter(d -> d != disease)
                .forEach(d -> {
                    diseaseField.addItem(d);
                });
        diseaseField.setSelectedItem(disease);

    }

    public void populateTargedGeneTable(List<String> targetedGene) {

        DefaultTableModel model = (DefaultTableModel) targetedGeneField.getModel();

        model.setRowCount(0);
        targetedGene
                .stream()
                .forEach(f -> {
                    Object[] arr = new Object[1];
                    arr[0] = f;
                    model.addRow(arr);
                });
        targetedGeneField.setRowSelectionInterval(0, targetedGene.size() - 1);
        Disease d = (Disease) diseaseField.getSelectedItem();
        d.getAffectedGenes()
                .stream()
                .filter(f -> !targetedGene.contains(f))
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
        diseaseField = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        targetedGeneField = new javax.swing.JTable();
        strengthField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        routeField = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        compoundTable = new javax.swing.JTable();
        submitBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Edit New Drug");

        jLabel2.setText("Drug Name");

        diseaseField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseaseFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Targeted Disease");

        jLabel4.setText("Targeted Gene");

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

        jLabel5.setText("Strength");

        jLabel6.setText("Route");

        routeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ORAL", "INJECTION" }));

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

        submitBtn.setText("update");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(398, Short.MAX_VALUE)
                .addComponent(submitBtn)
                .addGap(65, 65, 65))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
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
                        .addGap(88, 88, 88)
                        .addComponent(backBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(routeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(strengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void diseaseFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseaseFieldActionPerformed
        // TODO add your handling code here:
        //        populateTargedGeneTable(targetedGene);
    }//GEN-LAST:event_diseaseFieldActionPerformed

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

        createDrug(drugName, targetedDisease, targedGenes, drugStrength, drugRoute);
        redirectUser();
    }//GEN-LAST:event_submitBtnActionPerformed

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

    public void createDrug(String drugName, Disease targetedDisease, List<String> targedGenes, int drugStrength, String drugRoute) {
        this.drug.setCreatedOn(new Date());
        this.drug.setDisease(targetedDisease);
        this.drug.setName(drugName);
        drug.setRouteType(drugRoute.equals("ORAL") ? RouteType.ORAL : RouteType.INJECTION);
        this.drug.setStatus(Status.UPDATED);
        this.drug.setStrength(drugStrength);
        editDrugComposition();

    }

    public void editDrugComposition() {

        DefaultTableModel model = (DefaultTableModel) compoundTable.getModel();

        IntStream.range(0, model.getRowCount())
                .forEach(r -> {
                    boolean checked = (boolean) model.getValueAt(r, 3);
                    if (checked) {

                        // figure this out, if you want to remove all and then
                        // create new.
                        Compound c = (Compound) model.getValueAt(r, 0);
                        //int newQuantity = Integer.valueOf(String.valueOf(model.getValueAt(r, 1)));
                        int selectedQuantity = Integer.valueOf(String.valueOf(model.getValueAt(r, 2)));
                        // update the amount of chemical units you have
                        DrugComposition oldDrugComposition = drug.getDrugCompositionCatalog().getDrugCompositionList()
                                .stream()
                                .filter(dcl -> dcl.getCompound() == c)
                                .findFirst()
                                .orElse(null);

                        Item inventoryItem = this.organization.getInventory().getItemList()
                                .stream()
                                .filter(it -> it.getCompound() == c)
                                .findFirst()
                                .get();
                        int difference = 0;
                        if (oldDrugComposition != null) {
                            int oldQuantity = oldDrugComposition.getUnit();
                            difference = selectedQuantity - oldQuantity;

                        } else {
                            difference = selectedQuantity;
                        }

                        for (ItemOrder io : inventoryItem.getItemOrderList()) {
                            if ((io.getQuantity() - difference) < 0) {
                                difference -= io.getQuantity();
                                io.setQuantity(0);
                            } else if (io.getQuantity() - difference == 0) {
                                difference -= io.getQuantity();
                                io.setQuantity(0);
                                break;
                            } else {
                                io.setQuantity(io.getQuantity() - difference);
                                difference = 0;
                                break;
                            }
                        }

                        if (oldDrugComposition != null) {
                            oldDrugComposition.setUnit(selectedQuantity);

                        } else {

                            DrugComposition dc = drug.getDrugCompositionCatalog().addDrugComposition();
                            dc.setCompound(c);
                            dc.setUnit(selectedQuantity);

                        }

                    }

                });

    }

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
