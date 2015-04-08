package com.example.nuuk.nuukappmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.Querys;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_location extends Fragment {
    private Spinner spinType;
    View rootView;
    Querys querys;
    ColumnsTables columnas= new ColumnsTables();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.lay_location, container, false);
        listado();
        return rootView;
    }

    public void listado() {
        spinType = (Spinner) rootView.findViewById(R.id.spin_type);
        querys = new Querys(rootView.getContext(), "municipio");
        querys.listado(columnas.getTableMunicipio(),1);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_spinner_item, querys.lista);
            spinType.setAdapter(adapter);
        } catch (Exception e) {

        }
    }
}
