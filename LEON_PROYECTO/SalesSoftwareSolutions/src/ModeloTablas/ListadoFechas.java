/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloTablas;

import ModelDB.conexion;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  Clase donde se lista la información que se encuentra en la BD.
public class ListadoFechas {

    private String tabla;
    private String columnas;
    private int max;
    private String[] columns;
    private String fechaInicio;
    private String fechaFin;
    private String campoFecha;
    private ModelDB.conexion con = new conexion();

//  Constructor: Tiene como parametros el  nombre de la tabla,
//  las columnas, los campos de fecha  para la consulta mas especifica (con clausula) en la BD.
    public ListadoFechas(String tabla, String columnas, String campoFecha, String fechaInicio, String fechaFin) {
        this.tabla = tabla;
        this.columnas = columnas;
        this.campoFecha = campoFecha;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        columns = columnas.split(",");
        max = columns.length;
    }

//    Obtiene los datos de la BD que se colocaran en el modelo seleccionado (parametro).
    public Object[][] getDatos(DefaultTableModel modelo) {
        int registros = 0;
        PreparedStatement pstm;
//obtenemos la cantidad de registros existentes en la tabla
        try {
            pstm = (PreparedStatement) con.conectar().prepareStatement("SELECT "
                    + "count(1) as total FROM " + tabla + ";");
            try (ResultSet res = pstm.executeQuery()) {
                res.next();
                registros = res.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        Object[][] data = new String[registros][max];
        String[] Datos = new String[max];

//Realizamos la consulta sql y llenamos los datos en "Object"
        try {
            int i = 0, j = 0;
            pstm = (PreparedStatement) con.conectar().prepareStatement("SELECT "
                    + columnas
                    + " FROM " + tabla + " WHERE " + campoFecha + " BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "';");
            ResultSet res = pstm.executeQuery();

            while (res.next()) {

                for (j = 0; j < max; j++) {
                    String estCampo = res.getString(columns[j]);
                    data[i][j] = estCampo;
                    Datos[j] = (String) data[i][j];
                }

                modelo.addRow(Datos);

                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
}
