package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        img = (ImageView)rootView.findViewById(R.id.goschool);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Fragment fragment = new Selected_school();
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
            spinTipos.setOnItemSelectedListener(new
                                                        AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view,
                                                                                       int position, long id) {
                                                                x = spinTipos.getSelectedItemPosition();
                                                                querys = new Querys(rootView.getContext(), "escuela");
                                                                querys.listadoCondicionId(columnas.getTableEscuela(), 0,
                                                                        "tipo", x);
                                                                listaEscuela = querys.lista;
                                                                String[] stockArr = new String[listaEscuela.size()];
                                                                stockArr = listaEscuela.toArray(stockArr);
                                                                listaEscuela.clear();
                                                                for (String s : stockArr) {
                                                                    querys = new Querys(rootView.getContext(),
                                                                            "relacion_escuela");
                                                                    querys.listadoCondicionId
                                                                            (columnas.getTableRelacionEscuela(), 2, "idEscuela", Integer.parseInt(s));
                                                                    stockArr = new String[querys.lista.size()];
                                                                    stockArr = querys.lista.toArray(stockArr);
                                                                    for (String s2 : stockArr) {
                                                                        listaEscuela.add(s2);
                                                                    }
                                                                }
                                                                stockArr = new String[listaEscuela.size()];
                                                                stockArr = listaEscuela.toArray(stockArr);
                                                                listaEscuela.clear();
                                                                for (String s : stockArr) {
                                                                    querys = new Querys(rootView.getContext(), "carrera");
                                                                    querys.listadoCondicionId(columnas.getTableCarrera(), 0,
                                                                            "idEscuela", Integer.parseInt(s));
                                                                    stockArr = new String[querys.lista.size()];
                                                                    stockArr = querys.lista.toArray(stockArr);
                                                                    for (String s2 : stockArr) {
                                                                        listaEscuela.add(s2);
                                                                    }
                                                                }

                                                                querys=new Querys(rootView.getContext(),"carrera");
                                                                querys.listado(columnas.getTableCarrera(),0);
                                                                String[] stockArr3 = new String[querys.lista.size()];
                                                                stockArr3 = querys.lista.toArray(stockArr3);

                                                                for (String s3 : stockArr3){
                                                                    for (String s4 : stockArr){
                                                                        if(s3==s4)
                                                                            listaMunicipio.add(s3);

                                                                    }
                                                                }
                                                                adapter = new ArrayAdapter<String>(rootView.getContext(),
                                                                        android.R.layout.simple_spinner_item, listaMunicipio);
                                                                spinCarreras.setAdapter(adapter);

                                                                querys = new Querys(rootView.getContext(), "escuela");
                                                                querys.listadoCondicionId(columnas.getTableEscuela(), 0,
                                                                        "tipo", x);
                                                                listaEscuela = querys.lista;
                                                                stockArr = new String[listaEscuela.size()];
                                                                stockArr = listaEscuela.toArray(stockArr);
                                                                stockArr3 = new String[querys.lista.size()];
                                                                stockArr3 = listaMunicipio.toArray(stockArr3);
                                                                listaMunicipio.clear();
                                                                for(String s5 : stockArr) {
                                                                    for (String s6 : stockArr3) {
                                                                        querys = new Querys(rootView.getContext(), "relacion_escuela");
                                                                        querys.listadoCondicionesId(columnas.getTableRelacionEscuela(), 1, "idEscuela", Integer.parseInt(s5), "idCarrera", Integer.parseInt(s6));
                                                                        listaMunicipio.add(querys.lista.get(0));
                                                                    }
                                                                }
                                                                listaMunicipio=querys.lista;
                                                                stockArr3 = new String[querys.lista.size()];
                                                                stockArr3 = listaMunicipio.toArray(stockArr3);
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });


            querys = new Querys(rootView.getContext(), "carrera");
            querys.listado(columnas.getTableCarrera(), 1);
            listaMunicipio = querys.lista;
            adapter = new ArrayAdapter<String>(rootView.getContext(),
                    android.R.layout.simple_spinner_item, listaMunicipio);
            spinCarreras.setAdapter(adapter);

            spinCarreras.setOnItemSelectedListener(new
                                                           AdapterView.OnItemSelectedListener() {
                                                               @Override
                                                               public void onItemSelected(AdapterView<?> parent, View view,
                                                                                          int position, long id) {
                                                                   x = spinTipos.getSelectedItemPosition();
                                                                   querys = new Querys(rootView.getContext(), "escuela");
                                                                   querys.listadoCondicionId(columnas.getTableEscuela(), 0,
                                                                           "tipo", x);
                                                                   listaEscuela = querys.lista;
                                                                   String[] stockArr = new String[listaEscuela.size()];
                                                                   stockArr = listaEscuela.toArray(stockArr);
                                                                   listaEscuela.clear();
                                                                   x = spinCarreras.getSelectedItemPosition();
                                                                   for (String s : stockArr) {
                                                                       querys = new Querys(rootView.getContext(),
                                                                               "relacion_escuela");
                                                                       querys.listadoCondicionesId
                                                                               (columnas.getTableRelacionEscuela(), 1, "idEscuela", Integer.parseInt
                                                                                       (s), "idCarrera", x);
                                                                       stockArr = new String[querys.lista.size()];
                                                                       stockArr = querys.lista.toArray(stockArr);
                                                                       for (String s2 : stockArr) {
                                                                           listaEscuela.add(s2);
                                                                       }
                                                                   }
                                                                   stockArr = new String[listaEscuela.size()];
                                                                   stockArr = listaEscuela.toArray(stockArr);
                                                                   listaEscuela.clear();
                                                                   for (String s : stockArr) {
                                                                       querys = new Querys(rootView.getContext(), "escuela");
                                                                       querys.listadoCondicionId(columnas.getTableEscuela(), 2,
                                                                               "idEscuela", Integer.parseInt(s));
                                                                       stockArr = new String[querys.lista.size()];
                                                                       stockArr = querys.lista.toArray(stockArr);
                                                                       for (String s2 : stockArr) {
                                                                           listaEscuela.add(s2);
                                                                       }
                                                                   }
                                                                   adapter = new ArrayAdapter<String>(rootView.getContext(),
                                                                           android.R.layout.simple_spinner_item, listaEscuela);
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