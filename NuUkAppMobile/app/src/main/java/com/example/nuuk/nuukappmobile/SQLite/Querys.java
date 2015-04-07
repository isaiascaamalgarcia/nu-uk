package com.example.nuuk.nuukappmobile.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    public Querys(Context context, String table)
    {
        this.context=context;
        this.tableName=table;
        admin = new AdminSQLiteOpenHelper(this.context,
                this.tableName, null, 1);
    }
    public void insertar(String columnas, String valores)
    {
        columns=columnas.split(",");
        values=valores.split(",");
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        for(int i=0;i<columns.length;i++)
        {
            registro.put(columns[i],values[i]);
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

    public List<Variables> listadoTest() {
        List<Variables> testList = new ArrayList<Variables>();
        String selectQuery = "SELECT  * FROM " + this.tableName;

        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Variables tabla_x = new Variables();

                tabla_x.setPreguntaEncuesta(cursor.getString(0));
                testList.add(tabla_x);
            } while (cursor.moveToNext());
        }
            return testList;
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

