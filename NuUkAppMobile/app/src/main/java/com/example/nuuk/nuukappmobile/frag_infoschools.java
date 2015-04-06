package com.example.nuuk.nuukappmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class frag_infoschools extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(container == null){
            return null;
        }
        return (ScrollView)inflater.inflate(R.layout.lay_infoschools,container,false);
    }
}
