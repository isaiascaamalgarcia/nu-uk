package ModelDB;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//  La función principal de la clase es determinar los permisos de usuarios que acceden al sistema.
public class sesion {

    private String User;
    private String Pass;
    private String Permissions;
    private String Access;
    conexion con;

//    Se tienen los parametros de usuarios y contraseña, se hace la consulta a la base de datos y
//    se determina si existe el usuario o no, y que permiso tiene.
    public sesion(String user, String pass) {
        con = new conexion();
        String[][] data = null;
        if ((data = con.leerDatos("usuarios", "usuario,password,permiso", "usuario='" + user + "'"
                + "and password='" + pass + "'")) == null) {
            Access = User = Pass = Permissions = "Acceso Denegado";
        } else {
            Access = "Acceso Concedido";
            User = data[0][0];
            Pass = data[0][1];
            Permissions = data[0][2];
        }
    }

    public String getAcceso() {
        return Access;
    }

    public String getPermisos() {
        return Permissions;
    }

    public String getUsuario() {
        return User;
    }

    public String getPassword() {
        return Pass;
    }
}
