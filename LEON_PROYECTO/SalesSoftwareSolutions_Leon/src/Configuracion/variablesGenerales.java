package Configuracion;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */

//Variables para la conexion a la base de datos.
public class variablesGenerales {

    public String baseDeDatos = "factura";
    public String usuarioMysql = "root";
    public String passMysql = "softshary";

    private String registroCliente = "";

    /**
     * @return the registroCliente
     */
    public String getRegistroCliente() {
        return registroCliente;
    }

    /**
     * @param registroCliente the registroCliente to set
     */
    public void setRegistroCliente(String registroCliente) {
        this.registroCliente = registroCliente;
    }
}
