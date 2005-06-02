/***********************************************************************
 *
 * $CVSHeader$
 *
 * This file is part of WebScarab, an Open Web Application Security
 * Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2004 Rogan Dawes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Getting Source
 * ==============
 *
 * Source for this application is maintained at Sourceforge.net, a
 * repository for free software projects.
 *
 * For details, please see http://www.sourceforge.net/projects/owasp
 *
 */

/*
 * MessagePanel.java
 *
 * Created on November 6, 2003, 8:43 AM
 */

package org.owasp.webscarab.ui.swing.editors;

import org.owasp.webscarab.model.Preferences;
import org.owasp.webscarab.model.NamedValue;

import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;

import javax.swing.table.TableColumn;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import java.awt.Component;
import javax.swing.CellEditor;

import org.owasp.webscarab.util.Encoding;

/**
 *
 * @author  rdawes
 */
public class UrlEncodedPanel extends javax.swing.JPanel implements ByteArrayEditor {
    
    private boolean _editable = false;
    private boolean _modified = false;
    private NamedValueTableModel _tableModel;
    private String _data = null;
    
    private List _values = new ArrayList();
    
    private ColumnWidthListener _columnWidthListener = new ColumnWidthListener();
    
    /** Creates new form MessagePanel */
    public UrlEncodedPanel() {
        initComponents();
        setName("URLEncoded");
        _tableModel  = new NamedValueTableModel();
        headerTable.setModel(_tableModel);
        setEditable(_editable);
        setColumnWidth(0,150);
        setColumnWidth(1,500);
    }
    
    private void setColumnWidth(int columnIndex, int def) {
        TableColumn column = headerTable.getColumnModel().getColumn(columnIndex);
        int index = column.getModelIndex();
        String name = headerTable.getModel().getColumnName(index);
        String preference = Preferences.getPreference("UrlEncoded." + name + ".width", Integer.toString(def));
        try {
            column.setPreferredWidth(Integer.parseInt(preference));
        } catch (NumberFormatException nfe) {
            column.setPreferredWidth(def);
        }
        column.addPropertyChangeListener(_columnWidthListener);
    }
    
    public String[] getContentTypes() {
        return new String[] { "application/x-www-form-urlencoded" };
    }
    
    public void setBytes(String contentType, byte[] bytes) {
        if (bytes == null) {
            _data = null;
            _values.clear();
        } else {
            try {
                _data = new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {}
            NamedValue[] values = NamedValue.splitNamedValues(_data, "&", "=");
            String value, decoded;
            for (int i=0; i<values.length; i++) {
                value = values[i].getValue();
                decoded = Encoding.urlDecode(value);
                if (value != null && !value.equals(decoded)) {
                    values[i] = new NamedValue(values[i].getName(), decoded);
                }
                _values.add(values[i]);
            }
        }
        _tableModel.fireTableDataChanged();
        _modified = false;
    }
    
    public byte[] getBytes() {
        if (_editable && isModified()) {
            StringBuffer buff = new StringBuffer();
            for (int i=0; i<_values.size(); i++) {
                NamedValue value = (NamedValue) _values.get(i);
                if (value.getName() == null || value.getName().equals("")) continue;
                if (i>0) buff.append("&");
                buff.append(Encoding.urlEncode(value.getName())).append("=");
                if (value.getValue() != null)
                    buff.append(Encoding.urlEncode(value.getValue()));
            }
            _data = buff.toString();
        }
        if (_data == null) {
            return new byte[0];
        } else {
            try {
                return _data.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                return new byte[0];
            }
        }
    }
    
    public void setEditable(boolean editable) {
        _editable = editable;
        buttonPanel.setVisible(_editable);
        java.awt.Color color;
        if (_editable) {
            color = java.awt.Color.WHITE;
        } else {
            color = new java.awt.Color(204, 204, 204);
        }
        headerTable.setBackground(color);
    }
    
    public boolean isModified() {
        if (headerTable.isEditing()) {
            headerTable.getCellEditor().stopCellEditing();
        }
        return _editable && _modified;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        headerTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        insertButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        setPreferredSize(new java.awt.Dimension(402, 102));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 50));
        headerTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(headerTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        buttonPanel.add(insertButton, gridBagConstraints);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        buttonPanel.add(deleteButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        add(buttonPanel, gridBagConstraints);

    }//GEN-END:initComponents
    
    public void insertRow(int row) {
        _values.add(row, new NamedValue("Variable", "value"));
        _modified = true;
        _tableModel.fireTableRowsInserted(row, row);
    }
    
    public void removeRow(int row) {
        _values.remove(row);
        _modified = true;
        _tableModel.fireTableRowsDeleted(row, row);
    }
    
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int rowIndex = headerTable.getSelectedRow();
        if (rowIndex > -1) {
            removeRow(rowIndex);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed
    
    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        int rowIndex = headerTable.getSelectedRow();
        if (rowIndex > -1) {
            insertRow(rowIndex);
        } else {
            insertRow(_tableModel.getRowCount());
        }
    }//GEN-LAST:event_insertButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTable headerTable;
    private javax.swing.JButton insertButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private class NamedValueTableModel extends AbstractTableModel {
        
        private String[] _names = new String[] { "Variable", "Value"};
        
        public String getColumnName(int column) {
            return _names[column];
        }
        
        public int getColumnCount() {
            return 2;
        }
        
        public int getRowCount() {
            return _values.size();
        }
        
        public Object getValueAt(int row, int column) {
            if (row > _values.size()-1) return "ERROR";
            NamedValue nv = (NamedValue) _values.get(row);
            if (column == 0) return nv.getName();
            return nv.getValue();
        }
        
        public void setValueAt(Object aValue, int row, int col) {
            if (_editable && aValue instanceof String) {
                NamedValue nv = (NamedValue) _values.get(row);
                if (col == 0) {
                    _values.set(row, new NamedValue((String)aValue, nv.getValue()));
                } else {
                    _values.set(row, new NamedValue(nv.getName(), (String) aValue));
                }
                _modified = true;
                fireTableCellUpdated(row, col);
            }
        }
        
        public boolean isCellEditable(int row, int column) {
            return _editable;
        }
        
    }
    
    private class ColumnWidthListener implements PropertyChangeListener {
        
        public void propertyChange(PropertyChangeEvent evt) {
            String property = evt.getPropertyName();
            if (property == null || !"preferredWidth".equals(property)) return;
            if (! (evt.getSource() instanceof TableColumn)) return;
            int index = ((TableColumn)evt.getSource()).getModelIndex();
            String name = headerTable.getModel().getColumnName(index);
            Object newValue = evt.getNewValue();
            if (!(newValue instanceof Integer)) return;
            Preferences.setPreference("UrlEncoded." + name + ".width", newValue.toString());
        }
        
    }

}
