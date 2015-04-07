package com.example.nuuk.nuukappmobile.SQLite;

/**
 * Created by Shary on 06/04/2015.
 */
public class Variables {
    //Tabla Municipio
    private int idMunicipio;
    private String municipio;
    private String cabecera;

    //Tabla Localidad
    private int idLocalidad;
    private String localidad;
    private int idMunicipio_localidad;

    //Tabla Escuela
    private int idEscuela;
    private int tipoEscuela;
    private String nombreEscuela;
    private String direccionEscuela;
    private String latitudEscuela;
    private String longitudEscuela;
    private String telefonoEscuela;
    private String paginaEscuela;
    private String correoEscuela;
    private String facebookEscuela;
    private String twitterEscuela;
    private String sectorEscuela;
    private int modificacionEscuela;
    private int idLocalidadEscuela;
    private int estadoEscuela;

    //Tabla relacion_escuela
    private int idRelacionEscuela;
    private int idEscuela_RelacionEscuela;
    private int idCarrera_RelacionEscuela;

    //Tabla carrera
    private int idCarrera;
    private String Carrera;
    private String tipoCarrera;

    //Tabla tipo
    private String tipo;
    private String nombreTipo;

    //Tabla encuesta
    private int idEncuesta;
    private String preguntaEncuesta;
    private String tipoCarreraEncuesta;

    //Tabla resultado_sugerencia
    private int idResultado;
    private String curpResultado;
    private int idCarreraResultado;

    //Tabla usuario
    private String curpUsuario;
    private String direccionUsuario;

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getIdMunicipio_localidad() {
        return idMunicipio_localidad;
    }

    public void setIdMunicipio_localidad(int idMunicipio_localidad) {
        this.idMunicipio_localidad = idMunicipio_localidad;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getTipoEscuela() {
        return tipoEscuela;
    }

    public void setTipoEscuela(int tipoEscuela) {
        this.tipoEscuela = tipoEscuela;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public String getDireccionEscuela() {
        return direccionEscuela;
    }

    public void setDireccionEscuela(String direccionEscuela) {
        this.direccionEscuela = direccionEscuela;
    }

    public String getLatitudEscuela() {
        return latitudEscuela;
    }

    public void setLatitudEscuela(String latitudEscuela) {
        this.latitudEscuela = latitudEscuela;
    }

    public String getLongitudEscuela() {
        return longitudEscuela;
    }

    public void setLongitudEscuela(String longitudEscuela) {
        this.longitudEscuela = longitudEscuela;
    }

    public String getTelefonoEscuela() {
        return telefonoEscuela;
    }

    public void setTelefonoEscuela(String telefonoEscuela) {
        this.telefonoEscuela = telefonoEscuela;
    }

    public String getPaginaEscuela() {
        return paginaEscuela;
    }

    public void setPaginaEscuela(String paginaEscuela) {
        this.paginaEscuela = paginaEscuela;
    }

    public String getCorreoEscuela() {
        return correoEscuela;
    }

    public void setCorreoEscuela(String correoEscuela) {
        this.correoEscuela = correoEscuela;
    }

    public String getFacebookEscuela() {
        return facebookEscuela;
    }

    public void setFacebookEscuela(String facebookEscuela) {
        this.facebookEscuela = facebookEscuela;
    }

    public String getTwitterEscuela() {
        return twitterEscuela;
    }

    public void setTwitterEscuela(String twitterEscuela) {
        this.twitterEscuela = twitterEscuela;
    }

    public String getSectorEscuela() {
        return sectorEscuela;
    }

    public void setSectorEscuela(String sectorEscuela) {
        this.sectorEscuela = sectorEscuela;
    }

    public int getModificacionEscuela() {
        return modificacionEscuela;
    }

    public void setModificacionEscuela(int modificacionEscuela) {
        this.modificacionEscuela = modificacionEscuela;
    }

    public int getIdLocalidadEscuela() {
        return idLocalidadEscuela;
    }

    public void setIdLocalidadEscuela(int idLocalidadEscuela) {
        this.idLocalidadEscuela = idLocalidadEscuela;
    }

    public int getEstadoEscuela() {
        return estadoEscuela;
    }

    public void setEstadoEscuela(int estadoEscuela) {
        this.estadoEscuela = estadoEscuela;
    }

    public int getIdRelacionEscuela() {
        return idRelacionEscuela;
    }

    public void setIdRelacionEscuela(int idRelacionEscuela) {
        this.idRelacionEscuela = idRelacionEscuela;
    }

    public int getIdEscuela_RelacionEscuela() {
        return idEscuela_RelacionEscuela;
    }

    public void setIdEscuela_RelacionEscuela(int idEscuela_RelacionEscuela) {
        this.idEscuela_RelacionEscuela = idEscuela_RelacionEscuela;
    }

    public int getIdCarrera_RelacionEscuela() {
        return idCarrera_RelacionEscuela;
    }

    public void setIdCarrera_RelacionEscuela(int idCarrera_RelacionEscuela) {
        this.idCarrera_RelacionEscuela = idCarrera_RelacionEscuela;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String carrera) {
        Carrera = carrera;
    }

    public String getTipoCarrera() {
        return tipoCarrera;
    }

    public void setTipoCarrera(String tipoCarrera) {
        this.tipoCarrera = tipoCarrera;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getPreguntaEncuesta() {
        return preguntaEncuesta;
    }

    public void setPreguntaEncuesta(String preguntaEncuesta) {
        this.preguntaEncuesta = preguntaEncuesta;
    }

    public String getTipoCarreraEncuesta() {
        return tipoCarreraEncuesta;
    }

    public void setTipoCarreraEncuesta(String tipoCarreraEncuesta) {
        this.tipoCarreraEncuesta = tipoCarreraEncuesta;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getCurpResultado() {
        return curpResultado;
    }

    public void setCurpResultado(String curpResultado) {
        this.curpResultado = curpResultado;
    }

    public int getIdCarreraResultado() {
        return idCarreraResultado;
    }

    public void setIdCarreraResultado(int idCarreraResultado) {
        this.idCarreraResultado = idCarreraResultado;
    }

    public String getCurpUsuario() {
        return curpUsuario;
    }

    public void setCurpUsuario(String curpUsuario) {
        this.curpUsuario = curpUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }
}
