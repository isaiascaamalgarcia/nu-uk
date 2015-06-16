package ModelDB;

import java.sql.*;
import Configuracion.variablesGenerales;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//La principal funcion de esta clase es realizar la conexion con la BD.
public class conexion {

    Connection con = null;
    variablesGenerales vg = new variablesGenerales();
    String bd, user, pass;
    public String registro_busqueda = "";
    public int registro_busquedaInt = 0;

//    Constructor e inicializacion de variables.
    public conexion() {
        bd = vg.baseDeDatos;
        user = vg.usuarioMysql;
        pass = vg.passMysql;
    }

//    Metodo que realiza la conexion con la BD.
    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + bd, user, pass);
            if (con != null) {
                System.out.println("**Conectados");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion:\n" + e);
        }
        return con;
    }

//    Desconecto con el gestor de la BD.
    public void desconectar() {
        con = null;
        System.out.println("**Desconectado");
    }

    // Recibe el nombre de la tabla, los campos a tratar, y los valores a agregar
    public boolean agregar(String tabla, String campos, String valores) {
        System.out.println("Entramos a agregar");
        String query = "INSERT INTO " + tabla;
        String[] camp = campos.split(",");
        int max = camp.length;

        query += campos(max, camp);
        System.out.println("Despues de query +  campos");
        query += valores(max);
        System.out.println(query);
        if (prepararEstados(query, valores)) {
            //Devuelve verdadero cuando ha surgido algun error
            System.out.println("agregar falso");
            return false;
        } else {
            //Devuelve falso cuando todo ha salido bien
            System.out.println("agregar verdadero");
            return true;
        }
    }

    //Recibe el nombre de la tabla,los campos a tratar,los valores a actualiza y la clausula a cumplir
    public boolean actualizar(String tabla, String campos,
            String valores, String clausula) {
        String[] camp;
        int max;
        camp = campos.split(",");
        max = camp.length;
        String c;
        String query = "UPDATE " + tabla + " SET ", aux = "";
        if (clausula == null) {
            c = "1";
        } else {
            c = clausula;
        }
        for (int i = 0; i < max; i++) {
            aux += camp[i] + "=?";
            if (i != max - 1) {
                aux += ",";
            }
        }
        query += aux + " WHERE " + c;

        if (prepararEstados(query, valores)) {
            //Devuelve verdadero cuando ha surgido algun error
            System.out.println("actualizar falso");
            return false;
        } else {
            //Devuelve falso cuando todo ha salido bien
            System.out.println("actualizar verdadero");
            return true;
        }
    }

    // Recibe el nombre de la tabla y la clausula que cumple que row eliminar
    public boolean eliminar(String tabla, String clausula) {
        String query = "DELETE FROM " + tabla + " WHERE " + clausula;
        if (prepararEstados(query, null)) {
            //Devuelve verdadero cuando ha surgido algun error
            System.out.println("eliminar falso");
            return false;
        } else {
            //Devuelve falso cuando todo ha salido bien
            System.out.println("eliminar verdadero");
            return true;
        }
    }
    
    public void like(String condicion)
    {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT nombre AS N FROM articulos WHERE existencia LIKE '%"+condicion+ "%'" + ";");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                registro_busqueda += res.getString("N") + ",";
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la BusquedaG:\n" + e);
        }
    }

    public String[][] leerDatos(String tabla) {
        int rows, columns;
        if ((rows = getRows(tabla)) == -1) {
            return null;
        }
        if ((columns = getColumns(tabla)) == -1) {
            return null;
        }
        String[][] data = new String[rows][columns];
        String[] columnas = getColumnsNames(tabla);
        String query = "SELECT * FROM " + tabla;
        ResultSet res;

        try {
            if ((res = prepararEstados(query)) == null) {
                return null;
            }
            int i = 0;
            while (res.next()) {
                for (int j = 0; j < columns; j++) {
                    data[i][j] = res.getString(columnas[j]);
                }
                i++;
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas en leerDatos(tabla):\n" + e);
            return null;
        }
        return data;
    }

//    Lee los datos que contienen las tablas.
    public String[][] leerDatos(String tabla, String campos, String condicion) {
        String query;
        if (condicion == null) {
            condicion = "1";
        }
        int rows, columns;
        if ((rows = getRows(tabla)) == -1) {
            return null;
        }

        String[] columnas = campos.split(",");
        columns = columnas.length;
        String[][] data = new String[rows][columns];
        query = "SELECT " + campos + " FROM " + tabla + " WHERE " + condicion;

        ResultSet res;
        try {
            if ((res = prepararEstados(query)) == null) {
                return null;
            }
            int i = 0;
            while (res.next()) {
                for (int j = 0; j < columns; j++) {
                    data[i][j] = res.getString(columnas[j]);
                }
                i++;
            }
            if (data[0][0] == null) {
                return null;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println("Problemas en leerDatos(campos,tabla,condicion: ):\n" + e);
            return null;
        }
        return data;
    }

    // Recoje el query predesarrollado y los valores a manejar en una query de mysql
    // y posteriormente los ejecuta.
    // Si values viene como null, esta echo para ELIMINAR
    private boolean prepararEstados(String query, String values) {
        PreparedStatement pstm;
        boolean r = true;
        String[] val = null;
        int max;
        if (values != null) {
            val = values.split(",");
            max = val.length;
            try {
                pstm = (PreparedStatement) conectar().prepareStatement(query);
                for (int i = 0; i < max; i++) {
                    pstm.setString(i + 1, val[i]);
                }
                r = pstm.execute();
                pstm.close();
            } catch (Exception e) {
                System.out.println("Problemas de sincronizacion con la base de datos:\n" + e);
            }
        } else {
            try {
                pstm = (PreparedStatement) conectar().prepareStatement(query);
                r = pstm.execute();
                pstm.close();
            } catch (Exception e) {
                System.out.println("Problemas al preparar estados:\n" + e);
            }
        }
        desconectar();
        return r;
    }

    private ResultSet prepararEstados(String query) {
        ResultSet res = null;
        try {
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement(query);
            res = pstm.executeQuery();
        } catch (SQLException e) {
            System.out.println("Problemas al preparar estados en leerDatos:\n" + e);
        }
        desconectar();
        return res;
    }

    private String campos(int max, String[] camp) {
        String str = "(";
        for (int i = 0; i < max; i++) {
            str += camp[i];
            if (i != max - 1) {
                str += ",";
            }
        }
        return str;
    }

    private String valores(int max) {
        String str = ") VALUES (";
        for (int i = 0; i < max; i++) {
            str += "?";
            if (i != max - 1) {
                str += ",";
            }
        }
        str += ")";
        return str;
    }

    private int getRows(String tabla) {
        PreparedStatement pstm;
        int rows = -1;
        try {
            pstm = (PreparedStatement) conectar().prepareStatement("SELECT count(1) as total FROM " + tabla);
            ResultSet res = pstm.executeQuery();
            res.next();
            rows = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener numero de Filas: " + e);
        }
        return rows;
    }

    private int getColumns(String tabla) {
        int columns = -1;
        String query;
        query = "SELECT Count(*) as total FROM INFORMATION_SCHEMA.Columns where "
                + "TABLE_NAME='" + tabla + "' AND table_schema='" + vg.baseDeDatos + "'  GROUP BY column_default";
        try {
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            res.next();
            columns = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println("Problemas al obtener el numero de columnas:\n" + e);
        }
        return columns;
    }

    private String[] getColumnsNames(String tabla) {
        String[] columns = new String[getColumns(tabla)];
        String query = "SELECT column_name FROM information_schema.columns WHERE table_name='" + tabla + "' AND table_schema='" + vg.baseDeDatos + "' GROUP BY column_name ORDER BY column_default";
        try {
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement(query);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                columns[i] = res.getString("column_name");
                i++;
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener el nombre de las columnas:\n" + e);
        }
        return columns;
    }

//    Realiza busquedas en la BD con la informacion proporcionada y clausula.
    public void busqueda(String tabla, String campoBusqueda, String campos, String clausula) {
        try {
            String[] columnas = campos.split(",");
            int max = columnas.length;
            registro_busqueda = "";

            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT " + campos + " FROM " + tabla + " WHERE " + campoBusqueda + "= '" + clausula + "';");
            ResultSet res = pstm.executeQuery();
            res.next();
            for (int i = 0; i < max; i++) {
                if (i < max - 1) {
                    registro_busqueda += res.getString(columnas[i]) + ",";
                } else if (i == max - 1) {
                    registro_busqueda += res.getString(columnas[i]);
                }
            }
            res.close();
        } catch (Exception e) {
//            System.out.println("Problemas al obtener la informacion en la Busqueda:\n" + e);
        }
    }

    //    Realiza varias busquedas en la BD con la informacion proporcionada y clausula.
    public void busqueda1(String tabla, String campoBusqueda, String campos, String clausula) {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT " + campos + " FROM " + tabla + " WHERE " + campoBusqueda + "= '" + clausula + "';");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                registro_busqueda += res.getString(campos) + ",";
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la Busqueda1:\n" + e);
        }
    }

    //    Realiza busquedas generales en la BD con la informacion proporcionada no tiene una clausula.
    public void busquedaG(String tabla, String campos) {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT " + campos + " AS A FROM " + tabla + ";");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                registro_busqueda += res.getString("A") + ",";
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la BusquedaG:\n" + e);
        }
    }

    //    Realiza busquedas en la BD con la informacion proporcionada, método generado especialmente para las notificaciones.
    public void busquedaEsp(String tabla, String campo) {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT " + campo + " AS COD FROM " + tabla + " WHERE existencia <5;");
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                registro_busqueda += res.getString("COD") + ",";
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la BusquedaEsp:\n" + e);
        }
    }

    //    Realiza busquedas del ID de cualquier tabla en la BD.
    public void busquedaID(String tabla) {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("SELECT MAX(id) AS ID FROM " + tabla + ";");
            ResultSet res = pstm.executeQuery();
            res.next();
            registro_busqueda = res.getString("ID");
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la BusquedaID:\n" + e);
        }
    }
    
    public void conteo(String tabla)
    {
        try {
            registro_busqueda = "";
            PreparedStatement pstm = (PreparedStatement) conectar().prepareStatement("select count(*) AS CNT from " + tabla + ";");
            ResultSet res = pstm.executeQuery();
            res.next();
            registro_busqueda = res.getString("CNT");
            
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener la informacion en la BusquedaID:\n" + e);
        }
        
    }
    

//    Realiza busquedas en la BD con la informacion proporcionada, este método fue especialmente generado.
//    Para la sección de estadisticas de la aplicacion, ya que estas busquedas contienen clausulas de fechas.
    public void busquedaGraficas(String tabla, String campos, String campoFecha, String fechaInicio,
            String fechaFin, String vendedor, int seleccion, int intFecha, int anio) {
        PreparedStatement pstm = null;
        try {
            String[] columnas = campos.split(",");
            int max = columnas.length;
            registro_busqueda = "";
            if (seleccion == 2) {
                pstm = (PreparedStatement) conectar().prepareStatement("SELECT SUM(" + campos + ") AS T FROM " + tabla
                        + " WHERE MONTH(fecha)=" + intFecha + " AND YEAR(fecha)=" + anio + ";");
                System.out.println("Opcion 2");
            } else if (seleccion == 1) {
                pstm = (PreparedStatement) conectar().prepareStatement("SELECT SUM(" + campos + ") AS T FROM " + tabla + " WHERE " + campoFecha + " BETWEEN '" + fechaInicio
                        + "' AND '" + fechaFin + "' AND vendedor='" + vendedor + "';");
            } else {
                pstm = (PreparedStatement) conectar().prepareStatement("SELECT SUM(" + campos + ") AS T FROM " + tabla + " WHERE vendedor='" + vendedor + "';");
            }
            ResultSet res = pstm.executeQuery();
            while (res.next()) {
                registro_busqueda += res.getString("T");
            }
            res.close();
        } catch (Exception e) {
            System.out.println("Problemas al obtener el nombre de las columnas:\n" + e);
        }
    }
}
