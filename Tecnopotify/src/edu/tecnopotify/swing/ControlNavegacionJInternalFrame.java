/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.swing;

import edu.tecnopotify.entidades.RegistroUsuarios;
import edu.tecnopotify.fabrica.Fabrica;
import edu.tecnopotify.interfaces.IControlador;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author root
 */
public class ControlNavegacionJInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form ControlNavegacionJInternalFrame
     */
    
    IControlador crl;
    
    public ControlNavegacionJInternalFrame() {
        initComponents();
        Fabrica fabrica = Fabrica.getInstance();
        crl = fabrica.getInstancia();
        List<RegistroUsuarios> reg = crl.listarRegistroUsuarios();
  
        Object rowDataReg[] = new Object[3];
        DefaultTableModel modelU = (DefaultTableModel) jTable1.getModel();
        for(int k = 0; k< reg.size(); k++) {
            rowDataReg[0] = reg.get(k).getIp();
            rowDataReg[1] = reg.get(k).getUrl();
            rowDataReg[2] = reg.get(k).getBrowser();
            modelU.addRow(rowDataReg);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IP", "URL", "BROWSER Y SO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("                                             Control de Navegaciòn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}