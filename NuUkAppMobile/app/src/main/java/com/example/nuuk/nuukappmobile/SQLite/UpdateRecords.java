package com.example.nuuk.nuukappmobile.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Shary on 07/04/2015.
 */
public class UpdateRecords {
    ListView listado;
    Context context;
    String tableName;
    String[]columnas;
    String[]valores;
    public Querys querys;
    AdminSQLiteOpenHelper admin;
    public UpdateRecords(Context context,String tableName,String[]columnas)
    {
        this.context=context;
        this.tableName=tableName;
        this.columnas=columnas;
        valores=new String[this.columnas.length];
        querys= new Querys(this.context,tableName);
        admin = new AdminSQLiteOpenHelper(this.context,
                this.tableName, null, 1);
        getData();
    }
    public void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        /*String url = "http://192.168.1.68/nu-uk/Administrador/index.php/consultas";*/
        String url = "http://nuuk.esy.es/index.php/consultas";
        RequestParams params = new RequestParams();
        params.put("tableName", this.tableName);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    //call function...

                    getJSONData(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void getJSONData(String response) {
        try{
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i<jsonArray.length();i++) {
                SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                for(int j=0;j<this.columnas.length;j++)
                {
                    valores[j]=jsonArray.getJSONObject(i).getString(this.columnas[j]);
                    /*Toast toast=Toast.makeText(context,valores[j],Toast.LENGTH_LONG);
                    toast.show();*/
                    registro.put(this.columnas[j], valores[j]);

                }
                bd.insert(this.tableName, null, registro);
                bd.close();
            }
        }catch(Exception e) {
        }
    }
}
