/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biogen.ecosystem.admin.view;
//
//import com.teamdev.jxbrowser.chromium.Browser;
//import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author deveshkandpal
 */
public class AnalyticsDashboard extends javax.swing.JPanel {

    /**
     * Creates new form AnaltricsDashboard
     */
    public AnalyticsDashboard() {
        initComponents();
        construct();
    }

    public void construct() {
//        Browser browser = new Browser();
//        BrowserView browserView = new BrowserView(browser);
//        browser.loadURL("http://www.google.com");
//
//        JPanel panel = new JPanel();
//
//        CardLayout layout = (CardLayout) browserPanel.getLayout();
//        browserPanel.add("browserWindow", browserView);
//        layout.next(browserPanel);




//        
//        
//         
//
//        JFrame frame = new JFrame();
//       // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.add(browserView, BorderLayout.CENTER);
//        frame.setSize(700, 500);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        browserPanel = new javax.swing.JPanel();

        browserPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));
        browserPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(browserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(browserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel browserPanel;
    // End of variables declaration//GEN-END:variables
}
