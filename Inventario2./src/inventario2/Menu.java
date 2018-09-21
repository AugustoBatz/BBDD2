/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author sys515
 */
public class Menu extends javax.swing.JFrame {

    

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setResizable(false);
       this.setTitle("Menu Principal - Sistema Inventario BTZ");
                
         this.setDefaultCloseOperation(this.HIDE_ON_CLOSE); 
        // this.setSize(720,310);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();    
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        botonventa = new javax.swing.JButton();
        botonclientes = new javax.swing.JButton();
        botonprov = new javax.swing.JButton();
        botonrh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(17, 111, 172));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 830, 30));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-caja-llena-100.png"))); // NOI18N
        jButton1.setText("Inventario");
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-caja-llena-filled-100.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 110));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-100.png"))); // NOI18N
        jButton2.setText("Compras");
        jButton2.setContentAreaFilled(false);
        jButton2.setPreferredSize(new java.awt.Dimension(109, 85));
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-filled-100.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 250, 110));

        botonventa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonventa.setForeground(new java.awt.Color(255, 255, 255));
        botonventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-checkout-100.png"))); // NOI18N
        botonventa.setText("Ventas");
        botonventa.setContentAreaFilled(false);
        botonventa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-checkout-filled-100.png"))); // NOI18N
        botonventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonventaActionPerformed(evt);
            }
        });
        jPanel2.add(botonventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 240, 110));

        botonclientes.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonclientes.setForeground(new java.awt.Color(255, 255, 255));
        botonclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-cliente-de-empresa-100.png"))); // NOI18N
        botonclientes.setText("Clientes");
        botonclientes.setContentAreaFilled(false);
        botonclientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-cliente-de-empresa-filled-100.png"))); // NOI18N
        botonclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonclientesActionPerformed(evt);
            }
        });
        jPanel2.add(botonclientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 230, 120));

        botonprov.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonprov.setForeground(new java.awt.Color(255, 255, 255));
        botonprov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-proveedor-100.png"))); // NOI18N
        botonprov.setText("Proveedores");
        botonprov.setContentAreaFilled(false);
        botonprov.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-proveedor-filled-100.png"))); // NOI18N
        botonprov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonprovActionPerformed(evt);
            }
        });
        jPanel2.add(botonprov, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 270, -1));

        botonrh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-grupos-de-usuarios-100.png"))); // NOI18N
        botonrh.setContentAreaFilled(false);
        botonrh.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-grupos-de-usuarios-filled-100.png"))); // NOI18N
        botonrh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonrhActionPerformed(evt);
            }
        });
        jPanel2.add(botonrh, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, -1, 110));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Recursos Humanos");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Inventario s = new Inventario();
        s.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        SubCompras trd = new SubCompras();
  //      trd.setVisible(true); // TODO add your handling code here:
    //    dispose();
    
    submenucompras sb = new submenucompras();
    sb.setVisible(true);
    dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
          log x=new log();
            x.setVisible(true);
            dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void botonventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonventaActionPerformed
      SubVentas x=new SubVentas();
      x.setVisible(true);
      dispose();// TODO add your handling code here:
    }//GEN-LAST:event_botonventaActionPerformed

    private void botonclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonclientesActionPerformed
        // TODO add your handling code here:
        verclientes vr = new verclientes();
        vr.setVisible(true);
        dispose();
    }//GEN-LAST:event_botonclientesActionPerformed

    private void botonprovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonprovActionPerformed
        // TODO add your handling code here:
        verprove prov = new verprove();
        prov.setVisible(true);
        dispose();
    }//GEN-LAST:event_botonprovActionPerformed

    private void botonrhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonrhActionPerformed
        // TODO add your handling code here:

        rh recursos = new rh();
        recursos.setVisible(true);
        dispose();

    }//GEN-LAST:event_botonrhActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonclientes;
    private javax.swing.JButton botonprov;
    private javax.swing.JButton botonrh;
    private javax.swing.JButton botonventa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}