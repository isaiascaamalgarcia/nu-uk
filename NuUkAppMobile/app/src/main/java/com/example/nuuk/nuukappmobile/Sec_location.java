package com.example.nuuk.nuukappmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.example.nuuk.nuukappmobile.NuukClass.CustomOnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_location extends Fragment {
    private Spinner spinType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.lay_location, container, false);
        return rootView;
    }
    public Sec_location()
    {
        spinType = (Spinner) getView().findViewById(R.id.spin_type);
        List<String> list = new ArrayList<String>();
        list.add("Android");
        list.add("Java");
        list.add("Spinner Data");
        list.add("Spinner Adapter");
        list.add("Spinner Example");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinType.setAdapter(dataAdapter);
        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection(){
        spinType.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
