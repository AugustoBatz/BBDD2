/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class RegistroAnular extends javax.swing.JFrame {

    Conexion con = new Conexion();
    DefaultTableModel modeloBusqueda = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    DefaultTableModel modeloBusqueda2 = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    Connection Consulta = con.conexion();
        Connection cn = con.conexion();

    private int año = 0;
    private int dia = 0;
    private int mes = 0;
    private int dia2 = 0;
    private int año2 = 0;
    private int mes2 = 0;
    private String idUsuario="";

    /**
     * Creates new form Registro
     */
    public RegistroAnular() {
        initComponents();
        this.setTitle("Registro Compras - Sistema Inventario BTZ");

//        AutoCompleteDecorator.decorate(Buscador);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(dim);
 this.setLocationRelativeTo(null);        

      //  Buscador.setVisible(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setSize(1350, 700);

//        Todo.setSelected(true);
        modeloBusqueda.addColumn("Admin 1");
        modeloBusqueda.addColumn("Admin 2");
        modeloBusqueda.addColumn("Fecha Compra");
        modeloBusqueda.addColumn("Fecha Transaccion");
         modeloBusqueda.addColumn("Fecha Anulado");

        modeloBusqueda.addColumn("Serie");
        modeloBusqueda.addColumn("Número");
        modeloBusqueda.addColumn("Cantidad");
        modeloBusqueda.addColumn("Nit");
        modeloBusqueda.addColumn("Proveedor");
        modeloBusqueda.addColumn("Total Factura");
        modeloBusqueda2.addColumn("Código");
        modeloBusqueda2.addColumn("Cantidad");
        modeloBusqueda2.addColumn("Medida");
        modeloBusqueda2.addColumn("Nombre");
        modeloBusqueda2.addColumn("Marca");
        modeloBusqueda2.addColumn("C/U");
        modeloBusqueda2.addColumn("C/T");
        modeloBusqueda2.addColumn("% Ganancia");
        modeloBusqueda2.addColumn("P/U Borrra");
        modeloBusqueda2.addColumn("P/T");
        
        Lote.setModel(modeloBusqueda);
        Detalles.setModel(modeloBusqueda2);

        String datos[] = new String[11];

        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Y.Usuario,Z.Usuario,DATE_FORMAT(F.Fecha, \"%d/%m/%Y %H:%i:%s\"),DATE_FORMAT(F.Trans, \"%d/%m/%Y %H:%i:%s\"),"
                    + "DATE_FORMAT(F.FechaAnulado, \"%d/%m/%Y %H:%i:%s\"),F.Serie,F.Numero,F.Cantidad_Prod,"
                    + "P.Nit,P.NombreV,F.Total_Factura "
                    + "FROM FacturaCompra F Inner join Proveedor P "
                    + "on P.id=F.Proveedor_id "
                    + "inner join Usuarios Y "
                    + "on Y.id=F.Usuarios_id "
                    + "inner join Usuarios Z "
                    + "on Z.id=F.Usuarios_id1 where F.Anulado=true ORDER BY F.Fecha");
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

                modeloBusqueda.addRow(datos);

            }
            Lote.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        Lote.setVisible(true);
        Lote.getColumn("Fecha Compra").setPreferredWidth(90);
        Lote.getColumn("Serie").setPreferredWidth(35);
        Lote.getColumn("Número").setPreferredWidth(50);
        Lote.getColumn("Nit").setPreferredWidth(50);
        //Lote.getColumn("Precio Unitario").setPreferredWidth(100);
        Lote.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent Mouse_evt) {
                //try{
                
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                 int seleccionar = 0;
                 seleccionar = Lote.getSelectedRow();
                 if(seleccionar!=-1)
                 {
                if (Mouse_evt.getClickCount() == 2) {

                    String Serie = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 1+2+1+1));
                    String Numero = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 2+2+1+1));
                    String Nit = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 4+2+1+1));
                    llenarDetalle(Serie,Numero,Nit);
                    
                }
                 }
                /*catch()
                         {
                 
                 }*/
            }
        });
        Lote.addKeyListener(new java.awt.event.KeyAdapter(){
             public void keyReleased(java.awt.event.KeyEvent e)
             {
                  int seleccionar = 0;
                 seleccionar = Lote.getSelectedRow();
                 if(seleccionar!=-1)
                 {
                  String Serie = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 1+2+1+1));
                    String Numero = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 2+1+2+1));
                    String Nit = String.valueOf(Lote.getValueAt(Lote.getSelectedRow(), 4+2+1+1));
                    llenarDetalle(Serie,Numero,Nit);
                 }
             }
         
         });
        

    }
   private void actualizar()
    {       modeloBusqueda.setRowCount(0);
            modeloBusqueda2.setRowCount(0);
            String[] datos=new String[11];
            try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Y.Usuario,Z.Usuario,DATE_FORMAT(F.Fecha, \"%d/%m/%Y %H:%i:%s\"),DATE_FORMAT(F.Trans, \"%d/%m/%Y %H:%i:%s\"),"
                    + "DATE_FORMAT(F.FechaAnulado, \"%d/%m/%Y %H:%i:%s\"),F.Serie,F.Numero,F.Cantidad_Prod,"
                    + "P.Nit,P.NombreV,F.Total_Factura "
                    + "FROM FacturaCompra F Inner join Proveedor P "
                    + "on P.id=F.Proveedor_id "
                    + "inner join Usuarios Y "
                    + "on Y.id=F.Usuarios_id "
                    + "inner join Usuarios Z "
                    + "on Z.id=F.Usuarios_id1 where F.Anulado=true ORDER BY F.Fecha");
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

                modeloBusqueda.addRow(datos);

            }
            Lote.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Lote = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Detalles = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));
        jPanel1.setPreferredSize(new java.awt.Dimension(1420, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1130, 240));

        Detalles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(Detalles);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1130, 290));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(1, 1, 1));
        jLabel6.setText("Facturas Anuladas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(55, 55, 55))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 310, 40));

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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 370, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void llenarDetalle(String Serie,String Numero,String Nit)
    {
        String[] datos = new String[11];
        modeloBusqueda2.setRowCount(0);
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select P.Codigo,L.Cantidadi,U.Medida,P.Nombre,P.Marca,L.CostoUnitario,L.CostoTotal,L.Ganancia,L.PrecioUnitario,L.PrecioTotal from UnidadMedida_1 U\n" +
"inner join Producto P \n" +
"on P.UnidadMedida_1_id=U.id\n" +
"inner join Lote L\n" +
"on P.id=L.Producto_id\n" +
"inner join FacturaCompra F\n" +
"on F.id=L.FacturaCompra_id\n" +
"inner join Proveedor V\n" +
"on V.id=F.Proveedor_id where V.Nit='"+Nit+"' && F.Numero='"+Numero+"' && F.Serie='"+Serie+"';");
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

        
                

                modeloBusqueda2.addRow(datos);
            }
             Detalles.setModel(modeloBusqueda2);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroAnular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void LoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LoteMouseClicked
    private void brz() {
        String[] datos = new String[7];
        modeloBusqueda.setRowCount(0);
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT F.Fecha,F.Serie,F.Numero,F.Cantidad_Prod,"
                    + "P.Nit,P.NombreV,F.Total_Factura "
                    + "FROM FacturaCompra F Inner join Proveedor P "
                    + "on P.id=F.Proveedor_id ORDER BY F.Fecha");
            while (Ca.next()) {

                datos[0] = Ca.getString(1);
                datos[1] = Ca.getString(2);
                datos[2] = Ca.getString(3);
                datos[3] = Ca.getString(4);
                datos[4] = Ca.getString(5);
                datos[5] = Ca.getString(6);
                datos[6] = Ca.getString(7);

                modeloBusqueda.addRow(datos);

            }
            Lote.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        submenucompras n=new submenucompras();
        n.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
    private Boolean construir(){
        LoginView n=new LoginView(null,true);
        new LoginController(n);
        n.addWindowListener(new WindowAdapter(){
             @Override
             public void windowClosing(WindowEvent we )
             {
                 System.exit(0);
             }
        });
        n.pack();
        n.setLocationRelativeTo(null);
        
        n.setVisible(true);
        idUsuario=n.getRol();
        if(idUsuario.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    private String idProveedor(String Nit)
    {
        String id="";
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select id from Proveedor where Nit='"+Nit+"'");
            while(Ca.next())
            {
                id=Ca.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroAnular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private int idFacturaCancelar(String Serie,String Numero,String Nit)
    {
        int id=0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select id from FacturaCompra Where Serie='"+Serie+"' && Numero='"+Numero+"' && Proveedor_id='"+idProveedor(Nit)+"'");
            while(Ca.next())
            {
                id=Integer.valueOf(Ca.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroAnular.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    private void restarProducto(String id,String cantidad)
    {
        try {
            PreparedStatement ActualizarProveedor = cn.prepareStatement("update Producto "
                    + "set Existencia=Existencia-'"+cantidad+"' where id='" + id + "';");
            ActualizarProveedor.executeUpdate();
            ActualizarProveedor.close();

        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

        }
    }
    private void restarDeInventario(String id)
    {
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Producto_id,Cantidadi FROM Lote WHERE FacturaCompra_id='"+id+"'");
            while(Ca.next())
            {
                restarProducto(Ca.getString(1),Ca.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroAnular.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void anularDetalles(String id)
    {
         try {
                    PreparedStatement ActualizarProveedor = cn.prepareStatement("update Lote "                 
                    + "set Anulado=true,Disponible=false where FacturaCompra_id='"+id+"';");
                    ActualizarProveedor.executeUpdate();
                    ActualizarProveedor.close();
                   restarDeInventario(id);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

                }
         
         
         
    }
    private void anularFactura(String id,String idAdmin)
    {
         try {
                    PreparedStatement ActualizarProveedor = cn.prepareStatement("update FacturaCompra "                 
                    + "set Anulado=true,Usuarios_id1='"+idAdmin+"'  where id='"+id+"';");
                    ActualizarProveedor.executeUpdate();
                    ActualizarProveedor.close();
                   
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Hubo un error en el proceso");

                }
    }    private void llenar2(String B) {
        modeloBusqueda.setRowCount(0);
        String datos[] = new String[13];
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT L.Fecha,C.Serie,C.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidadi,P.Medida,L.PrecioUnitario,L.PrecioTotal,V.Nit,V.NombreV \n"
                    + "FROM Producto P \n"
                    + "inner JOIN Lote L \n"
                    + "on P.id=L.Producto_id \n"
                    + "inner JOIN FacturaCompra C \n"
                    + "on C.id=L.FacturaCompra_id\n"
                    + "inner JOIN Proveedor V \n"
                    + "on V.id=C.Proveedor_id where P.Codigo='" + B + "'&& L.Fecha BETWEEN '" + año + "-" + mes + "-" + dia + "' AND '" + año2 + "-" + mes2 + "-" + dia2 + "' Order By L.Fecha");
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
                modeloBusqueda.addRow(datos);

            }
            Lote.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Llenar(String B) {
        modeloBusqueda.setRowCount(0);
        String datos[] = new String[13];
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT L.Fecha,C.Serie,C.Numero,L.NoLote,P.Nombre,P.Codigo,P.Marca,L.Cantidadi,P.Medida,L.PrecioUnitario,L.PrecioTotal,V.Nit,V.NombreV \n"
                    + "FROM Producto P \n"
                    + "inner JOIN Lote L \n"
                    + "on P.id=L.Producto_id \n"
                    + "inner JOIN FacturaCompra C \n"
                    + "on C.id=L.FacturaCompra_id\n"
                    + "inner JOIN Proveedor V \n"
                    + "on V.id=C.Proveedor_id where V.Nit='" + B + "'&& L.Fecha BETWEEN '" + año + "-" + mes + "-" + dia + "' AND '" + año2 + "-" + mes2 + "-" + dia2 + "' Order By L.Fecha");
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

                modeloBusqueda.addRow(datos);

            }
            Lote.setModel(modeloBusqueda);

        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(RegistroAnular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroAnular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroAnular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroAnular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroAnular().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Detalles;
    private javax.swing.JTable Lote;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
