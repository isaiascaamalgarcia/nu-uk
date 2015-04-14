package com.example.nuuk.nuukappmobile.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.apache.http.Header;
import org.json.JSONArray;

import java.util.Locale;

import javax.security.auth.login.LoginException;

/**
 * Created by Shary on 07/04/2015.
 */
public class JsonFace {
    ListView listado;
    Context context;
    String tableName;
    String[]columnas={"id","first_name","gender","last_name"};
    public  String[]valores;
    public Querys querys;
    AdminSQLiteOpenHelper admin;
    public JsonFace(Context context, String tableName, String[] columnas)
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
        String url = "http://graph.facebook.com"+this.tableName.toString();
        //RequestParams params = new RequestParams();
     //   params.put("tableName", this.tableName);
        Log.i("URL",url);
        client.post(url, null, new AsyncHttpResponseHandler() {
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
                for(int j=0;j<this.columnas.length;j++)
                {
                    valores[j]=jsonArray.getJSONObject(i).getString(this.columnas[j]);
                }
            }

        }catch(Exception e) {
        }
    }
}
