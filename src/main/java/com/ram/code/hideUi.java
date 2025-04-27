//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class hideUi extends JFrame {
    controlPanel myUi;
    private JButton jButton1;
    private JLabel jLabel1;

    public hideUi(controlPanel myUi) {
        this.initComponents();
        this.myUi = myUi;
    }

    private void initComponents() {
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.setDefaultCloseOperation(0);
        this.jLabel1.setText("LET THE TRADE TO RUN TILL TARGET OR  SL");
        this.jButton1.setText("BACK TO HOME SCREEN");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                hideUi.this.jButton1ActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addComponent(this.jButton1, -1, -1, 32767).addComponent(this.jLabel1, -1, 239, 32767)).addContainerGap(-1, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jButton1).addContainerGap(-1, 32767)));
        this.pack();
        this.setLocationRelativeTo((Component)null);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        this.myUi.setVisible(true);
    }
}
