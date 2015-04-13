package com.example.nuuk.nuukappmobile.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Shary on 07/04/2015.
 */
public class Querys {
    public Context context;
    public String tableName;
    public String [] columns;
    public String [] values;
    public AdminSQLiteOpenHelper admin;
    public Variables variables;
    public List<String> lista,lista1;
    public  String informacionEscuela="";
    public Querys(Context context, String table)
    {
        this.context=context;
        this.tableName=table;
        admin = new AdminSQLiteOpenHelper(this.context,
                this.tableName, null, 1);
    }
    public void insertar(String []columnas, String []valores)
    {
       /* columns=columnas.split(",");
        values=valores.split(",");*/
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        for(int i=0;i<columnas.length;i++)
        {
            registro.put(columnas[i],valores[i]);
        }
        bd.insert(this.tableName, null, registro);
        bd.close();
    }


    public void listado(String []columnas, int numColumna) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        lista1= new ArrayList<String>();
        try {
            String selectQuery = "SELECT  *FROM "+ this.tableName;
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                     valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                    lista1.add(valor[0]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
        catch (Exception e)
        {
        }
    }

    /*SELECT DISTINCT carrera
    FROM carrera
    INNER JOIN relacion_escuela ON carrera.id = relacion_escuela.idCarrera
    INNER JOIN escuela ON escuela.id = relacion_escuela.idEscuela
    WHERE escuela.id =9*/
    public void listadoJoin(String columnaTable1,String []columnas, String tableName2,String tableName3,int numColumna,String condicion,
                            String condicion1,String condicion2,String condicion3, String valorCondicion, String valorCondicion2) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        try {
            String selectQuery = "SELECT "+columnaTable1.toString()+" FROM "+ this.tableName+" INNER JOIN "+ tableName2+
                    " ON "+this.tableName+"."+condicion+"="+tableName2+"."+condicion1+
                    " INNER JOIN "+ tableName3+" ON "+tableName3+"."+condicion+"="+tableName2+"."+condicion2+
                    " WHERE "+tableName3+"."+condicion+"=?";

            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor=bd.rawQuery(selectQuery, new String[]{condicion3});
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                        valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();

        } catch(Exception e)
                {
                }
            }

    public void listadoInnerJoinLoc(String column, String idMun, String nivel) {
        String dato;
        lista= new ArrayList<String>();
        lista1= new ArrayList<String>();
        String []columnas=column.split(",");
        String [] valor= new String[columnas.length];

        try {
            String selectQuery = "SELECT ".concat(column.toString())+" FROM "+"escuela INNER JOIN localidad ON escuela.idlocalidad=localidad.id AND localidad.idMunicipio="+idMun+" AND escuela.tipo=?";
            Log.i("QUERY ", selectQuery);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor=bd.rawQuery(selectQuery, new String[]{nivel});
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                        valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[1]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch(Exception e)
        {
        }
    }

    public void listadoInnerJoinCarrEscuela(String column, String idCar, String nivel) {
        String dato;
        lista= new ArrayList<String>();
        String []columnas=column.split(",");
        String [] valor= new String[columnas.length];
        try {
            //String selectQuery = "SELECT ".concat(column.toString())+" FROM "+"escuela INNER JOIN localidad ON escuela.idlocalidad=localidad.id AND localidad.idMunicipio="+idMun+" AND escuela.tipo=?";
            String selectQuery = "SELECT ".concat(column.toString())+" FROM escuela INNER JOIN relacion_escuela ON escuela.id = relacion_escuela.idEscuela INNER JOIN carrera ON carrera.id = relacion_escuela.idCarrera WHERE relacion_escuela.idCarrera ="+idCar+" AND escuela.tipo =?";

            Log.i("QUERY ", selectQuery);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor=bd.rawQuery(selectQuery, new String[]{nivel});
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                        valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[1]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch(Exception e)
        {
        }
    }

    public void listadoInnerJoinCarrCarrera(String nivel) {
        String dato;
        lista= new ArrayList<String>();
        lista1= new ArrayList<String>();
        String [] valor= new String[2];
        try {
            String selectQuery="SELECT DISTINCT carrera, carrera.id\n" +
                    "FROM carrera\n" +
                    "INNER JOIN relacion_escuela ON carrera.id = relacion_escuela.idCarrera\n" +
                    "INNER JOIN escuela ON escuela.id = relacion_escuela.idEscuela\n" +
                    "WHERE escuela.tipo =?";
            Log.i("QUERY ", selectQuery);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor=bd.rawQuery(selectQuery, new String[]{nivel});
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<2;i++)
                    {
                        valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[0]);
                    lista1.add(valor[1]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        } catch(Exception e)
        {
        }
    }


    public void listadoCondicionId(String []columnas, int numColumna,String campoClausula, String condicion) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        try {
            String selectQuery = "SELECT  *FROM "+ this.tableName +" WHERE "+ campoClausula+"="+"'"+condicion+"'";
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                        valor[i]=cursor.getString(i);
                        informacionEscuela+=valor[i]+",";
                    }
                    lista.add(valor[numColumna]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
        catch (Exception e)
        {
        }
    }


    public int conteoRegistros() {
        String countQuery = "SELECT  * FROM " + this.tableName;
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor cursor = bd.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    public void actualizarRegistro(String columnas, String valores, int id) {
        columns=columnas.split(",");
        values=valores.split(",");
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        for(int i=0;i<columns.length;i++)
        {
            registro.put(columns[i],values[i]);
        }
         bd.update(this.tableName, registro,  "id  = ?",
                new String[] { String.valueOf(id)});
    }

    public void eliminarRegistro(int id) {
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.delete(this.tableName, "id = ?",
                new String[] { String.valueOf(id)});
        bd.close();
    }
}

