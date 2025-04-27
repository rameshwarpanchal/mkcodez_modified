//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class ButtonEditor extends DefaultCellEditor {
    private JButton button = new JButton();
    private String label;
    private boolean isClicked;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        this.button.setOpaque(true);
        this.button.addActionListener((e) -> {
            this.fireEditingStopped();
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.label = value == null ? "" : value.toString();
        this.button.setText(this.label);
        this.isClicked = true;
        return this.button;
    }

    public Object getCellEditorValue() {
        this.isClicked = false;
        return this.label;
    }

    public boolean stopCellEditing() {
        this.isClicked = false;
        return super.stopCellEditing();
    }
}
