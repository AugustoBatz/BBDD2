/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author chr97lubuntu
 */
public class recuperar extends javax.swing.JFrame {
Conexion con = new Conexion();
   
    Connection Consulta = con.conexion();
    boolean verificado;
    Correo c=new Correo();
    /**
     * Creates new form recuperar
     */
    public recuperar() {
        initComponents();
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        DPI = new javax.swing.JTextField();
        recordar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Número de DPI DPI");

        recordar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        recordar.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-contraseña-1-50.png"))); // NOI18N
        jButton1.setText("Recuperar contraseña");
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-contraseña-1-filled-50.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(DPI, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(recordar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(13, 13, 13)
                .addComponent(DPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(126, 126, 126)
                .addComponent(recordar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String crearNuevaContraseña()
    {
        String[] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", ""
                + "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3"
        , "4", "5", "6", "7", "8", "9", "0", "+", "-", "*"};
        
        String n="";
        System.out.println("Largi= "+abecedario.length);
        for (int i = 0; i < 10; i++) {
            
            int numRandon = (int) Math.round(Math.random() * (abecedario.length-1));
            System.out.println("num "+numRandon);
            n=n+abecedario[numRandon];
        }
        return n;
    }  
    private void cambiar(String nuevo)
    {
    try {
        PreparedStatement ActualizarProveedor = Consulta.prepareStatement("UPDATE Usuarios SET Contraseña='" + nuevo + "' where DPI= '"+DPI.getText()+ "'");
        ActualizarProveedor.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(recuperar.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    private void enviarCorreo()
    {   String n=crearNuevaContraseña();
        System.out.println("N "+n);
        cambiar(n);
        c.setContraseña("ijsxmonhovlaqnjg");
        c.setUsuario("inventariopeps@gmail.com");
        c.setAsunto("Recuperar Contraseña");
        c.setMensaje("DPI: "+DPI.getText()+ " \n"
                + "Su nueva contraseña es: "+n);
        c.setDestino("miguelbatz2@gmail.com");
        Controlador cc=new Controlador();
        if(cc.enviarCorre(c))
        {
            JOptionPane.showMessageDialog(null, "Su Constraseña se envio al correo del admin");
        }
        else
        {
            
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(DPI.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Ingrese su Número de DPI");
        }
        else
        {
            if(DPICorrecto())
            {
                enviarCorreo();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "DPI no valido");
            }
        }
      
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private Boolean DPICorrecto()
    {
        Boolean valor=false;
    try {
        Statement sx = Consulta.createStatement();
        ResultSet Ca = sx.executeQuery("select id from Usuarios where DPI='"+DPI.getText()+"'");
        while(Ca.next())
        {
             valor= true;
        }
       
    } catch (SQLException ex) {
        Logger.getLogger(recuperar.class.getName()).log(Level.SEVERE, null, ex);
    }
    return valor;
    }
   
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
            java.util.logging.Logger.getLogger(recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(recuperar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new recuperar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DPI;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel recordar;
    // End of variables declaration//GEN-END:variables

    private static class Random {

        public Random() {
        }
    }
}
