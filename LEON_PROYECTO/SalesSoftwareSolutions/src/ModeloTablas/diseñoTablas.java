/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTablas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  Clase que permite dar formato a las tablas de la aplicación.
public class diseñoTablas {

//    Se crea el modelo para las tablas, este es No editable.
    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable tabla;
    private String[] campos;
    private int max = 0;
    private String nombretabla;
    private String columnas;

//    Constructor: Tiene como parámetros la tabla, las campos que lo conforman, el nombre de la tabla, y las columnas.
//    Asigna el modelo a la tabla con las columnas que se requieren.
    public diseñoTablas(JTable tabla, String cadena, String nombreTabla, String columnas) {
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

//    Se limpia la tabla enviada como parametro y se rellena buevamente con la clase de listado.
    public void reiniciarJtable() {
        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        Listado l = new Listado(nombretabla, columnas);
        l.getDatos(modelo);
    }

    //    Se limpia la tabla enviada como parametro y se rellena buevamente con la clase de listadoFechas.
    public void listadoFechas(String campoFecha, String fechaInicio, String fechaFin) {
        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        System.out.println("Entrando Fecha");
        ListadoFechas li = new ListadoFechas(nombretabla, columnas, campoFecha, fechaInicio, fechaFin);
        li.getDatos(modelo);
        System.out.println("Saliendo Fecha");
    }

    //    Se limpia la tabla enviada como parametro y se rellena buevamente con la clase de listadoBusqueda.
    public void listadoBusqueda(String campoBusqueda, String valorBusqueda) {
        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        System.out.println("Entrando campoBuqueda");
        ListadoBusqueda li = new ListadoBusqueda(nombretabla, columnas, campoBusqueda, valorBusqueda);
        li.getDatos(modelo);
        System.out.println("Saliendo campoBuqueda");
    }

    //    Se limpia la tabla enviada como parametro y se rellena buevamente con la clase de listadoFechasCampo.
    public void listadoFechasCampo(String campoFecha, String campoNombre, String fechaInicio, String fechaFin, String campo) {
        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        System.out.println("Entrando Fecha & Busqueda");
        ListadoFechasCampo li = new ListadoFechasCampo(nombretabla, columnas, campoFecha, campoNombre, fechaInicio, fechaFin, campo);
        li.getDatos(modelo);
        System.out.println("Saliendo Fecha & Busqueda");
    }

}
