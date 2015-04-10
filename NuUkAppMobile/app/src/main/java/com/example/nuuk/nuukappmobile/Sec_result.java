package com.example.nuuk.nuukappmobile;

import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


/**
 * Created by Kody on 09/04/2015.
 */
public class Sec_result extends Fragment{
    LinearLayout la;
    View rootView;
    String[] lista;
    int art,adm,def,cien,hum,ing,med;


    public Sec_result() {
        Sec_test object = new Sec_test();
        this.lista =  object.querysAnswer.lista.toArray(new String[object.querysAnswer.lista.size()]);
        this.art= object.ing;
        this.adm = object.adm;
        this.def = object.def;
        this.cien = object.cien;
        this.hum = object.hum;
        this.ing = object.ing;
        this.med = object.med;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        rootView = inflater.inflate(R.layout.lay_result, container, false);
        la = (LinearLayout)rootView.findViewById(R.id.chart);
        int color[] = new int[this.lista.length];
        for(int i = 0;i<lista.length;i++){
            color[i] = i;
        }
        int height[] = {art,adm,def,cien,hum,ing,med};
        for(int j = 0; j<7;j++){
            Log.i("Ramon", "Value j:" + j);
            drawChart(1, color[j], height[j]);
        }
        return rootView;
    }
    private void drawChart(int count, int color, int height) {
        System.out.println(count+color+height);
        if(color==0){
            color = Color.RED;
        }
        if(color==1){
            color = Color.BLUE;
        }if(color==2) {
            color = Color.YELLOW;
        }if(color==3) {
            color = Color.GREEN;
        }if(color==4){
            color = Color.rgb(120,120,120);
        }if(color==5){
            color = Color.rgb(140,140,140);
        }if(color==6){
            color = Color.rgb(160,160,180);
        }
        for(int k = 0; k<= count; k++){
            View view = new View(rootView.getContext());//
            view.setBackgroundColor(color);
            view.setLayoutParams(new HorizontalScrollView.LayoutParams(20,height*20));
            la.addView(view);
        }

    }
}