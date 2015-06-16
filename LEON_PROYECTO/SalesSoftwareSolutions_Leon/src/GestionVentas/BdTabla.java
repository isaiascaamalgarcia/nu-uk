/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVentas;

import ModeloTablas.diseñoTablas;
import javax.swing.JTable;

/**
 *
 * @author Shary
 */
public class BdTabla {

    private JTable tabla;
    private String diseñoTabla = "";
    private String nombreTabla = "";
    private String columnas = "";

    public BdTabla(JTable tabla) {
        this.tabla = tabla;
        diseñoTabla = "VENDEDOR,TOTAL VENTA,FECHA,HORA,FACTURA";
        nombreTabla = "ventas";
        columnas = "vendedor,total,fecha,hora,factura";
        diseñoTablas tabla1 = new diseñoTablas(tabla, diseñoTabla, nombreTabla, columnas);
    }

}
