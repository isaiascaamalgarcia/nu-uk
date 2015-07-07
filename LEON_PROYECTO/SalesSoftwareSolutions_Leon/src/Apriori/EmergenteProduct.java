/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apriori;

import PuntodeVenta.*;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import DesignSoftware.FrameDesign;
import ModelDB.conexion;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  Clase que permite visualizar la informacion del usuario seleccionado.
public class EmergenteProduct extends javax.swing.JFrame {

    private int x = 0, y = 0;
    private FrameDesign img;
    private String busqueda;
    String URL = "../Images/loginImages/";
    private conexion con= new conexion();
    private String[]list1;
    private String[]list2;
    private int[]list1Aux;
    private int[]list2Aux;
    
 

    /**
     * Creates new form Emergente
     */
//    Constructor: pasa el parametro de busqueda
    public EmergenteProduct() {
        this.getContentPane().setBackground(Color.WHITE);
        initComponents();
        cargar();
        
    }

//    Carga diseño a los botones.
    public void cargar() {
        img = new FrameDesign(URL + "buttonMinimize.png");
        img.addButton(btnMinimizar);
        img = new FrameDesign(URL + "buttonClose.png");
        img.addButton(btnCerrar);
        llenar();
    }
//Método que llena los campos de texto con la busqueda.

    public void llenar() {
        String numero,datos;
        con.conteo("articulos");
        numero=con.registro_busqueda;
        System.out.println(numero);
        con.busquedaG("articulos", "existencia");
        datos=con.registro_busqueda;
        System.out.println(datos);
        AprioriSearchProduct apriori = new AprioriSearchProduct(numero, datos);
        list1=apriori.listado.split(",");
        list2=apriori.listado1.split(",");  
        convercionEntero();     
        burbuja(list2Aux,list1Aux);
    }
    
    public void convercionEntero()
    {
        list1Aux=new int[list1.length];
        list2Aux=new int[list1.length];
        for(int i=0;i<list1.length;i++)
        {
            list1Aux[i]=Integer.parseInt(list1[i]);
            list2Aux[i]=Integer.parseInt(list2[i]);
            //System.out.println(list1Aux[i]+" "+ list2Aux[i]);
        }
        //seleccion(list2Aux,list1Aux);      
    }
    
   public void burbuja(int arreglo[],int arreglo1[])
    {
        for(int i = 0; i < arreglo.length - 1; i++)
        {
            for(int j = 0; j < arreglo.length - 1; j++)
            {
                if (arreglo[j] < arreglo[j + 1])
                {
                    int tmp = arreglo[j+1];
                    arreglo[j+1] = arreglo[j];
                    arreglo[j] = tmp;
                    
                    int tmp2 = arreglo1[j+1];
                    arreglo1[j+1] = arreglo1[j];
                    arreglo1[j]=tmp2;
                }
            }
        }
        for(int i = 0;i < arreglo.length; i++)
        {
            System.out.print(arreglo[i]+"/"+arreglo1[i]+"\n");
        }
        con.like(String.valueOf(arreglo1[0]));
        String valor=con.registro_busqueda;
        System.out.println(valor);
        String listaLista[] = valor.split(",");
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);
        for(int i = 0; i<listaLista.length;i++){
            model.addElement(listaLista[i]);
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

        btnMinimizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        menuUsuarios = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        getContentPane().add(btnMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 32, 32));

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
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 32, 32));

        menuUsuarios.setBackground(new java.awt.Color(116, 16, 30));
        menuUsuarios.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        menuUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        menuUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setText("PRODUCTOS MENOS VENDIDOS");
        menuUsuarios.setDoubleBuffered(true);
        menuUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuUsuarios.setOpaque(true);
        menuUsuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                menuUsuariosMouseDragged(evt);
            }
        });
        menuUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuUsuariosMousePressed(evt);
            }
        });
        getContentPane().add(menuUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 400, 20));

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 400, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUsuariosMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_menuUsuariosMousePressed

    private void menuUsuariosMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUsuariosMouseDragged
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_menuUsuariosMouseDragged

    private void btnMinimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseEntered
        try {
            img = new FrameDesign(URL + "buttonMinimize1.png");
            img.addButton(btnMinimizar);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnMinimizarMouseEntered

    private void btnMinimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseExited
        try {
            img = new FrameDesign(URL + "buttonMinimize.png");
            img.addButton(btnMinimizar);

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

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        try {
            img = new FrameDesign(URL + "buttonClose.png");
            img.addButton(btnCerrar);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.hide();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel menuUsuarios;
    // End of variables declaration//GEN-END:variables
}