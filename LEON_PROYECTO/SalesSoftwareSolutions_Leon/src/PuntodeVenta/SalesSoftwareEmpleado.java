/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntodeVenta;

import DesignSoftware.BordesButton;
import DesignSoftware.DiseñoPanel;
import DesignSoftware.FormDesign;
import DesignSoftware.FrameDesign;
import GestionArticulos.BdTabla;
import GestionVentas.ReducirArticulos;
import Login.Login;
import ModelDB.conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.util.Date;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
public class SalesSoftwareEmpleado extends javax.swing.JFrame {
//  Variables de caracter obligatorio para su uso en la aplicación.

    private conexion con = new conexion();
    private FormDesign shape;
    private FrameDesign img;
    private DiseñoPanel tabPanel;
    private BordesButton bordesBoton;
    private JTableHeader Header;
    private ImageIcon icon;
    private Pattern p;
    private Matcher m;
    public int x = 0, y = 0, bandera = 0;
    private String separarCodigo = "";
    private String codigoBaseEdicion = "";
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String dateStringInicio = "";
    private String dateStringFin = "";
    private String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    private DecimalFormat df = new DecimalFormat("#.##");
    String URL = "../Images/loginImages/";
    String URL1 = "../Images/articulos/";
    String[] Datos = new String[5];
    String unidadTablaTemp = "";

//    Hace que las celdas de las tablas que la ocupen sean No editables.
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form SalesSoftware
     */
//    Constructor: Tiene como parametro el usuario, es quien inicio la sesión.
//    Tiene diferentes métodos que llamar para su funcionamiento correctamente.
    public SalesSoftwareEmpleado(String usuario) {
        this.getContentPane().setBackground(new Color(116, 16, 30));
        initComponents();
        this.setLocationRelativeTo(null);
        this.lbAdmin.setText(usuario);
        getFechaActual();
        tablaTemporal();
        cargar();
        diseñar();
        activarTab();
        actualizarF5();
    }

//    Método  que proporciona la fecha actual.
    private void getFechaActual() {
        Date hoy = new Date();
        Calendar cal1 = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
        lbAdmin1.setText(formato.format(hoy));
    }

//    Método  que crea y da formato a la tabla de jtVentasEmpleado.
    public void tablaTemporal() {
        Header = jtVentasEmpleado.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 10));
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("UNIDAD");
        modelo.addColumn("CONCEPTO");
        modelo.addColumn("PRECIO");
        modelo.addColumn("IMPORTE");
        jtVentasEmpleado.setModel(modelo);
    }

//    Metodo que permite activar el evento del Tab en la aplicacion.
    public void activarTab() {
//        Campos de texto del panel de ariculos en la opcion de agregado.
        txtProducto.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtUnidad.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtExistencia.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtPrecio.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        //        Campos de texto del panel de ariculos en la opcion de Edición.
        txtProductoE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtUnidadE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtExistenciaE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtPrecioE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        //        Campos de texto del panel de Clientes en la opcion de agregado.
        txtRfc.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEmpresa.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtDomicilio.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtCiudad.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEstado.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEmail.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        //        Campos de texto del panel de Clientes en la opcion de Edicion.
        txtRfcE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEmpresaE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtDomicilioE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtCiudadE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEstadoE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtEmailE.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        //        Campos de texto del panel de Ventas en la opcion de ventas.
        txtCodigoVentas.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtCantidad.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

        txtPago.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
    }

//    Método que permite agregar diseño a la cabecera de la tabla JtArticulos & jtCliente.
    public void diseñar() {
        Header = JtArticulos.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        BdTabla tA = new BdTabla(JtArticulos);

        Header = jtCliente.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        GestionClientes.BdTabla tC = new GestionClientes.BdTabla(jtCliente);
    }

//    Método que permite aactivar en el teclado del usuario el F5.
    public void actualizarF5() {
        String key = "";

        //        Atsjo de tecla Ctrl+B psra el focus en el campo de texto 'buscar' en el panel articulos.
        Action buttonActionB = new AbstractAction("F5") {
            @Override

            public void actionPerformed(ActionEvent evt) {
                txtBuscar.requestFocus();
            }
        };
        key = "Referesh";
        txtBuscar.setAction(buttonActionB);
        buttonActionB.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        txtBuscar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK), key);
        txtBuscar.getActionMap().put(key, buttonActionB);

        //        Atsjo de tecla Ctrl+B psra el focus en el campo de texto 'buscar' en el panel clientes.
        Action buttonActionB1 = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtBuscarCliente.requestFocus();
            }
        };
        key = "Referesh";
        txtBuscarCliente.setAction(buttonActionB1);
        buttonActionB1.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        txtBuscarCliente.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK), key);
        txtBuscarCliente.getActionMap().put(key, buttonActionB1);

        //        Atsjo de tecla Ctrl+B psra el focus en el campo de texto 'buscar' en el panel usuarios.
        Action buttonActionB3 = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtBuscarUsers.requestFocus();
            }
        };
        key = "Referesh";
        txtBuscarUsers.setAction(buttonActionB3);
        buttonActionB3.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        txtBuscarUsers.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK), key);
        txtBuscarUsers.getActionMap().put(key, buttonActionB3);
    }

//    Método que permite agregar diseño de imagenes a los botones,jlabela,panels,tabpanels, entre otros.
    public void cargar() {
        shape = new FormDesign(this);

        img = new FrameDesign(URL + "buttonMinimize.png");
        img.addButton(btnMinimizar);
        img = new FrameDesign(URL + "buttonClose.png");
        img.addButton(btnCerrar);
        img = new FrameDesign(URL + "back.png");
        img.addButton(btnBack);

        img = new FrameDesign("../Splash/SalesSolutions.png");
        img.addLabel(jLabel1);

        tabPanel = new DiseñoPanel(panelPuntoVenta, JpArticulos, "ARTÍCULOS", "productos.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpClientes, "CLIENTES", "clientes.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpVentas, "VENTAS", "ventas.png");

        JpAgregar.setVisible(false);
        JpEditar.setVisible(false);
        JpAgregarCliente.setVisible(false);
        JpEditarCliente.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        barraFormulario = new javax.swing.JLabel();
        lbAdmin = new javax.swing.JLabel();
        panelPuntoVenta = new javax.swing.JTabbedPane();
        JpArticulos = new javax.swing.JPanel();
        menuArticulo = new javax.swing.JLabel();
        JpListado = new javax.swing.JPanel();
        scrolArticulos = new javax.swing.JScrollPane();
        JtArticulos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        JpAgregar = new javax.swing.JPanel();
        btnAñadir = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtUnidad = new javax.swing.JTextField();
        JpEditar = new javax.swing.JPanel();
        btnAñadir1 = new javax.swing.JButton();
        txtCodigoE = new javax.swing.JTextField();
        txtProductoE = new javax.swing.JTextField();
        txtExistenciaE = new javax.swing.JTextField();
        txtPrecioE = new javax.swing.JTextField();
        lbProducto = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        lbEmailk = new javax.swing.JLabel();
        lbNotas = new javax.swing.JLabel();
        txtUnidadE = new javax.swing.JTextField();
        JpClientes = new javax.swing.JPanel();
        btnAgregarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnListaCliente = new javax.swing.JButton();
        menuCliente = new javax.swing.JLabel();
        JpListadoCliente = new javax.swing.JPanel();
        scrolCliente = new javax.swing.JScrollPane();
        jtCliente = new javax.swing.JTable();
        btnBuscarCliente = new javax.swing.JButton();
        txtBuscarCliente = new javax.swing.JTextField();
        JpAgregarCliente = new javax.swing.JPanel();
        btnAñadirCliente = new javax.swing.JButton();
        txtRfc = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();
        JpEditarCliente = new javax.swing.JPanel();
        btnAñadir2 = new javax.swing.JButton();
        txtRfcE = new javax.swing.JTextField();
        txtEmpresaE = new javax.swing.JTextField();
        txtDomicilioE = new javax.swing.JTextField();
        txtCiudadE = new javax.swing.JTextField();
        txtEmailE = new javax.swing.JTextField();
        txtEstadoE = new javax.swing.JTextField();
        lbRfc = new javax.swing.JLabel();
        lbEmpresa = new javax.swing.JLabel();
        lbDomicilio = new javax.swing.JLabel();
        lbCiudad = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbEstado1 = new javax.swing.JLabel();
        JpVentas = new javax.swing.JPanel();
        btnEditarUser = new javax.swing.JButton();
        btnInf = new javax.swing.JButton();
        btnAgregarUser = new javax.swing.JButton();
        btnEliminarUser = new javax.swing.JButton();
        menuUsuarios = new javax.swing.JLabel();
        JpListadoUsuarios = new javax.swing.JPanel();
        txtCodigoVentas = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPrecioEmpleado = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtBuscarUsers = new javax.swing.JTextField();
        scrolUsuarios = new javax.swing.JScrollPane();
        jtVentasEmpleado = new javax.swing.JTable();
        btnBuscarUser = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        lbTotal = new javax.swing.JLabel();
        lbIva = new javax.swing.JLabel();
        lbVentas = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbPago = new javax.swing.JLabel();
        lbCambio = new javax.swing.JLabel();
        txtPago = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        lbAdmin1 = new javax.swing.JLabel();
        barraEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("puntoVenta"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 150, 160));

        btnBack.setBorder(null);
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBackMouseExited(evt);
            }
        });
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 32, 32));

        btnMinimizar.setBorder(null);
        btnMinimizar.setBorderPainted(false);
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseExited(evt);
            }
        });
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 32, 32));

        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 32, 32));

        barraFormulario.setBackground(new java.awt.Color(255, 255, 255));
        barraFormulario.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        barraFormulario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barraFormulario.setText("PUNTO DE VENTA");
        barraFormulario.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        barraFormulario.setOpaque(true);
        barraFormulario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraFormularioMouseDragged(evt);
            }
        });
        barraFormulario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraFormularioMousePressed(evt);
            }
        });
        getContentPane().add(barraFormulario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 40));

        lbAdmin.setBackground(new java.awt.Color(116, 16, 30));
        lbAdmin.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        lbAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lbAdmin.setText("Admin");
        lbAdmin.setOpaque(true);
        getContentPane().add(lbAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 140, 30));

        panelPuntoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelPuntoVenta.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        panelPuntoVenta.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        panelPuntoVenta.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        panelPuntoVenta.setMinimumSize(new java.awt.Dimension(688, 425));
        panelPuntoVenta.setPreferredSize(new java.awt.Dimension(688, 425));

        JpArticulos.setBackground(new java.awt.Color(255, 255, 255));
        JpArticulos.setPreferredSize(new java.awt.Dimension(516, 420));
        JpArticulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuArticulo.setBackground(new java.awt.Color(116, 16, 30));
        menuArticulo.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        menuArticulo.setForeground(new java.awt.Color(255, 255, 255));
        menuArticulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/listaP.png"))); // NOI18N
        menuArticulo.setText("LISTADO DE ARTÍCULOS   ");
        menuArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuArticulo.setDoubleBuffered(true);
        menuArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        menuArticulo.setOpaque(true);
        JpArticulos.add(menuArticulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListado.setBackground(new java.awt.Color(255, 255, 255));
        JpListado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        scrolArticulos.setBackground(new java.awt.Color(255, 255, 255));
        scrolArticulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolArticulos.setForeground(new java.awt.Color(255, 255, 255));
        scrolArticulos.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        JtArticulos.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        JtArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CÓDIGO ", "PRODUCTO", "EXISTENCIA", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtArticulos.setGridColor(new java.awt.Color(116, 16, 30));
        JtArticulos.setOpaque(false);
        JtArticulos.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolArticulos.setViewportView(JtArticulos);

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMouseExited(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBuscar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscar.setText("Buscar Codigo/Producto");
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JpListadoLayout = new javax.swing.GroupLayout(JpListado);
        JpListado.setLayout(JpListadoLayout);
        JpListadoLayout.setHorizontalGroup(
            JpListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(JpListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrolArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JpListadoLayout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        JpListadoLayout.setVerticalGroup(
            JpListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(JpListadoLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrolArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        JpArticulos.add(JpListado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        JpAgregar.setBackground(new java.awt.Color(255, 255, 255));
        JpAgregar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "ARTÍCULOS PAPELERIA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(91, 137, 193))); // NOI18N
        JpAgregar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JpAgregar.setPreferredSize(new java.awt.Dimension(516, 350));

        btnAñadir.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/accept1.png"))); // NOI18N
        btnAñadir.setText("Aceptar");
        btnAñadir.setBorder(null);
        btnAñadir.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAñadir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadirMouseExited(evt);
            }
        });
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtCodigo.setText("CODIGO ARTÍCULO");
        txtCodigo.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoFocusLost(evt);
            }
        });
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtProducto.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtProducto.setText("NOMBRE ARTÍCULO");
        txtProducto.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProductoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProductoFocusLost(evt);
            }
        });
        txtProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoKeyPressed(evt);
            }
        });

        txtExistencia.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtExistencia.setText("EXISTENCIA ARTÍCULO");
        txtExistencia.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtExistencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtExistenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtExistenciaFocusLost(evt);
            }
        });
        txtExistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExistenciaKeyPressed(evt);
            }
        });

        txtPrecio.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtPrecio.setText("PRECIO ARTÍCULO");
        txtPrecio.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioFocusLost(evt);
            }
        });
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
        });

        txtUnidad.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtUnidad.setText("UNIDAD");
        txtUnidad.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtUnidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUnidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUnidadFocusLost(evt);
            }
        });
        txtUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnidadKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout JpAgregarLayout = new javax.swing.GroupLayout(JpAgregar);
        JpAgregar.setLayout(JpAgregarLayout);
        JpAgregarLayout.setHorizontalGroup(
            JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpAgregarLayout.createSequentialGroup()
                .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpAgregarLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodigo)
                                .addComponent(txtProducto)
                                .addComponent(txtExistencia)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JpAgregarLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        JpAgregarLayout.setVerticalGroup(
            JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        JpArticulos.add(JpAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 360));

        JpEditar.setBackground(new java.awt.Color(255, 255, 255));
        JpEditar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "EDICIÓN DE ARTÍCULOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(91, 137, 193))); // NOI18N
        JpEditar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JpEditar.setPreferredSize(new java.awt.Dimension(516, 350));

        btnAñadir1.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadir1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAñadir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/accept1.png"))); // NOI18N
        btnAñadir1.setText("Aceptar");
        btnAñadir1.setBorder(null);
        btnAñadir1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAñadir1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadir1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadir1MouseExited(evt);
            }
        });
        btnAñadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadir1ActionPerformed(evt);
            }
        });

        txtCodigoE.setEditable(false);
        txtCodigoE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtCodigoE.setSelectionColor(new java.awt.Color(91, 137, 193));

        txtProductoE.setEditable(false);
        txtProductoE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtProductoE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtProductoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoEKeyPressed(evt);
            }
        });

        txtExistenciaE.setEditable(false);
        txtExistenciaE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtExistenciaE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtExistenciaE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExistenciaEKeyPressed(evt);
            }
        });

        txtPrecioE.setEditable(false);
        txtPrecioE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtPrecioE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtPrecioE.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioEFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioEFocusLost(evt);
            }
        });
        txtPrecioE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioEKeyPressed(evt);
            }
        });

        lbProducto.setBackground(new java.awt.Color(255, 255, 255));
        lbProducto.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbProducto.setText("Nombre Artículo");
        lbProducto.setOpaque(true);

        lbNombre.setBackground(new java.awt.Color(255, 255, 255));
        lbNombre.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbNombre.setText("Codigo Artículo");
        lbNombre.setOpaque(true);

        lbUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lbUsuario.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbUsuario.setText("Unidad");
        lbUsuario.setOpaque(true);

        lbEmailk.setBackground(new java.awt.Color(255, 255, 255));
        lbEmailk.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEmailk.setText("Existencia de Artículo");
        lbEmailk.setOpaque(true);

        lbNotas.setBackground(new java.awt.Color(255, 255, 255));
        lbNotas.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbNotas.setText("Precio Artículo");
        lbNotas.setOpaque(true);

        txtUnidadE.setEditable(false);
        txtUnidadE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtUnidadE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtUnidadE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUnidadEKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout JpEditarLayout = new javax.swing.GroupLayout(JpEditar);
        JpEditar.setLayout(JpEditarLayout);
        JpEditarLayout.setHorizontalGroup(
            JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpEditarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JpEditarLayout.createSequentialGroup()
                        .addComponent(txtUnidadE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnAñadir1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpEditarLayout.createSequentialGroup()
                        .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProductoE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtExistenciaE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEmailk, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        JpEditarLayout.setVerticalGroup(
            JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEditarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JpEditarLayout.createSequentialGroup()
                        .addComponent(lbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductoE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExistenciaE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpEditarLayout.createSequentialGroup()
                        .addComponent(lbEmailk, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)))
                .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUnidadE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        JpArticulos.add(JpEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 360));

        panelPuntoVenta.addTab("ARTICULOS", JpArticulos);

        JpClientes.setBackground(new java.awt.Color(255, 255, 255));
        JpClientes.setPreferredSize(new java.awt.Dimension(516, 420));
        JpClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarCliente.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/add.png"))); // NOI18N
        btnAgregarCliente.setMnemonic('A');
        btnAgregarCliente.setText("Agregar");
        btnAgregarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnAgregarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAgregarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgregarCliente.setPreferredSize(new java.awt.Dimension(90, 30));
        btnAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarClienteMouseExited(evt);
            }
        });
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });
        JpClientes.add(btnAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 90, 30));

        btnEditarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarCliente.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/edit.png"))); // NOI18N
        btnEditarCliente.setMnemonic('E');
        btnEditarCliente.setText("Editar");
        btnEditarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEditarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEditarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditarCliente.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEditarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarClienteMouseExited(evt);
            }
        });
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });
        JpClientes.add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 90, 30));

        btnListaCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnListaCliente.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnListaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/list.png"))); // NOI18N
        btnListaCliente.setMnemonic('L');
        btnListaCliente.setText("Listado");
        btnListaCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnListaCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnListaCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnListaCliente.setPreferredSize(new java.awt.Dimension(90, 30));
        btnListaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListaClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListaClienteMouseExited(evt);
            }
        });
        btnListaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaClienteActionPerformed(evt);
            }
        });
        JpClientes.add(btnListaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 30));

        menuCliente.setBackground(new java.awt.Color(116, 16, 30));
        menuCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuCliente.setDoubleBuffered(true);
        menuCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuCliente.setOpaque(true);
        JpClientes.add(menuCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListadoCliente.setBackground(new java.awt.Color(255, 255, 255));
        JpListadoCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        scrolCliente.setBackground(new java.awt.Color(255, 255, 255));
        scrolCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolCliente.setForeground(new java.awt.Color(255, 255, 255));
        scrolCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jtCliente.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jtCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "RFC", "EMPRESA/NOMBRE", "DIRECCION", "E-MAIL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCliente.setGridColor(new java.awt.Color(116, 16, 30));
        jtCliente.setOpaque(false);
        jtCliente.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolCliente.setViewportView(jtCliente);

        btnBuscarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarCliente.setBorderPainted(false);
        btnBuscarCliente.setContentAreaFilled(false);
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseExited(evt);
            }
        });
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        txtBuscarCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarCliente.setText("Buscar RFC/Empresa");
        txtBuscarCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarClienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarClienteFocusLost(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JpListadoClienteLayout = new javax.swing.GroupLayout(JpListadoCliente);
        JpListadoCliente.setLayout(JpListadoClienteLayout);
        JpListadoClienteLayout.setHorizontalGroup(
            JpListadoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoClienteLayout.createSequentialGroup()
                .addGroup(JpListadoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoClienteLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpListadoClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrolCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        JpListadoClienteLayout.setVerticalGroup(
            JpListadoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoClienteLayout.createSequentialGroup()
                .addGroup(JpListadoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCliente)
                    .addGroup(JpListadoClienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(scrolCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        JpClientes.add(JpListadoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        JpAgregarCliente.setBackground(new java.awt.Color(255, 255, 255));
        JpAgregarCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(91, 137, 193))); // NOI18N
        JpAgregarCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JpAgregarCliente.setPreferredSize(new java.awt.Dimension(516, 350));

        btnAñadirCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadirCliente.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAñadirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/accept1.png"))); // NOI18N
        btnAñadirCliente.setText("Aceptar");
        btnAñadirCliente.setBorder(null);
        btnAñadirCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAñadirCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadirCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadirClienteMouseExited(evt);
            }
        });
        btnAñadirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirClienteActionPerformed(evt);
            }
        });

        txtRfc.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtRfc.setText("RFC");
        txtRfc.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtRfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRfcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRfcFocusLost(evt);
            }
        });
        txtRfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRfcKeyPressed(evt);
            }
        });

        txtEmpresa.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmpresa.setText("EMPRESA/NOMBRE");
        txtEmpresa.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmpresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmpresaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmpresaFocusLost(evt);
            }
        });
        txtEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpresaKeyPressed(evt);
            }
        });

        txtDomicilio.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtDomicilio.setText("DOMICILIO");
        txtDomicilio.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtDomicilio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDomicilioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDomicilioFocusLost(evt);
            }
        });
        txtDomicilio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDomicilioKeyPressed(evt);
            }
        });

        txtCiudad.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtCiudad.setText("CIUDAD");
        txtCiudad.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtCiudad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCiudadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCiudadFocusLost(evt);
            }
        });
        txtCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiudadKeyPressed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmail.setText("E-MAIL");
        txtEmail.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        txtEstado.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEstado.setText("ESTADO");
        txtEstado.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEstado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEstadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEstadoFocusLost(evt);
            }
        });
        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstadoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout JpAgregarClienteLayout = new javax.swing.GroupLayout(JpAgregarCliente);
        JpAgregarCliente.setLayout(JpAgregarClienteLayout);
        JpAgregarClienteLayout.setHorizontalGroup(
            JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpAgregarClienteLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpAgregarClienteLayout.createSequentialGroup()
                        .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtRfc)
                            .addComponent(txtEmpresa)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEstado)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JpAgregarClienteLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnAñadirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JpAgregarClienteLayout.setVerticalGroup(
            JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpAgregarClienteLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JpAgregarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpAgregarClienteLayout.createSequentialGroup()
                        .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpAgregarClienteLayout.createSequentialGroup()
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(btnAñadirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        JpClientes.add(JpAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 360));

        JpEditarCliente.setBackground(new java.awt.Color(255, 255, 255));
        JpEditarCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "EDICIÓN DE CLIENTES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 14), new java.awt.Color(91, 137, 193))); // NOI18N
        JpEditarCliente.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        JpEditarCliente.setPreferredSize(new java.awt.Dimension(516, 350));

        btnAñadir2.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadir2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAñadir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/accept1.png"))); // NOI18N
        btnAñadir2.setText("Aceptar");
        btnAñadir2.setBorder(null);
        btnAñadir2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAñadir2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAñadir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadir2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadir2MouseExited(evt);
            }
        });
        btnAñadir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadir2ActionPerformed(evt);
            }
        });

        txtRfcE.setEditable(false);
        txtRfcE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtRfcE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtRfcE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRfcEKeyPressed(evt);
            }
        });

        txtEmpresaE.setEditable(false);
        txtEmpresaE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmpresaE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmpresaE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpresaEKeyPressed(evt);
            }
        });

        txtDomicilioE.setEditable(false);
        txtDomicilioE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtDomicilioE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtDomicilioE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDomicilioEKeyPressed(evt);
            }
        });

        txtCiudadE.setEditable(false);
        txtCiudadE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtCiudadE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtCiudadE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiudadEKeyPressed(evt);
            }
        });

        txtEmailE.setEditable(false);
        txtEmailE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmailE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmailE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailEKeyPressed(evt);
            }
        });

        txtEstadoE.setEditable(false);
        txtEstadoE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEstadoE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEstadoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstadoEKeyPressed(evt);
            }
        });

        lbRfc.setBackground(new java.awt.Color(255, 255, 255));
        lbRfc.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbRfc.setText("RFC");
        lbRfc.setOpaque(true);

        lbEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        lbEmpresa.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEmpresa.setText("Empresa");
        lbEmpresa.setOpaque(true);

        lbDomicilio.setBackground(new java.awt.Color(255, 255, 255));
        lbDomicilio.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbDomicilio.setText("Domicilio");
        lbDomicilio.setOpaque(true);

        lbCiudad.setBackground(new java.awt.Color(255, 255, 255));
        lbCiudad.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbCiudad.setText("Ciudad");
        lbCiudad.setOpaque(true);

        lbEstado.setBackground(new java.awt.Color(255, 255, 255));
        lbEstado.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEstado.setText("Estado");
        lbEstado.setOpaque(true);

        lbEstado1.setBackground(new java.awt.Color(255, 255, 255));
        lbEstado1.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEstado1.setText("E-mail");
        lbEstado1.setOpaque(true);

        javax.swing.GroupLayout JpEditarClienteLayout = new javax.swing.GroupLayout(JpEditarCliente);
        JpEditarCliente.setLayout(JpEditarClienteLayout);
        JpEditarClienteLayout.setHorizontalGroup(
            JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpEditarClienteLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtEmpresaE)
                                        .addComponent(txtDomicilioE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmailE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRfcE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCiudadE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(JpEditarClienteLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(btnAñadir2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        JpEditarClienteLayout.setVerticalGroup(
            JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRfcE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudadE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresaE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilioE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnAñadir2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        JpClientes.add(JpEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 350));

        panelPuntoVenta.addTab("CLIENTES", JpClientes);

        JpVentas.setBackground(new java.awt.Color(255, 255, 255));
        JpVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEditarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/edit.png"))); // NOI18N
        btnEditarUser.setMnemonic('E');
        btnEditarUser.setText("Editar");
        btnEditarUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEditarUser.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEditarUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditarUser.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEditarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarUserMouseExited(evt);
            }
        });
        btnEditarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUserActionPerformed(evt);
            }
        });
        JpVentas.add(btnEditarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 90, 30));

        btnInf.setBackground(new java.awt.Color(255, 255, 255));
        btnInf.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnInf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/finalizarVenta.png"))); // NOI18N
        btnInf.setMnemonic('V');
        btnInf.setText("Venta");
        btnInf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnInf.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnInf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInf.setPreferredSize(new java.awt.Dimension(85, 30));
        btnInf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInfMouseExited(evt);
            }
        });
        btnInf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfActionPerformed(evt);
            }
        });
        JpVentas.add(btnInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 90, 30));

        btnAgregarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAgregarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/add.png"))); // NOI18N
        btnAgregarUser.setMnemonic('A');
        btnAgregarUser.setText("Agregar");
        btnAgregarUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnAgregarUser.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAgregarUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgregarUser.setPreferredSize(new java.awt.Dimension(85, 30));
        btnAgregarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarUserMouseExited(evt);
            }
        });
        btnAgregarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUserActionPerformed(evt);
            }
        });
        JpVentas.add(btnAgregarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 90, 30));

        btnEliminarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarUser.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/delete.png"))); // NOI18N
        btnEliminarUser.setMnemonic('B');
        btnEliminarUser.setText("Borrar");
        btnEliminarUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEliminarUser.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEliminarUser.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminarUser.setPreferredSize(new java.awt.Dimension(85, 30));
        btnEliminarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarUserMouseExited(evt);
            }
        });
        btnEliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserActionPerformed(evt);
            }
        });
        JpVentas.add(btnEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 90, 30));

        menuUsuarios.setBackground(new java.awt.Color(116, 16, 30));
        menuUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuUsuarios.setDoubleBuffered(true);
        menuUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setOpaque(true);
        JpVentas.add(menuUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListadoUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        JpListadoUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JpListadoUsuarios.setPreferredSize(new java.awt.Dimension(516, 360));

        txtCodigoVentas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCodigoVentas.setText("Codigo");
        txtCodigoVentas.setPreferredSize(new java.awt.Dimension(5, 30));
        txtCodigoVentas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoVentasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoVentasFocusLost(evt);
            }
        });
        txtCodigoVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoVentasKeyReleased(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtNombre.setText("Nombre Artículo");
        txtNombre.setPreferredSize(new java.awt.Dimension(5, 30));

        txtPrecioEmpleado.setEditable(false);
        txtPrecioEmpleado.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtPrecioEmpleado.setText("Precio");
        txtPrecioEmpleado.setPreferredSize(new java.awt.Dimension(5, 30));

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCantidad.setText("Cantidad");
        txtCantidad.setPreferredSize(new java.awt.Dimension(5, 30));
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });

        txtBuscarUsers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarUsers.setText("Buscar Codigo");
        txtBuscarUsers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarUsersFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarUsersFocusLost(evt);
            }
        });
        txtBuscarUsers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarUsersKeyReleased(evt);
            }
        });

        scrolUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        scrolUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        scrolUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jtVentasEmpleado.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jtVentasEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CANTIDAD", "UNIDAD", "CONCEPTO", "PRECIO", "IMPORTE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtVentasEmpleado.setGridColor(new java.awt.Color(116, 16, 30));
        jtVentasEmpleado.setOpaque(false);
        jtVentasEmpleado.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolUsuarios.setViewportView(jtVentasEmpleado);

        btnBuscarUser.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarUser.setBorderPainted(false);
        btnBuscarUser.setContentAreaFilled(false);
        btnBuscarUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarUserMouseExited(evt);
            }
        });
        btnBuscarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUserActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTotal.setPreferredSize(new java.awt.Dimension(5, 30));

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtSubtotal.setPreferredSize(new java.awt.Dimension(5, 30));

        txtIVA.setEditable(false);
        txtIVA.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtIVA.setPreferredSize(new java.awt.Dimension(5, 30));

        lbTotal.setBackground(new java.awt.Color(255, 255, 255));
        lbTotal.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbTotal.setText("Total");
        lbTotal.setOpaque(true);

        lbIva.setBackground(new java.awt.Color(255, 255, 255));
        lbIva.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbIva.setText("IVA");
        lbIva.setOpaque(true);

        lbVentas.setBackground(new java.awt.Color(255, 255, 255));
        lbVentas.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        lbVentas.setForeground(new java.awt.Color(116, 16, 30));
        lbVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVentas.setText("AGREGAR VENTAS");
        lbVentas.setOpaque(true);

        lbSubtotal.setBackground(new java.awt.Color(255, 255, 255));
        lbSubtotal.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbSubtotal.setText("Subtotal");
        lbSubtotal.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(116, 16, 30), 1, true), " PAGO EN EFECTIVO ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 10), new java.awt.Color(116, 16, 30))); // NOI18N

        lbPago.setBackground(new java.awt.Color(255, 255, 255));
        lbPago.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbPago.setText("Pago:");
        lbPago.setOpaque(true);

        lbCambio.setBackground(new java.awt.Color(255, 255, 255));
        lbCambio.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbCambio.setText("Cambio:");
        lbCambio.setOpaque(true);

        txtPago.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtPago.setPreferredSize(new java.awt.Dimension(5, 30));
        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagoKeyPressed(evt);
            }
        });

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCambio.setPreferredSize(new java.awt.Dimension(5, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbPago, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPago, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                        .addComponent(txtCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(25, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout JpListadoUsuariosLayout = new javax.swing.GroupLayout(JpListadoUsuarios);
        JpListadoUsuarios.setLayout(JpListadoUsuariosLayout);
        JpListadoUsuariosLayout.setHorizontalGroup(
            JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(txtIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCodigoVentas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioEmpleado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scrolUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(txtBuscarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        JpListadoUsuariosLayout.setVerticalGroup(
            JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscarUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrolUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(txtCodigoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtPrecioEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        JpVentas.add(JpListadoUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 360));

        panelPuntoVenta.addTab("VENTAS", JpVentas);

        getContentPane().add(panelPuntoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 690, 410));

        lbAdmin1.setBackground(new java.awt.Color(116, 16, 30));
        lbAdmin1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        lbAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        lbAdmin1.setText("Fecha");
        lbAdmin1.setOpaque(true);
        getContentPane().add(lbAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 140, 30));

        barraEstado.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        barraEstado.setForeground(new java.awt.Color(255, 255, 255));
        barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(barraEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, 570, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barraFormularioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraFormularioMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_barraFormularioMousePressed

    private void barraFormularioMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraFormularioMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_barraFormularioMouseDragged

//    Evento que permite realizar búsquedas de articulos.
    private void btnBuscarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUserActionPerformed
        try {
            String[] cadena;
            String ele = txtBuscarUsers.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            con.busqueda("articulos", "codigo", "codigo,nombre,precio,unidad", ele);
            if (!"null".equals(con.registro_busqueda)) {
                cadena = con.registro_busqueda.split(",");
                txtCodigoVentas.setText(cadena[0]);
                txtNombre.setText(cadena[1]);
                txtPrecioEmpleado.setText(cadena[2]);
                unidadTablaTemp = cadena[3];
                txtCantidad.requestFocus(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarUserActionPerformed

    private void btnBuscarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarUserMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarUser.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarUserMouseExited

    private void btnBuscarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarUserMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarUser.setIcon(icon);
        barraEstado.setText("Buscar: Botón que permite buscar artículos en la base de datos para su venta.  ");
    }//GEN-LAST:event_btnBuscarUserMouseEntered

    //    Evento que permite realizar búsquedas de articulos al presionar enter o tab.
    private void txtBuscarUsersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsersKeyReleased
        try {
            String[] cadena;
            icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
            btnBuscarUser.setIcon(icon);
            String ele = txtBuscarUsers.getText().trim().toUpperCase();
            con.busqueda("articulos", "codigo", "codigo,nombre,precio,unidad", ele);
            if (!"null".equals(con.registro_busqueda)) {
                cadena = con.registro_busqueda.split(",");
                txtCodigoVentas.setText(cadena[0]);
                txtNombre.setText(cadena[1]);
                txtPrecioEmpleado.setText(cadena[2]);
                unidadTablaTemp = cadena[3];
                txtCantidad.requestFocus(true);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarUsersKeyReleased

    private void txtBuscarUsersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarUsersFocusLost
        try {
            if ("".equals(txtBuscarUsers.getText())) {
                txtBuscarUsers.setText("Buscar Codigo");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarUsersFocusLost

    private void txtBuscarUsersFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarUsersFocusGained
        txtBuscarUsers.setText("");
    }//GEN-LAST:event_txtBuscarUsersFocusGained

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        boolean respuesta;
        int cantidad = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                cantidad = Integer.parseInt(txtCantidad.getText());
                btnAgregarUser.requestFocus(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "'Cantidad' Es un numero\nentero.");
            }
        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        try {
            if ("".equals(txtCantidad.getText())) {
                txtCantidad.setText("Cantidad");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        txtCantidad.setText("");
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCodigoVentasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoVentasFocusLost
        try {
            if ("".equals(txtCodigoVentas.getText())) {
                txtCodigoVentas.setText("Nombre");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodigoVentasFocusLost

    private void txtCodigoVentasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoVentasFocusGained
        txtCodigoVentas.setText("");
    }//GEN-LAST:event_txtCodigoVentasFocusGained

    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        try {
            Eliminar();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserActionPerformed

//    Método que permite eliminar la fila de la tabla seleccionada.
    public void Eliminar() {
        try {
            modelo = (DefaultTableModel) jtVentasEmpleado.getModel();
            modelo.removeRow(jtVentasEmpleado.getSelectedRow());
            actualizarTotales();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE
                    + "Seleccione fila.");
        }
    }

//    Método que actualiza los totales de ventas en la compra del cliente, subtotal,iva y total.
    public void actualizarTotales() {
        String cambioDatos = "";
        String[] cadena;
        double _subtotal = 0, _total = 0, _iva = 0, aux;
        try {
            for (int i = 0; i < jtVentasEmpleado.getRowCount(); i++) {
                cambioDatos = jtVentasEmpleado.getValueAt(i, 4).toString();
                System.out.println(cambioDatos);
                _subtotal += Double.parseDouble(cambioDatos);
            }
            txtSubtotal.setText(String.valueOf(_subtotal));
            _iva = _subtotal * 16 / 100;
            txtIVA.setText(String.valueOf(_iva));
            _total = _subtotal + _iva;
            txtTotal.setText(String.valueOf(df.format(_total)));
        } catch (Exception e) {
        }
    }

    private void btnEliminarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnEliminarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserMouseExited

    private void btnEliminarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnEliminarUser);
            bordesBoton.encender();
            barraEstado.setText("Borrar: Botón que permite eliminar artículos en la compra del cliente.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserMouseEntered

//    Evento que permite agregar a la tabla de carrito de compra del cliente los articulos solicitados.
    private void btnAgregarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUserActionPerformed
        String codigo = "";
        int contador = 0;
        int cantidad = 0;
        try {
            if ("Codigo".equals(txtCodigoVentas.getText()) || "Cantidad".equals(txtCantidad.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los\ncampos.");
            } else {
                cantidad = Integer.parseInt(txtCantidad.getText());
                GestionVentas.ReducirArticulos verificar = new ReducirArticulos();
                verificar.verificarCantidad(txtCantidad.getText(), txtNombre.getText());

                if (verificar.respuesta == true) {
                    codigo = txtCodigoVentas.getText().trim().toUpperCase();
                    Datos[0] = txtCantidad.getText();
                    Datos[1] = unidadTablaTemp;
                    Datos[2] = txtNombre.getText();
                    Datos[3] = txtPrecioEmpleado.getText();
                    Datos[4] = String.valueOf((Integer.parseInt(Datos[0]) * Double.parseDouble(Datos[3])));
                    modelo.addRow(Datos);
                    actualizarTotales();
                    limpiar();
                    txtPago.requestFocus();
                } else {
                    txtCantidad.requestFocus(true);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato invalido.\n"
                    + "Verifique la cantidad solicitada.");
            txtCantidad.requestFocus(true);
        }
    }//GEN-LAST:event_btnAgregarUserActionPerformed

//    Método que permite limpiar los campos de textos solicitados.
    public void limpiar() {
        try {
            txtCodigoVentas.setText("Codigo");
            txtNombre.setText("Nombre Art'iculo");
            txtPrecioEmpleado.setText("Precio");
            txtCantidad.setText("Cantidad");
        } catch (Exception e) {
        }
    }
    private void btnAgregarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnAgregarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarUserMouseExited

    private void btnAgregarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnAgregarUser);
            bordesBoton.encender();
            barraEstado.setText("Agregar: Botón que permite agregar artículos en la compra del cliente.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarUserMouseEntered

//    Evento de ventas: permite alamacenar en variables los diferentes valores de las columnas.
//    Actualiza el total de la compra o venta.
//    Envia esos parametros a el jframe EmergenteVentas.
    private void btnInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfActionPerformed
        String cantidades = "", unidades = "", conceptos = "", precios = "", importes = "";
        try {
            int n = jtVentasEmpleado.getRowCount();
            if (n <= 0 || "".equals(txtPago.getText()) || "".equals(txtCambio.getText())) {
                JOptionPane.showMessageDialog(null, "Realice la venta.");
            } else {
                for (int i = 0; i < n; i++) {
                    cantidades += jtVentasEmpleado.getValueAt(i, 0).toString().concat("|");
                    unidades += jtVentasEmpleado.getValueAt(i, 1).toString().concat("|");
                    conceptos += jtVentasEmpleado.getValueAt(i, 2).toString().concat("|");
                    precios += jtVentasEmpleado.getValueAt(i, 3).toString().concat("|");
                    importes += jtVentasEmpleado.getValueAt(i, 4).toString().concat("|");
                }
                actualizarTotales();
                EmergenteVentas emg = new EmergenteVentas(lbAdmin.getText(), cantidades, unidades, conceptos, precios, importes,
                        txtSubtotal.getText(), txtIVA.getText(), txtTotal.getText(), txtSubtotal, txtIVA, txtTotal, modelo, jtVentasEmpleado, txtPago, txtCambio);
                emg.setVisible(true);
                emg.setLocationRelativeTo(this);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnInfActionPerformed

    private void btnInfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfMouseExited
        try {
            bordesBoton = new BordesButton(btnInf);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInfMouseExited

    private void btnInfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfMouseEntered
        try {
            bordesBoton = new BordesButton(btnInf);
            bordesBoton.encender();
            barraEstado.setText("Ventas: Botón que permite generar el Ticket y/o Factura.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInfMouseEntered

//    Evento que permite editar unicamente las cantidades de los productos que se encuentran en la tabla de
//    compra del cliente.
    private void btnEditarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUserActionPerformed
        String clausula = "";
        try {
            txtPago.setText("");
            txtCambio.setText("");
            String columnas = "nombre";
            clausula = this.jtVentasEmpleado.getValueAt(this.jtVentasEmpleado.getSelectedRow(), 2).toString();
            con.busqueda("articulos", "nombre", columnas, clausula);
            clausula = this.jtVentasEmpleado.getValueAt(this.jtVentasEmpleado.getSelectedRow(), 3).toString();
            EmergenteEdicionVentas emg = new EmergenteEdicionVentas(con.registro_busqueda,
                    this.jtVentasEmpleado.getSelectedRow(), modelo, clausula, this.jtVentasEmpleado,
                    txtSubtotal, txtIVA, txtTotal);
            emg.setVisible(true);
            emg.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnEditarUserActionPerformed

    private void btnEditarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnEditarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarUserMouseExited

    private void btnEditarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnEditarUser);
            bordesBoton.encender();
            barraEstado.setText("Editar: Botón que permite editar UNICAMENTE la cantidad de artículos de la compra.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarUserMouseEntered

//    Evento que permite actualizar la información de clientes en la BD.
    private void btnAñadir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadir2ActionPerformed
        String columnas = "rfc,nombre_cliente,domicilio,ciudad,estado,email";
        boolean respuesta;
        p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        m = p.matcher(txtEmailE.getText());
        m.matches();
        respuesta = m.matches();
        try {
            if ("".equals(txtRfcE.getText())
                    || "".equals(txtEmpresaE.getText()) || "".equals(txtDomicilioE.getText())
                    || "".equals(txtCiudadE.getText()) || "".equals(txtEstadoE.getText())
                    || "".equals(txtEmailE.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos.");
            } else {
                if (respuesta == true) {
                    con.actualizar("clientes", columnas, txtRfcE.getText() + ","
                            + txtEmpresaE.getText() + "," + txtDomicilioE.getText() + ","
                            + txtCiudadE.getText() + "," + txtEstadoE.getText() + ","
                            + txtEmailE.getText(), "rfc='" + codigoBaseEdicion + "';");
                    camposEdicionClientes();
                    reiniciarJTableClientes();
                    limpiarTxtCliente();
                } else {
                    JOptionPane.showMessageDialog(null, "Correo Electronico Invalido");
                    txtEmail.requestFocus(true);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAñadir2ActionPerformed

    private void btnAñadir2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir2MouseExited

    private void btnAñadir2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir2MouseEntered

    private void txtEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyPressed
        String mayuscula = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                mayuscula = txtEstado.getText().trim().toUpperCase();
                txtEstado.setText(mayuscula);
                txtEmail.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEstadoKeyPressed

    private void txtEstadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEstadoFocusLost
        try {
            if ("".equals(txtEstado.getText())) {
                txtEstado.setText("ESTADO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEstadoFocusLost

    private void txtEstadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEstadoFocusGained
        txtEstado.setText("");
    }//GEN-LAST:event_txtEstadoFocusGained

//    Evento que permite verificar los atributos de un email valido.
    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed
        boolean respuesta;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                m = p.matcher(txtEmail.getText());
                m.matches();
                respuesta = m.matches();
                if (respuesta == true) {
                    btnAñadirCliente.requestFocus(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Correo Electronico Invalido");
                    txtEmail.requestFocus(true);
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_txtEmailKeyPressed

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        try {
            if ("".equals(txtEmail.getText())) {
                txtEmail.setText("E-MAIL");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtCiudadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudadKeyPressed
        String mayuscula = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                mayuscula = txtCiudad.getText().trim().toUpperCase();
                txtCiudad.setText(mayuscula);
                txtEstado.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtCiudadKeyPressed

    private void txtCiudadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCiudadFocusLost
        try {
            if ("".equals(txtCiudad.getText())) {
                txtCiudad.setText("CIUDAD");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCiudadFocusLost

    private void txtCiudadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCiudadFocusGained
        txtCiudad.setText("");
    }//GEN-LAST:event_txtCiudadFocusGained

    private void txtDomicilioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioKeyPressed
        String mayuscula = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                mayuscula = txtDomicilio.getText().trim().toUpperCase();
                txtDomicilio.setText(mayuscula);
                txtCiudad.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtDomicilioKeyPressed

    private void txtDomicilioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDomicilioFocusLost
        try {
            if ("".equals(txtDomicilio.getText())) {
                txtDomicilio.setText("DOMICILIO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtDomicilioFocusLost

    private void txtDomicilioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDomicilioFocusGained
        txtDomicilio.setText("");
    }//GEN-LAST:event_txtDomicilioFocusGained

    private void txtEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaKeyPressed
        String mayuscula = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                mayuscula = txtEmpresa.getText().trim().toUpperCase();
                txtEmpresa.setText(mayuscula);
                txtDomicilio.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEmpresaKeyPressed

    private void txtEmpresaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpresaFocusLost
        try {
            if ("".equals(txtEmpresa.getText())) {
                txtEmpresa.setText("EMPRESA/NOMBRE");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEmpresaFocusLost

    private void txtEmpresaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpresaFocusGained
        txtEmpresa.setText("");
    }//GEN-LAST:event_txtEmpresaFocusGained

    private void txtRfcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                validarRFC();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtRfcKeyPressed

    private void txtRfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRfcFocusLost
        try {
            if ("".equals(txtRfc.getText())) {
                txtRfc.setText("RFC");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtRfcFocusLost

    private void txtRfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRfcFocusGained
        txtRfc.setText("");
    }//GEN-LAST:event_txtRfcFocusGained

//    Evento que permite añadir clientes en la BD
    private void btnAñadirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirClienteActionPerformed
        String columnas = "rfc,nombre_cliente,domicilio,ciudad,estado,email";
        boolean respuesta;
        try {
            if ("RFC".equals(txtRfc.getText())
                    || "EMPRESA/NOMBRE".equals(txtEmpresa.getText()) || "DOMICILIO".equals(txtDomicilio.getText())
                    || "CIUDAD".equals(txtCiudad.getText()) || "ESTADO".equals(txtEstado.getText())
                    || "E-MAIL".equals(txtEmail.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos de Registro");
            } else {
                p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                m = p.matcher(txtEmail.getText());
                m.matches();
                respuesta = m.matches();
                if (respuesta == true) {
                    String valores = "";
                    valores += txtRfc.getText() + ",";
                    valores += txtEmpresa.getText() + ",";
                    valores += txtDomicilio.getText() + ",";
                    valores += txtCiudad.getText() + ",";
                    valores += txtEstado.getText() + ",";
                    valores += txtEmail.getText();

                    con.agregar("clientes", columnas, valores);
                    reiniciarJTableClientes();
                    limpiarTxtCliente();
                } else {
                    JOptionPane.showMessageDialog(null, "Correo Electronico Invalido");
                    txtEmail.requestFocus(true);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAñadirClienteActionPerformed

    private void btnAñadirClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirClienteMouseExited
        bordesBoton = new BordesButton(btnAñadirCliente);
        bordesBoton.apagar();
    }//GEN-LAST:event_btnAñadirClienteMouseExited

    private void btnAñadirClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirClienteMouseEntered
        bordesBoton = new BordesButton(btnAñadirCliente);
        bordesBoton.encender();
    }//GEN-LAST:event_btnAñadirClienteMouseEntered

//    Evento que permite realizar búsquedas de clientes ademas de seleccionarlo en su respectiva tabla.
    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        try {
            icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
            btnBuscarCliente.setIcon(icon);
            String ele = txtBuscarCliente.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < jtCliente.getRowCount(); i++) {
                if (jtCliente.getValueAt(i, 0).equals(ele)) {
                    jtCliente.changeSelection(i, 0, false, false);

                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscarCliente.setIcon(icon);
                    break;
                } else if (jtCliente.getValueAt(i, 1).equals(ele)) {
                    jtCliente.changeSelection(i, 1, false, false);

                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscarCliente.setIcon(icon);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void txtBuscarClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClienteFocusLost
        try {
            if ("".equals(txtBuscarCliente.getText())) {
                txtBuscarCliente.setText("Buscar RFC/Empresa");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarClienteFocusLost

    private void txtBuscarClienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarClienteFocusGained
        txtBuscarCliente.setText("");
    }//GEN-LAST:event_txtBuscarClienteFocusGained

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        try {
            String ele = txtBuscarCliente.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < jtCliente.getRowCount(); i++) {
                if (jtCliente.getValueAt(i, 0).equals(ele)) {
                    jtCliente.changeSelection(i, 0, false, false);
                    break;
                } else if (jtCliente.getValueAt(i, 1).equals(ele)) {
                    jtCliente.changeSelection(i, 1, false, false);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnBuscarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarCliente.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarClienteMouseExited

    private void btnBuscarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarCliente.setIcon(icon);
        barraEstado.setText("Buscar: Permite realizar búsquedas específicas con RFC o Empresa.");
    }//GEN-LAST:event_btnBuscarClienteMouseEntered

    private void btnListaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaClienteActionPerformed
        JpListadoCliente.setVisible(true);
        JpAgregarCliente.setVisible(false);
        JpEditarCliente.setVisible(false);
        reiniciarJTableClientes();
    }//GEN-LAST:event_btnListaClienteActionPerformed

    private void btnListaClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaClienteMouseExited
        bordesBoton = new BordesButton(btnListaCliente);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnListaClienteMouseExited

    private void btnListaClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaClienteMouseEntered
        bordesBoton = new BordesButton(btnListaCliente);
        bordesBoton.encender();
        barraEstado.setText("Listado: Botón que permite ver la lista de clientes disponibles en la base de datos.");
    }//GEN-LAST:event_btnListaClienteMouseEntered

//    Evento que permite visualizar en los campos de texto la informacion de los clientes a editar.
    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        String[] cadena;
        try {
            String clausula = this.jtCliente.getValueAt(this.jtCliente.getSelectedRow(), 0).toString();
            JpListadoCliente.setVisible(false);
            JpAgregarCliente.setVisible(false);
            JpEditarCliente.setVisible(true);

            String columnas = "rfc,nombre_cliente,domicilio,ciudad,estado,email";
            con.busqueda("clientes", "rfc", columnas, clausula);
            cadena = con.registro_busqueda.split(",");
            camposEdicionClientesTrue();

            txtRfcE.setText(cadena[0]);
            codigoBaseEdicion = cadena[0];
            txtEmpresaE.setText(cadena[1]);
            txtDomicilioE.setText(cadena[2]);
            txtCiudadE.setText(cadena[3]);
            txtEstadoE.setText(cadena[4]);
            txtEmailE.setText(cadena[5]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void btnEditarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarClienteMouseExited
        bordesBoton = new BordesButton(btnEditarCliente);
        bordesBoton.apagar();
    }//GEN-LAST:event_btnEditarClienteMouseExited

    private void btnEditarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarClienteMouseEntered
        bordesBoton = new BordesButton(btnEditarCliente);
        bordesBoton.encender();
        barraEstado.setText("Editar: Botón que permite editar clientes de la base de datos.");
    }//GEN-LAST:event_btnEditarClienteMouseEntered

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        JpListadoCliente.setVisible(false);
        JpAgregarCliente.setVisible(true);
        JpEditarCliente.setVisible(false);
        limpiarTxtCliente();
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    //    Método que limpia los campos de texto del cliente.
    public void limpiarTxtCliente() {
        try {
            txtRfc.setText("RFC");
            txtEmpresa.setText("EMPRESA/NOMBRE");
            txtDomicilio.setText("DOMICILIO");
            txtCiudad.setText("CIUDAD");
            txtEstado.setText("ESTADO");
            txtEmail.setText("E-MAIL");

            txtRfcE.setText("");
            txtEmpresaE.setText("");
            txtDomicilioE.setText("");
            txtCiudadE.setText("");
            txtEstadoE.setText("");
            txtEmailE.setText("");
        } catch (Exception e) {
        }
    }
    private void btnAgregarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarClienteMouseExited
        bordesBoton = new BordesButton(btnAgregarCliente);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnAgregarClienteMouseExited

    private void btnAgregarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarClienteMouseEntered
        bordesBoton = new BordesButton(btnAgregarCliente);
        bordesBoton.encender();
        barraEstado.setText("Agregar: Botón que permite agregar clientes en la base de datos.");
    }//GEN-LAST:event_btnAgregarClienteMouseEntered

    private void txtUnidadEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadEKeyPressed
        String unidad = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                unidad = txtUnidadE.getText().trim().toUpperCase();
                txtUnidadE.setText(unidad);
                txtExistenciaE.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtUnidadEKeyPressed

    private void txtPrecioEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioEKeyPressed
        double precio = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                precio = Double.parseDouble(txtPrecioE.getText().trim());
                btnAñadir1.requestFocus(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato Invalido\nIngrese un Precio.");
            }

        }
    }//GEN-LAST:event_txtPrecioEKeyPressed

    private void txtPrecioEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioEFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioEFocusLost

    private void txtPrecioEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioEFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioEFocusGained

    private void txtExistenciaEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaEKeyPressed
        int existencia = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                existencia = Integer.parseInt(txtExistenciaE.getText().trim());
                txtPrecioE.requestFocus(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato Invalido\nIngrese en Existencia un entero.");
            }
        }
    }//GEN-LAST:event_txtExistenciaEKeyPressed

//    Evento que al presionar enter o tab, verifica que el nombre del articulo tenga minimo 3 letras.
    private void txtProductoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoEKeyPressed
        String cadena = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            cadena = txtProductoE.getText().trim().toUpperCase();
            if (cadena.length() < 3) {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre de artículo\ncon 3 o más caracteres.");
                txtProductoE.requestFocus(true);
            } else {
                separarStringNumero(cadena);
                txtCodigoE.setText(separarCodigo);
                txtUnidadE.requestFocus(true);
            }

        }
    }//GEN-LAST:event_txtProductoEKeyPressed

//    Evento que permite actualizar los cambios realizados en editar articulos.
    private void btnAñadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadir1ActionPerformed
        String columnas = "nombre,codigo,unidad,existencia,precio";
        try {
            con.actualizar("articulos", columnas, txtProductoE.getText() + ","
                    + txtCodigoE.getText() + "," + txtUnidadE.getText() + ","
                    + txtExistenciaE.getText() + "," + txtPrecioE.getText(), "codigo='" + codigoBaseEdicion + "';");
            camposEdicionArticulos();
            reiniciarJTableArticulos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAñadir1ActionPerformed

    private void btnAñadir1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir1MouseExited

    private void btnAñadir1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir1MouseEntered

    private void txtUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUnidadKeyPressed
        String unidad = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                unidad = txtUnidad.getText().trim().toUpperCase();
                txtUnidad.setText(unidad);
                txtExistencia.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtUnidadKeyPressed

    private void txtUnidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnidadFocusLost
        try {
            if ("".equals(txtUnidad.getText())) {
                txtUnidad.setText("UNIDAD");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtUnidadFocusLost

    private void txtUnidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnidadFocusGained
        txtUnidad.setText("");
    }//GEN-LAST:event_txtUnidadFocusGained

    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        double precio = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                precio = Double.parseDouble(txtPrecio.getText().trim());
                btnAñadir.requestFocus(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato Invalido\nIngrese un Precio.");
            }

        }
    }//GEN-LAST:event_txtPrecioKeyPressed

    private void txtPrecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusLost
        try {
            if ("".equals(txtPrecio.getText())) {
                txtPrecio.setText("PRECIO ARTÍCULO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPrecioFocusLost

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
        txtPrecio.setText("");
    }//GEN-LAST:event_txtPrecioFocusGained

    private void txtExistenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExistenciaKeyPressed
        int existencia = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                existencia = Integer.parseInt(txtExistencia.getText().trim());
                txtPrecio.requestFocus(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato Invalido\nIngrese en Existencia un entero.");
            }
        }
    }//GEN-LAST:event_txtExistenciaKeyPressed

    private void txtExistenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExistenciaFocusLost
        try {
            if ("".equals(txtExistencia.getText())) {
                txtExistencia.setText("EXISTENCIA ARTÍCULO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtExistenciaFocusLost

    private void txtExistenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtExistenciaFocusGained
        txtExistencia.setText("");
    }//GEN-LAST:event_txtExistenciaFocusGained

//    Evento que verifica lo estipulado para obtener el codigo de articulo. Minimo 3 caracteres.
    private void txtProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoKeyPressed
        String producto = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                producto = txtProducto.getText().trim().toUpperCase();
                txtProducto.setText(producto);
                con.busquedaG("articulos", "codigo");
                GestionArticulos.CodigoArticulos cod = new GestionArticulos.CodigoArticulos(txtProducto.getText(), con.registro_busqueda);
                if (producto.length() < 3) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de artículo\ncon 3 o más caracteres.");
                    txtProducto.requestFocus(true);
                } else {
                    String substring = txtProducto.getText().substring(0, 3);
                    txtCodigo.setText(substring + (cod.mayor + 1));
                    txtUnidad.requestFocus(true);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese el nombre del producto");
            }
        }
    }//GEN-LAST:event_txtProductoKeyPressed

    private void txtProductoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusLost
        try {
            if ("".equals(txtProducto.getText())) {
                txtProducto.setText("NOMBRE ARTÍCULO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtProductoFocusLost

    private void txtProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProductoFocusGained
        txtProducto.setText("");
    }//GEN-LAST:event_txtProductoFocusGained

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusLost
        try {
            if ("".equals(txtCodigo.getText())) {
                txtCodigo.setText("CODIGO ARTÍCULO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCodigoFocusLost

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        txtCodigo.setText("");
    }//GEN-LAST:event_txtCodigoFocusGained
//      Evento que permite añadir articulos en la BD.
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        String columnas = "nombre,codigo,unidad,existencia,precio";
        try {
            if ("NOMBRE ARTÍCULO".equals(txtProducto.getText())
                    || "CODIGO ARTÍCULO".equals(txtCodigo.getText()) || "UNIDAD".equals(txtUnidad.getText())
                    || "EXISTENCIA ARTÍCULO".equals(txtExistencia.getText()) || "PRECIO ARTÍCULO".equals(txtPrecio.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos de Registro");
            } else {
                String valores = "";
                valores += txtProducto.getText() + ",";
                valores += txtCodigo.getText() + ",";
                valores += txtUnidad.getText() + ",";
                valores += txtExistencia.getText() + ",";
                valores += txtPrecio.getText();

                con.agregar("articulos", columnas, valores);
                reiniciarJTableArticulos();

                txtProducto.setText("NOMBRE ARTíCULO");
                txtCodigo.setText("CODIGO ARTíCULO");
                txtUnidad.setText("UNIDAD");
                txtExistencia.setText("EXISTENCIA ARTíCULO");
                txtPrecio.setText("PRECIO ARTíCULO");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnAñadirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseExited
        bordesBoton = new BordesButton(btnAñadir);
        bordesBoton.apagar();
    }//GEN-LAST:event_btnAñadirMouseExited

    private void btnAñadirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseEntered
        try {
            bordesBoton = new BordesButton(btnAñadir);
            bordesBoton.encender();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAñadirMouseEntered

//    Evento que permite realizar búsquedas en articulos ademas de seleccionarlo o marcarlo en su respectiva tabla.
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
            btnBuscar.setIcon(icon);
            String ele = txtBuscar.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < JtArticulos.getRowCount(); i++) {
                if (JtArticulos.getValueAt(i, 0).equals(ele)) {
                    JtArticulos.changeSelection(i, 0, false, false);
                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscar.setIcon(icon);
                    break;
                } else if (JtArticulos.getValueAt(i, 1).equals(ele)) {
                    JtArticulos.changeSelection(i, 1, false, false);
                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscar.setIcon(icon);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        try {
            if ("".equals(txtBuscar.getText())) {
                txtBuscar.setText("Buscar Codigo/Producto");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarFocusLost

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        txtBuscar.setText("");
    }//GEN-LAST:event_txtBuscarFocusGained

    //    Evento que permite realizar búsquedas en articulos ademas de seleccionarlo o marcarlo en su respectiva tabla.
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            String ele = txtBuscar.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < JtArticulos.getRowCount(); i++) {
                if (JtArticulos.getValueAt(i, 0).equals(ele)) {
                    JtArticulos.changeSelection(i, 0, false, false);
                    break;
                } else if (JtArticulos.getValueAt(i, 1).equals(ele)) {
                    JtArticulos.changeSelection(i, 1, false, false);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscar.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarMouseExited

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscar.setIcon(icon);
        barraEstado.setText("Buscar: Permite realizar búsquedas específicas en articulos"
                + " con el codigo o nombre del producto.");

    }//GEN-LAST:event_btnBuscarMouseEntered

//    Evento que al presionar enter o tab realiza una conulta a la BD acerca de la descripcion de un articulo, y los escribe
//    en los campos de texto especificados.
    private void txtCodigoVentasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentasKeyReleased
        String[] info;
        String columnas = "nombre,precio,unidad";
        String codigo = "";
        try {
            codigo = txtCodigoVentas.getText().trim().toUpperCase();
            txtCodigoVentas.setText(codigo);
            con.busqueda("articulos", "codigo", columnas, codigo);
            System.out.println(con.registro_busqueda);
            if ("null".equals(con.registro_busqueda) || "".equals(con.registro_busqueda)) {
                txtCodigoVentas.requestFocus(true);
            } else {
                info = con.registro_busqueda.split(",");
                txtNombre.setText(info[0]);
                txtPrecioEmpleado.setText(info[1]);
                unidadTablaTemp = info[2];
                txtCantidad.requestFocus(true);
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_txtCodigoVentasKeyReleased

//    Evento que al presionar enter hace una resta matematica para determinar el cambio del cliente.
    private void txtPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoKeyPressed
        double total = 0.0, pago = 0.0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                total = Double.parseDouble(txtTotal.getText());
                pago = Double.parseDouble(txtPago.getText());
                if (total > pago) {
                    JOptionPane.showMessageDialog(null, "Cantidad menor al total.\n"
                            + "Faltan $" + (total - pago) + " PESOS");
                    txtPago.requestFocus(true);
                } else {
                    txtCambio.setText(String.valueOf(df.format(pago - total)));
                    txtCambio.requestFocus();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato Invalido.");
            }
        }
    }//GEN-LAST:event_txtPagoKeyPressed

    private void btnBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseEntered
        try {
            img = new FrameDesign(URL + "back1.png");
            img.addButton(btnBack);
            barraEstado.setText("Atrás: Botón que permite cerrar sesión y regresar al inicio de la aplicación (Login).");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBackMouseEntered

    private void btnBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseExited
        try {
            img = new FrameDesign(URL + "back.png");
            img.addButton(btnBack);
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBackMouseExited

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            Login login = new Login();
            login.setVisible(true);
            this.hide();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        try {
            img = new FrameDesign(URL + "buttonMinimize1.png");
            img.addButton(btnMinimizar);
            barraEstado.setText("Minimizar: Botón que permite minimizar la ventana actual.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        try {
            img = new FrameDesign(URL + "buttonMinimize.png");
            img.addButton(btnMinimizar);
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnMinimizarMouseExited

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        try {
            img = new FrameDesign(URL + "buttonClose1.png");
            img.addButton(btnCerrar);
            barraEstado.setText("Salir: Botón que permite salir de la aplicación Sales Solutions.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        try {
            img = new FrameDesign(URL + "buttonClose.png");
            img.addButton(btnCerrar);
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtRfcEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcEKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtEmpresaE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtRfcEKeyPressed

    private void txtEmpresaEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpresaEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtDomicilioE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEmpresaEKeyPressed

    private void txtDomicilioEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDomicilioEKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtCiudadE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtDomicilioEKeyPressed

    private void txtCiudadEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCiudadEKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtEstadoE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtCiudadEKeyPressed

    private void txtEstadoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoEKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtEmailE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEstadoEKeyPressed

    private void txtEmailEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                btnAñadir2.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEmailEKeyPressed

//    Método que limpia y da la opcion de No editables a los campos de texto.
    public void camposEdicionArticulos() {
        txtProductoE.setText("");
        txtCodigoE.setText("");
        txtUnidadE.setText("");

        txtExistenciaE.setText("");
        txtPrecioE.setText("");

        txtProductoE.setEditable(false);

        txtUnidadE.setEditable(false);
        txtExistenciaE.setEditable(false);
        txtPrecioE.setEditable(false);

    }

//    Activa la opcion de editables a los campos de texto.
    public void camposEdicionArticulosTrue() {
        txtProductoE.setEditable(true);
        txtUnidadE.setEditable(true);
        txtExistenciaE.setEditable(true);
        txtPrecioE.setEditable(true);

    }

    //    Activa la opcion de editables a los campos de texto.
    public void camposEdicionClientesTrue() {
        txtRfcE.setEditable(true);
        txtEmpresaE.setEditable(true);
        txtDomicilioE.setEditable(true);
        txtCiudadE.setEditable(true);
        txtEstadoE.setEditable(true);
        txtEmailE.setEditable(true);
    }

    //    Activa la opcion de No editables a los campos de texto.
    public void camposEdicionClientes() {
        txtRfcE.setEditable(false);
        txtEmpresaE.setEditable(false);
        txtDomicilioE.setEditable(false);
        txtCiudadE.setEditable(false);
        txtEstadoE.setEditable(false);
        txtEmailE.setEditable(false);
    }

//    Método que separa de la cadena las primeras tres letras y le agrega el numero correspondiente.
    public void separarStringNumero(String cadena) {
        int contador = 0;
        String substring = "";
        try {
            contador = txtCodigoE.getText().length();
            substring = cadena.substring(0, 3);
            separarCodigo = cadena.substring(0, 3) + txtCodigoE.getText().substring(3, contador);
        } catch (Exception e) {
        }
    }

//  Limpia la tabla de jtVentasEmpleado
    public void reiniciarJTableEmpleado() {
        try {
            for (int i = 0; i < jtVentasEmpleado.getRowCount(); i++) {
                modelo.removeRow(i);
                i -= 1;
            }
        } catch (Exception e) {
        }
    }

    //  Limpia la tabla de JtArticulos
    public void reiniciarJTableArticulos() {
        try {
            GestionArticulos.BdTabla tA = new GestionArticulos.BdTabla(JtArticulos);
        } catch (Exception e) {
        }
    }

    //  Limpia la tabla de jtCliente
    public void reiniciarJTableClientes() {
        try {
            GestionClientes.BdTabla tC = new GestionClientes.BdTabla(jtCliente);
        } catch (Exception e) {
        }
    }

    //    Valida que el rfc contenga los determinados caracteres en el orden preciso.
    public void validarRFC() {
        int v = 0;
        String RFC = "";
        try {
            RFC = txtRfc.getText().trim().toUpperCase();
            if (RFC.length() < 13 || RFC.length() > 13) {
                JOptionPane.showMessageDialog(null, "Ingrese un RFC valido", "RFC NO VALIDO",
                        JOptionPane.INFORMATION_MESSAGE);
                txtRfc.setText("");
                txtRfc.requestFocus();
            } else {
                if (!RFC.matches("^[a-zA-Z]{4}.*$")) {
                    RFC = "XXXX" + RFC.substring(4);
                    v = 1;
                }
                if (!RFC.matches("^.{4}\\d{6}.*$")) {
                    RFC = RFC.substring(0, 4) + "000000" + RFC.substring(10);
                    v = 1;
                }
                if (v == 1) {
                    JOptionPane.showMessageDialog(null, RFC + " Invalido");
                    txtRfc.setText("");
                    txtRfc.requestFocus();
                } else {
                    txtRfc.setText(RFC);
                    txtEmpresa.requestFocus(true);
                }
            }
        } catch (Exception e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JpAgregar;
    private javax.swing.JPanel JpAgregarCliente;
    private javax.swing.JPanel JpArticulos;
    private javax.swing.JPanel JpClientes;
    private javax.swing.JPanel JpEditar;
    private javax.swing.JPanel JpEditarCliente;
    private javax.swing.JPanel JpListado;
    private javax.swing.JPanel JpListadoCliente;
    private javax.swing.JPanel JpListadoUsuarios;
    private javax.swing.JPanel JpVentas;
    private javax.swing.JTable JtArticulos;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JLabel barraFormulario;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarUser;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnAñadir1;
    private javax.swing.JButton btnAñadir2;
    private javax.swing.JButton btnAñadirCliente;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarUser;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarUser;
    private javax.swing.JButton btnEliminarUser;
    private javax.swing.JButton btnInf;
    private javax.swing.JButton btnListaCliente;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTable jtVentasEmpleado;
    private javax.swing.JLabel lbAdmin;
    private javax.swing.JLabel lbAdmin1;
    private javax.swing.JLabel lbCambio;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbDomicilio;
    private javax.swing.JLabel lbEmailk;
    private javax.swing.JLabel lbEmpresa;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbEstado1;
    private javax.swing.JLabel lbIva;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNotas;
    private javax.swing.JLabel lbPago;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbRfc;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel lbVentas;
    private javax.swing.JLabel menuArticulo;
    private javax.swing.JLabel menuCliente;
    private javax.swing.JLabel menuUsuarios;
    private javax.swing.JTabbedPane panelPuntoVenta;
    private javax.swing.JScrollPane scrolArticulos;
    private javax.swing.JScrollPane scrolCliente;
    private javax.swing.JScrollPane scrolUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarUsers;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCiudadE;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoE;
    private javax.swing.JTextField txtCodigoVentas;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtDomicilioE;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailE;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtEmpresaE;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtEstadoE;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtExistenciaE;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioE;
    private javax.swing.JTextField txtPrecioEmpleado;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProductoE;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtRfcE;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUnidad;
    private javax.swing.JTextField txtUnidadE;
    // End of variables declaration//GEN-END:variables
}
