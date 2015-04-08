package com.example.nuuk.nuukappmobile.NuukClass;

/**
 * Created by Shary on 08/04/2015.
 */
public class ColumnsTables {
    private String[] tableCarrera               = {"id", "carrera","tipoCarrera"};
    private String[] tableEncuesta              = {"id", "pregunta","tipoCarrera"};
    private String[] tableEscuela               = {"id", "tipo", "nombre", "direccion" ,"latitud", "longitud",
            "telefono" , "pagina", "correo", "facebook", "twitter", "sector", "modificacion", "idLocalidad", "estado"};
    private String[] tableMunicipio={"id","municipio","cabecera"};
    private String[] tableLocalidad             = {"id", "localidad", "idMunicipio"};
    private String[] tableRelacionEscuela       = {"id", "idEscuela", "idCarrera"};
    private String[] tableResultado_Sugerencias = {"id", "curp", "idCarrera"};
    private String[] tableTipo                  = {"tipo", "nombre"};
    private String[] tableUsuario               = {"curp", "direccion"};

    public String[] getTableCarrera() {
        return tableCarrera;
    }

    public void setTableCarrera(String[] tableCarrera) {
        this.tableCarrera = tableCarrera;
    }

    public String[] getTableEncuesta() {
        return tableEncuesta;
    }

    public void setTableEncuesta(String[] tableEncuesta) {
        this.tableEncuesta = tableEncuesta;
    }

    public String[] getTableEscuela() {
        return tableEscuela;
    }

    public void setTableEscuela(String[] tableEscuela) {
        this.tableEscuela = tableEscuela;
    }

    public String[] getTableMunicipio() {
        return tableMunicipio;
    }

    public void setTableMunicipio(String[] tableMunicipio) {
        this.tableMunicipio = tableMunicipio;
    }

    public String[] getTableLocalidad() {
        return tableLocalidad;
    }

    public void setTableLocalidad(String[] tableLocalidad) {
        this.tableLocalidad = tableLocalidad;
    }

    public String[] getTableRelacionEscuela() {
        return tableRelacionEscuela;
    }

    public void setTableRelacionEscuela(String[] tableRelacionEscuela) {
        this.tableRelacionEscuela = tableRelacionEscuela;
    }

    public String[] getTableResultado_Sugerencias() {
        return tableResultado_Sugerencias;
    }

    public void setTableResultado_Sugerencias(String[] tableResultado_Sugerencias) {
        this.tableResultado_Sugerencias = tableResultado_Sugerencias;
    }

    public String[] getTableTipo() {
        return tableTipo;
    }

    public void setTableTipo(String[] tableTipo) {
        this.tableTipo = tableTipo;
    }

    public String[] getTableUsuario() {
        return tableUsuario;
    }

    public void setTableUsuario(String[] tableUsuario) {
        this.tableUsuario = tableUsuario;
    }
}
