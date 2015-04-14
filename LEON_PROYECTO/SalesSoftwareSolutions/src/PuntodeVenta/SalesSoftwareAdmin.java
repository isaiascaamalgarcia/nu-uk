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
import GestionUsuarios.BdTabla1;
import ModelDB.conexion;
import ModeloTablas.*;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Login.Login;
import java.awt.Font;
import java.util.Date;
import java.util.Locale;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
public class SalesSoftwareAdmin extends javax.swing.JFrame {
//    Variables de caracter obligatorio para su uso.
    private conexion con = new conexion();
    private FormDesign shape;
    private FrameDesign img;
    private DiseñoPanel tabPanel;
    private BordesButton bordesBoton;
    private DefaultTableModel modelo;
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
    String URL = "../Images/loginImages/";
    String URL1 = "../Images/articulos/";
    private int banderaNotificacion = 0;

    /**
     * Creates new form SalesSoftware
     */
//    Constructor: Llama a diferentes métodos que le darán formato al Jframe y al uso de funcionalidades.
    public SalesSoftwareAdmin(String usuario) {
        this.getContentPane().setBackground(new Color(116, 16, 30));
        initComponents();
        this.setLocationRelativeTo(null);
        this.lbAdmin.setText(usuario);
        getFechaActual();
        cargar();
        diseñar();
        avisarMensajeNotificacion();
        graficarEmpleadoMes();
        graficar();
        activarTab();
        actualizarF5();

    }
    
//    Método que obtiene la fecha actual.
    private void getFechaActual() {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
        lbAdmin1.setText(formato.format(hoy));
    }

//    Método que obtiene los eventos relevantes del sistema, para verificar y evitar un mal funcionamiento,
//    avisa al administrador mediante mensajes.
    public void avisarMensajeNotificacion() {
        int estado = 0;
        String codigos = "";
        String folio = "";
        String numeroPrueba = "";
        try {
            con.busquedaEsp("articulos", "codigo");
            codigos = con.registro_busqueda;

            con.busquedaID("folios");
            numeroPrueba = String.valueOf(con.registro_busqueda);

            con.busqueda("folios", "estado", "folio", "1");
            folio = con.registro_busqueda;

            System.out.println("codigos " + codigos);
            System.out.println("ID FOLIO " + numeroPrueba);
            System.out.println("folio " + folio);

            if (!"".equals(codigos) || "null".equals(numeroPrueba) || "".equals(folio)) {
                icon = new ImageIcon(getClass().getResource(URL + "no1.png"));
                btnNotif.setIcon(icon);
                banderaNotificacion = 1;
            } else {
                icon = new ImageIcon(getClass().getResource(URL + "e1.png"));
                btnNotif.setIcon(icon);
                banderaNotificacion = 0;
            }
        } catch (Exception e) {
        }
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

        //        Campos de texto del panel de Usuarios en la opcion de Agregado.
        txtNombre.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        txtCorreo.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);
        notas.setFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

    }

//    Metodo que le da diseño a todas las tablas de la aplicación.
    public void diseñar() {
        Header = JtArticulos.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        BdTabla tA = new BdTabla(JtArticulos);

        Header = jtCliente.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        GestionClientes.BdTabla tC = new GestionClientes.BdTabla(jtCliente);

        Header = jtVentas.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        GestionVentas.BdTabla tV = new GestionVentas.BdTabla(jtVentas);

        Header = jtFactura.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        GestionFactura.BdTabla tF = new GestionFactura.BdTabla(jtFactura);

        Header = jtUsuarios.getTableHeader();
        Header.setForeground(new Color(116, 16, 30));
        Header.setFont(new Font("Times New Roman", Font.BOLD, 11));
        GestionUsuarios.BdTabla1 jU = new GestionUsuarios.BdTabla1(jtUsuarios);
    }

//    Método que permite al teclado del usuario poder utilizar el evento F5.
    public void actualizarF5() {
        String key = "";
//        Atsjo de tecla F5 psra la actualizacion de la tabla de  ventas.
        Action buttonActionV = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GestionVentas.BdTabla tV = new GestionVentas.BdTabla(jtVentas);
            }
        };
        key = "Referesh";
        btnF5.setAction(buttonActionV);
        buttonActionV.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        btnF5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
        btnF5.getActionMap().put(key, buttonActionV);

        //        Atsjo de tecla F5 psra la actualizacion de la tabla de  Facturas.
        Action buttonActionF = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GestionFactura.BdTabla tF = new GestionFactura.BdTabla(jtFactura);
            }
        };
        key = "Referesh";
        btnF6.setAction(buttonActionF);
        buttonActionF.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        btnF6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), key);
        btnF6.getActionMap().put(key, buttonActionF);

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

        //        Atsjo de tecla Ctrl+B psra el focus en el campo de texto 'buscar' en el panel factura.
        Action buttonActionB2 = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtBuscarFolio.requestFocus();
            }
        };
        key = "Referesh";
        txtBuscarFolio.setAction(buttonActionB2);
        buttonActionB2.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        txtBuscarFolio.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK), key);
        txtBuscarFolio.getActionMap().put(key, buttonActionB2);

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

        //        Atsjo de tecla Ctrl+B psra el focus en el campo de texto 'buscar' en el panel Ventas.
        Action buttonActionB4 = new AbstractAction("F5") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                txtBuscarVendedor.requestFocus();
            }
        };
        key = "Referesh";
        txtBuscarVendedor.setAction(buttonActionB4);
        buttonActionB4.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        txtBuscarVendedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK), key);
        txtBuscarVendedor.getActionMap().put(key, buttonActionB4);
    }

//    Método que permite graficar a todos los empleados que laboran en la empresa.
//    La visualizacion se ve en un grafico de barras.
//    Puede realizar busquedas unicamente de vendedor.
//    Puede realizar busquedas unicamente de fechas.
//    O bien puede realizar busquedas del nombre del vendedor y fechas .
    public void graficarEmpleadoMes() {
        lbContenedor1.setIcon(null);
        String[] cadena;
        double[] ventas;
        try {
            JFreeChart barra = null;
            DefaultCategoryDataset datos;
            datos = new DefaultCategoryDataset();
            con.busqueda1("usuarios", "permiso", "usuario", "0");
            cadena = con.registro_busqueda.split(",");
            ventas = new double[cadena.length];
            for (int i = 0; i < cadena.length; i++) {
                con.busquedaGraficas("ventas", "total", "fecha", "",
                        "", cadena[i], 0, 0, 0);
                if ("null".equals(con.registro_busqueda)) {
                    ventas[i] = 0;
                    System.out.println(cadena[i] + " tiene: " + ventas[i]);
                } else {
                    ventas[i] = Double.parseDouble(con.registro_busqueda);
                    System.out.println(cadena[i] + " tiene: " + ventas[i]);
                }

                datos.setValue(ventas[i], cadena[i], "");
                barra = ChartFactory.createBarChart("Ventas Empleado del Mes", "Vendedores", "Ventas", datos, PlotOrientation.VERTICAL, true, true, true);
                BufferedImage graficoBarra = barra.createBufferedImage(465, 300);
                lbContenedor1.setSize(465, 300);
                lbContenedor1.setIcon(new ImageIcon(graficoBarra));
                JpEmpleadoMes.updateUI();
            }
        } catch (Exception e) {
        }
    }

//    Método que permite graficar todas las ventas de la empresa.
//    La visualizacion se ve en un grafico de barras.
//    Puede realizar busquedas unicamente por años.
    public void graficar() {
        lbContenedor.setIcon(null);
        String[] cadena;
        double[] ventas;
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        try {
            JFreeChart barra = null;
            DefaultCategoryDataset datos;
            datos = new DefaultCategoryDataset();
            ventas = new double[12];
            for (int i = 0; i < 12; i++) {
                con.busquedaGraficas("ventas", "total", "fecha", "",
                        "", "", 2, (i + 1), año);
                if ("null".equals(con.registro_busqueda)) {
                    ventas[i] = 0;
                    System.out.println(mes[i] + " tiene: " + ventas[i]);
                } else {
                    ventas[i] = Double.parseDouble(con.registro_busqueda);
                    System.out.println(mes[i] + " tiene: " + ventas[i]);
                }
                datos.setValue(ventas[i], mes[i], "");
                barra = ChartFactory.createBarChart("Estadisticas de ventas", "Meses", "Ventas", datos, PlotOrientation.VERTICAL, true, true, true);
                BufferedImage graficoBarra = barra.createBufferedImage(465, 300);
                lbContenedor.setSize(465, 300);
                lbContenedor.setIcon(new ImageIcon(graficoBarra));
                JpEstadisticas.updateUI();
            }
        } catch (Exception e) {
        }
    }

//    Carga el disseño de imagenes en los botones.
//    Carga el diseño de los tabs.
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
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpVentas, "CONTABILIDAD DE VENTAS", "ventas.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpFacturas, "FACTURAS", "factura.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpUsuarios, "USUARIOS", "caja.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpEmpleadoMes, "EMPLEADO DEL MES", "empleadoMes.png");
        tabPanel = new DiseñoPanel(panelPuntoVenta, JpEstadisticas, "ESTADÍSTICAS", "estadisticas.png");

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
        btnCerrar = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        btnNotif = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        barraFormulario = new javax.swing.JLabel();
        lbAdmin = new javax.swing.JLabel();
        barraEstado = new javax.swing.JLabel();
        panelPuntoVenta = new javax.swing.JTabbedPane();
        JpArticulos = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLista = new javax.swing.JButton();
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
        jLabel4 = new javax.swing.JLabel();
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
        btnEliminarCliente = new javax.swing.JButton();
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
        lbCiudad = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        lbEmpresa = new javax.swing.JLabel();
        lbDomicilio = new javax.swing.JLabel();
        lbEstado1 = new javax.swing.JLabel();
        JpVentas = new javax.swing.JPanel();
        btnF5 = new javax.swing.JButton();
        menuVentas = new javax.swing.JLabel();
        JpListadoVentas = new javax.swing.JPanel();
        txtBuscarVendedor = new javax.swing.JTextField();
        lbInicio = new javax.swing.JLabel();
        lbFin = new javax.swing.JLabel();
        btnBuscarVentas = new javax.swing.JButton();
        scrolVentas = new javax.swing.JScrollPane();
        jtVentas = new javax.swing.JTable();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        JpFacturas = new javax.swing.JPanel();
        btnF6 = new javax.swing.JButton();
        menuFactura = new javax.swing.JLabel();
        JpListadoFactura = new javax.swing.JPanel();
        txtBuscarFolio = new javax.swing.JTextField();
        lbInicio1 = new javax.swing.JLabel();
        lbFin1 = new javax.swing.JLabel();
        btnBuscarFactura = new javax.swing.JButton();
        scrolFactura = new javax.swing.JScrollPane();
        jtFactura = new javax.swing.JTable();
        jXDatePicker3 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker4 = new org.jdesktop.swingx.JXDatePicker();
        JpUsuarios = new javax.swing.JPanel();
        btnEditarUser = new javax.swing.JButton();
        btnInf = new javax.swing.JButton();
        btnAgregarUser = new javax.swing.JButton();
        btnEliminarUser = new javax.swing.JButton();
        menuUsuarios = new javax.swing.JLabel();
        JpListadoUsuarios = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtBuscarUsers = new javax.swing.JTextField();
        btnBuscarUser = new javax.swing.JButton();
        scrolUsuarios = new javax.swing.JScrollPane();
        jtUsuarios = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        notas = new javax.swing.JTextArea();
        cbxPermiso = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        JpEmpleadoMes = new javax.swing.JPanel();
        jXDatePicker7 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker8 = new org.jdesktop.swingx.JXDatePicker();
        btnBuscarGraf1 = new javax.swing.JButton();
        lbContenedor1 = new javax.swing.JLabel();
        JpEstadisticas = new javax.swing.JPanel();
        JpGraficaEstadistica = new javax.swing.JPanel();
        btnBuscarGraf = new javax.swing.JButton();
        lbContenedor = new javax.swing.JLabel();
        jYear = new com.toedter.calendar.JYearChooser();
        jLabel3 = new javax.swing.JLabel();
        lbAdmin1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("puntoVenta"); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 150, 160));

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

        btnNotif.setBackground(new java.awt.Color(255, 255, 255));
        btnNotif.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnNotif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginImages/e1.png"))); // NOI18N
        btnNotif.setMnemonic('C');
        btnNotif.setBorder(null);
        btnNotif.setBorderPainted(false);
        btnNotif.setContentAreaFilled(false);
        btnNotif.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNotif.setPreferredSize(new java.awt.Dimension(85, 30));
        btnNotif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNotifMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNotifMouseExited(evt);
            }
        });
        btnNotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotifActionPerformed(evt);
            }
        });
        getContentPane().add(btnNotif, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 80, 60));

        btnConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/loginImages/conf1.png"))); // NOI18N
        btnConfiguracion.setMnemonic('C');
        btnConfiguracion.setBorder(null);
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfiguracion.setPreferredSize(new java.awt.Dimension(85, 30));
        btnConfiguracion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfiguracionMouseExited(evt);
            }
        });
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 32, 32));

        barraFormulario.setBackground(new java.awt.Color(255, 255, 255));
        barraFormulario.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        barraFormulario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barraFormulario.setText("ADMINISTRACIÓN PUNTO DE VENTA");
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
        getContentPane().add(lbAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 140, 30));

        barraEstado.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        barraEstado.setForeground(new java.awt.Color(255, 255, 255));
        barraEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(barraEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 570, 30));

        panelPuntoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelPuntoVenta.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        panelPuntoVenta.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        panelPuntoVenta.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        panelPuntoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPuntoVentaMouseClicked(evt);
            }
        });

        JpArticulos.setBackground(new java.awt.Color(255, 255, 255));
        JpArticulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/delete.png"))); // NOI18N
        btnEliminar.setMnemonic('B');
        btnEliminar.setText("Borrar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        JpArticulos.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 90, 30));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/add.png"))); // NOI18N
        btnAgregar.setMnemonic('A');
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgregar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarMouseExited(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        JpArticulos.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 30));

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/edit.png"))); // NOI18N
        btnEditar.setMnemonic('E');
        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.setPreferredSize(new java.awt.Dimension(85, 30));
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarMouseExited(evt);
            }
        });
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        JpArticulos.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 90, 30));

        btnLista.setBackground(new java.awt.Color(255, 255, 255));
        btnLista.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/list.png"))); // NOI18N
        btnLista.setMnemonic('L');
        btnLista.setText("Listado");
        btnLista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnLista.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnLista.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLista.setPreferredSize(new java.awt.Dimension(85, 30));
        btnLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnListaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnListaMouseExited(evt);
            }
        });
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });
        JpArticulos.add(btnLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 30));

        menuArticulo.setBackground(new java.awt.Color(116, 16, 30));
        menuArticulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuArticulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuArticulo.setDoubleBuffered(true);
        menuArticulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addGroup(JpListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(JpListadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrolArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
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
        txtProducto.setToolTipText("Presiona Enter o Tab");
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
        txtExistencia.setToolTipText("Presiona Enter o Tab");
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
        txtPrecio.setToolTipText("Presiona Enter o Tab");
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
        txtUnidad.setToolTipText("Presiona Enter o Tab");
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

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/pencil1.png"))); // NOI18N
        jLabel4.setOpaque(true);

        javax.swing.GroupLayout JpAgregarLayout = new javax.swing.GroupLayout(JpAgregar);
        JpAgregar.setLayout(JpAgregarLayout);
        JpAgregarLayout.setHorizontalGroup(
            JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpAgregarLayout.createSequentialGroup()
                .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpAgregarLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCodigo)
                                .addComponent(txtProducto)
                                .addComponent(txtExistencia)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(JpAgregarLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        JpAgregarLayout.setVerticalGroup(
            JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpAgregarLayout.createSequentialGroup()
                        .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        txtProductoE.setToolTipText("Presiona Enter o Tab");
        txtProductoE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtProductoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoEKeyPressed(evt);
            }
        });

        txtExistenciaE.setEditable(false);
        txtExistenciaE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtExistenciaE.setToolTipText("Presiona Enter o Tab");
        txtExistenciaE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtExistenciaE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExistenciaEKeyPressed(evt);
            }
        });

        txtPrecioE.setEditable(false);
        txtPrecioE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtPrecioE.setToolTipText("Presiona Enter o Tab");
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
        txtUnidadE.setToolTipText("Presiona Enter o Tab");
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
        JpClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/delete.png"))); // NOI18N
        btnEliminarCliente.setMnemonic('B');
        btnEliminarCliente.setText("Borrar");
        btnEliminarCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnEliminarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnEliminarCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminarCliente.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEliminarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseExited(evt);
            }
        });
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        JpClientes.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 90, 30));

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
        JpClientes.add(btnAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 90, 30));

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
        JpClientes.add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 90, 30));

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
        JpClientes.add(btnListaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 30));

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
        txtBuscarCliente.setPreferredSize(new java.awt.Dimension(168, 28));
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
                .addContainerGap(47, Short.MAX_VALUE))
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
        txtRfc.setToolTipText("Presiona Enter o Tab");
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
        txtEmpresa.setToolTipText("Presiona Enter o Tab");
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
        txtDomicilio.setToolTipText("Presiona Enter o Tab");
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
        txtCiudad.setToolTipText("Presiona Enter o Tab");
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
        txtEmail.setToolTipText("Presiona Enter o Tab");
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
        txtEstado.setToolTipText("Presiona Enter o Tab");
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
        txtRfcE.setToolTipText("Presiona Enter o Tab");
        txtRfcE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtRfcE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRfcEKeyPressed(evt);
            }
        });

        txtEmpresaE.setEditable(false);
        txtEmpresaE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmpresaE.setToolTipText("Presiona Enter o Tab");
        txtEmpresaE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmpresaE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpresaEKeyPressed(evt);
            }
        });

        txtDomicilioE.setEditable(false);
        txtDomicilioE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtDomicilioE.setToolTipText("Presiona Enter o Tab");
        txtDomicilioE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtDomicilioE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDomicilioEKeyPressed(evt);
            }
        });

        txtCiudadE.setEditable(false);
        txtCiudadE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtCiudadE.setToolTipText("Presiona Enter o Tab");
        txtCiudadE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtCiudadE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCiudadEKeyPressed(evt);
            }
        });

        txtEmailE.setEditable(false);
        txtEmailE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEmailE.setToolTipText("Presiona Enter o Tab");
        txtEmailE.setSelectionColor(new java.awt.Color(91, 137, 193));
        txtEmailE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailEKeyPressed(evt);
            }
        });

        txtEstadoE.setEditable(false);
        txtEstadoE.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        txtEstadoE.setToolTipText("Presiona Enter o Tab");
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

        lbCiudad.setBackground(new java.awt.Color(255, 255, 255));
        lbCiudad.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbCiudad.setText("Ciudad");
        lbCiudad.setOpaque(true);

        lbEstado.setBackground(new java.awt.Color(255, 255, 255));
        lbEstado.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEstado.setText("Estado");
        lbEstado.setOpaque(true);

        lbEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        lbEmpresa.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEmpresa.setText("Empresa");
        lbEmpresa.setOpaque(true);

        lbDomicilio.setBackground(new java.awt.Color(255, 255, 255));
        lbDomicilio.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbDomicilio.setText("Domicilio");
        lbDomicilio.setOpaque(true);

        lbEstado1.setBackground(new java.awt.Color(255, 255, 255));
        lbEstado1.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        lbEstado1.setText("E-mail");
        lbEstado1.setOpaque(true);

        javax.swing.GroupLayout JpEditarClienteLayout = new javax.swing.GroupLayout(JpEditarCliente);
        JpEditarCliente.setLayout(JpEditarClienteLayout);
        JpEditarClienteLayout.setHorizontalGroup(
            JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRfcE)
                    .addComponent(txtEmpresaE)
                    .addComponent(txtDomicilioE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudadE)
                    .addComponent(lbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailE)
                    .addComponent(txtEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(btnAñadir2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpEditarClienteLayout.setVerticalGroup(
            JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpEditarClienteLayout.createSequentialGroup()
                        .addComponent(lbRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRfcE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmpresaE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtDomicilioE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpEditarClienteLayout.createSequentialGroup()
                        .addComponent(lbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCiudadE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpEditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(txtEmailE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpEditarClienteLayout.createSequentialGroup()
                                .addComponent(txtEstadoE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lbEstado1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29)
                .addComponent(btnAñadir2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        JpClientes.add(JpEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 350));

        panelPuntoVenta.addTab("CLIENTES", JpClientes);

        JpVentas.setBackground(new java.awt.Color(255, 255, 255));
        JpVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnF5.setBackground(new java.awt.Color(255, 255, 255));
        btnF5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnF5.setText("F5");
        btnF5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnF5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnF5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnF5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnF5MouseExited(evt);
            }
        });
        btnF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnF5ActionPerformed(evt);
            }
        });
        JpVentas.add(btnF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 30, 30));

        menuVentas.setBackground(new java.awt.Color(116, 16, 30));
        menuVentas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuVentas.setDoubleBuffered(true);
        menuVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuVentas.setOpaque(true);
        JpVentas.add(menuVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListadoVentas.setBackground(new java.awt.Color(255, 255, 255));
        JpListadoVentas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JpListadoVentas.setPreferredSize(new java.awt.Dimension(516, 360));

        txtBuscarVendedor.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarVendedor.setText("Buscar Vendedor");
        txtBuscarVendedor.setPreferredSize(new java.awt.Dimension(168, 28));
        txtBuscarVendedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarVendedorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarVendedorFocusLost(evt);
            }
        });
        txtBuscarVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarVendedorKeyPressed(evt);
            }
        });

        lbInicio.setBackground(new java.awt.Color(255, 255, 255));
        lbInicio.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbInicio.setText("Desde");
        lbInicio.setOpaque(true);

        lbFin.setBackground(new java.awt.Color(255, 255, 255));
        lbFin.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbFin.setText("Hasta");
        lbFin.setOpaque(true);

        btnBuscarVentas.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarVentas.setBorderPainted(false);
        btnBuscarVentas.setContentAreaFilled(false);
        btnBuscarVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarVentasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarVentasMouseExited(evt);
            }
        });
        btnBuscarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarVentasActionPerformed(evt);
            }
        });

        scrolVentas.setBackground(new java.awt.Color(255, 255, 255));
        scrolVentas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolVentas.setForeground(new java.awt.Color(255, 255, 255));
        scrolVentas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jtVentas.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jtVentas.setModel(new javax.swing.table.DefaultTableModel(
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
                "ID", "VENDEDOR", "TOTAL VENTA", "FECHA", "HORA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        jtVentas.setGridColor(new java.awt.Color(116, 16, 30));
        jtVentas.setOpaque(false);
        jtVentas.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolVentas.setViewportView(jtVentas);
        if (jtVentas.getColumnModel().getColumnCount() > 0) {
            jtVentas.getColumnModel().getColumn(4).setResizable(false);
            jtVentas.getColumnModel().getColumn(4).setHeaderValue("HORA");
        }

        jXDatePicker1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jXDatePicker1KeyPressed(evt);
            }
        });

        jXDatePicker2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jXDatePicker2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout JpListadoVentasLayout = new javax.swing.GroupLayout(JpListadoVentas);
        JpListadoVentas.setLayout(JpListadoVentasLayout);
        JpListadoVentasLayout.setHorizontalGroup(
            JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoVentasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoVentasLayout.createSequentialGroup()
                        .addComponent(lbFin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JpListadoVentasLayout.createSequentialGroup()
                        .addComponent(lbInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(btnBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoVentasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrolVentas, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpListadoVentasLayout.setVerticalGroup(
            JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoVentasLayout.createSequentialGroup()
                .addGroup(JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoVentasLayout.createSequentialGroup()
                        .addGroup(JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpListadoVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpListadoVentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarVentas, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(scrolVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        JpVentas.add(JpListadoVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 370));

        panelPuntoVenta.addTab("VENTAS", JpVentas);

        JpFacturas.setBackground(new java.awt.Color(255, 255, 255));
        JpFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnF6.setBackground(new java.awt.Color(255, 255, 255));
        btnF6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnF6.setText("F5");
        btnF6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        btnF6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnF6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnF6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnF6MouseExited(evt);
            }
        });
        btnF6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnF6ActionPerformed(evt);
            }
        });
        JpFacturas.add(btnF6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 30, 30));

        menuFactura.setBackground(new java.awt.Color(116, 16, 30));
        menuFactura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuFactura.setDoubleBuffered(true);
        menuFactura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuFactura.setOpaque(true);
        JpFacturas.add(menuFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListadoFactura.setBackground(new java.awt.Color(255, 255, 255));
        JpListadoFactura.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JpListadoFactura.setPreferredSize(new java.awt.Dimension(516, 360));

        txtBuscarFolio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarFolio.setText("Buscar Folio");
        txtBuscarFolio.setPreferredSize(new java.awt.Dimension(168, 28));
        txtBuscarFolio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFolioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFolioFocusLost(evt);
            }
        });
        txtBuscarFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarFolioKeyPressed(evt);
            }
        });

        lbInicio1.setBackground(new java.awt.Color(255, 255, 255));
        lbInicio1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbInicio1.setText("Desde");
        lbInicio1.setOpaque(true);

        lbFin1.setBackground(new java.awt.Color(255, 255, 255));
        lbFin1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbFin1.setText("Hasta");
        lbFin1.setOpaque(true);

        btnBuscarFactura.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarFactura.setBorderPainted(false);
        btnBuscarFactura.setContentAreaFilled(false);
        btnBuscarFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarFacturaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarFacturaMouseExited(evt);
            }
        });
        btnBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFacturaActionPerformed(evt);
            }
        });

        scrolFactura.setBackground(new java.awt.Color(255, 255, 255));
        scrolFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolFactura.setForeground(new java.awt.Color(255, 255, 255));
        scrolFactura.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jtFactura.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jtFactura.setModel(new javax.swing.table.DefaultTableModel(
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
                "FOLIO", "SUBTOTAL", "TOTAL", "FECHA", "HORA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
        jtFactura.setGridColor(new java.awt.Color(116, 16, 30));
        jtFactura.setOpaque(false);
        jtFactura.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolFactura.setViewportView(jtFactura);

        javax.swing.GroupLayout JpListadoFacturaLayout = new javax.swing.GroupLayout(JpListadoFactura);
        JpListadoFactura.setLayout(JpListadoFacturaLayout);
        JpListadoFacturaLayout.setHorizontalGroup(
            JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoFacturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(btnBuscarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrolFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );
        JpListadoFacturaLayout.setVerticalGroup(
            JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoFacturaLayout.createSequentialGroup()
                .addGroup(JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoFacturaLayout.createSequentialGroup()
                        .addGroup(JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpListadoFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpListadoFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscarFactura, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(scrolFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        JpFacturas.add(JpListadoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 370));

        panelPuntoVenta.addTab("FACTURAS", JpFacturas);

        JpUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        JpUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        JpUsuarios.add(btnEditarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 90, 30));

        btnInf.setBackground(new java.awt.Color(255, 255, 255));
        btnInf.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnInf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/information.png"))); // NOI18N
        btnInf.setMnemonic('I');
        btnInf.setText("Informacion");
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
        JpUsuarios.add(btnInf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 110, 30));

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
        JpUsuarios.add(btnAgregarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 90, 30));

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
        JpUsuarios.add(btnEliminarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 90, 30));

        menuUsuarios.setBackground(new java.awt.Color(116, 16, 30));
        menuUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        menuUsuarios.setDoubleBuffered(true);
        menuUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setOpaque(true);
        JpUsuarios.add(menuUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 516, 50));

        JpListadoUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        JpListadoUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JpListadoUsuarios.setPreferredSize(new java.awt.Dimension(516, 360));

        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNombre.setText("Nombre");
        txtNombre.setToolTipText("Presiona Enter o Tab");
        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtUser.setEditable(false);
        txtUser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtUser.setText("Usuario");
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserFocusLost(evt);
            }
        });

        txtPassword.setEditable(false);
        txtPassword.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPassword.setText("Contraseña");
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        txtCorreo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCorreo.setText("E-mail");
        txtCorreo.setToolTipText("Presiona Enter o Tab");
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoKeyPressed(evt);
            }
        });

        txtBuscarUsers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBuscarUsers.setText("Buscar Usuario ");
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

        scrolUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        scrolUsuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(116, 16, 30)));
        scrolUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        scrolUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jtUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        jtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "PERMISO", "USUARIO", "CONTRASEÑA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtUsuarios.setGridColor(new java.awt.Color(116, 16, 30));
        jtUsuarios.setOpaque(false);
        jtUsuarios.setSelectionBackground(new java.awt.Color(91, 137, 193));
        scrolUsuarios.setViewportView(jtUsuarios);
        if (jtUsuarios.getColumnModel().getColumnCount() > 0) {
            jtUsuarios.getColumnModel().getColumn(0).setHeaderValue("PERMISO");
            jtUsuarios.getColumnModel().getColumn(1).setHeaderValue("USUARIO");
            jtUsuarios.getColumnModel().getColumn(2).setHeaderValue("CONTRASEÑA");
        }

        notas.setColumns(20);
        notas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        notas.setRows(5);
        notas.setText("Observación");
        notas.setToolTipText("Presiona Enter o Tab");
        notas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                notasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                notasFocusLost(evt);
            }
        });
        notas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                notasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(notas);

        cbxPermiso.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbxPermiso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Vendedor" }));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Permiso:");

        javax.swing.GroupLayout JpListadoUsuariosLayout = new javax.swing.GroupLayout(JpListadoUsuarios);
        JpListadoUsuarios.setLayout(JpListadoUsuariosLayout);
        JpListadoUsuariosLayout.setHorizontalGroup(
            JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(cbxPermiso, 0, 170, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(txtBuscarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(scrolUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        JpListadoUsuariosLayout.setVerticalGroup(
            JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoUsuariosLayout.createSequentialGroup()
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBuscarUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                                .addComponent(btnBuscarUser)
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpListadoUsuariosLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(JpListadoUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JpListadoUsuariosLayout.createSequentialGroup()
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrolUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        JpUsuarios.add(JpListadoUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, 370));

        panelPuntoVenta.addTab("USUARIOS", JpUsuarios);

        JpEmpleadoMes.setBackground(new java.awt.Color(255, 255, 255));

        btnBuscarGraf1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarGraf1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarGraf1.setBorderPainted(false);
        btnBuscarGraf1.setContentAreaFilled(false);
        btnBuscarGraf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarGraf1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarGraf1MouseExited(evt);
            }
        });
        btnBuscarGraf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGraf1ActionPerformed(evt);
            }
        });

        lbContenedor1.setBackground(new java.awt.Color(255, 255, 255));
        lbContenedor1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbContenedor1.setOpaque(true);

        javax.swing.GroupLayout JpEmpleadoMesLayout = new javax.swing.GroupLayout(JpEmpleadoMes);
        JpEmpleadoMes.setLayout(JpEmpleadoMesLayout);
        JpEmpleadoMesLayout.setHorizontalGroup(
            JpEmpleadoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEmpleadoMesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpEmpleadoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JpEmpleadoMesLayout.createSequentialGroup()
                        .addComponent(jXDatePicker7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jXDatePicker8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarGraf1))
                    .addComponent(lbContenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        JpEmpleadoMesLayout.setVerticalGroup(
            JpEmpleadoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpEmpleadoMesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpEmpleadoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpEmpleadoMesLayout.createSequentialGroup()
                        .addComponent(btnBuscarGraf1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpEmpleadoMesLayout.createSequentialGroup()
                        .addGroup(JpEmpleadoMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jXDatePicker7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXDatePicker8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addComponent(lbContenedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelPuntoVenta.addTab("EMPLEADO DEL MES", JpEmpleadoMes);

        JpEstadisticas.setBackground(new java.awt.Color(255, 255, 255));
        JpEstadisticas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JpGraficaEstadistica.setBackground(new java.awt.Color(255, 255, 255));
        JpGraficaEstadistica.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        JpGraficaEstadistica.setPreferredSize(new java.awt.Dimension(516, 360));

        btnBuscarGraf.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarGraf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/articulos/buscar.png"))); // NOI18N
        btnBuscarGraf.setBorderPainted(false);
        btnBuscarGraf.setContentAreaFilled(false);
        btnBuscarGraf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarGrafMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarGrafMouseExited(evt);
            }
        });
        btnBuscarGraf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarGrafActionPerformed(evt);
            }
        });

        lbContenedor.setBackground(new java.awt.Color(255, 255, 255));
        lbContenedor.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lbContenedor.setOpaque(true);

        jYear.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Año");

        javax.swing.GroupLayout JpGraficaEstadisticaLayout = new javax.swing.GroupLayout(JpGraficaEstadistica);
        JpGraficaEstadistica.setLayout(JpGraficaEstadisticaLayout);
        JpGraficaEstadisticaLayout.setHorizontalGroup(
            JpGraficaEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpGraficaEstadisticaLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lbContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(JpGraficaEstadisticaLayout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarGraf)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpGraficaEstadisticaLayout.setVerticalGroup(
            JpGraficaEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpGraficaEstadisticaLayout.createSequentialGroup()
                .addGroup(JpGraficaEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarGraf)
                    .addGroup(JpGraficaEstadisticaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JpGraficaEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        JpEstadisticas.add(JpGraficaEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 380));

        panelPuntoVenta.addTab("ESTADISTICAS", JpEstadisticas);

        getContentPane().add(panelPuntoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 690, 410));

        lbAdmin1.setBackground(new java.awt.Color(116, 16, 30));
        lbAdmin1.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        lbAdmin1.setForeground(new java.awt.Color(255, 255, 255));
        lbAdmin1.setText("Fecha");
        lbAdmin1.setOpaque(true);
        getContentPane().add(lbAdmin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 140, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void barraFormularioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraFormularioMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_barraFormularioMousePressed

    private void barraFormularioMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraFormularioMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_barraFormularioMouseDragged

//    Evento que permite realizar búsquedas respeto a lo solicitado.
    private void btnBuscarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFacturaActionPerformed
        try {
            dateStringInicio = format.format(jXDatePicker3.getDate());
            dateStringFin = format.format(jXDatePicker4.getDate());

            String diseñoTabla = "FOLIO,RFC,TOTAL COMPRA,FECHA,LUGAR";
            String columnas = "folio,subtotal,total,fecha,hora";
            if ("Buscar Folio".equals(txtBuscarFolio.getText())) {
                diseñoTablas di = new diseñoTablas(jtFactura, diseñoTabla, "factura", columnas);
                di.listadoFechas("fecha", dateStringInicio, dateStringFin);
            } else {
                diseñoTablas di = new diseñoTablas(jtFactura, diseñoTabla, "factura", columnas);
                di.listadoFechasCampo("fecha", "folio", dateStringInicio, dateStringFin, txtBuscarFolio.getText().trim().toUpperCase());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarFacturaActionPerformed

    private void btnBuscarFacturaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarFacturaMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarFactura.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarFacturaMouseExited

    private void btnBuscarFacturaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarFacturaMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarFactura.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas específicas con el Folio");
    }//GEN-LAST:event_btnBuscarFacturaMouseEntered

    private void txtBuscarFolioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFolioFocusLost
        try {
            if ("".equals(txtBuscarFolio.getText())) {
                txtBuscarFolio.setText("Buscar Folio");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarFolioFocusLost

    private void txtBuscarFolioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFolioFocusGained
        txtBuscarFolio.setText("");
    }//GEN-LAST:event_txtBuscarFolioFocusGained

    private void btnF6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnF6ActionPerformed
        try {
            GestionFactura.BdTabla tF = new GestionFactura.BdTabla(jtFactura);
            graficar();
            graficarEmpleadoMes();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnF6ActionPerformed

    private void btnF6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnF6MouseExited
        bordesBoton = new BordesButton(btnF6);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnF6MouseExited

    private void btnF6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnF6MouseEntered
        bordesBoton = new BordesButton(btnF6);
        bordesBoton.encender();
        barraEstado.setText("F5: Botón que permite actualizar los datos de la tabla.");
    }//GEN-LAST:event_btnF6MouseEntered

//    Evento que permite realizar búsquedas respeto a lo solicitado.
    private void btnBuscarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarVentasActionPerformed
        try {
            dateStringInicio = format.format(jXDatePicker1.getDate());
            dateStringFin = format.format(jXDatePicker2.getDate());
            System.out.println(dateStringInicio + "  " + dateStringFin);
            String diseñoTabla = "VENDEDOR,TOTAL VENTA,FECHA,HORA,FACTURA";
            String columnas = "vendedor,total,fecha,hora,factura";
            if ("Buscar Vendedor".equals(txtBuscarVendedor.getText())) {
                diseñoTablas di = new diseñoTablas(jtVentas, diseñoTabla, "ventas", columnas);
                di.listadoFechas("fecha", dateStringInicio, dateStringFin);
            } else {
                diseñoTablas di = new diseñoTablas(jtVentas, diseñoTabla, "ventas", columnas);
                di.listadoFechasCampo("fecha", "vendedor", dateStringInicio, dateStringFin, txtBuscarVendedor.getText().trim().toUpperCase());
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnBuscarVentasActionPerformed

    private void btnBuscarVentasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarVentasMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarVentas.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarVentasMouseExited

    private void btnBuscarVentasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarVentasMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarVentas.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas específicas con el nombre de vendedor y fechas.");
    }//GEN-LAST:event_btnBuscarVentasMouseEntered

    private void txtBuscarVendedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarVendedorFocusLost
        try {
            if ("".equals(txtBuscarVendedor.getText())) {
                txtBuscarVendedor.setText("Buscar Vendedor");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarVendedorFocusLost

    private void txtBuscarVendedorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarVendedorFocusGained
        txtBuscarVendedor.setText("");
    }//GEN-LAST:event_txtBuscarVendedorFocusGained

    private void btnF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnF5ActionPerformed
        try {
            GestionVentas.BdTabla tV = new GestionVentas.BdTabla(jtVentas);
            graficar();
            graficarEmpleadoMes();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnF5ActionPerformed

    private void btnF5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnF5MouseExited
        bordesBoton = new BordesButton(btnF5);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnF5MouseExited

    private void btnF5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnF5MouseEntered
        bordesBoton = new BordesButton(btnF5);
        bordesBoton.encender();
        barraEstado.setText("F5: Botón que permite actualizar los datos de la tabla.");
    }//GEN-LAST:event_btnF5MouseEntered
//  Evento que agrega clientes en la BD.
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

    private void btnAñadirClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirClienteMouseExited
        bordesBoton = new BordesButton(btnAñadirCliente);
        bordesBoton.apagar();
    }//GEN-LAST:event_btnAñadirClienteMouseExited

    private void btnAñadirClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirClienteMouseEntered
        bordesBoton = new BordesButton(btnAñadirCliente);
        bordesBoton.encender();
    }//GEN-LAST:event_btnAñadirClienteMouseEntered

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

    private void btnBuscarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarCliente.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarClienteMouseExited

    private void btnBuscarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarCliente.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas específicas con RFC o Empresa.");
    }//GEN-LAST:event_btnBuscarClienteMouseEntered

    private void btnListaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaClienteActionPerformed
        JpListadoCliente.setVisible(true);
        JpAgregarCliente.setVisible(false);
        JpEditarCliente.setVisible(false);
    }//GEN-LAST:event_btnListaClienteActionPerformed

    private void btnListaClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaClienteMouseExited
        bordesBoton = new BordesButton(btnListaCliente);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnListaClienteMouseExited

    private void btnListaClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaClienteMouseEntered
        bordesBoton = new BordesButton(btnListaCliente);
        bordesBoton.encender();
        barraEstado.setText("Listado: Botón que permite al administrador ver la lista de clientes disponibles en la base de datos.");
    }//GEN-LAST:event_btnListaClienteMouseEntered

//  Evento que permite editar a los clientes de la BD.    
    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        String[] cadena;
        try {
            String clausula = (String) this.jtCliente.getValueAt(this.jtCliente.getSelectedRow(), 0);
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
        barraEstado.setText("");
    }//GEN-LAST:event_btnEditarClienteMouseExited

    private void btnEditarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarClienteMouseEntered
        bordesBoton = new BordesButton(btnEditarCliente);
        bordesBoton.encender();
        barraEstado.setText("Editar: Botón que permite al administrador editar clientes de la base de datos.");
    }//GEN-LAST:event_btnEditarClienteMouseEntered

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        JpListadoCliente.setVisible(false);
        JpAgregarCliente.setVisible(true);
        JpEditarCliente.setVisible(false);
        limpiarTxtCliente();
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void btnAgregarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarClienteMouseExited
        bordesBoton = new BordesButton(btnAgregarCliente);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnAgregarClienteMouseExited

    private void btnAgregarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarClienteMouseEntered
        bordesBoton = new BordesButton(btnAgregarCliente);
        bordesBoton.encender();
        barraEstado.setText("Agregar: Botón que permite al administrador agregar clientes en la base de datos.");
    }//GEN-LAST:event_btnAgregarClienteMouseEntered

    private void btnEliminarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseExited
        bordesBoton = new BordesButton(btnEliminarCliente);
        bordesBoton.apagar();
        barraEstado.setText("");
    }//GEN-LAST:event_btnEliminarClienteMouseExited

    private void btnEliminarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseEntered
        bordesBoton = new BordesButton(btnEliminarCliente);
        bordesBoton.encender();
        barraEstado.setText("Borrar: Botón que permite al administrador eliminar clientes de la base de datos.");
    }//GEN-LAST:event_btnEliminarClienteMouseEntered

    private void txtPrecioEFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioEFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioEFocusLost

    private void txtPrecioEFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioEFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioEFocusGained

    //  Evento que permite agregar agregar articulos ala BD.
    private void btnAñadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadir1ActionPerformed
        String columnas = "nombre,codigo,unidad,existencia,precio";
        int existencia = 0;
        double precio = 0.0;
        try {
            existencia = Integer.parseInt(txtExistenciaE.getText().trim());
            precio = Double.parseDouble(txtPrecioE.getText().trim());
            if ("".equals(txtProductoE.getText())
                    || "".equals(txtCodigoE.getText()) || "".equals(txtUnidadE.getText())
                    || "".equals(txtExistenciaE.getText()) || "".equals(txtPrecioE.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos");
            } else {
                con.actualizar("articulos", columnas, txtProductoE.getText() + ","
                        + txtCodigoE.getText() + "," + txtUnidadE.getText() + ","
                        + txtExistenciaE.getText() + "," + txtPrecioE.getText(), "codigo='" + codigoBaseEdicion + "';");
                camposEdicionArticulos();
                reiniciarJTableArticulos();
                limpiarTxt();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato invalido.\nVerifique existencia y precio.");
        }
    }//GEN-LAST:event_btnAñadir1ActionPerformed

//    Limpia los campos de texto solicitados.
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

//    Permite poner en editables los campos de texto.
    public void camposEdicionArticulosTrue() {
        txtProductoE.setEditable(true);
        txtUnidadE.setEditable(true);
        txtExistenciaE.setEditable(true);
        txtPrecioE.setEditable(true);

    }

    //    Permite poner en editables los campos de texto.
    public void camposEdicionClientesTrue() {
        txtRfcE.setEditable(true);
        txtEmpresaE.setEditable(true);
        txtDomicilioE.setEditable(true);
        txtCiudadE.setEditable(true);
        txtEstadoE.setEditable(true);
        txtEmailE.setEditable(true);
    }

    //    Permite poner en No editables los campos de texto.
    public void camposEdicionClientes() {
        txtRfcE.setEditable(false);
        txtEmpresaE.setEditable(false);
        txtDomicilioE.setEditable(false);
        txtCiudadE.setEditable(false);
        txtEstadoE.setEditable(false);
        txtEmailE.setEditable(false);
    }
    private void btnAñadir1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir1MouseExited

    private void btnAñadir1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadir1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAñadir1MouseEntered

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

//    Evento en el cual se ingrea a ala BD articulos.
    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        String columnas = "nombre,codigo,unidad,existencia,precio";
        int existencia = 0;
        double precio = 0.0;
        try {
            existencia = Integer.parseInt(txtExistencia.getText().trim());
            precio = Double.parseDouble(txtPrecio.getText().trim());
            try {
                if ("NOMBRE ARTÍCULO".equals(txtProducto.getText())
                        || "CODIGO ARTÍCULO".equals(txtCodigo.getText()) || "UNIDAD".equals(txtUnidad.getText())
                        || "EXISTENCIA ARTÍCULO".equals(txtExistencia.getText()) || "PRECIO ARTÍCULO".equals(txtPrecio.getText())) {
                    JOptionPane.showMessageDialog(null, "Llene todos los campos de Registro");
                } else {
                    System.out.println("entro a agregar");
                    String valores = "";
                    valores += txtProducto.getText() + ",";
                    valores += txtCodigo.getText() + ",";
                    valores += txtUnidad.getText() + ",";
                    valores += txtExistencia.getText() + ",";
                    valores += txtPrecio.getText();

                    con.agregar("articulos", columnas, valores);
                    reiniciarJTableArticulos();
                    limpiarTxt();
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato invalido.\nVerifique existencia y precio.");
        }
    }//GEN-LAST:event_btnAñadirActionPerformed
//   Limpia los campos de texto.

    public void limpiarTxt() {
        try {
            txtProducto.setText("NOMBRE ARTíCULO");
            txtCodigo.setText("CODIGO ARTÍCULO");
            txtUnidad.setText("UNIDAD");
            txtExistencia.setText("EXISTENCIA ARTíCULO");
            txtPrecio.setText("PRECIO ARTíCULO");

            txtProductoE.setText("");
            txtCodigoE.setText("");
            txtUnidadE.setText("");
            txtExistenciaE.setText("");
            txtPrecioE.setText("");
        } catch (Exception e) {
        }

    }
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

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscar.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarMouseExited

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscar.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas específicas con el codigo o nombre del producto.");
    }//GEN-LAST:event_btnBuscarMouseEntered

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        JpListado.setVisible(true);
        JpAgregar.setVisible(false);
        JpEditar.setVisible(false);
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnListaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMouseExited
        try {
            bordesBoton = new BordesButton(btnLista);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnListaMouseExited

    private void btnListaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMouseEntered
        try {
            bordesBoton = new BordesButton(btnLista);
            bordesBoton.encender();
            barraEstado.setText("Listado: Botón que permite al administrador ver la lista de artículos disponibles en la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnListaMouseEntered

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

//    Evento que permite editar articulos de la BD.
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String[] cadena;
        try {
            String clausula = (String) this.JtArticulos.getValueAt(this.JtArticulos.getSelectedRow(), 0);
            JpListado.setVisible(false);
            JpAgregar.setVisible(false);
            JpEditar.setVisible(true);

            String columnas = "nombre,codigo,unidad,existencia,precio";
            con.busqueda("articulos", "codigo", columnas, clausula);
            cadena = con.registro_busqueda.split(",");
            camposEdicionArticulosTrue();

            txtProductoE.setText(cadena[0]);
            txtCodigoE.setText(cadena[1]);
            codigoBaseEdicion = cadena[1];
            txtUnidadE.setText(cadena[2]);
            txtExistenciaE.setText(cadena[3]);
            txtPrecioE.setText(cadena[4]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnEditarActionPerformed


    private void btnEditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseExited
        try {
            bordesBoton = new BordesButton(btnEditar);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarMouseExited

    private void btnEditarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseEntered
        try {
            bordesBoton = new BordesButton(btnEditar);
            bordesBoton.encender();
            barraEstado.setText("Editar: Botón que permite al administrador editar artículos de la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarMouseEntered

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            JpListado.setVisible(false);
            JpAgregar.setVisible(true);
            JpEditar.setVisible(false);
            limpiarTxt();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseExited
        try {
            bordesBoton = new BordesButton(btnAgregar);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarMouseExited

    private void btnAgregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseEntered
        try {
            bordesBoton = new BordesButton(btnAgregar);
            bordesBoton.encender();
            barraEstado.setText("Agregar: Botón que permite al administrador agregar artículos en la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        try {
            bordesBoton = new BordesButton(btnEliminar);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarMouseExited

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        try {
            bordesBoton = new BordesButton(btnEliminar);
            bordesBoton.encender();
            barraEstado.setText("Borrar: Botón que permite al administrador eliminar artículos de la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarMouseEntered

//      Evento que permite seleccionar la fila de una tabla para ver el articulo buscado.
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

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        txtNombre.setText("");
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        try {
            if ("".equals(txtNombre.getText())) {
                txtNombre.setText("Nombre");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtNombreFocusLost

    private void btnBuscarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarUserMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarUser.setIcon(icon);
    }//GEN-LAST:event_btnBuscarUserMouseEntered

    private void btnBuscarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarUserMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarUser.setIcon(icon);
    }//GEN-LAST:event_btnBuscarUserMouseExited

//    Evento que permite realizar búsquedas respeto a lo solicitado.
    private void btnBuscarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUserActionPerformed
        try {

            String ele = txtBuscarUsers.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < jtUsuarios.getRowCount(); i++) {
                if (jtUsuarios.getValueAt(i, 0).equals(ele)) {
                    jtUsuarios.changeSelection(i, 0, false, false);

                    break;
                } else if (jtUsuarios.getValueAt(i, 1).equals(ele)) {
                    jtUsuarios.changeSelection(i, 1, false, false);

                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarUserActionPerformed

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        txtCorreo.setText("");
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        try {
            if ("".equals(txtCorreo.getText())) {
                txtCorreo.setText("E-mail");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtBuscarUsersFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarUsersFocusGained
        txtBuscarUsers.setText("");
    }//GEN-LAST:event_txtBuscarUsersFocusGained

    private void txtBuscarUsersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarUsersFocusLost
        try {
            if ("".equals(txtBuscarUsers.getText())) {
                txtBuscarUsers.setText("Buscar Usuario");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarUsersFocusLost

    private void notasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notasFocusGained
        notas.setText("");
    }//GEN-LAST:event_notasFocusGained

    private void notasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notasFocusLost
        try {
            if ("".equals(notas.getText())) {
                notas.setText("Observación");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_notasFocusLost

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        try {
            eliminarTabla del = new eliminarTabla(jtCliente, 0, "clientes", "rfc = '");
            reiniciarJTableClientes();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnAgregarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnAgregarUser);
            bordesBoton.encender();
            barraEstado.setText("Agregar: Botón que permite al administrador agregar usuarios en la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarUserMouseEntered

    private void btnAgregarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnAgregarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarUserMouseExited

//    Evento que permite  agregar usuarios a la BD.
    private void btnAgregarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUserActionPerformed
        String permiso;
        String columnas = "nombre,usuario,password,permiso,email,notas";
        try {
            if (cbxPermiso.getSelectedIndex() == 0) {
                permiso = "1";
            } else {
                permiso = "0";
            }

            if ("Nombre".equals(txtNombre.getText())
                    || "Usuario".equals(txtUser.getText()) || "E-mail".equals(txtCorreo.getText())
                    || "contraseña".equals(txtPassword.getText())) {
                JOptionPane.showMessageDialog(null, "Llene todos los campos de Registro");
            } else {
                String valores = "";
                valores += txtNombre.getText() + ",";
                valores += txtUser.getText() + ",";
                valores += txtPassword.getText() + ",";
                valores += permiso + ",";
                valores += txtCorreo.getText() + ",";
                valores += notas.getText();
                con.agregar("usuarios", columnas, valores);
                reiniciarJTableUsuarios();

                txtNombre.setText("Nombre");
                txtUser.setText("Usuario");
                txtPassword.setText("contraseña");
                txtCorreo.setText("E-mail");
                notas.setText("Observación");
            }
            graficar();
            graficarEmpleadoMes();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarUserActionPerformed

    private void btnEliminarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnEliminarUser);
            bordesBoton.encender();
            barraEstado.setText("Borrar: Botón que permite al administrador eliminar usuarios de la base de datos.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserMouseEntered

    private void btnEliminarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnEliminarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserMouseExited

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
        txtUser.setText("");
    }//GEN-LAST:event_txtUserFocusGained

    private void txtUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusLost
        try {
            if ("".equals(txtUser.getText())) {
                txtUser.setText("Usuario");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtUserFocusLost

//     Evento que determina el nombre de usuario
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        String[] user;
        String nombre;
        int max = 0;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            nombre = txtNombre.getText().toUpperCase();
            txtNombre.setText(nombre);
            try {
                user = txtNombre.getText().toUpperCase().split(" ");
                max = user.length;
                switch (max) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Escriba nombre completo");
                        txtNombre.requestFocus(true);
                        break;
                    case 2:
                        txtUser.setText(user[0] + "." + user[1]);
                        txtCorreo.requestFocus(true);
                        break;
                    case 3:
                        txtUser.setText(user[0] + "." + user[1]);
                        txtCorreo.requestFocus(true);
                        break;
                    case 4:
                        txtUser.setText(user[0] + "." + user[2]);
                        txtCorreo.requestFocus(true);
                        break;
                    case 5:
                        txtUser.setText(user[0] + "." + user[3]);
                        txtCorreo.requestFocus(true);
                        break;
                }
                introducirContraseña(nombre);

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtNombreKeyPressed

//  Método que realiza un encriptado del nombre.
//    Se genera automaticamente.
    public void introducirContraseña(String nombre) {
        char array[] = nombre.toCharArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) (array[i] + (char) 5);
        }
        String encriptado = String.valueOf(array);
        txtPassword.setText(encriptado);
    }

    public void reiniciarJTableUsuarios() {
        try {
            GestionUsuarios.BdTabla1 prueba = new BdTabla1(jtUsuarios);
        } catch (Exception e) {
        }
    }

    public void reiniciarJTableArticulos() {
        try {
            GestionArticulos.BdTabla tA = new GestionArticulos.BdTabla(JtArticulos);
        } catch (Exception e) {
        }
    }

    public void reiniciarJTableClientes() {
        try {
            GestionClientes.BdTabla tC = new GestionClientes.BdTabla(jtCliente);
        } catch (Exception e) {
        }
    }

//    Evento que permite verificar el E-mail proporcionado.
    private void txtCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyPressed
        boolean respuesta;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                m = p.matcher(txtCorreo.getText());
                m.matches();
                respuesta = m.matches();
                if (respuesta == true) {
                    cbxPermiso.requestFocus(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Correo Electronico Invalido");
                    txtCorreo.requestFocus(true);
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtCorreoKeyPressed

    private void btnInfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfMouseEntered
        try {
            bordesBoton = new BordesButton(btnInf);
            bordesBoton.encender();
            barraEstado.setText("Información: Botón que permite al administrador visualizar todos"
                    + " los datos del usuario.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInfMouseEntered

    private void btnInfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfMouseExited
        try {
            bordesBoton = new BordesButton(btnInf);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInfMouseExited

//    Evento que llama a otro jframe
    private void btnInfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfActionPerformed
        try {
            String columnas = "permiso,nombre,usuario,password,email,notas";
            String clausula = (String) this.jtUsuarios.getValueAt(this.jtUsuarios.getSelectedRow(), 1);
            con.busqueda("usuarios", "password", columnas, clausula);
            Emergente emg = new Emergente(con.registro_busqueda);
            emg.setVisible(true);
            emg.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnInfActionPerformed

//      Evento que permite eliminar una fila en la tabla y un registro en la BD.
    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        try {
            eliminarTabla del = new eliminarTabla(jtUsuarios, 0, "usuarios", "usuario = '");
            reiniciarJTableUsuarios();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarUserActionPerformed

    private void txtUnidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnidadFocusGained
        txtUnidad.setText("");
    }//GEN-LAST:event_txtUnidadFocusGained

    private void txtUnidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUnidadFocusLost
        try {
            if ("".equals(txtUnidad.getText())) {
                txtUnidad.setText("UNIDAD");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtUnidadFocusLost

//    Evento que permite buscar ariculos en este campo de texto.
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

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtProductoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoEKeyPressed
        String cadena = "";
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                cadena = txtProductoE.getText().trim().toUpperCase();
                if (cadena.length() < 3) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de artículo\ncon 3 o más caracteres.");
                    txtProductoE.requestFocus(true);
                } else {
                    separarStringNumero(cadena);
                    txtCodigoE.setText(separarCodigo);
                    txtUnidadE.requestFocus(true);
                }
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtProductoEKeyPressed

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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            eliminarTabla del = new eliminarTabla(JtArticulos, 0, "articulos", "codigo = '");
            reiniciarJTableArticulos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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

//    Evento que permite Verificar los atributos del email valido.
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

//    Evento que permite añadir clientes en la BD de datos.
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
    private void txtRfcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                validarRFC();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtRfcKeyPressed

    //    Evento que permite buscar ariculos en la BD.
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

    //    Evento que permite buscar clientes en la BD.
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

    //    Evento que permite buscar ariculos en la BD y seleccionarlo en su tabla.
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

    //    Evento que permite buscar ventas en la BD.
    private void txtBuscarVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVendedorKeyPressed
        Date valor = null;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String diseñoTabla = "VENDEDOR,TOTAL VENTA,FECHA,HORA,FACTURA";
                String columnas = "vendedor,total,fecha,hora,factura";
                diseñoTablas di = new diseñoTablas(jtVentas, diseñoTabla, "ventas", columnas);
                di.listadoBusqueda("vendedor", txtBuscarVendedor.getText().trim().toUpperCase());
                jXDatePicker1.setDate(valor);
                jXDatePicker2.setDate(valor);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtBuscarVendedorKeyPressed

    private void jXDatePicker1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jXDatePicker1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                jXDatePicker2.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jXDatePicker1KeyPressed

    private void jXDatePicker2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jXDatePicker2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                btnBuscarVentas.requestFocus(true);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jXDatePicker2KeyPressed

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        txtPassword.setText("");
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        try {
            if ("".equals(txtPassword.getText())) {
                txtPassword.setText("Contraseña");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtPasswordFocusLost

    private void notasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                btnAgregarUser.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_notasKeyPressed

    //    Evento que permite buscar usuarios en la BD.
    private void txtBuscarUsersKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsersKeyReleased
        try {
            icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
            btnBuscarUser.setIcon(icon);
            String ele = txtBuscarUsers.getText().trim().toUpperCase();
            System.out.println("ENTRANDO BOTON");
            for (int i = 0; i < jtUsuarios.getRowCount(); i++) {
                if (jtUsuarios.getValueAt(i, 0).equals(ele)) {
                    jtUsuarios.changeSelection(i, 0, false, false);
                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscarUser.setIcon(icon);
                    break;
                } else if (jtUsuarios.getValueAt(i, 1).equals(ele)) {
                    jtUsuarios.changeSelection(i, 1, false, false);
                    icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
                    btnBuscarUser.setIcon(icon);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarUsersKeyReleased

    private void btnBuscarGrafMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarGrafMouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarGraf.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas de ventas respecto el año deseado.");
    }//GEN-LAST:event_btnBuscarGrafMouseEntered

    private void btnBuscarGrafMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarGrafMouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarGraf.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarGrafMouseExited

    //    Evento que permite buscar ventas en la BD y ademas graficarlo en el panel.
    private void btnBuscarGrafActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGrafActionPerformed
        lbContenedor.setIcon(null);
        String[] cadena;
        double[] ventas;
        int año = jYear.getYear();
        System.out.println(año);
        try {
            JFreeChart barra = null;
            DefaultCategoryDataset datos;
            datos = new DefaultCategoryDataset();
            ventas = new double[12];
            for (int i = 0; i < 12; i++) {
                con.busquedaGraficas("ventas", "total", "fecha", "",
                        "", "", 2, (i + 1), año);
                if ("null".equals(con.registro_busqueda)) {
                    ventas[i] = 0;
                    System.out.println(mes[i] + " tiene: " + ventas[i]);
                } else {
                    ventas[i] = Double.parseDouble(con.registro_busqueda);
                    System.out.println(mes[i] + " tiene: " + ventas[i]);
                }
                datos.setValue(ventas[i], mes[i], "");
                barra = ChartFactory.createBarChart("Estadisticas de ventas", "Meses", "Ventas", datos, PlotOrientation.VERTICAL, true, true, true);
                BufferedImage graficoBarra = barra.createBufferedImage(465, 300);
                lbContenedor.setSize(465, 300);
                lbContenedor.setIcon(new ImageIcon(graficoBarra));
                JpEstadisticas.updateUI();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarGrafActionPerformed

    private void btnBuscarGraf1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarGraf1MouseEntered
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar1.png"));
        btnBuscarGraf1.setIcon(icon);
        barraEstado.setText("Buscar: Permite al administrador realizar búsquedas específicas con fechas respecto"
                + " a las ventas obtenidas por el empleado.");
    }//GEN-LAST:event_btnBuscarGraf1MouseEntered

    private void btnBuscarGraf1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarGraf1MouseExited
        icon = new ImageIcon(getClass().getResource(URL1 + "buscar.png"));
        btnBuscarGraf1.setIcon(icon);
        barraEstado.setText("");
    }//GEN-LAST:event_btnBuscarGraf1MouseExited

    //    Evento que permite buscar ventas de los empleados en la BD y ademas graficar quien ha realizado o tenido mejores ventas.
    private void btnBuscarGraf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarGraf1ActionPerformed
        lbContenedor1.setIcon(null);
        String[] cadena;
        double[] ventas;
        try {
            JFreeChart barra1 = null;
            DefaultCategoryDataset datos1 = null;
            datos1 = new DefaultCategoryDataset();

            dateStringInicio = format.format(jXDatePicker7.getDate());
            dateStringFin = format.format(jXDatePicker8.getDate());

            con.busqueda1("usuarios", "permiso", "usuario", "0");
            cadena = con.registro_busqueda.split(",");
            ventas = new double[cadena.length];
            for (int i = 0; i < cadena.length; i++) {
                con.busquedaGraficas("ventas", "total", "fecha", dateStringInicio,
                        dateStringFin, cadena[i], 1, 0, 0);
                System.out.println(cadena[i] + " tiene esta cant: " + con.registro_busqueda);
                if ("null".equals(con.registro_busqueda)) {
                    ventas[i] = 0;
                    System.out.println(cadena[i] + " tiene: " + ventas[i]);
                } else {
                    ventas[i] = Double.parseDouble(con.registro_busqueda);
                    System.out.println(cadena[i] + " tiene: " + ventas[i]);
                }
                datos1.setValue(ventas[i], cadena[i], "");
                barra1 = ChartFactory.createBarChart("Ventas Empleado del Mes", "Vendedores", "Ventas", datos1, PlotOrientation.VERTICAL, true, true, true);
                BufferedImage graficoBarra1 = barra1.createBufferedImage(465, 300);
                lbContenedor1.setSize(465, 300);
                lbContenedor1.setIcon(new ImageIcon(graficoBarra1));
                JpEmpleadoMes.updateUI();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBuscarGraf1ActionPerformed

    private void btnEditarUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarUserMouseEntered
        try {
            bordesBoton = new BordesButton(btnEditarUser);
            bordesBoton.encender();
            barraEstado.setText("Editar: Botón que permite al administrador editar UNICAMENTE "
                    + "el permiso del usuario.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarUserMouseEntered

    private void btnEditarUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarUserMouseExited
        try {
            bordesBoton = new BordesButton(btnEditarUser);
            bordesBoton.apagar();
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEditarUserMouseExited

//    Evento que llama a otro jframe EmergenteEdicionUser.
    private void btnEditarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUserActionPerformed
        try {
            String columnas = "nombre";
            String clausula = (String) this.jtUsuarios.getValueAt(this.jtUsuarios.getSelectedRow(), 1);
            con.busqueda("usuarios", "password", columnas, clausula);
            EmergenteEdicionUser emg = new EmergenteEdicionUser(con.registro_busqueda);
            emg.setVisible(true);
            emg.setLocationRelativeTo(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }//GEN-LAST:event_btnEditarUserActionPerformed

    private void panelPuntoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPuntoVentaMouseClicked
        try {
            graficar();
            graficarEmpleadoMes();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_panelPuntoVentaMouseClicked

    private void btnConfiguracionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseEntered
        try {
            icon = new ImageIcon(getClass().getResource(URL + "conf2.png"));
            btnConfiguracion.setIcon(icon);
            barraEstado.setText("Configuración: Botón que permite al administrador insertar un nuevo folio.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnConfiguracionMouseEntered

    private void btnConfiguracionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfiguracionMouseExited
        try {
            icon = new ImageIcon(getClass().getResource(URL + "conf1.png"));
            btnConfiguracion.setIcon(icon);
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnConfiguracionMouseExited

    //    Evento que permite detrminar cuando agregar otros folios.
    //    Llama a otro jframe AdministracionFolios.
    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        try {
            int estado = 0;
            String folio = "";
            String estadoCadena = "";
            con.busquedaID("folios");
            String numeroPrueba = String.valueOf(con.registro_busqueda);
            if ("null".equals(numeroPrueba)) {
                AdministracionFolios emg = new AdministracionFolios();
                emg.setVisible(true);
                emg.setLocationRelativeTo(this);
            } else {
                con.busqueda("folios", "estado", "folio", "1");
                folio = con.registro_busqueda;
                if ("".equals(folio)) {
                    AdministracionFolios emg = new AdministracionFolios();
                    emg.setVisible(true);
                    emg.setLocationRelativeTo(this);
                } else {
                    JOptionPane.showMessageDialog(null, "Aun tiene folios en\nexistencia.");
                }
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnConfiguracionActionPerformed

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

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        try {
            img = new FrameDesign(URL + "buttonClose.png");
            img.addButton(btnCerrar);
            barraEstado.setText("");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        try {
            img = new FrameDesign(URL + "buttonClose1.png");
            img.addButton(btnCerrar);
            barraEstado.setText("Salir: Botón que permite salir de la aplicación Sales Solutions.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseEntered

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

    //    Evento que permite regresar al login.
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            Login login = new Login();
            login.setVisible(true);
            this.hide();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnBackActionPerformed

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

    private void txtEmailEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailEKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                btnAñadir2.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEmailEKeyPressed

    //    Evento que permite buscar folios en la tabla Factura de la BD.
    private void txtBuscarFolioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarFolioKeyPressed
        Date valor = null;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String diseñoTabla = "FOLIO,RFC,TOTAL COMPRA,FECHA,LUGAR";
                String columnas = "folio,subtotal,total,fecha,hora";
                diseñoTablas di = new diseñoTablas(jtFactura, diseñoTabla, "factura", columnas);
                di.listadoBusqueda("folio", txtBuscarFolio.getText().trim().toUpperCase());
                jXDatePicker3.setDate(valor);
                jXDatePicker4.setDate(valor);
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_txtBuscarFolioKeyPressed

    private void btnNotifMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotifMouseEntered
        try {
            if (banderaNotificacion == 0) {
                icon = new ImageIcon(getClass().getResource(URL + "e2.png"));
                btnNotif.setIcon(icon);
            } else {
                icon = new ImageIcon(getClass().getResource(URL + "no2.png"));
                btnNotif.setIcon(icon);
            }
            barraEstado.setText("Notificación: Permite al administrador visualizar las notificaciones importantes del sistema.");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNotifMouseEntered

    private void btnNotifMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNotifMouseExited
        try {
            if (banderaNotificacion == 0) {
                icon = new ImageIcon(getClass().getResource(URL + "e1.png"));
                btnNotif.setIcon(icon);
            } else {
                icon = new ImageIcon(getClass().getResource(URL + "no1.png"));
                btnNotif.setIcon(icon);
            }
            barraEstado.setText("");

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNotifMouseExited

    //    Evento que permite llamar a otro jframe EmergenteNotificaciones.
    private void btnNotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotifActionPerformed
        try {
            banderaNotificacion = 0;
            icon = new ImageIcon(getClass().getResource(URL + "e1.png"));
            btnNotif.setIcon(icon);
            EmergenteNotificaciones emg = new EmergenteNotificaciones();
            emg.setVisible(true);
            emg.setLocationRelativeTo(this);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNotifActionPerformed

    private void txtEstadoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoEKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            try {
                txtEmailE.requestFocus();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_txtEstadoEKeyPressed

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
    private javax.swing.JPanel JpEmpleadoMes;
    private javax.swing.JPanel JpEstadisticas;
    private javax.swing.JPanel JpFacturas;
    private javax.swing.JPanel JpGraficaEstadistica;
    private javax.swing.JPanel JpListado;
    private javax.swing.JPanel JpListadoCliente;
    private javax.swing.JPanel JpListadoFactura;
    private javax.swing.JPanel JpListadoUsuarios;
    private javax.swing.JPanel JpListadoVentas;
    private javax.swing.JPanel JpUsuarios;
    private javax.swing.JPanel JpVentas;
    private javax.swing.JTable JtArticulos;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JLabel barraFormulario;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarUser;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnAñadir1;
    private javax.swing.JButton btnAñadir2;
    private javax.swing.JButton btnAñadirCliente;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarFactura;
    private javax.swing.JButton btnBuscarGraf;
    private javax.swing.JButton btnBuscarGraf1;
    private javax.swing.JButton btnBuscarUser;
    private javax.swing.JButton btnBuscarVentas;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarUser;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarUser;
    private javax.swing.JButton btnF5;
    private javax.swing.JButton btnF6;
    private javax.swing.JButton btnInf;
    private javax.swing.JButton btnLista;
    private javax.swing.JButton btnListaCliente;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnNotif;
    private javax.swing.JComboBox cbxPermiso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker3;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker4;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker7;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker8;
    private com.toedter.calendar.JYearChooser jYear;
    private javax.swing.JTable jtCliente;
    private javax.swing.JTable jtFactura;
    private javax.swing.JTable jtUsuarios;
    private javax.swing.JTable jtVentas;
    private javax.swing.JLabel lbAdmin;
    private javax.swing.JLabel lbAdmin1;
    private javax.swing.JLabel lbCiudad;
    private javax.swing.JLabel lbContenedor;
    private javax.swing.JLabel lbContenedor1;
    private javax.swing.JLabel lbDomicilio;
    private javax.swing.JLabel lbEmailk;
    private javax.swing.JLabel lbEmpresa;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbEstado1;
    private javax.swing.JLabel lbFin;
    private javax.swing.JLabel lbFin1;
    private javax.swing.JLabel lbInicio;
    private javax.swing.JLabel lbInicio1;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNotas;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbRfc;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JLabel menuArticulo;
    private javax.swing.JLabel menuCliente;
    private javax.swing.JLabel menuFactura;
    private javax.swing.JLabel menuUsuarios;
    private javax.swing.JLabel menuVentas;
    private javax.swing.JTextArea notas;
    private javax.swing.JTabbedPane panelPuntoVenta;
    private javax.swing.JScrollPane scrolArticulos;
    private javax.swing.JScrollPane scrolCliente;
    private javax.swing.JScrollPane scrolFactura;
    private javax.swing.JScrollPane scrolUsuarios;
    private javax.swing.JScrollPane scrolVentas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarFolio;
    private javax.swing.JTextField txtBuscarUsers;
    private javax.swing.JTextField txtBuscarVendedor;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCiudadE;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigoE;
    private javax.swing.JTextField txtCorreo;
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
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioE;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtProductoE;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtRfcE;
    private javax.swing.JTextField txtUnidad;
    private javax.swing.JTextField txtUnidadE;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
