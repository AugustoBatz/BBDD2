/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sys515
 */
public class FacturasExCanceladas extends javax.swing.JFrame {

    Conexion con = new Conexion();
    Connection Consulta = con.conexion();
     Connection cn = con.conexion();
    private String idUsuario="";
     /**
     * Creates new form Ventas
     */
    
        DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }; //
            DefaultTableModel modelo2 = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }; //
    public FacturasExCanceladas() {
        
            initComponents();
            this.setTitle("Facturas Anuladas - Sistema Inventario BTZ");
            this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
             this.setLocationRelativeTo(null);        

        modelo.setRowCount(0);
        modelo2.setRowCount(0);
        modelo.addColumn("Admin 1");
        modelo.addColumn("Admin 2");
        modelo.addColumn("Fecha Venta");
        modelo.addColumn("Fecha Transaccion");
        modelo.addColumn("Fecha Anulado");
        modelo.addColumn("Serie");
        modelo.addColumn("Número");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Nit");
        modelo.addColumn("Cliente");
        modelo.addColumn("Total Factura");

        Factura.setModel(modelo);
        modelo2.addColumn("No. Lote");
        modelo2.addColumn("Código");
        modelo2.addColumn("Producto");
        modelo2.addColumn("Marca");
        modelo2.addColumn("Unidad");
        modelo2.addColumn("Categoria");
        modelo2.addColumn("Presentación");
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("C/U");
        modelo2.addColumn("C/T");
        modelo2.addColumn("P/U");
        modelo2.addColumn("P/T");

        Detalle.setModel(modelo2);
        Factura.getColumn("Serie").setPreferredWidth(25);
        Factura.getColumn("Número").setPreferredWidth(27);
        Detalle.getColumn("No. Lote").setPreferredWidth(60);
        Detalle.getColumn("Código").setPreferredWidth(50);
        Detalle.getColumn("P/U").setPreferredWidth(50);
        Detalle.getColumn("P/T").setPreferredWidth(50);
        Detalle.getColumn("C/U").setPreferredWidth(50);
        Detalle.getColumn("C/T").setPreferredWidth(50);
//            Detalle.getColumn("Ganancia").setPreferredWidth(70);    
        String datos[] = new String[11];
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Y.Usuario,Z.Usuario,DATE_FORMAT(F.Fecha, \"%d/%m/%Y %H:%i:%s\"),"
                    + "DATE_FORMAT(F.Trans, \"%d/%m/%Y %H:%i:%s\"),"
                    + "DATE_FORMAT(F.FechaAnulado, \"%d/%m/%Y %H:%i:%s\"), F.Serie,F.Numero,F.Cantidad_Prod,P.Nit,P.NombreC,F.Total \n"
                    + "FROM FacturaVenta F \n"
                    + "Inner join Cliente P\n"
                    + "on P.id=F.Cliente_id\n"
                    + "inner join Usuarios Y\n"
                    + "on F.Usuarios_id=Y.id\n"
                    + "inner join Usuarios Z\n"
                    + "on F.Usuarios_id1=Z.id\n"
                    + "where F.Anulado=true ORDER BY F.Fecha;");
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

                modelo.addRow(datos);
            }
            Factura.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(FacturasExCanceladas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Factura.getColumn("Fecha Venta").setPreferredWidth(90);
        Factura.getColumn("Serie").setPreferredWidth(35);
        Factura.getColumn("Número").setPreferredWidth(50);
        Factura.getColumn("Nit").setPreferredWidth(50);
        Factura.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                //try{
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 2) {

                    String Serie = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 1 + 2+2));
                    String Numero = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 2 + 2+2));
                    String Nit = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 4 + 2+2));
                    llenarDetalle(Serie, Numero, Nit);

                }
                /*catch()
                         {
                 
                 }*/
            }
        });

        Factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                String Serie = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 1 + 2 + 2));
                String Numero = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 2 + 2 + 2));
                String Nit = String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 4 + 2 + 2));
                llenarDetalle(Serie, Numero, Nit);
            }

        });

    }

    private void actualizar() {
        modelo.setRowCount(0);
        modelo2.setRowCount(0);
        String datos[] = new String[8];
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT  Y.Usuario,DATE_FORMAT(F.Fecha, \"%d/%m/%Y %H:%i:%s\"), F.Serie,F.Numero,F.Cantidad_Prod,P.Nit,P.NombreC,F.Total \n"
                    + "FROM FacturaVenta F Inner join Cliente P \n"
                    + "on P.id=F.Cliente_id "
                    + " inner join Usuarios Y "
                    + " on Y.id=F.Usuarios_id "
                    + "where F.Anulado=false ORDER BY F.Fecha;");
            while (Ca.next()) {
                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                datos[6] = Ca.getString(7);
                datos[7] = Ca.getString(8);

                modelo.addRow(datos);
            }
            Factura.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(FacturasExCanceladas.class.getName()).log(Level.SEVERE, null, ex);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Factura = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Detalle = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        jMenuItem1.setText("Anular Factura");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(189, 189, 189));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Factura.setModel(new javax.swing.table.DefaultTableModel(
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
        Factura.setComponentPopupMenu(jPopupMenu1);
        jScrollPane3.setViewportView(Factura);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1200, 250));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Detalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(Detalle);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 1200, 300));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 1, 1));
        jLabel5.setText("Detalles de Factura");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(81, 81, 81))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 370, 40));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("Facturas Anuladas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(81, 81, 81))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SubVentas n = new SubVentas();
        n.setVisible(true);

    }//GEN-LAST:event_formWindowClosing
    private void llenarDetalle(String Serie, String Numero, String Nit) {
        String[] datos = new String[14];
        modelo2.setRowCount(0);
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select L.NoLote,P.Codigo,P.Nombre,P.Marca,\n"
                    + "U.Medida,W.Categoria,Z.Presentacion,L.Cantidad,L.CostoUnitario,L.CostoTotal,\n"
                    + "L.PrecioUnitario,L.PrecioTotal from Producto P \n"
                    + "inner join UnidadMedida_1 U\n"
                    + "on P.UnidadMedida_1_id=U.id\n"
                    + "inner join Presentacion_1 Z\n"
                    + "on Z.id=P.Presentacion_1_id\n"
                    + "inner join Catalogo W \n"
                    + "on W.id=P.Catalogo_id \n"
                    + "inner join LoteVenta L\n"
                    + "on P.id=L.Producto_id\n"
                    + "inner join FacturaVenta F\n"
                    + "on F.id=L.FacturaVenta_id\n"
                    + "inner join Cliente V\n"
                    + "on V.id=F.Cliente_id "
                    + "where V.Nit='" + Nit + "' && F.Numero='" + Numero + "' && F.Serie='" + Serie + "';");
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
                
                modelo2.addRow(datos);
            }
            Detalle.setModel(modelo2);
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Boolean construir() {
        LoginView n = new LoginView(null, true);
        new LoginController(n);
        n.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        n.pack();
        n.setLocationRelativeTo(null);

        n.setVisible(true);
        idUsuario = n.getRol();
        if(idUsuario.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private void anularFactura(String id,String idAdmin)
    {
         try {
             System.out.println("Admin "+idAdmin);
                    PreparedStatement ActualizarProveedor = cn.prepareStatement("update FacturaVenta "                 
                        + "set Anulado=true,Usuarios_id1='"+idAdmin+"' where id='"+id+"';");
                    ActualizarProveedor.executeUpdate();
                    ActualizarProveedor.close();
                   
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

                }
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int seleccionar = 0;
        seleccionar = Factura.getSelectedRow();
       
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione Una Factura");
        } else {
          if(construir())
          {
              String serie=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 1+1));
              String numero=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 2+1));
              String nit=String.valueOf(Factura.getValueAt(Factura.getSelectedRow(), 4+1));
              String id=String.valueOf(idFacturaCancelar(serie,numero,nit));
              anularFactura(id,idUsuario);
              anularDetalles(id);
               JOptionPane.showMessageDialog(null, "FacturaAnulada");
              actualizar();
          }
          else
          {
              JOptionPane.showMessageDialog(null, "Acceso Denegado");
          }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    private void sumarlotes(String id,String cantidad,String NoLote)
    {
        try {
            PreparedStatement ActualizarProveedor = cn.prepareStatement("update Lote "
                    + "set Cantidad=Cantidad +'"+cantidad+"' , Disponible=true && Anulado=false where Producto_id='" + id + "' && NoLote='"+NoLote+"';");
            ActualizarProveedor.executeUpdate();
            ActualizarProveedor.close();

        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

        }
    }
    private void sumarInventario(String id)
    {
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Producto_id,Cantidad,NoLote FROM LoteVenta WHERE FacturaVenta_id='"+id+"'");
            while(Ca.next())
            {
                sumarlotes(Ca.getString(1),Ca.getString(2),Ca.getString(3));
                sumarPro(Ca.getString(1),Ca.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void sumarPro(String id,String cantidad)
    {
        try {
            PreparedStatement ActualizarProveedor = cn.prepareStatement("update Producto "
                    + "set Existencia=Existencia +'"+cantidad+"' where id='" + id + "';");
            ActualizarProveedor.executeUpdate();
            ActualizarProveedor.close();

        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

        }
    }
    private void anularDetalles(String id)
    {
         try {
                    PreparedStatement ActualizarProveedor = cn.prepareStatement("update LoteVenta "                 
                    + "set Anulado=true where FacturaVenta_id='"+id+"';");
                    ActualizarProveedor.executeUpdate();
                    ActualizarProveedor.close();
                   sumarInventario(id);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

                }
         
         
    } 
     private String idProveedor(String Nit)
    {
        String id="";
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select id from Cliente where Nit='"+Nit+"'");
            while(Ca.next())
            {
                id=Ca.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    private int idFacturaCancelar(String Serie,String Numero,String Nit)
    {
        int id=0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select id from FacturaVenta Where Serie='"+Serie+"' && Numero='"+Numero+"' && Cliente_id='"+idProveedor(Nit)+"'");
            while(Ca.next())
            {
                id=Integer.valueOf(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private void Detalles(String Serie,String Numero)
    {
        modelo2.setRowCount(0);
         String datos[] = new String[11];
            try {
                Statement sx = Consulta.createStatement();
                ResultSet Ca = sx.executeQuery("select L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,\n" +
"L.CostoUnitario,L.CostoTotal,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC From Cliente C\n" +
"inner join FacturaVenta F\n" +
"on C.id=F.Cliente_id\n" +
"inner Join LoteVenta L\n" +
"on F.id=L.FacturaVenta_id\n" +
"inner join Producto P\n" +
"on P.id=L.Producto_id where F.Serie='"+Serie+"' && F.Numero='"+Numero+"';");
                while(Ca.next())
                {
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
                    modelo2.addRow(datos);
                }
                 Detalle.setModel(modelo2);
                } 
            catch (SQLException ex) {
            Logger.getLogger(FacturasExCanceladas.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            java.util.logging.Logger.getLogger(FacturasExCanceladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FacturasExCanceladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FacturasExCanceladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FacturasExCanceladas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FacturasExCanceladas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Detalle;
    private javax.swing.JTable Factura;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
