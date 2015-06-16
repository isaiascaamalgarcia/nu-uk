/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notificaciones;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ModelDB.conexion;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  Clase que permite dar formato a la tabla de notificaciones.
public class BdTabla {

    private JTable tabla;
    private String diseñoTabla = "";
    private String nombreTabla = "";
    private String columnas = "";
    private String[] campos;
    private int max = 0;
    private String nombretabla;
    private conexion con = new conexion();

//    Celdas no editables.
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

//    Construcor: Tiene como parametro la tabla a la que se le aplicara la acción.
    public BdTabla(JTable tabla) {
        this.tabla = tabla;
        diseñoTabla = "ARTICULOS POR AGOTARSE";
        nombreTabla = "articulos";
        columnas = "codigo";
        diseñoTablas(tabla, diseñoTabla, nombreTabla, columnas);
    }

//  Lso parametros son los enviados desde el constructor.
//  Da formato a la tabla con las columnas correspondientes.
//  Llama al método reiniciarJtable.
    public void diseñoTablas(JTable tabla, String cadena, String nombreTabla, String columnas) {
        int i = 0;
        campos = cadena.split(",");
        max = campos.length;
        this.tabla = tabla;
        this.nombretabla = nombreTabla;
        this.columnas = columnas;
        for (i = 0; i < max; i++) {
            modelo.addColumn(campos[i]);
            this.tabla.setModel(modelo);
        }
        reiniciarJtable();
    }

//    Limpia la tabla y llena nuevamente la tabla con la información tomada de la consulta a la BD.
    public void reiniciarJtable() {
        int max = 0;
        String[] cadena;
        String[] Datos = new String[1];
        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        con.busquedaEsp(nombretabla, columnas);
        cadena = con.registro_busqueda.split(",");
        max = cadena.length;
        Datos = new String[max];

        for (int j = 0; j < max; j++) {
            System.out.println(cadena[j] + " " + j);
            Datos[j] = cadena[j];
            modelo.addRow(Datos);
        }
    }

}
