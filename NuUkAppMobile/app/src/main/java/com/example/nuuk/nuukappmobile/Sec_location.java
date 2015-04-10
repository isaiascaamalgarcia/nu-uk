package com.example.nuuk.nuukappmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.Querys;

import java.util.List;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_location extends Fragment{
    private Spinner spinType;
    private Spinner spinMunicipio;
    private Spinner spinEscuela;
    private ArrayAdapter<String> adapter;
    public List<String> listaMunicipio,listaEscuela;
    View rootView;
    Querys querys;
    int x=1;
    private ImageView img;
    ColumnsTables columnas= new ColumnsTables();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.lay_location, container, false);
        listado();
        return rootView;
    }

    public void listado() {
        spinType = (Spinner) rootView.findViewById(R.id.spin_type);
        spinMunicipio = (Spinner) rootView.findViewById(R.id.spin_carrer);
        spinEscuela = (Spinner) rootView.findViewById(R.id.spin_school);

        try {
            adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, columnas.getNivelEducativo());
            spinType.setAdapter(adapter);


            querys = new Querys(rootView.getContext(), "municipio");
            querys.listado(columnas.getTableMunicipio(),1);
            listaMunicipio=querys.lista;
            adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, listaMunicipio);
            spinMunicipio.setAdapter(adapter);

            querys = new Querys(rootView.getContext(), "escuela");
            querys.listadoJoin();
            listaEscuela=querys.lista;
            adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, listaEscuela);
            spinEscuela.setAdapter(adapter);


        }
           catch (Exception e) {
        }
    }
}
