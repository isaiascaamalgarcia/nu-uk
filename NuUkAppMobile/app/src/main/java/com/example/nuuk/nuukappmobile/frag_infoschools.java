package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
        else
            direccion.setText(informacion[3]);

        if(informacion[6].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lTelefono.setLayoutParams(var);
        }
        else
            telefono.setText(informacion[6]);


        if(informacion[7].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lPagina.setLayoutParams(var);
        }
        else
            pagina.setText(informacion[7]);


        if(informacion[8].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lCorreo.setLayoutParams(var);
        }
        else
            correo.setText(informacion[8]);

        if(informacion[9].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lFacebook.setLayoutParams(var);
        }
        else
            facebook.setText(informacion[9]);

        if(informacion[10].equals("NULL"))
        {
            LinearLayout.LayoutParams var=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
            lTwitter.setLayoutParams(var);
        }
        else
            twitter.setText(informacion[10]);
    }

}
