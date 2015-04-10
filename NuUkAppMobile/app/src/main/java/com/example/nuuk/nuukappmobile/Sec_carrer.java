package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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
    private TextView tv1,tv2,tv3;
    private ArrayAdapter<String> adapter;
    private String[] stockArr;
    public List<String> listaCarreras, listaEscuelas,listaEscuelasId;
    View rootView;
    Querys querys;
    int x = 1,x1=1;
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
                fragmentTransaction.replace(R.id.vista_schoooltype, f). addToBackStack(null);
            }
        });
        tv1 = (TextView)rootView.findViewById(R.id.tv_carrer);
        tv2 = (TextView)rootView.findViewById(R.id.tv2_carrer);
        tv3 = (TextView)rootView.findViewById(R.id.tv3_carrer);
        setFont sf = new setFont();
        sf.setFontTextView(tv1,getActivity());
        sf.setFontTextView(tv2,getActivity());
        sf.setFontTextView(tv3,getActivity());
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

            querys = new Querys(rootView.getContext(), "carrera");
            querys.listado(columnas.getTableCarrera(),1);
            listaCarreras=querys.lista;
            listaEscuelasId=querys.lista1;
            adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, listaCarreras);
            spinCarreras.setAdapter(adapter);
            stockArr = new String[listaEscuelasId.size()];
            stockArr = listaEscuelasId.toArray(stockArr);

            spinTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    x=spinCarreras.getSelectedItemPosition();
                    x1=spinTipos.getSelectedItemPosition();
                    String aux=stockArr[x];
                    Log.i("AUX ",aux);
                    querys = new Querys(rootView.getContext(), "escuela");
                    querys.listadoInnerJoinCarr("tipo,nombre",aux,String.valueOf(x1));
                    listaEscuelas=querys.lista;
                    adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, listaEscuelas);
                    spinEscuelas.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            spinCarreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    x=spinCarreras.getSelectedItemPosition();
                    x1=spinTipos.getSelectedItemPosition();
                    String aux=stockArr[x];
                    Log.i("AUX ",aux);
                    querys = new Querys(rootView.getContext(), "escuela");
                    querys.listadoInnerJoinCarr("tipo,nombre",aux,String.valueOf(x1));
                    listaEscuelas=querys.lista;
                    adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, listaEscuelas);
                    spinEscuelas.setAdapter(adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



        } catch (Exception e) {
        }

    }
}