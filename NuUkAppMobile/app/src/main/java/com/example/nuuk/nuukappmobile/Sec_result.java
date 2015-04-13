package com.example.nuuk.nuukappmobile;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Kody on 09/04/2015.
 */
public class Sec_result extends Fragment{
    LinearLayout la;
    TestResult tr;
    View rootView;
    String[] lista;
    Button btn;
    int art=0,adm=0,def=0,cien=0,hum=0,ing=0,med=0;


    public Sec_result() {
        Sec_test object = new Sec_test();
        this.lista =  object.querysAnswer.lista.toArray(new String[object.querysAnswer.lista.size()]);
        this.art = object.art;
        this.adm = object.adm;
        this.def = object.def;
        this.cien = object.cien;
        this.hum = object.hum;
        this.ing = object.ing;
        this.med = object.med;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        rootView = inflater.inflate(R.layout.lay_result, container, false);
        la = (LinearLayout)rootView.findViewById(R.id.chart);
        btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Fragment f = new Sec_carrer();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, f). addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
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
            color = Color.rgb(204,83,20);
        }
        if(color==1){
            color = Color.rgb(55,178,18);
        }if(color==2) {
            color = Color.rgb(30,30,255);
        }if(color==3) {
            color = Color.rgb(59,255,0);
        }if(color==4){
            color = Color.rgb(178,61,0);
        }if(color==5){
            color = Color.rgb(140,140,140);
        }if(color==6){
            color = Color.rgb(160,160,180);
        }
//los for de android no sirven
            RelativeLayout view = new RelativeLayout(rootView.getContext());//
            view.setId(color);
            view.setBackgroundColor(color);
            view.setLayoutParams(new RelativeLayout.LayoutParams(30, height * 17));
            TextView tv = new TextView(getActivity());
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.ABOVE,view.getId());
            double porcent = (height*100)/14;
            tv.setText("" + String.valueOf(porcent) + "%");

            view.addView(tv);
            la.addView(view);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}