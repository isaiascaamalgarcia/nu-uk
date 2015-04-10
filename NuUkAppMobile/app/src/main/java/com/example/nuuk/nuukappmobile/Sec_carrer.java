package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.Querys;

import java.util.List;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_carrer extends Fragment {
    private Spinner spinTipos;
    private Spinner spinCarreras;
    private Spinner spinEscuelas;
    private ImageView img;
    private ArrayAdapter<String> adapter;
    public List<String> listaMunicipio, listaEscuela;
    View rootView;
    Querys querys;
    int x = 1;
    ColumnsTables columnas = new ColumnsTables();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        rootView = inflater.inflate(R.layout.lay_schooltype, container, false);
        img = (ImageView)rootView.findViewById(R.id.imagen);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Fragment f = new Selected_school();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.vista_schoooltype, f);
                fragmentTransaction.commit();
            }
        });
             listadoCarreras();
        return rootView;
    }

    public void listadoCarreras() {
        spinTipos = (Spinner) rootView.findViewById(R.id.spin_type2);
        spinCarreras = (Spinner) rootView.findViewById(R.id.spin_carrer2);
        spinEscuelas = (Spinner) rootView.findViewById(R.id.spin_school2);
        try {
            adapter = new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_spinner_item, columnas.getNivelEducativo());
            spinTipos.setAdapter(adapter);
        } catch (Exception e) {
        }

    }
}