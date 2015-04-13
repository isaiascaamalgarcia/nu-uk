package com.example.nuuk.nuukappmobile;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_home extends Fragment {
    private  View rootView;
    private ImageView imageViewRound;
    private TextView tv;
    private Bitmap bitmap;
    private EditText et;
    private ProgressDialog pDialog;
    String mensaje;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.lay_home, container, false);
        //imageViewRound=(ImageView)rootView.findViewById(R.id.imageView_round);
        //new LoadImage().execute("http://imagenestiernas.info/wp-content/uploads/2013/03/16917_540713432628376_2067568813_n-550x550.jpg");
        //loadImageFromURL("http://imagenestiernas.info/wp-content/uploads/2013/03/16917_540713432628376_2067568813_n-550x550.jpg",imageViewRound);
        /*Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.logobw);
        imageViewRound.setImageBitmap(icon);*/
        imageViewRound = (ImageView)rootView.findViewById(R.id.iv2_home);

        tv = (TextView)rootView.findViewById(R.id.tv_home);
        et = (EditText)rootView.findViewById(R.id.et_home);
        setFont sf = new setFont();
        sf.setFontTextView(tv,getActivity());

        if(savedInstanceState==null){
            imageViewRound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String curp = String.valueOf(et.getText());
                    if(validateCurp(curp)) {
                        Log.i("Ramon", "Correto curp");
                        tv.setText("Bienvenido");
                        imageViewRound.setVisibility(View.INVISIBLE);
                        et.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(rootView.getContext(),"Formato no valido",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            mensaje=savedInstanceState.getString("values");
            tv.setText(mensaje);
            imageViewRound.setVisibility(View.INVISIBLE);
            et.setVisibility(View.INVISIBLE);
        }
        return rootView;
    }
    public boolean validateCurp(String curp) {
        curp = curp.toUpperCase().trim();
        return curp.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9]{2}");

    }
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Image ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                imageViewRound.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("values", "Bienvenido");
    }
}
