package com.example.nuuk.nuukappmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
    }
}
