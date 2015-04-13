package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.nuuk.nuukappmobile.SQLite.Querys;

import java.util.ArrayList;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class frag_carrersonschool extends Fragment{
    private String[]informacion;
    private Querys query;
    public ArrayList<String> listado;
    private ListView listView;
    private String[] stockArr;
    View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.lay_carrersonschool,container,false);
            if(container == null){
                return null;
            }
            Log.i("SHARY ", getArguments().getString("QUERYC"));
            consultaCarreras();
            return (ScrollView)rootView;
        }
    public void consultaCarreras()
    {
        informacion= getArguments().getString("QUERYC").split(",");
        listView = (ListView) rootView.findViewById(R.id.listView);
        String columnaTable1="carrera";
        String []columnas={"carrera"};
        String tableName2="relacion_escuela";
        String tableName3="escuela";
        String condicion="id";
        String condicion1="idCarrera";
        String condicion2="idEscuela";
        String condicion3=informacion[0].toString();
        int numColumna=0;


        query=new Querys(rootView.getContext(),"carrera");
        query.listadoJoin(columnaTable1,columnas,tableName2,tableName3,condicion,condicion1,condicion2,condicion3,numColumna);
        listado=query.lista;

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(rootView.getContext(), R.layout.list_item, listado);
        listView.setAdapter(itemsAdapter);
    }
}
