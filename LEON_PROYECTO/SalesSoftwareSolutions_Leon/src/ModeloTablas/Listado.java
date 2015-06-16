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
public class Listado {

    private String tabla;
    private String columnas;
    private int max;
    private String[] columns;
    private ModelDB.conexion con = new conexion();
    
//  Constructor: Tiene como parametros el  nombre de la tabla y las columnas que se requieren para la consulta general.
    public Listado(String tabla, String columnas) {
        this.tabla = tabla;
        this.columnas = columnas;
        columns = columnas.split(",");
        max = columns.length;

    }
    
//    Obtiene los datos de la BD que se colocaran en el modelo seleccionado (parametro).
    public Object[][] getDatos(DefaultTableModel modelo) {
        int registros = 0;
        PreparedStatement pstm;
//  obtenemos la cantidad de registros existentes en la tabla
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

//  Realizamos la consulta sql y llenamos los datos en "Object"
        try {
            int i = 0, j = 0;
            pstm = (PreparedStatement) con.conectar().prepareStatement("SELECT "
                    + columnas
                    + " FROM " + tabla + ";");
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
