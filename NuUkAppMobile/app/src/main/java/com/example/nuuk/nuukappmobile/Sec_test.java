package com.example.nuuk.nuukappmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_test extends Fragment {
    ToggleButton tb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.lay_test, container, false);
        tb=(ToggleButton)rootView.findViewById(R.id.tb1);
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb2);
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb3);
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb4);
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb5);
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        return rootView;
    }
}
