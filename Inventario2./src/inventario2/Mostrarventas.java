/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario2;

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
 * @author chr97lubuntu
 */
public class Mostrarventas extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    private int mes = 0, dia = 0, año = 0, dia2 = 0, año2 = 0, mes2 = 0;
    Connection Consulta = con.conexion();
    DefaultTableModel modeloBusqueda = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    /**
     * Creates new form Mostrarventas
     */
    public Mostrarventas() {
        initComponents();
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        Todo.setSelected(true);
        this.setTitle("Registro De Ventas - Sistema Inventario BTZ");
        
        modeloBusqueda.addColumn("Fecha Compra");
        modeloBusqueda.addColumn("Serie");
        modeloBusqueda.addColumn("Número");
        modeloBusqueda.addColumn("No. Lote");
        modeloBusqueda.addColumn("Código");
        modeloBusqueda.addColumn("Producto");
        modeloBusqueda.addColumn("Marca");
        modeloBusqueda.addColumn("Cantidad");
        modeloBusqueda.addColumn("Unidad");
        modeloBusqueda.addColumn("Precio Unitario");
        modeloBusqueda.addColumn("Precio Total");
        modeloBusqueda.addColumn("Nit");
        modeloBusqueda.addColumn("Cliente");
        Buscador.setVisible(false);
        AutoCompleteDecorator.decorate(Buscador);
        rSTableMetro1.setModel(modeloBusqueda);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        rSTableMetro1.setSize(1000, 1000);
        rSTableMetro1.setVisible(true);
        
        rSTableMetro1.getColumn("Fecha Compra").setPreferredWidth(90);
        rSTableMetro1.getColumn("Serie").setPreferredWidth(35);
        rSTableMetro1.getColumn("Número").setPreferredWidth(45);
        rSTableMetro1.getColumn("Precio Unitario").setPreferredWidth(100);
        
        Todo.setSelected(true);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        rSTableMetro1 = new rojerusan.RSTableMetro();
        Final = new com.toedter.calendar.JDateChooser();
        Final2 = new javax.swing.JLabel();
        Cliente = new javax.swing.JRadioButton();
        Buscador = new javax.swing.JComboBox<>();
        Producto = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        Todo = new javax.swing.JRadioButton();
        Inicio2 = new javax.swing.JLabel();
        Inicio = new com.toedter.calendar.JDateChooser();
        Codigo = new javax.swing.JRadioButton();
        Marca = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSTableMetro1.setModel(new javax.swing.table.DefaultTableModel(
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
        rSTableMetro1.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jScrollPane2.setViewportView(rSTableMetro1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 1140, 380));

        Final.setDateFormatString("yyyy-MM-d");
        jPanel1.add(Final, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 134, -1));

        Final2.setForeground(new java.awt.Color(255, 255, 255));
        Final2.setText("Final");
        jPanel1.add(Final2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        Cliente.setForeground(new java.awt.Color(255, 255, 255));
        Cliente.setText("Cliente");
        Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        Buscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorActionPerformed(evt);
            }
        });
        jPanel1.add(Buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 50, 200, -1));

        Producto.setForeground(new java.awt.Color(255, 255, 255));
        Producto.setText("Producto");
        Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoActionPerformed(evt);
            }
        });
        jPanel1.add(Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, -1));

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
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 30, -1, -1));

        Todo.setForeground(new java.awt.Color(255, 255, 255));
        Todo.setText("Todo");
        Todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodoActionPerformed(evt);
            }
        });
        jPanel1.add(Todo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        Inicio2.setForeground(new java.awt.Color(255, 255, 255));
        Inicio2.setText("Inicio");
        jPanel1.add(Inicio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        Inicio.setDateFormatString("yyyy-MM-d");
        jPanel1.add(Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 139, -1));

        Codigo.setForeground(new java.awt.Color(255, 255, 255));
        Codigo.setText("Código");
        Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoActionPerformed(evt);
            }
        });
        jPanel1.add(Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, -1, -1));

        Marca.setForeground(new java.awt.Color(255, 255, 255));
        Marca.setText("Marca");
        Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaActionPerformed(evt);
            }
        });
        jPanel1.add(Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteActionPerformed
        if (Cliente.isSelected()) {
            Todo.setSelected(false);
            Codigo.setSelected(false);
            Producto.setSelected(false);
            Marca.setSelected(false);
            Buscador.setVisible(true);
            Llenar2("Cliente");
            
        } else {
            Todo.setSelected(true);
            Buscador.setVisible(false);
        }
    }//GEN-LAST:event_ClienteActionPerformed
    private void Llenar2(String Tipo) {
        Buscador.removeAllItems();
         try {
            año = Inicio.getCalendar().get(Calendar.YEAR);
            mes = Inicio.getCalendar().get(Calendar.MONTH) + 1;
            dia = Inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
            año2 = Final.getCalendar().get(Calendar.YEAR);
            mes2 = Final.getCalendar().get(Calendar.MONTH) + 1;
            dia2 = Final.getCalendar().get(Calendar.DAY_OF_MONTH);
        } catch (NullPointerException ex) {
            Todo.setSelected(true);
            Marca.setSelected(false);
            Codigo.setSelected(false);
            Producto.setSelected(false);
            Cliente.setSelected(false);
            Buscador.setVisible(false);
            
        }
        if (año == 0 || dia == 0 || mes == 00 || año2 == 0 || dia2 == 0 || mes2 == 00) {
            JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
            Todo.setSelected(true);
            Marca.setSelected(false);
            Codigo.setSelected(false);
            Producto.setSelected(false);
            Cliente.setSelected(false);
            Buscador.setVisible(false);

        } 
        else 
        {
                               
            if (Tipo.equals("Cliente")) {
                 String aux;
                try {
                    Statement sx = Consulta.createStatement();
                    ResultSet Ca = sx.executeQuery("Select C.Nit FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id \n" +
"");
                    while (Ca.next()) {
                        aux=Ca.getString(1);
                        if(Buscador.getItemCount()==0)
                        {
                            Buscador.addItem(aux);
                        }
                        else
                        {
                            Boolean Valor=false;
                            for (int i = 0; i < Buscador.getItemCount(); i++) {
                                if(aux.equals(Buscador.getItemAt(i)))
                                {
                                    Valor=true;
                                }
                        }
                        if(Valor==false)
                        {
                            Buscador.addItem(aux); 
                        }

                        }
                
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Tipo.equals("Codigo")) {
                String aux;
                try {
                    Statement sx = Consulta.createStatement();
                    ResultSet Ca = sx.executeQuery("Select P.Codigo FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id \n" +
"");
                    while (Ca.next()) {
                        aux=Ca.getString(1);
                        if(Buscador.getItemCount()==0)
                        {
                            Buscador.addItem(aux);
                        }
                        else
                        {
                            Boolean Valor=false;
                            for (int i = 0; i < Buscador.getItemCount(); i++) {
                                if(aux.equals(Buscador.getItemAt(i)))
                                {
                                    Valor=true;
                                }
                        }
                        if(Valor==false)
                        {
                            Buscador.addItem(aux); 
                        }

                        }
                
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            if (Tipo.equals("Producto")) {
                String aux;
                try {
                    Statement sx = Consulta.createStatement();
                    ResultSet Ca = sx.executeQuery("Select P.Nombre FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id");
                    while (Ca.next()) {
                        aux=Ca.getString(1);
                        if(Buscador.getItemCount()==0)
                        {
                            Buscador.addItem(aux);
                        }
                        else
                        {
                            Boolean Valor=false;
                            for (int i = 0; i < Buscador.getItemCount(); i++) {
                                if(aux.equals(Buscador.getItemAt(i)))
                                {
                                    Valor=true;
                                }
                        }
                        if(Valor==false)
                        {
                            Buscador.addItem(aux); 
                        }

                        }
                
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Tipo.equals("Marca")) {
                String aux;
                try {
                    Statement sx = Consulta.createStatement();
                    ResultSet Ca = sx.executeQuery("Select P.Marca FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id");
                    while (Ca.next()) {
                        aux=Ca.getString(1);
                        if(Buscador.getItemCount()==0)
                        {
                            Buscador.addItem(aux);
                        }
                        else
                        {
                            Boolean Valor=false;
                            for (int i = 0; i < Buscador.getItemCount(); i++) {
                                if(aux.equals(Buscador.getItemAt(i)))
                                {
                                    Valor=true;
                                }
                        }
                        if(Valor==false)
                        {
                            Buscador.addItem(aux); 
                        }

                        }
                
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private void BuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorActionPerformed
         try {
            año = Inicio.getCalendar().get(Calendar.YEAR);
            mes = Inicio.getCalendar().get(Calendar.MONTH) + 1;
            dia = Inicio.getCalendar().get(Calendar.DAY_OF_MONTH);
            año2 = Final.getCalendar().get(Calendar.YEAR);
            mes2 = Final.getCalendar().get(Calendar.MONTH) + 1;
            dia2 = Final.getCalendar().get(Calendar.DAY_OF_MONTH);
        } catch (NullPointerException ex) {
                      
        }
        if(Cliente.isSelected())
        {
            String F1=año+"-"+mes+"-"+dia;
            String F2=año2+"-"+mes2+"-"+dia2;
            Clientes((String) Buscador.getSelectedItem(),F1,F2);
        }
        if(Codigo.isSelected())
        {
            String F1=año+"-"+mes+"-"+dia;
            String F2=año2+"-"+mes2+"-"+dia2;
            Codigo((String) Buscador.getSelectedItem(),F1,F2);
        }
        if(Producto.isSelected())
        {
            String F1=año+"-"+mes+"-"+dia;
            String F2=año2+"-"+mes2+"-"+dia2;
            Producto((String) Buscador.getSelectedItem(),F1,F2);
        }
        if(Marca.isSelected())
        {
            String F1=año+"-"+mes+"-"+dia;
            String F2=año2+"-"+mes2+"-"+dia2;
            Marca((String) Buscador.getSelectedItem(),F1,F2);
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_BuscadorActionPerformed
    private void Marca(String Nit,String F1,String F2)
    {
        modeloBusqueda.setRowCount(0);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id "
                    + "where P.Marca='"+Nit+"' && L.Fecha BETWEEN '"+F1+"' and '"+F2+"' Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void Producto(String Nit,String F1,String F2)
    {
        modeloBusqueda.setRowCount(0);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id "
                    + "where P.Nombre='"+Nit+"' && L.Fecha BETWEEN '"+F1+"' and '"+F2+"' Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    private void Codigo(String Nit,String F1,String F2)
    {
        modeloBusqueda.setRowCount(0);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id "
                    + "where P.Codigo='"+Nit+"' && L.Fecha BETWEEN '"+F1+"' and '"+F2+"' Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void Clientes(String Nit,String F1,String F2)
    {
        modeloBusqueda.setRowCount(0);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id where C.Nit='"+Nit+"' && L.Fecha BETWEEN '"+F1+"' and '"+F2+"' Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
   
    private void ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoActionPerformed
        if (Producto.isSelected()) {
            Todo.setSelected(false);
            Cliente.setSelected(false);
            Codigo.setSelected(false);
            Marca.setSelected(false);
            Buscador.setVisible(true);
            Llenar2("Producto");
        } else {
            Todo.setSelected(true);
            Buscador.setVisible(false);
        }
    }//GEN-LAST:event_ProductoActionPerformed
    private void Reporte_Cliente(String Nombre){
     
        try {
            Date uno=new Date(año-1900,mes-1,dia);
            Date dos=new Date(año2-1900,mes2-1,dia2);
            Connection tr = con.conexion();
            JasperReport reporte = null;
            String ruta = System.getProperty("user.dir");
            ruta = ruta + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "Reportes" + System.getProperty("file.separator") + "RegistroVentas_Cliente.jasper";
            Map parametro = new HashMap();
            parametro.put("Nit", Nombre);
            parametro.put("Fecha1",uno);
            parametro.put("Fecha2",dos);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, tr);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      private void Reporte_Producto(String Nombre){
     
        try {
            Date uno=new Date(año-1900,mes-1,dia);
            Date dos=new Date(año2-1900,mes2-1,dia2);
            Connection tr = con.conexion();
            JasperReport reporte = null;
            String ruta = System.getProperty("user.dir");
            ruta = ruta + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "Reportes" + System.getProperty("file.separator") + "RegistroVentas_Producto.jasper";
            Map parametro = new HashMap();
            parametro.put("Nit", Nombre);
            parametro.put("Fecha1",uno);
            parametro.put("Fecha2",dos);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, tr);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
      private void Reporte_Codigo(String Nombre){
     
        try {
            Date uno=new Date(año-1900,mes-1,dia);
            Date dos=new Date(año2-1900,mes2-1,dia2);
            Connection tr = con.conexion();
            JasperReport reporte = null;
            String ruta = System.getProperty("user.dir");
            ruta = ruta + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "Reportes" + System.getProperty("file.separator") + "RegistroVentas_Codigo.jasper";
            Map parametro = new HashMap();
            parametro.put("Nit", Nombre);
            parametro.put("Fecha1",uno);
            parametro.put("Fecha2",dos);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, tr);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
      private void Reporte_Marca(String Nombre){
     
        try {
            Date uno=new Date(año-1900,mes-1,dia);
            Date dos=new Date(año2-1900,mes2-1,dia2);
            Connection tr = con.conexion();
            JasperReport reporte = null;
            String ruta = System.getProperty("user.dir");
            ruta = ruta + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "Reportes" + System.getProperty("file.separator") + "RegistroVentas_Marca.jasper";
            Map parametro = new HashMap();
            parametro.put("Nit", Nombre);
            parametro.put("Fecha1",uno);
            parametro.put("Fecha2",dos);
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, tr);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if(Cliente.isSelected())
        {
           Reporte_Cliente((String)Buscador.getSelectedItem());
        }
        if(Codigo.isSelected())
        {
          Reporte_Codigo((String)Buscador.getSelectedItem());
        }
        if(Producto.isSelected())
        {
            Reporte_Producto((String)Buscador.getSelectedItem());
        }
        if(Marca.isSelected())
        {
            Reporte_Marca((String)Buscador.getSelectedItem());
        }
        if(Todo.isSelected())
        {
            Reporte((String)Buscador.getSelectedItem());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    private void Reporte(String g){
        try {
           
            Connection tr = con.conexion();
            JasperReport reporte = null;
            String ruta = System.getProperty("user.dir");
            ruta = ruta + System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "Reportes" + System.getProperty("file.separator") + "RegistroVentas.jasper";
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, tr);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

            // TODO add your handling code here:
        } catch (JRException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Menu xx = new Menu();
        xx.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void brz() {
        modeloBusqueda.setRowCount(0);
        
        String datos[] = new String[13];
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("Select L.Fecha,F.Serie,F.Numero,L.NoLote,P.Codigo,P.Nombre,P.Marca,L.Cantidad,P.Medida,L.PrecioUnitario,L.PrecioTotal,C.Nit,C.NombreC FROM Cliente C inner JOIN FacturaVenta F on C.id=F.Cliente_id inner JOIN LoteVenta L on L.FacturaVenta_id=F.id inner JOIN Producto P on P.id=L.Producto_id Order by L.Fecha");
            
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
            rSTableMetro1.setModel(modeloBusqueda);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void TodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodoActionPerformed
        if (Todo.isSelected()) {
            Cliente.setSelected(false);
            Producto.setSelected(false);
            Codigo.setSelected(false);
            Marca.setSelected(false);
            Buscador.setVisible(false);
            brz();
        } else {
            Todo.setSelected(true);
        }
        

    }//GEN-LAST:event_TodoActionPerformed

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoActionPerformed
        if (Codigo.isSelected()) {
            Todo.setSelected(false);
            Cliente.setSelected(false);
            Producto.setSelected(false);
            Marca.setSelected(false);
            Buscador.setVisible(true);
            Llenar2("Codigo");
            
        } else {
            Todo.setSelected(true);
            Buscador.setVisible(false);
            
        }
    }//GEN-LAST:event_CodigoActionPerformed

    private void MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaActionPerformed
        
        if (Marca.isSelected()) {
            Todo.setSelected(false);
            Cliente.setSelected(false);
            Producto.setSelected(false);
            Codigo.setSelected(false);
            Buscador.setVisible(true);
            Llenar2("Marca");
            
        } else {
            Todo.setSelected(true);
            Buscador.setVisible(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaActionPerformed

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
            java.util.logging.Logger.getLogger(Mostrarventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrarventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrarventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrarventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mostrarventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Buscador;
    private javax.swing.JRadioButton Cliente;
    private javax.swing.JRadioButton Codigo;
    private com.toedter.calendar.JDateChooser Final;
    private javax.swing.JLabel Final2;
    private com.toedter.calendar.JDateChooser Inicio;
    private javax.swing.JLabel Inicio2;
    private javax.swing.JRadioButton Marca;
    private javax.swing.JRadioButton Producto;
    private javax.swing.JRadioButton Todo;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro rSTableMetro1;
    // End of variables declaration//GEN-END:variables
}
