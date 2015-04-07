package com.example.nuuk.nuukappmobile.SQLite;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    public UpdateRecords(Context context)
    {
     this.context=context;
    }
    public void getData(String tableName) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.0.19/Administrador/index.php/consultas";
        RequestParams params = new RequestParams();
        params.put("tableName", tableName);

        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    //call function...
                    loadList(getJSONData(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void loadList(ArrayList<String> datos){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adapter);
    }

    public ArrayList<String> getJSONData(String response) {
        ArrayList<String> list = new ArrayList<String>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            String text;
            for (int i=0; i<jsonArray.length();i++) {
                text = jsonArray.getJSONObject(i).getString("pregunta")+" "+
                        jsonArray.getJSONObject(i).getString("tipoCarrera")+" ";
                list.add(text);
            }
        }catch(Exception e) {

        }
        return list;
    }
}
