package inventario2;
//valor en globo bloquear
//import com.mxrck.autocompleter.TextAutoCompleter;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author sys515
 */
public class Compras extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn = con.conexion();
    Connection Consulta = con.conexion();
    Connection Consulta2 = con.conexion();
    Connection tr = con.conexion();
    private String nitglobal = null;
    private Boolean valor = false;
    private int tp = 0;
    private int n2 = 0;
    private int año = 0;
    private int mes = 0;
    private int dia = 0;
    private String Codigo;
    private String idUsuario="";
    private Boolean Seleccion=false;
    private String[] EFactura = new String[3];
    private String[] EProducto = new String[3];
    private String[] EDetalle = new String[3];
    private ArrayList<String> Detalles = new ArrayList<String>();
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    TextAutoCompleter textAutoCompleter;

    /**
     * Creates new form Compras
     */
    public Compras(String FacturaG[], String Producto[], String Detalle[],ArrayList<String> EDetalles) {
        initComponents();
        Nombre.setText("");
        Apellido.setText("");
        Nombre2.setText("");
        Marca.setText("");
        
        textAutoCompleter = new TextAutoCompleter(Pr, new AutoCompleterCallback() {
            @Override
            public void callback(Object selectedItem) {
                if (textAutoCompleter.itemExists(selectedItem)) {
                    llenar((String)selectedItem);
                    valor = true;
                    Seleccion = true;
                    

                } else {
                    JOptionPane.showMessageDialog(null, "No existe el producto");

                }
                
                }   
            
        });
        textAutoCompleter.setCaseSensitive(false);
        textAutoCompleter.setMode(0);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(dim);
        this.setLocationRelativeTo(null);

        this.setResizable(false);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        java.util.Date fecha = new Date();
        Fech.setDate(fecha);

        this.setSize(1265, 720);
        //AutoCompleteDecorator.decorate(Otro);
        AutoCompleteDecorator.decorate(Otro1);
        this.setResizable(false);
        modelo.setRowCount(0);
        modelo.addColumn("Codigo"); //0
        this.setTitle("Compras - Sistema Inventario BTZ");

        modelo.addColumn("Cantidad"); //1
        modelo.addColumn("Medida"); //2
        modelo.addColumn("Nombre");//3
        modelo.addColumn("Marca");//4
        modelo.addColumn("Costo Unitario");//5
        modelo.addColumn("Costo Total");//6
        //modelo.addColumn("Descripcion");//7
        modelo.addColumn("Ganancia");//8
        modelo.addColumn("Precio Unitario");//9
        modelo.addColumn("Precio Total");//10
        Factura.setModel(modelo);
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        //Costo.setText("");

        Cantidad.setText("");
        Serie.setText(FacturaG[0]);
        Numero.setText(FacturaG[1]);
        Ganancia.setText("");
        Serie.requestFocus();
        try {

            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("	SELECT P.Codigo,P.Nombre,P.Marca,U.Medida,Z.Presentacion,C.Categoria FROM Producto P \n"
                    + "inner join UnidadMedida_1 U \n"
                    + "on U.id=P.UnidadMedida_1_id\n"
                    + "inner join Presentacion_1 Z\n"
                    + "on Z.id=P.Presentacion_1_id\n"
                    + "inner join Catalogo C \n"
                    + "on C.id=P.Catalogo_id ");
            while (Ca.next()) {

                textAutoCompleter.addItem(Ca.getString(1) + "," + Ca.getString(2) + "," + Ca.getString(3) + "," + Ca.getString(4) + "," + Ca.getString(5)
                        + "," + Ca.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Statement sx2 = Consulta2.createStatement();
            ResultSet Ca2 = sx2.executeQuery("SELECT Nit FROM Proveedor");
            while (Ca2.next()) {
                Otro1.addItem(Ca2.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        Costo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Costo.setText(null);
                tp = 0;
                n2 = 0;
            }
        });

        Pr.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Pr.setText("");
            }
        });
        if (EDetalles.isEmpty()) {

        } else {
            int tamaño = EDetalles.size();
            int total = tamaño / 10;

            int conteo = 0;
            for (int i = 0; i < total; i++) {

                modelo.addRow(new Object[]{
                    EDetalles.get(conteo),
                    EDetalles.get(conteo + 1),
                    EDetalles.get(conteo + 2),
                    EDetalles.get(conteo + 3),
                    EDetalles.get(conteo + 4),
                    EDetalles.get(conteo + 5),
                    EDetalles.get(conteo + 6),
                    EDetalles.get(conteo + 7),
                    EDetalles.get(conteo + 8),
                    EDetalles.get(conteo + 9),
                    //EDetalles.get(conteo + 10)

                });
                conteo = conteo + 10;

            }
            sumar_columnas();

        }

    }

    private String mensaje(String x) {
        int numero = 0;
        try {
            
            numero = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("introduce numero"));
            return String.valueOf(numero);
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Solo se permiten numeros");
            
        }
        return x;
    }
    
    private int getidPro(String Nom, String Marca, String uni) {
        int id3 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT P.id FROM Producto P inner join UnidadMedida_1 U on U.id=P.UnidadMedida_1_id WHERE P.Nombre='" + Nom + "'&& P.Marca='" + Marca + "' && U.Medida='" + uni + "'");
            while (Ca.next()) {
                id3 = Integer.parseInt(Ca.getString(1));
            }
            return id3;
            
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private BigDecimal x(Double r) {
        return BigDecimal.valueOf(r).setScale(2, BigDecimal.ROUND_DOWN);
    }

    private int getidProve(String nit) {
        int nit2 = 0;
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Proveedor WHERE Nit='" + nit + "'");
            while (Ca.next()) {
                nit2 = Integer.parseInt(Ca.getString(1));
                
            }
            return nit2;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The1 content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        rSEstiloTablaHeader1 = new rojeru_san.complementos.RSEstiloTablaHeader();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPaneldedatos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Serie = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Numero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Otro1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Fech = new com.toedter.calendar.JDateChooser();
        Nombre2 = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        Nota = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        Costo = new javax.swing.JTextField();
        addfila = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        Unidad = new javax.swing.JTextField();
        Ganancia = new javax.swing.JTextField();
        Nombre = new javax.swing.JTextField();
        Marca = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        Pr = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        Uni = new javax.swing.JTextField();
        Prese = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Factura = new rojerusan.RSTableMetro();
        TotalCantidad = new javax.swing.JTextField();
        Totales = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jMenuItem1.setText("Eliminar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1000, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(17, 111, 172));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPaneldedatos.setBackground(new java.awt.Color(62, 142, 194));
        jPaneldedatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPaneldedatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Información de Facturación");
        jPaneldedatos.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Serie");
        jPaneldedatos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        Serie.setToolTipText("");
        jPaneldedatos.add(Serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 140, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Numero");
        jPaneldedatos.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        Numero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NumeroFocusLost(evt);
            }
        });
        Numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumeroKeyTyped(evt);
            }
        });
        jPaneldedatos.add(Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 200, -1));

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Nombre");
        jPaneldedatos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Representante");
        jPaneldedatos.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nit Proveedor");
        jPaneldedatos.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        Otro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Otro1ActionPerformed(evt);
            }
        });
        jPaneldedatos.add(Otro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 143, -1));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-agregar-administrador-50.png"))); // NOI18N
        jButton2.setText("Agregar proveedor");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-agregar-administrador-filled-50.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPaneldedatos.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 240, 230, 70));

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Proveedor");
        jLabel22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPaneldedatos.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setText("Fecha");
        jPaneldedatos.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));
        jPaneldedatos.add(Fech, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 170, 30));

        Nombre2.setEditable(false);
        jPaneldedatos.add(Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 210, -1));

        Apellido.setEditable(false);
        Apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApellidoActionPerformed(evt);
            }
        });
        jPaneldedatos.add(Apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 210, -1));

        Nota.setText("Nota De Crédito");
        Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotaActionPerformed(evt);
            }
        });
        jPaneldedatos.add(Nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jPanel1.add(jPaneldedatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 580, 320));

        jPanel2.setBackground(new java.awt.Color(189, 189, 189));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3499, 37, -1, -1));

        panel3.setBackground(new java.awt.Color(62, 142, 194));
        panel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel3.setAutoscrolls(true);
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Producto");
        panel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 20, 84, -1));

        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });
        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });
        panel3.add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 226, 100, -1));

        Costo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                CostoFocusLost(evt);
            }
        });
        Costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CostoActionPerformed(evt);
            }
        });
        Costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoKeyTyped(evt);
            }
        });
        panel3.add(Costo, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 264, 100, 29));

        addfila.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addfila.setForeground(new java.awt.Color(255, 255, 255));
        addfila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-más-2-matemáticas-50.png"))); // NOI18N
        addfila.setText("Agregar");
        addfila.setContentAreaFilled(false);
        addfila.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-más-2-matemáticas-filled-50.png"))); // NOI18N
        addfila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfilaActionPerformed(evt);
            }
        });
        panel3.add(addfila, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 160, 50));

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cantidad");
        panel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 233, -1, -1));

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CostoUnitario");
        panel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 270, -1, -1));

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Producto:");
        panel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 67, -1, -1));

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Marca:");
        panel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 105, -1, -1));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Unidad");
        panel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 144, -1, -1));

        jButton5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-producto-50.png"))); // NOI18N
        jButton5.setText("Agregar producto");
        jButton5.setContentAreaFilled(false);
        jButton5.setDefaultCapable(false);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-producto-filled-50.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, -1, 50));

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Ganancia");
        panel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 191, -1, -1));

        Unidad.setEditable(false);
        Unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnidadActionPerformed(evt);
            }
        });
        panel3.add(Unidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 143, 80, -1));

        Ganancia.setEditable(false);
        panel3.add(Ganancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 188, 80, -1));

        Nombre.setEditable(false);
        panel3.add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 60, 356, -1));

        Marca.setEditable(false);
        Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaActionPerformed(evt);
            }
        });
        panel3.add(Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 98, 356, -1));

        jTextField1.setEditable(false);
        jTextField1.setText("%");
        panel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 188, 42, -1));

        Pr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PrFocusLost(evt);
            }
        });
        Pr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PrKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PrKeyPressed(evt);
            }
        });
        panel3.add(Pr, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 13, 440, -1));

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Categoria");
        panel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 195, -1, -1));

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Presentación:");
        panel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 150, -1, -1));

        Uni.setEditable(false);
        panel3.add(Uni, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 188, 174, -1));

        Prese.setEditable(false);
        panel3.add(Prese, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 143, 174, -1));

        jPanel1.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 630, 320));

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cantidad Total");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 680, -1, -1));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Regresar");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 590, -1, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Comprar");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 450, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-50.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-volver-asignación-filled-50.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 530, 64, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-50.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/iconoso/icons8-comprar-filled-50.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 380, 80, 68));

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 680, -1, -1));

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
        Factura.setColorBackgoundHead(new java.awt.Color(0, 141, 232));
        Factura.setColorSelBackgound(new java.awt.Color(10, 221, 254));
        Factura.setComponentPopupMenu(jPopupMenu1);
        Factura.setFuenteHead(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jScrollPane3.setViewportView(Factura);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 1130, 280));

        TotalCantidad.setEditable(false);
        TotalCantidad.setText("0");
        TotalCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalCantidadActionPerformed(evt);
            }
        });
        jPanel1.add(TotalCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 640, 60, -1));

        Totales.setEditable(false);
        Totales.setText("0.0");
        jPanel1.add(Totales, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 640, 100, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1130, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int lotereciente(int idProd) {
        
        int Loteant = 0;
        try {
            
            Statement xq = Consulta2.createStatement();
            try (ResultSet red = xq.executeQuery("SELECT NoLote FROM Lote WHERE Producto_id ='" + String.valueOf(idProd) + "'&& NoLote= (SELECT MAX(NoLote) FROM Lote WHERE Producto_id ='" + String.valueOf(idProd) + "') ")) {
                while (red.next()) {
                    Loteant = Integer.parseInt(red.getString(1));
                    
                }
                
                Loteant++;
            }
            return Loteant;
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
    private void CrearLote(int idProd, int lotegrande, String idProv, String inf[], int id) {
        Calendar calendario = new GregorianCalendar();
     int hora =calendario.get(Calendar.HOUR_OF_DAY);
     int minutos = calendario.get(Calendar.MINUTE);
     int segundos = calendario.get(Calendar.SECOND);
        String FechaConHora=año+"-"+mes+"-"+dia+" "+hora+":"+minutos+
                ":"+segundos;
        try {
            int idUsuario = 0;
            
            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO Lote("
                    + "Producto_id,"
                    + "CostoUnitario,"
                    + "Cantidad,"
                    + "Cantidadi,"
                    + "CostoTotal,"
                    + "NoLote,"
                    + "Ganancia,"
                    + "PrecioUnitario,"
                    + "PrecioTotal,"
                    + "Fecha,"
                    + "FacturaCompra_id,"
                    + "Disponible,"
                    + "Anulado) VALUES(?,?,?,?,?,?,?,?,?,?,?,1,0)",
                    Statement.RETURN_GENERATED_KEYS);
            
            CrearLot.setString(1, String.valueOf(idProd));
            CrearLot.setString(2, inf[5]);
            CrearLot.setString(3, inf[1]);
            CrearLot.setString(4, inf[1]);
            CrearLot.setString(5, inf[6]);
            //CrearLot.setString(6, inf[7]);
            CrearLot.setString(6, String.valueOf(lotegrande));
            CrearLot.setString(7, inf[7]);
            CrearLot.setString(8, inf[8]);
            CrearLot.setString(9, inf[9]);
            CrearLot.setString(10, FechaConHora);
            CrearLot.setString(11, String.valueOf(id));
            CrearLot.executeUpdate();
            
            try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }
                
                idUsuario = rs.getInt(1);
                CrearLot.close();
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private String obtenerid(String nit) {
        
        String idProv = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rd = st.executeQuery("SELECT NombreV, Representante, id FROM Proveedor WHERE Nit ='" + nit + "'");
            while (rd.next()) {
                Nombre2.setText(rd.getString(1));
                Apellido.setText(rd.getString(2));
                
                idProv = rd.getString(3);
            }
            return idProv;
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void NM(String Codigo) {
        try {
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT Nombre,Marca,Ganancia FROM Producto WHERE Codigo='" + Codigo + "'");
            while (Ca.next()) {
                Nombre.setText(Ca.getString(1));
                Marca.setText(Ca.getString(2));
                Ganancia.setText(Ca.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        submenucompras men = new submenucompras();
        men.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        submenucompras men = new submenucompras();
        men.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
    private int Fecha2(String f) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fecha3 = dateFormat.format(date);
        
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fecha1 = null;
        Date fecha2 = null;
        try {
            
            fecha1 = formatoDelTexto.parse(fecha3);
            fecha2 = formatoDelTexto.parse(f);
            
        } catch (ParseException ex) {
            
            ex.printStackTrace();
            
        }
        return fecha1.compareTo(fecha2);
    }
    private Boolean validarFactura()
    {
        Boolean Valor=true;
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT F.id FROM FacturaCompra F Inner join Proveedor P on P.id=F.Proveedor_id"
                    + " WHERE P.Nit='" +nitglobal+ "' && F.Serie='"+Serie.getText()+"' && F.Numero='"+Numero.getText()+"'");
            while(Ca.next())
            {
                Valor=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Valor;
    }
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(construir())
        {
            try {

                año = Fech.getCalendar().get(Calendar.YEAR);
                mes = Fech.getCalendar().get(Calendar.MONTH) + 1;
                dia = Fech.getCalendar().get(Calendar.DAY_OF_MONTH);

            } catch (NullPointerException ex) {
            }
            if (año == 0 && dia == 0 && mes == 00) {
                JOptionPane.showMessageDialog(this, "Al menos selecciona una fecha válida!", "Error!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String F = año + "-" + mes + "-" + dia;
                if (Fecha2(F) >= 0) {
                    if (Serie.getText().equals("") || Numero.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Información de Factura Faltante");
                    } else {
                        if (Factura.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Factura Vacía");

                        } else {

                            if (validarFactura()) {
                                int idF = generarFactura();
                                String x[] = new String[11];
                                for (int i = 0; i < Factura.getRowCount(); i++) {
                                    for (int j = 0; j < Factura.getColumnCount(); j++) {
                                        x[j] = Factura.getValueAt(i, j).toString().trim();

                                    }
                                    int iddd = getidPro(x[3], x[4], x[2]);

                                    int loteee = lotereciente(iddd);
                                    CrearLote(iddd, loteee, obtenerid(nitglobal), x, idF);
                                }
                                JOptionPane.showMessageDialog(null, "Productos Comprado Con Exito");
                                submenucompras men = new submenucompras();
                                men.setVisible(true);
                                dispose();

                            } else {
                                JOptionPane.showMessageDialog(null, "Esta Factura ya esta registrada con el mismo Número y Nit");
                            }
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Fecha incoherente: " + F);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
   
    private int generarFactura() {
  
        int id = 0;
        Calendar calendario = new GregorianCalendar();
     int hora =calendario.get(Calendar.HOUR_OF_DAY);
     int minutos = calendario.get(Calendar.MINUTE);
     int segundos = calendario.get(Calendar.SECOND);
        String FechaConHora=año+"-"+mes+"-"+dia+" "+hora+":"+minutos+
                ":"+segundos;
        try {
            PreparedStatement CrearLot = tr.prepareStatement("INSERT INTO FacturaCompra(Serie,Numero,Total_Factura,Proveedor_id,Cantidad_Prod,Fecha,Usuarios_id,Anulado,Trans) VALUES(?,?,?,?,?,?,?,0,now())",
                    Statement.RETURN_GENERATED_KEYS);
            
            CrearLot.setString(1, Serie.getText());
            CrearLot.setString(2, Numero.getText());
            CrearLot.setString(3, Totales.getText());
            CrearLot.setString(4, String.valueOf(getidProve(nitglobal)));
            CrearLot.setString(5, TotalCantidad.getText());
            
            CrearLot.setString(6, FechaConHora);
            CrearLot.setString(7, idUsuario);
            CrearLot.executeUpdate();
            try (ResultSet rs = CrearLot.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new RuntimeException("no devolvió el ID");
                }
                
                id = rs.getInt(1);
                CrearLot.close();
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingreso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int filas = modelo.getRowCount();
        
        if (filas != 0) {
            for (int i = 0; i < Factura.getRowCount(); i++) {
                for (int j = 0; j < Factura.getColumnCount(); j++) {
                    Detalles.add(Factura.getValueAt(i, j).toString());
                }
            }
            
        }
        
        String[] EFactura = new String[3];
        EFactura[0] = Serie.getText();
        EFactura[1] = Numero.getText();
        Date aux = new Date();
        aux = Fech.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EFactura[2] = sdf.format(aux);
        IngresarProve2 x = new IngresarProve2(EFactura,Detalles);
        x.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Otro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Otro1ActionPerformed
        nitglobal = (String) Otro1.getSelectedItem();
        String id3 = null;
        id3 = obtenerid(nitglobal);
    }//GEN-LAST:event_Otro1ActionPerformed
    private Boolean CompararEntrada(String Nombre, String Marca, String unidad) {
        
        String x[] = new String[3];
        if (Factura.getRowCount() != 0) {
            for (int i = 0; i < Factura.getRowCount(); i++) {
                
                x[0] = Factura.getValueAt(i, 3).toString().trim();
                x[1] = Factura.getValueAt(i, 4).toString().trim();
                x[2] = Factura.getValueAt(i, 2).toString().trim();
                if (x[0].equals(Nombre) && x[1].equals(Marca) && x[2].equals(unidad)) {
                    
                    return false;
                    
                }
                
            }
        } else {
            return true;
            
        }
        return true;
        
    }
    private void addfilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addfilaActionPerformed
        if(Seleccion==true)
        {
            if (Cantidad.getText().equals("")||Costo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese la Cantidad que desea comprar de: " + Nombre.getText());

            } else {

                if (CompararEntrada(Nombre.getText(), Marca.getText(), (String) Unidad.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Ya tiene este Producto Registrdo en la Factura");
                } else {
                    String Completo = Codigo;
                    try {

                        modelo.addRow(new Object[]{Codigo, Cantidad.getText(), (String) Unidad.getText(), Nombre.getText(), Marca.getText(), Costo.getText(), CostoTotal(Double.parseDouble(Cantidad.getText()), Double.parseDouble(Costo.getText())),
                            Ganancia.getText(), PrecioUnitario(Double.parseDouble(Costo.getText()), Double.parseDouble(Ganancia.getText()), Double.parseDouble(Costo.getText())),
                            PrecioTotal(PrecioUnitario(Double.parseDouble(Costo.getText()), Double.parseDouble(Ganancia.getText()), Double.parseDouble(Costo.getText())), Double.parseDouble(Cantidad.getText()))});
                        int TC = 0;
                        TC = Integer.valueOf(TotalCantidad.getText());
                        BigDecimal auxT = BigDecimal.valueOf(Double.parseDouble(Totales.getText()));
                        auxT = auxT.add(PrecioTotal(PrecioUnitario(Double.parseDouble(Costo.getText()), Double.parseDouble(Ganancia.getText()), Double.parseDouble(Costo.getText())), Double.parseDouble(Cantidad.getText())));
                        Totales.setText(String.valueOf((auxT)));
                        TC = TC + Integer.valueOf(Cantidad.getText());
                        TotalCantidad.setText(String.valueOf(TC));
                        Cantidad.setText("");
                        Costo.setText("");
                        Nombre.setText("");
                        Marca.setText("");
                        Unidad.setText("");
                        Ganancia.setText("");
                        Uni.setText("");
                        Seleccion=false;
                        Prese.setText("");
                        Pr.setText("");
                        sumar_columnas();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error");

                    }

                }

            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Seleccion un Producto Antes");                
 
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_addfilaActionPerformed
    private BigDecimal CostoTotal(Double Cantidad, Double Costo) {
        return BigDecimal.valueOf(Cantidad).multiply(BigDecimal.valueOf(Costo)).setScale(2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal PrecioTotal(BigDecimal Uni, Double T) {
        return Uni.multiply(BigDecimal.valueOf(T)).setScale(2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal PrecioUnitario(Double PreU, Double G, Double CU) {
        BigDecimal w = BigDecimal.valueOf(PreU).multiply(BigDecimal.valueOf(G)).setScale(2, BigDecimal.ROUND_DOWN);        
        w = w.divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_DOWN);
        w = w.add(BigDecimal.valueOf(CU)).setScale(2, BigDecimal.ROUND_DOWN);
        return w;
    }
    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k >= 33 && k <= 47) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            Costo.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadKeyTyped

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed
    
    private void LlenarUnidad(String s) {
        try {
            Statement xq = cn.createStatement();
            ResultSet red = xq.executeQuery("SELECT Medida FROM Producto WHERE Codigo ='" + s + "' ");
            while (red.next()) {
                Unidad.setText(red.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void NumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumeroKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k >= 33 && k <= 47) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
           // Numero.transferFocus();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_NumeroKeyTyped

    private void NumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NumeroFocusLost
//        Fech.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_NumeroFocusLost

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        int seleccionar = 0;
        seleccionar = Factura.getSelectedRow();
        if (seleccionar == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un fila valida");
        } else {
            modelo.removeRow(seleccionar);
        }
        sumar_columnas();

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private Boolean ifexist()
    {
        Boolean au=false;
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM Presentacion_1");
            while(Ca.next())
            {
                au=true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return au;
    }
     private Boolean ifexist2()
    {
        Boolean au=false;
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("SELECT id FROM UnidadMedida_1");
            while(Ca.next())
            {
                au=true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return au;
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(ifexist2()&&ifexist())
        {
            int filas = modelo.getRowCount();

            if (filas != 0) {
                for (int i = 0; i < Factura.getRowCount(); i++) {
                    for (int j = 0; j < Factura.getColumnCount(); j++) {
                        Detalles.add(Factura.getValueAt(i, j).toString());
                    }
                }

            }

            String[] EFactura = new String[3];
            EFactura[0] = Serie.getText();
            EFactura[1] = Numero.getText();
            Date aux = new Date();
            aux = Fech.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            EFactura[2] = sdf.format(aux);
            CrearProducto ss = new CrearProducto(EFactura, EProducto, EDetalle,Detalles);
            ss.setVisible(true);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Categoria y/o Medidas Vacias, Ingrese Nuevas ");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApellidoActionPerformed

    private void UnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UnidadActionPerformed

    private void MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaActionPerformed

    private void TotalCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalCantidadActionPerformed
    private void llenar(String datos) {
        String[] parts = datos.split(",");
        String part1 = parts[0];        
        Codigo = part1;
        llenarPM(Codigo);
    }

    private void llenarPM(String Codigo) {
        
        try {
            
            Statement sx = Consulta.createStatement();
            ResultSet Ca = sx.executeQuery("select P.Nombre,P.Marca,U.Medida,P.Ganancia,Z.Presentacion,C.Categoria FROM Producto P inner join UnidadMedida_1 U on U.id=P.UnidadMedida_1_id inner join Presentacion_1 Z\n" +
"on Z.id=P.Presentacion_1_id\n" +
"inner join Catalogo C \n" +
"on C.id=P.Catalogo_id  Where P.Codigo='"+Codigo+"'");
            while (Ca.next()) {
                
                Unidad.setText(Ca.getString(3));
                Nombre.setText(Ca.getString(1));
                Marca.setText(Ca.getString(2));
                Ganancia.setText(Ca.getString(4));
                Prese.setText(Ca.getString(5));
                Uni.setText(Ca.getString(6));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void PrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrKeyTyped
        int k = (int) evt.getKeyChar();
        if (k == 10) {
            
            if (textAutoCompleter.itemExists(textAutoCompleter.getItemSelected())) {
                llenar((String) textAutoCompleter.getItemSelected());
                valor = true;
                Seleccion=true;
                            Cantidad.requestFocus();

            } else {
                JOptionPane.showMessageDialog(null, "No existe el producto");
                
            }
            
        }
       
        
        // TODO add your handling code here:
    }//GEN-LAST:event_PrKeyTyped

    private void PrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_PrKeyPressed

    private void CostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyTyped

        int k = (int) evt.getKeyChar();
        if (k == 10) {
           // Costo.transferFocus();
            tp = 1;
            Costo.setText("" + x(Double.parseDouble(Costo.getText())));

        } else {
            if (k == 46) {
                tp++;
            }

            if (tp > 1) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "Punto de mas", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
                tp--;
            }

            if (k >= 97 && k <= 127 || k >= 58 && k <= 97) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }
            if (k == 241 || k == 209) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }
            if (k >= 33 && k <= 45 || k == 47) {
                evt.setKeyChar((char) KeyEvent.VK_CLEAR);
                JOptionPane.showMessageDialog(null, "No puede ingresar Simbolos!!!", "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_CostoKeyTyped

    private void CostoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CostoFocusLost
        try {
            Costo.setText("" + x(Double.parseDouble(Costo.getText())));
        } catch (NumberFormatException ex) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoFocusLost

    private void Construir()
            
    {
       // NotaCredito n=new NotaCredito(null,true);
        
    }
    private void NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotaActionPerformed
        if(Nota.isSelected())
        {
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_NotaActionPerformed

    private void CostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CostoActionPerformed

    private void PrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PrFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PrFocusLost
    private void sumar_columnas() {
        if (Factura.getRowCount() != 0) {
            BigDecimal n = BigDecimal.valueOf(0);
            int c = 0;
            for (int i = 0; i < Factura.getRowCount(); i++) {
                c = c + Integer.valueOf(Factura.getValueAt(i, 1).toString().trim());
                n = n.add(BigDecimal.valueOf(Double.valueOf(Factura.getValueAt(i, 9).toString().trim())));
                
            }
            TotalCantidad.setText(String.valueOf(c));
            Totales.setText(String.valueOf(n));
        } else {
            TotalCantidad.setText("0");
            Totales.setText("0.00");
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
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Compras(null, null, null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Costo;
    private rojerusan.RSTableMetro Factura;
    private com.toedter.calendar.JDateChooser Fech;
    private javax.swing.JLabel Fecha;
    private javax.swing.JTextField Ganancia;
    private javax.swing.JTextField Marca;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Nombre2;
    private javax.swing.JRadioButton Nota;
    private javax.swing.JTextField Numero;
    private javax.swing.JComboBox<String> Otro1;
    private javax.swing.JTextField Pr;
    private javax.swing.JTextField Prese;
    private javax.swing.JTextField Serie;
    private javax.swing.JTextField TotalCantidad;
    private javax.swing.JTextField Totales;
    private javax.swing.JTextField Uni;
    private javax.swing.JTextField Unidad;
    private javax.swing.JButton addfila;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPaneldedatos;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panel3;
    private rojeru_san.complementos.RSEstiloTablaHeader rSEstiloTablaHeader1;
    // End of variables declaration//GEN-END:variables
}
