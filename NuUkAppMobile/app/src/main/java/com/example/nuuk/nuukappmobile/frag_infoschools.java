package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class frag_infoschools extends Fragment {
    private String [] informacion;
    private TextView direccion,telefono,pagina,correo,facebook,twitter;
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

        informacion=getArguments().getString("QUERY").split(",");
        direccion.setText(informacion[3]);
        telefono.setText(informacion[6]);
        pagina.setText(informacion[7]);
        correo.setText(informacion[8]);
        facebook.setText(informacion[9]);
        twitter.setText(informacion[10]);



    }

}
