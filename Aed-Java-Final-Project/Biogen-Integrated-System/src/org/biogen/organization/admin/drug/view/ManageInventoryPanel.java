/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.organization.admin.drug.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.biogen.business.chem.model.Compound;
import org.biogen.business.common.Status;
import org.biogen.business.drug.model.Item;
import org.biogen.business.drug.model.ItemOrder;
import org.biogen.business.drug.model.ItemStatus;
import org.biogen.business.enterprise.model.BusinessUnitType;
import org.biogen.business.enterprise.model.DrugManufacturingBusiness;
import org.biogen.ecosystem.model.Ecosystem;
import org.biogen.ecosystem.model.Organization;
import org.biogen.network.model.OperationalRegion;
import org.biogen.order.model.ChemicalSynthesisOrder;
import org.biogen.organization.admin.chem.view.EditNewCompoundPanel;
import org.biogen.organization.model.ChemicalManufacturer;
import org.biogen.organization.model.DrugManufactuer;
import org.biogen.organization.work.model.WorkRequest;
import org.biogen.organization.work.model.WorkRequestStatus;
import org.biogen.user.model.BusinessUser;
import org.biogen.user.model.RoleType;
import org.biogen.user.model.UserType;
import org.biogen.work.attachment.model.InventoryUpdateAttachment;

/**
 *
 * @author deveshkandpal
 */
public class ManageInventoryPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageInventoryPanel
     */
    private JPanel container;
    private BusinessUser user;
    private Ecosystem system;
    private DrugManufactuer organization;
    private OperationalRegion network;
    private DrugManufacturingBusiness enterprise;

    public ManageInventoryPanel(JPanel container,
            BusinessUser user,
            Ecosystem system,
            DrugManufactuer organization,
            DrugManufacturingBusiness enterprise,
            OperationalRegion network) {
        initComponents();
        this.container = container;
        this.user = user;
        this.system = system;
        this.organization = organization;
        this.network = network;
        this.enterprise = enterprise;
        populateExistingInventory();
        populateAnticipatedInventory();
        populateCompoundTable();
    }

    public void populateCompoundTable() {

        DefaultTableModel model = (DefaultTableModel) compoundNameTable.getModel();
        model.setRowCount(0);
        prepareListModel().stream()
                .forEach(pop -> {
                    Object[] arr = new Object[1];
                    arr[0] = pop;
                    model.addRow(arr);
                });

    }

    public Set<Compound> prepareListModel() {

        Set<Compound> synthesisSet = this.system.getOrganizationDirectory()
                .getOrganizationList()
                .stream()
                .filter(org -> org.getBuType() == BusinessUnitType.CHEMICAL)
                .map(m -> (ChemicalManufacturer) m)
                .map(m1 -> m1.getChemicalSynthesisOrderList())
                .flatMap(flat -> flat.stream())
                .map(newMap -> newMap.getCompound())
                .distinct()
                .collect(Collectors.toSet());

        System.out.println("Compound populating");

        return synthesisSet;

    }

    public void populateExistingInventory() {

        DefaultTableModel model = (DefaultTableModel) existingInventoryTable.getModel();
        model.setRowCount(0);
        // combine result of same item from multiple manufacturers

        this.organization.getInventory().getItemList()
                .stream()
                .collect(Collectors.toMap(Item::getCompound,
                        it -> it.getItemOrderList().stream()
                                .filter(inter -> inter.getItemStatus() == ItemStatus.APPROVED)
                                .mapToInt(q -> q.getQuantity()).sum()))
                .forEach((k, v) -> {
                    if (v != 0) {
                        Object[] arr = new Object[2];
                        arr[0] = k;
                        arr[1] = v;
                        model.addRow(arr);
                    }
                });

    }

    public void populateAnticipatedInventory() {

        DefaultTableModel model = (DefaultTableModel) anticipatedInventoryTable.getModel();
        model.setRowCount(0);

        this.organization
                .getInventory()
                .getItemList()
                .stream()
                .forEach(it -> {
                    it.getItemOrderList()
                            .stream()
                            .filter(oi -> oi.getItemStatus() != ItemStatus.APPROVED
                            && oi.getItemStatus() != ItemStatus.CANCELLED)
                            .forEach(a -> {

                                Object[] arr = new Object[6];
                                arr[0] = it;
                                arr[1] = a; // prints quantity
                                arr[2] = a.getOrganization();
                                arr[3] = a.getRegion();
                                arr[4] = a.getItemStatus();
                                arr[5] = a.getRequestedOn();
                                model.addRow(arr);

                            });

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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        existingInventoryTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        searchInNetworkBtn = new javax.swing.JCheckBox();
        searchBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        searchResultTable = new javax.swing.JTable();
        viewBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        anticipatedInventoryTable = new javax.swing.JTable();
        updateRequestBtn = new javax.swing.JButton();
        deleteRequestBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        compoundNameTable = new javax.swing.JTable();

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
        jScrollPane3.setViewportView(jTable2);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Manage Inventory");

        existingInventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Compound", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(existingInventoryTable);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Existing Inventory");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Search Compound");

        jLabel5.setText("Search In Network");

        searchBtn.setText("search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        searchResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Manufacturer", "Region", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(searchResultTable);

        viewBtn.setText("view");
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        backBtn.setText("back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Anticipated Inventory");

        anticipatedInventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Compound", "Quantity", "Manufacturer", "Region", "Status", "Requested On"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(anticipatedInventoryTable);

        updateRequestBtn.setText("update");
        updateRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRequestBtnActionPerformed(evt);
            }
        });

        deleteRequestBtn.setText("delete");
        deleteRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRequestBtnActionPerformed(evt);
            }
        });

        compoundNameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Compound Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(compoundNameTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(379, 379, 379)
                                    .addComponent(viewBtn))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(220, 220, 220)
                                    .addComponent(deleteRequestBtn)
                                    .addGap(29, 29, 29)
                                    .addComponent(updateRequestBtn))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(searchInNetworkBtn))
                                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(backBtn)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateRequestBtn)
                    .addComponent(deleteRequestBtn))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(searchInNetworkBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(viewBtn)
                .addGap(18, 18, 18)
                .addComponent(backBtn)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = searchResultTable.getSelectedRow();
        if (selectedRow >= 0) {
            ChemicalSynthesisOrder cso = (ChemicalSynthesisOrder) searchResultTable.getValueAt(selectedRow, 0);
            ManageInventoryOrderPanel panel = new ManageInventoryOrderPanel(container,
                    system, user, organization, enterprise, cso);
            CardLayout layout = (CardLayout) container.getLayout();
            container.add("ManageInventoryOrderPanel", panel);
            layout.next(container);
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item to be ordered");
        }


    }//GEN-LAST:event_viewBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:

        container.remove(this);
        Component[] componentArray = container.getComponents();
        Component component = componentArray[componentArray.length - 1];
        CardLayout layout = (CardLayout) container.getLayout();
        layout.previous(container);

    }//GEN-LAST:event_backBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        boolean checkBox = searchInNetworkBtn.isSelected();

        int[] selectedRows = compoundNameTable
                .getSelectedRows();
        
        if (selectedRows.length > 0) {

            // This needs to change because you are creating a range of length
            // whereas what you have are the indices
            List<Compound> synthesizedList = IntStream.of(selectedRows)
                    .mapToObj(idx -> (Compound) compoundNameTable.getValueAt(idx, 0))
                    .collect(Collectors.toList());

            List<ChemicalSynthesisOrder> filteredList = null;

            if (checkBox) {
                System.out.println("checkbox selected" + this.network
                        .getOrganizationList().size());
                filteredList = this.network
                        .getOrganizationList()
                        .stream()
                        .filter(o -> o.getBuType() == BusinessUnitType.CHEMICAL)
                        .map(org -> (ChemicalManufacturer) org)
                        .map(c -> c.getChemicalSynthesisOrderList())
                        .flatMap(so -> so.stream())
                        .filter(s -> synthesizedList.contains(s.getCompound()))
                        .distinct()
                        .collect(Collectors.toList());

            } else {
                filteredList = this.system.getOrganizationDirectory()
                        .getOrganizationList()
                        .stream()
                        .filter(org -> org.getBuType() == BusinessUnitType.CHEMICAL)
                        .map(o -> (ChemicalManufacturer) o)
                        .map(c -> c.getChemicalSynthesisOrderList())
                        .flatMap(c -> c.stream())
                        .filter(s -> synthesizedList.contains(s.getCompound()))
                        .distinct()
                        .collect(Collectors.toList());
            }
            populateTable(filteredList);
        } else {

            JOptionPane.showMessageDialog(null, "Please select atleast one row");
        }


    }//GEN-LAST:event_searchBtnActionPerformed

    public void populateTable(List<ChemicalSynthesisOrder> filteredList) {
        DefaultTableModel model = (DefaultTableModel) searchResultTable.getModel();
        model.setRowCount(0);

        filteredList.stream()
                .forEach(c -> {
                    Object[] arr = new Object[4];

                    arr[0] = c;
                    System.out.println("filtered object c=> : " + c);
                    System.out.println("filtered object c=> : " + c.getCompound().getManufacturerId());
                    Organization org = this.system.getOrganizationDirectory().getOrganizationList()
                            .stream().filter(o -> o == c.getSynthesizingManufacturerId())
                            .findFirst().get();

                    arr[1] = org;
                    arr[2] = this.system.getOpRegionDirectory().getOperationalRegionList()
                            .stream().filter(n -> n.getRegionId() == org.getNetworkId());
                    arr[3] = c.getQuantity();
                    model.addRow(arr);
                });

    }

    private void updateRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRequestBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = anticipatedInventoryTable.getSelectedRow();
        if (selectedRow >= 0) {

            Item item = (Item) anticipatedInventoryTable.getValueAt(selectedRow, 0);
            ItemOrder itemOrder = (ItemOrder) anticipatedInventoryTable.getValueAt(selectedRow, 1);

            EditAnticipatedInventoryPanel panel = new EditAnticipatedInventoryPanel(itemOrder,
                    container, system, item, organization, enterprise);
            CardLayout layout = (CardLayout) container.getLayout();
            container.add("EditAnticipatedInventoryPanel", panel);
            layout.next(container);
        } else {
            JOptionPane.showMessageDialog(null, "Please Select a compound request to be modified");
        }
    }//GEN-LAST:event_updateRequestBtnActionPerformed

    private void deleteRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRequestBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = anticipatedInventoryTable.getSelectedRow();
        if (selectedRow >= 0) {
            Item item = (Item) anticipatedInventoryTable.getValueAt(selectedRow, 0);
            ItemOrder itemOrder = (ItemOrder) anticipatedInventoryTable.getValueAt(selectedRow, 1);
            updateWorkOrder(itemOrder);
            populateAnticipatedInventory();
            item.removeItemOrder(itemOrder);
            JOptionPane.showMessageDialog(null, "Placed Order deleted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row");
        }
    }//GEN-LAST:event_deleteRequestBtnActionPerformed

    public void updateWorkOrder(ItemOrder itemOrder) {
        itemOrder.setItemStatus(ItemStatus.CANCELLED);
        ChemicalManufacturer receiverOrg = (ChemicalManufacturer) itemOrder.getOrganization();

        BusinessUser receiver = receiverOrg.getUserList()
                .stream()
                .filter(u -> u.getType() == UserType.BU)
                .map(us -> (BusinessUser) us)
                .filter(use -> use.getRole().getType() == RoleType.ORG_ADMIN)
                .findFirst()
                .get();

        WorkRequest request = receiver.getWorkQueue().getWorkRequestList().
                stream().filter(wr -> wr.getWorkRequestId() == itemOrder.getWorkRequestId())
                .findFirst()
                .get();
        request.setComments("Order has been cancelled");
        request.setStartDate(new Date());
        request.setStatus(WorkRequestStatus.CANCELLED);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable anticipatedInventoryTable;
    private javax.swing.JButton backBtn;
    private javax.swing.JTable compoundNameTable;
    private javax.swing.JButton deleteRequestBtn;
    private javax.swing.JTable existingInventoryTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton searchBtn;
    private javax.swing.JCheckBox searchInNetworkBtn;
    private javax.swing.JTable searchResultTable;
    private javax.swing.JButton updateRequestBtn;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
}
