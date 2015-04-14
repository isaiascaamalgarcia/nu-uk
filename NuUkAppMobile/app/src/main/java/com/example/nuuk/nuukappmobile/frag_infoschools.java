package com.example.nuuk.nuukappmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nuuk.nuukappmobile.SQLite.JsonFace;
import com.example.nuuk.nuukappmobile.SQLite.UpdateRecords;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class frag_infoschools extends Fragment {
    private String [] informacion;
    private TextView direccion,telefono,pagina,correo,facebook,twitter;
    private LinearLayout lDireccion,lTelefono,lPagina,lCorreo,lFacebook,lTwitter;
    View rootView;
    private Sec_carrer carrera= new Sec_carrer();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.lay_infoschools,container,false);
        if(container == null){
            return null;
        }
        Log.i("IMP ",getArguments().getString("QUERY"));
        informacionEscuela();
        return (ScrollView)rootView;
    }
    public void informacionEscuela()
    {
        final String[]columnas={"id","first_name","gender","last_name"};
        String[]valores;
        direccion=(TextView)rootView.findViewById(R.id._direccion);
        telefono=(TextView)rootView.findViewById(R.id._tel);
        pagina=(TextView)rootView.findViewById(R.id._pagina);
        correo=(TextView)rootView.findViewById(R.id._correo);
        facebook=(TextView)rootView.findViewById(R.id._face);
        twitter=(TextView)rootView.findViewById(R.id._twitter);

        lDireccion=(LinearLayout)rootView.findViewById(R.id._linearDireccion);
        lTelefono=(LinearLayout)rootView.findViewById(R.id._linearTelefono);
        lPagina=(LinearLayout)rootView.findViewById(R.id._linearPagina);
        lCorreo=(LinearLayout)rootView.findViewById(R.id._linearCorreo);
        lFacebook=(LinearLayout)rootView.findViewById(R.id._linearFacebook);
        lTwitter=(LinearLayout)rootView.findViewById(R.id._linearTwitter);

        informacion=getArguments().getString("QUERY").split(",");

        if(informacion[3].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lDireccion.setLayoutParams(var);
        }
        else {
            direccion.setText(informacion[3]);
            direccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*JsonFace face=new JsonFace(rootView.getContext(),facebook.getText().toString(),columnas);
                    face.getData();*/
                    String uri = "fb://messaging/"+"110878572304597";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }

        if(informacion[6].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lTelefono.setLayoutParams(var);
        }
        else {
            SpannableString content = new SpannableString(informacion[6]);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            telefono.setText(content);
            telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:"+telefono.getText()));
                    startActivity(call);
                }
            });
        }


        if(informacion[7].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lPagina.setLayoutParams(var);
        }
        else {
            SpannableString content = new SpannableString(informacion[7]);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            pagina.setText(content);
            pagina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent page = new Intent(Intent.ACTION_VIEW);
                    page.setData(Uri.parse(""+pagina.getText()));
                    startActivity(page);
                }
            });
        }

        if(informacion[8].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lCorreo.setLayoutParams(var);
        }
        else {
            SpannableString content = new SpannableString(informacion[8]);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            correo.setText(content);
            correo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setData(Uri.parse("mailto:"));
                    String[] to = {""+correo.getText()};
                    email.putExtra(Intent.EXTRA_EMAIL,to);
                    email.putExtra(Intent.EXTRA_SUBJECT,"Postulante - Nu' uk");
                    email.setType("message/rfc822");
                    Intent chooser = Intent.createChooser(email,"Enviar correo");
                    startActivity(chooser);
                }
            });
        }
        if(informacion[9].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lFacebook.setLayoutParams(var);
        }
        else {
            SpannableString content = new SpannableString(informacion[9]);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            facebook.setText(content);
        }
        if(informacion[10].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lTwitter.setLayoutParams(var);
        }
        else {
            SpannableString content = new SpannableString(informacion[10]);
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            twitter.setText(content);
        }
    }

}
