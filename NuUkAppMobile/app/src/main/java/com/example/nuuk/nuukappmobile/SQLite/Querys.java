package com.example.nuuk.nuukappmobile.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    public List<String> lista;
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

    public void buscar(int id, String columnas,String campoClausula)
    {
        columns=columnas.split(",");
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor cursor =
                bd.query(tableName,
                        columns,campoClausula+"= ?",
                        new String[] { String.valueOf(id) },
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        if (cursor != null)
            cursor.moveToFirst();

        for(int i=0;i<columnas.length();i++)
        {
            cursor.getString(i);
        }
        //bd.close();
    }

    public void listado(String []columnas, int numColumna) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
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
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();
        }
        catch (Exception e)
        {
        }
    }

    public void listadoJoin() {
        String dato;
        String []columnas={"tipo", "nombre"};
       /* String []columnas={"tipo", "nombre", "direccion" ,"latitud", "longitud",
                "telefono" , "pagina", "correo", "facebook", "twitter", "sector", "modificacion", "idLocalidad", "estado"};*/
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        try {

            String selectQuery = "SELECT tipo,nombre FROM escuela INNER JOIN localidad ON escuela.idlocalidad=localidad.id AND localidad.idMunicipio=1 AND escuela.tipo=?";

            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor=bd.rawQuery(selectQuery, new String[]{"1"});
            if (cursor.moveToFirst()) {
                do {
                    for (int i=0;i<columnas.length;i++)
                    {
                        valor[i]=cursor.getString(i);
                    }
                    lista.add(valor[0]);
                } while (cursor.moveToNext());
            }
            cursor.close();
            bd.close();

        } catch(Exception e)
                {
                }
            }



    public void listadoCondicionId(String []columnas, int numColumna,String campoClausula, int condicion) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        try {
            String selectQuery = "SELECT  *FROM "+ this.tableName +" WHERE "+ campoClausula+"="+condicion;
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
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
        }
        catch (Exception e)
        {
        }
    }

    public void listadoCondicionesId(String []columnas, int numColumna,String campoClausula, int condicion,String campoClausula2, int condicion2) {
        String dato;
        String [] valor= new String[columnas.length];
        lista= new ArrayList<String>();
        try {
            String selectQuery = "SELECT  * FROM "+ this.tableName +" WHERE "+ campoClausula+"="+condicion+" AND "+ campoClausula2 + "=" + condicion2;
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery(selectQuery, null);
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

