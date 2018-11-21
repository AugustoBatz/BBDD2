/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sys515
 */
public class Registro1 extends javax.swing.JFrame {

    Conexion con = new Conexion();
   DefaultTableModel modeloBusqueda = new DefaultTableModel() {
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };
    Connection Consulta = con.conexion();
    private int año=0;
    private int dia=0;
    private int mes=0;
    private int dia2=0;
    private int año2=0;
    private int mes2=0;
    private String X;
    private String Y;
    private String Z;
    /**
     * Creates new form Registro
     */
    public Registro1(String x,String y,String z) {
        initComponents();
                this.setTitle("Registro Compras - Sistema Inventario BTZ");
    
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();    
        //this.setSize(dim);
 this.setLocationRelativeTo(null);        
        
        this.setResizable(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE); 
        this.setSize(1300, 570);
       X=x;
       Y=x;
       Z=z;
          
            
            modeloBusqueda.addColumn("Fecha Compra");
            modeloBusqueda.addColumn("Serie");
            modeloBusqueda.addColumn("Número");
            modeloBusqueda.addColumn("No. Lote");
            modeloBusqueda.addColumn("Código");
            modeloBusqueda.addColumn("Producto");
            modeloBusqueda.addColumn("Marca");
            modeloBusqueda.addColumn("Cantidad Inicial");
            modeloBusqueda.addColumn("Cantidad Stock");
            modeloBusqueda.addColumn("Unidad");
            modeloBusqueda.addColumn("Costo Unitario");
            modeloBusqueda.addColumn("Costo Total");
            modeloBusqueda.addColumn("Ganancia");
//modeloBusqueda.addColumn("Precio Unitario");
            //modeloBusqueda.addColumn("Precio Total");
            //modeloBusqueda.addColumn("Nitgñkjgñ");
           // modeloBusqueda.addColumn("Proveedor");
            
            
            Lote.setModel(modeloBusqueda);

            String datos[] = new String[16];

          try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT DATE_FORMAT(L.Fecha,\"%d/%m/%Y %H:%i:%s\"),C.Serie,C.Numero,L.NoLote,P.Codigo,"
                    + "P.Nombre,P.Marca,L.Cantidadi,L.Cantidad,U.Medida,L.CostoUnitario,L.CostoTotal,L.Ganancia\n" +
"FROM UnidadMedida_1 U inner join Producto P on U.id=P.UnidadMedida_1_id\n" +
"inner JOIN Lote L \n" +
"on P.id=L.Producto_id \n" +
"inner JOIN FacturaCompra C \n" +
"on C.id=L.FacturaCompra_id\n" +
"inner JOIN Proveedor V \n" +
"on V.id=C.Proveedor_id where P.Nombre='"+y+"' && P.Marca='"+x+"' && U.Medida='"+z+"' && L.Disponible=true && L.Anulado=false Order By L.Fecha ");          
            while (Ca.next()) {
               
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                datos[6] = Ca.getString(7);
                datos[7] = Ca.getString(8);
                datos[8] = Ca.getString(9);
                datos[9] = Ca.getString(10);
                datos[10] = Ca.getString(11);
                datos[11] = Ca.getString(12);
                datos[12] = Ca.getString(13);
                //datos[13] = Ca.getString(14);
                //datos[14] = Ca.getString(15);
                modeloBusqueda.addRow(datos);
                
            }
            Lote.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        Lote.setVisible(true);
        Lote.getColumn("Fecha Compra").setPreferredWidth(90);
        Lote.getColumn("Serie").setPreferredWidth(35);
        Lote.getColumn("Número").setPreferredWidth(45);
//        Lote.getColumn("Precio Unitario").setPreferredWidth(100);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Inicio = new com.toedter.calendar.JDateChooser();
        Final = new com.toedter.calendar.JDateChooser();
        Inicio2 = new javax.swing.JLabel();
        Final2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lote = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-multiedición-50.png"))); // NOI18N
        jButton2.setText("Generar reporte");
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-multiedición-filled-50.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, -1, -1));
        jPanel1.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 139, -1));
        jPanel1.add(Final, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 140, -1));

        Inicio2.setForeground(new java.awt.Color(255, 255, 255));
        Inicio2.setText("Inicio");
        jPanel1.add(Inicio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        Final2.setForeground(new java.awt.Color(255, 255, 255));
        Final2.setText("Final");
        jPanel1.add(Final2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        Lote.setModel(new javax.swing.table.DefaultTableModel(
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
        Lote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Lote);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1270, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LoteMouseClicked
   
   
    
 
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
   
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
            java.util.logging.Logger.getLogger(Registro1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro1(null,null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Final;
    private javax.swing.JLabel Final2;
    private com.toedter.calendar.JDateChooser Inicio;
    private javax.swing.JLabel Inicio2;
    private javax.swing.JTable Lote;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
