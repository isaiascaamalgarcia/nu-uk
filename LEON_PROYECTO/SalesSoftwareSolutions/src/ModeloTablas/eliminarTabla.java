/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTablas;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import ModelDB.conexion;
import javax.swing.JOptionPane;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  Clase que permite eliminar datos de la tabla seleccionada.
public class eliminarTabla {

    private JTable tabla;
    private DefaultTableModel modelo;
    private conexion con = new conexion();

//    Constructot: Tiene como parametros la tabla sobre la cual se aplicará la acción,
//    El numero de columna para determinar el valor de registro, el nombre de la tabla y la clausula correspondiente
//    para eliminar de la BD.
    public eliminarTabla(JTable tabla, int col, String nombreTabla, String clausula) {
        this.tabla = tabla;
        try {
            String registro;
            registro = (String) this.tabla.getValueAt(this.tabla.getSelectedRow(), col);
            modelo = (DefaultTableModel) this.tabla.getModel();
            modelo.removeRow(this.tabla.getSelectedRow());
            con.eliminar(nombreTabla, clausula + registro + "';");
            JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione fila");
        }
    }

}
