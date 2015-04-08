package com.example.nuuk.nuukappmobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.Querys;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_test extends Fragment {
    ToggleButton tb;
    TextView question;
    View rootView;
    Querys querys;
    ColumnsTables ct;
    ImageView next;
    int tableSize, currentPosition = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.lay_test, container, false);
        next = (ImageView)rootView.findViewById(R.id.nextbutton);
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                generateQuestionSection();
            }
        });
        tb=(ToggleButton)rootView.findViewById(R.id.tb1);
        tb.setText("");
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb2);
        tb.setText("");
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb3);
        tb.setText("");
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb4);
        tb.setText("");
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        tb=(ToggleButton)rootView.findViewById(R.id.tb5);
        tb.setText("");
        tb.setTextOn("SI");
        tb.setTextOff("NO");
        querys = new Querys(rootView.getContext(),"encuesta");
        ct = new ColumnsTables();
        querys.listado(ct.getTableEncuesta(),1);
        setCurrentSize(querys.lista.size());
        generateQuestionSection();

        return rootView;
    }
    /*----------------Local Function for test-----------------------------------------*/
    public void generateQuestionSection(){
        int size = getCurrentSize();
        if(size>5) {
            question = (TextView) rootView.findViewById(R.id.preg1);
            question.setText(querys.lista.get(currentPosition));
            question = (TextView) rootView.findViewById(R.id.preg2);
            question.setText(querys.lista.get(currentPosition+1));
            question = (TextView) rootView.findViewById(R.id.preg3);
            question.setText(querys.lista.get(currentPosition+2));
            question = (TextView) rootView.findViewById(R.id.preg4);
            question.setText(querys.lista.get(currentPosition+3));
            question = (TextView) rootView.findViewById(R.id.preg5);
            question.setText(querys.lista.get(currentPosition+4));
            currentPosition += 5;
            setCurrentSize(getCurrentSize()-5);
        }
    }
    /*-------------get & set values for table size---------------------------*/
    public int getCurrentSize() {
        return tableSize;
    }
    public void setCurrentSize(int size) {
        this.tableSize = size;
    }

}
