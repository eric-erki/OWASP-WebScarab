/*
 * SpiderPanel.java
 *
 * Created on August 6, 2003, 10:01 PM
 */

package org.owasp.webscarab.ui.swing.spider;

import org.owasp.webscarab.ui.Framework;

import org.owasp.webscarab.model.URLTreeModel;
import org.owasp.webscarab.ui.swing.SwingPlugin;
import org.owasp.webscarab.plugin.spider.Spider;

import javax.swing.tree.TreePath;
import java.net.URL;
/**
 *
 * @author  rdawes
 */
public class SpiderPanel extends javax.swing.JPanel implements SwingPlugin {

    private Spider _spider;
    
    /** Creates new form SpiderPanel */
    public SpiderPanel(Framework framework) {
        _spider = new Spider(framework);
        framework.addPlugin(_spider);
        
        initComponents();
        unseenLinkTable.setModel(_spider.getUnseenLinkTableModel());
        unseenLinkTree.setModel(_spider.getUnseenLinkTreeModel());
        unseenLinkTree.setRootVisible(false);
        unseenLinkTree.setShowsRootHandles(true);
        configure();
    }
    
    public javax.swing.JPanel getPanel() {
        return this;
    }
    
    public String getPluginName() {
        return new String("Spider");
    }    
    
    private void configure() {
        recursiveCheckBox.setSelected(_spider.getRecursive());
        cookieSyncCheckBox.setSelected(_spider.getCookieSync());
        domainRegexTextField.setText(_spider.getAllowedDomains());
        pathRegexTextField.setText(_spider.getForbiddenPaths());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        domainRegexTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pathRegexTextField = new javax.swing.JTextField();
        recursiveCheckBox = new javax.swing.JCheckBox();
        unseenTabbedPane = new javax.swing.JTabbedPane();
        linkTablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unseenLinkTable = new javax.swing.JTable();
        linkTableFetchButton = new javax.swing.JButton();
        linkTreePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        unseenLinkTree = new javax.swing.JTree();
        linkTreeFetchSelectionButton = new javax.swing.JButton();
        linkTreeFetchTreeButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        cookieSyncCheckBox = new javax.swing.JCheckBox();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Allowed Domains");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jLabel1, gridBagConstraints);

        domainRegexTextField.setToolTipText("A regular expression describing which domains to include");
        domainRegexTextField.setMinimumSize(new java.awt.Dimension(60, 19));
        domainRegexTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                domainRegexTextFieldActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(domainRegexTextField, gridBagConstraints);

        jLabel2.setText("Forbidden Paths");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(jLabel2, gridBagConstraints);

        pathRegexTextField.setToolTipText("A regular expression describing which paths to exclude");
        pathRegexTextField.setMinimumSize(new java.awt.Dimension(60, 19));
        pathRegexTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pathRegexTextFieldActionPerformed(evt);
            }
        });

        pathRegexTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pathRegexTextFieldFocusLost(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(pathRegexTextField, gridBagConstraints);

        recursiveCheckBox.setText("Fetch Recursively");
        recursiveCheckBox.setToolTipText("Enables recursive fetching of Links");
        recursiveCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        recursiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recursiveCheckBoxActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(recursiveCheckBox, gridBagConstraints);

        linkTablePanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setViewportView(unseenLinkTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        linkTablePanel.add(jScrollPane1, gridBagConstraints);

        linkTableFetchButton.setText("Fetch Selection");
        linkTableFetchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkTableFetchButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        linkTablePanel.add(linkTableFetchButton, gridBagConstraints);

        unseenTabbedPane.addTab("Link Table", linkTablePanel);

        linkTreePanel.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setViewportView(unseenLinkTree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        linkTreePanel.add(jScrollPane2, gridBagConstraints);

        linkTreeFetchSelectionButton.setText("Fetch Selection");
        linkTreeFetchSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkTreeFetchSelectionButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        linkTreePanel.add(linkTreeFetchSelectionButton, gridBagConstraints);

        linkTreeFetchTreeButton.setText("Fetch Tree");
        linkTreeFetchTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkTreeFetchTreeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        linkTreePanel.add(linkTreeFetchTreeButton, gridBagConstraints);

        unseenTabbedPane.addTab("Link Tree", linkTreePanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(unseenTabbedPane, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        add(stopButton, gridBagConstraints);

        cookieSyncCheckBox.setText("Synchronise cookies");
        cookieSyncCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cookieSyncCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSyncCheckBoxActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(cookieSyncCheckBox, gridBagConstraints);

    }//GEN-END:initComponents

    private void cookieSyncCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSyncCheckBoxActionPerformed
        _spider.setCookieSync(cookieSyncCheckBox.isSelected());
    }//GEN-LAST:event_cookieSyncCheckBoxActionPerformed

    private void linkTreeFetchTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkTreeFetchTreeButtonActionPerformed
        TreePath[] selection = unseenLinkTree.getSelectionPaths();
        if (selection != null && selection.length==1) {
            URLTreeModel.URLNode node = (URLTreeModel.URLNode) selection[0].getLastPathComponent();
            String root = node.getURL();
            _spider.requestLinksUnder(root);
        } else {
            System.out.println("Cannot fetch a tree if there are 0 or many paths selected!");
        }
    }//GEN-LAST:event_linkTreeFetchTreeButtonActionPerformed

    private void linkTreeFetchSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkTreeFetchSelectionButtonActionPerformed
        TreePath[] selection = unseenLinkTree.getSelectionPaths();
        if (selection == null || selection.length == 0) return;
        unseenLinkTree.clearSelection();
        String[] urls = new String[selection.length];
        for (int i=0; i<selection.length; i++) {
            URLTreeModel.URLNode node = (URLTreeModel.URLNode) selection[i].getLastPathComponent();
            urls[i] = node.getURL();
        }
        _spider.requestLinks(urls);
    }//GEN-LAST:event_linkTreeFetchSelectionButtonActionPerformed

    private void pathRegexTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pathRegexTextFieldFocusLost
        _spider.setForbiddenPaths(pathRegexTextField.getText());
        System.out.println("pathRegex focus lost");
    }//GEN-LAST:event_pathRegexTextFieldFocusLost

    private void pathRegexTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pathRegexTextFieldActionPerformed
        _spider.setForbiddenPaths(pathRegexTextField.getText());
        System.out.println("path regex actionPerformed");
    }//GEN-LAST:event_pathRegexTextFieldActionPerformed

    private void domainRegexTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_domainRegexTextFieldActionPerformed
        _spider.setAllowedDomains(domainRegexTextField.getText());
    }//GEN-LAST:event_domainRegexTextFieldActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        _spider.resetRequestQueue();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void linkTableFetchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkTableFetchButtonActionPerformed
        int[] rows = unseenLinkTable.getSelectedRows();
        if (rows.length<1) return;
        unseenLinkTable.clearSelection();
        String[] urls = new String[rows.length];
        for (int i=0; i<rows.length; i++) {
            urls[i] = ((URL) unseenLinkTable.getModel().getValueAt(rows[i], 1)).toString();
            System.out.println("Selected '" + urls[i] + "'");
        }
        _spider.requestLinks(urls);
    }//GEN-LAST:event_linkTableFetchButtonActionPerformed

    private void recursiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recursiveCheckBoxActionPerformed
        _spider.setRecursive(recursiveCheckBox.isSelected());
    }//GEN-LAST:event_recursiveCheckBoxActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel linkTablePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox recursiveCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JCheckBox cookieSyncCheckBox;
    private javax.swing.JButton linkTreeFetchTreeButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton linkTableFetchButton;
    private javax.swing.JTextField pathRegexTextField;
    private javax.swing.JTree unseenLinkTree;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField domainRegexTextField;
    private javax.swing.JButton linkTreeFetchSelectionButton;
    private javax.swing.JTable unseenLinkTable;
    private javax.swing.JTabbedPane unseenTabbedPane;
    private javax.swing.JPanel linkTreePanel;
    // End of variables declaration//GEN-END:variables
    
}
