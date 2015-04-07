package com.example.nuuk.nuukappmobile.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shary on 17/03/2015.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "nuuk";

    private static final String TABLE_MUNICIPIO = "municipio";
        private static final String MUNICIPIO_ID = "id";
        private static final String MUNICIPIO_MUN = "municipio";
        private static final String MUNICIPIO_CABECERA = "cabecera";

    private static final String TABLE_LOCALIDAD = "localidad";
        private static final String LOCALIDAD_ID = "id";
        private static final String LOCALIDAD_LOC = "localidad";
        private static final String LOCALIDAD_MUNICIPIO = "idMunicipio";

    private static final String TABLE_ENCUESTA = "encuesta";
        private static final String ENCUESTA_ID = "id";
        private static final String ENCUESTA_PREGUNTA = "pregunta";
        private static final String ENCUESTA_TIPO = "tipoCarrera";

    private static final String TABLE_ESCUELA = "escuela";
        private static final String ESCUELA_ID = "id";
        private static final String ESCUELA_TIPO = "tipo";
        private static final String ESCUELA_NOMBRE = "nombre";
        private static final String ESCUELA_DIRECCION = "direccion";
        private static final String ESCUELA_LATITUD = "latitud";
        private static final String ESCUELA_LONGITUD = "longitud";
        private static final String ESCUELA_TELEFONO = "telefono";
        private static final String ESCUELA_PAGINA = "pagina";
        private static final String ESCUELA_CORREO = "correo";
        private static final String ESCUELA_FACEBOOK = "facebook";
        private static final String ESCUELA_TWITTER = "twitter";
        private static final String ESCUELA_SECTOR = "sector";
        private static final String ESCUELA_MODIFICACION = "modificacion";

    private static final String TABLE_CARRERA = "carrera";
        private static final String CARRERA_ID = "id";
        private static final String CARRERA_CARR = "carrera";
        private static final String CARRERA_TIPO = "tipoCarrera";

    private static final String TABLE_RESULTADO_SUG = "resultado_sugerencia";
        private static final String RESULTADO_ID = "id";
        private static final String RESULTADO_CURP = "curp";
        private static final String RESULTADO_IDCARRERA = "idCarrera";

    private static final String TABLE_RELACION_ESC = "relacion_escuela";
        private static final String RELACION_ID = "id";
        private static final String RELACION_IDESCUELA = "idEscuela";
        private static final String RELACION_IDCARRERA = "idCarrera";

    private static final String TABLE_TIPO_CARRERA = "tipo";
        private static final String TIPOCARRERA_TIPO = "tipo";
        private static final String TIPOCARRERA_NOMBRE = "nombre";

    private static final String TABLE_USUARIO = "usuario";
        private static final String USUARIO_CURP = "curp";
        private static final String USUARIO_DIRECCION = "direccion";


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MUNICIPIO_TABLE = "CREATE TABLE " + TABLE_MUNICIPIO + "("
                + MUNICIPIO_ID + " INTEGER PRIMARY KEY," + MUNICIPIO_MUN + " TEXT,"
                + MUNICIPIO_CABECERA + " TEXT" + ")";
        String CREATE_LOCALIDAD_TABLE = "CREATE TABLE " + TABLE_LOCALIDAD + "("
                + LOCALIDAD_ID + " INTEGER PRIMARY KEY," + LOCALIDAD_LOC + " TEXT,"
                + LOCALIDAD_MUNICIPIO + " TEXT" + ")";
        String CREATE_ENCUESTA_TABLE = "CREATE TABLE " + TABLE_ENCUESTA + "("
                + ENCUESTA_ID + " INTEGER PRIMARY KEY," + ENCUESTA_PREGUNTA + " TEXT,"
                + ENCUESTA_TIPO + " TEXT" + ")";
        String CREATE_ESCUELA_TABLE = "CREATE TABLE " + TABLE_ESCUELA + "("
                + ESCUELA_ID + " INTEGER PRIMARY KEY," + ESCUELA_TIPO + " TEXT,"
                + ESCUELA_NOMBRE + " TEXT,"+ESCUELA_DIRECCION +" TEXT,"+ ESCUELA_LATITUD+ " TEXT," +ESCUELA_LONGITUD+ " TEXT,"
                +ESCUELA_TELEFONO+ " TEXT,"+ESCUELA_PAGINA+ " TEXT,"+ESCUELA_CORREO+ " TEXT,"+ESCUELA_FACEBOOK+ " TEXT,"
                +ESCUELA_TWITTER+ " TEXT,"+ESCUELA_SECTOR+ " TEXT,"+ESCUELA_MODIFICACION+ " TEXT"+ ")";
        String CREATE_CARRERA_TABLE = "CREATE TABLE " + TABLE_CARRERA + "("
                + CARRERA_ID + " INTEGER PRIMARY KEY," + CARRERA_CARR + " TEXT,"
                + CARRERA_TIPO + " TEXT" + ")";
        String CREATE_RESULTADO_TABLE = "CREATE TABLE " + TABLE_RESULTADO_SUG + "("
                + RESULTADO_ID + " INTEGER PRIMARY KEY," + RESULTADO_CURP + " TEXT,"
                + RESULTADO_IDCARRERA + " TEXT" + ")";
        String CREATE_RELACION_ESC_TABLE = "CREATE TABLE " + TABLE_RELACION_ESC + "("
                + RELACION_ID + " INTEGER PRIMARY KEY," + RELACION_IDESCUELA + " INTEGER,"
                + RELACION_IDCARRERA + " INTEGER" + ")";

        String CREATE_TIPO_CARR_TABLE = "CREATE TABLE " + TABLE_TIPO_CARRERA + "("
                + TIPOCARRERA_TIPO + " TEXT PRIMARY KEY," + TIPOCARRERA_NOMBRE + " TEXT"+ ")";

        String CREATE_USUARIO_TABLE = "CREATE TABLE " + TABLE_USUARIO + "("
                + USUARIO_CURP + " TEXT PRIMARY KEY," + USUARIO_DIRECCION + " TEXT"+ ")";

        db.execSQL(CREATE_MUNICIPIO_TABLE);
        db.execSQL(CREATE_LOCALIDAD_TABLE);
        db.execSQL(CREATE_ENCUESTA_TABLE);
        db.execSQL(CREATE_ESCUELA_TABLE);
        db.execSQL(CREATE_CARRERA_TABLE);
        db.execSQL(CREATE_RESULTADO_TABLE);
        db.execSQL(CREATE_RELACION_ESC_TABLE);
        db.execSQL(CREATE_TIPO_CARR_TABLE);
        db.execSQL(CREATE_USUARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP DATABASE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
