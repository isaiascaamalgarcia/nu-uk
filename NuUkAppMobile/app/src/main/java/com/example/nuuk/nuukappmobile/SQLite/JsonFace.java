package com.example.nuuk.nuukappmobile.SQLite;


import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Shary on 07/04/2015.
 */

    public class JsonFace {
    Context context;
    String usuario;
    public String var="";
    public String idFace="";

    public JsonFace(Context context,String usuarioFacebook)
    {
        this.context=context;
        this.usuario=usuarioFacebook;
    }
    public void getData() {

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://192.168.1.69/iqm/getfbid.php";
        RequestParams params = new RequestParams();
        params.put("url","/izzy.factorial");

        client.post(url,params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //call function...
                    Log.i("200","Aqui estoy");
                    var = getJSONData(new String(responseBody)).toString();
                    Log.i("varllena",var);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.i("200","Aqui estoy mal");
            }
         });
    }
    public String getJSONData(String response) {
        try{
            //JSONArray jsonArray = new JSONArray(response);
            idFace = response.toString();
            Log.i("TEXT",idFace);

        }catch(Exception e) {

           Log.i("JSON","aqui ando mal");
        }

        Log.i("TEXT",idFace);
        return  idFace;
    }
}
