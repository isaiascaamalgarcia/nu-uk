package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.nuuk.nuukappmobile.SQLite.Querys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class frag_carrersonschool extends Fragment{
    private String[]informacion;
    private Querys query;
    public List<String> listado;
    View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.lay_carrersonschool,container,false);
            if(container == null){
                return null;
            }
            Log.i("SHARY ", getArguments().getString("QUERYC"));
            informacion= getArguments().getString("QUERYC").split(",");
            query=new Querys(rootView.getContext(),"carrera");
            query.listadoJoin();
            listado=query.lista;
            Log.i("QQQ ",String.valueOf(listado.size()));
            return (ScrollView)rootView;
        }
}
