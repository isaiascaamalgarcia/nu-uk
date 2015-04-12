package com.example.nuuk.nuukappmobile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.Querys;

/**
 * Created by Kody on 28/03/2015.
 */
public class Sec_test extends Fragment {
    ToggleButton tb1, tb2, tb3, tb4, tb5;
    TextView question;
    String[] status = new String[5];
    View rootView;
    public static Querys querys, querysAnswer;
    ColumnsTables ct;
    ImageView next;
    public static int tableSize=0, currentPosition = 1, ing=0, art=0, adm=0, def=0, cien=0, hum=0, med=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.lay_test, container, false);

        if(savedInstanceState==null){
            next = (ImageView)rootView.findViewById(R.id.nextbutton);
            next.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    getResultByUser();
                    generateQuestionSection();
                }
            });
            querys = new Querys(rootView.getContext(),"encuesta");
            querysAnswer = new Querys(rootView.getContext(),"encuesta");
            ct = new ColumnsTables();
            querys.listado(ct.getTableEncuesta(),1);
            querysAnswer.listado(ct.getTableEncuesta(),2);
            setCurrentSize(querys.lista.size());
            currentPosition=1;
            generateQuestionSection();
        }else {
            next = (ImageView)rootView.findViewById(R.id.nextbutton);
            next.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    getResultByUser();
                    generateQuestionSection();
                }
            });
            status=savedInstanceState.getStringArray("values");
            question = (TextView) rootView.findViewById(R.id.preg1);
            question.setText(status[0]);
            question = (TextView) rootView.findViewById(R.id.preg2);
            question.setText(status[1]);
            question = (TextView) rootView.findViewById(R.id.preg3);
            question.setText(status[2]);
            question = (TextView) rootView.findViewById(R.id.preg4);
            question.setText(status[3]);
            question = (TextView) rootView.findViewById(R.id.preg5);
            question.setText(status[4]);
            generateCustomButtons();
        }

        return rootView;
    }
    /*----------------Local Function for test-----------------------------------------*/
    public void generateQuestionSection(){
        int size = getCurrentSize();
        if(size>=5 ) {
            /*----------------------Question section-------------------------------------*/
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
            /*---------------------Toggle Button Section----------------------------------*/
            generateCustomButtons();


            currentPosition += 5;
            setCurrentSize(getCurrentSize()-5);
        }
        else {
            switch(size){
                case 5:
                    question = (TextView) rootView.findViewById(R.id.preg1);
                    question.setText(querys.lista.get(currentPosition));
                    question = (TextView) rootView.findViewById(R.id.preg2);
                    question.setText(querys.lista.get(currentPosition+1));
                    question = (TextView) rootView.findViewById(R.id.preg3);
                    question.setText(querys.lista.get(currentPosition+2));
                    question = (TextView) rootView.findViewById(R.id.preg4);
                    question.setText(querys.lista.get(currentPosition+3));
                    question = (TextView) rootView.findViewById(R.id.preg5);
                    question.setVisibility(View.INVISIBLE);
                    tb1=(ToggleButton)rootView.findViewById(R.id.tb1);
                    tb1.setChecked(false);
                    tb1.setText("No");
                    tb1.setTextOn("SI");
                    tb1.setTextOff("NO");
                    tb2=(ToggleButton)rootView.findViewById(R.id.tb2);
                    tb2.setChecked(false);
                    tb2.setText("No");
                    tb2.setTextOn("SI");
                    tb2.setTextOff("NO");
                    tb3=(ToggleButton)rootView.findViewById(R.id.tb3);
                    tb3.setChecked(false);
                    tb3.setText("No");
                    tb3.setTextOn("SI");
                    tb3.setTextOff("NO");
                    tb4=(ToggleButton)rootView.findViewById(R.id.tb4);
                    tb4.setChecked(false);
                    tb4.setText("No");
                    tb4.setTextOn("SI");
                    tb4.setTextOff("NO");
                    tb5=(ToggleButton)rootView.findViewById(R.id.tb5);
                    tb5.setVisibility(View.INVISIBLE);
                    currentPosition += 4;
                    setCurrentSize(getCurrentSize()-4);
                    break;
                case 4:
                    question = (TextView) rootView.findViewById(R.id.preg1);
                    question.setText(querys.lista.get(currentPosition));
                    question = (TextView) rootView.findViewById(R.id.preg2);
                    question.setText(querys.lista.get(currentPosition+1));
                    question = (TextView) rootView.findViewById(R.id.preg3);
                    question.setText(querys.lista.get(currentPosition+2));
                    question = (TextView) rootView.findViewById(R.id.preg4);
                    question.setVisibility(View.INVISIBLE);
                    question = (TextView) rootView.findViewById(R.id.preg5);
                    question.setVisibility(View.INVISIBLE);
                    tb1=(ToggleButton)rootView.findViewById(R.id.tb1);
                    tb1.setChecked(false);
                    tb1.setText("No");
                    tb1.setTextOn("SI");
                    tb1.setTextOff("NO");
                    tb2=(ToggleButton)rootView.findViewById(R.id.tb2);
                    tb2.setChecked(false);
                    tb2.setText("No");
                    tb2.setTextOn("SI");
                    tb2.setTextOff("NO");
                    tb3=(ToggleButton)rootView.findViewById(R.id.tb3);
                    tb3.setChecked(false);
                    tb3.setText("No");
                    tb3.setTextOn("SI");
                    tb3.setTextOff("NO");
                    tb4=(ToggleButton)rootView.findViewById(R.id.tb4);
                    tb4.setVisibility(View.INVISIBLE);
                    tb5=(ToggleButton)rootView.findViewById(R.id.tb5);
                    tb5.setVisibility(View.INVISIBLE);
                    currentPosition += 3;
                    setCurrentSize(getCurrentSize()-3);
                    break;
                case 3:
                    question = (TextView) rootView.findViewById(R.id.preg1);
                    question.setText(querys.lista.get(currentPosition));
                    question = (TextView) rootView.findViewById(R.id.preg2);
                    question.setText(querys.lista.get(currentPosition+1));
                    question = (TextView) rootView.findViewById(R.id.preg3);
                    question.setVisibility(View.INVISIBLE);
                    question = (TextView) rootView.findViewById(R.id.preg4);
                    question.setVisibility(View.INVISIBLE);
                    question = (TextView) rootView.findViewById(R.id.preg5);
                    question.setVisibility(View.INVISIBLE);
                    tb1=(ToggleButton)rootView.findViewById(R.id.tb1);
                    tb1.setChecked(false);
                    tb1.setText("No");
                    tb1.setTextOn("SI");
                    tb1.setTextOff("NO");
                    tb2=(ToggleButton)rootView.findViewById(R.id.tb2);
                    tb2.setChecked(false);
                    tb2.setText("No");
                    tb2.setTextOn("SI");
                    tb2.setTextOff("NO");
                    tb3=(ToggleButton)rootView.findViewById(R.id.tb3);
                    tb3.setVisibility(View.INVISIBLE);
                    tb4=(ToggleButton)rootView.findViewById(R.id.tb4);
                    tb4.setVisibility(View.INVISIBLE);
                    tb5=(ToggleButton)rootView.findViewById(R.id.tb5);
                    tb5.setVisibility(View.INVISIBLE);
                    currentPosition += 2;
                    setCurrentSize(getCurrentSize()-2);
                    break;
                case 2:
                    question = (TextView) rootView.findViewById(R.id.preg1);
                    question.setText(querys.lista.get(currentPosition));
                    question.setVisibility(View.INVISIBLE);
                    question.setText(querys.lista.get(currentPosition+1));
                    question = (TextView) rootView.findViewById(R.id.preg3);
                    question.setVisibility(View.INVISIBLE);
                    question = (TextView) rootView.findViewById(R.id.preg4);
                    question.setVisibility(View.INVISIBLE);
                    question = (TextView) rootView.findViewById(R.id.preg5);
                    question.setVisibility(View.INVISIBLE);
                    tb1=(ToggleButton)rootView.findViewById(R.id.tb1);
                    tb1.setChecked(false);
                    tb1.setText("No");
                    tb1.setTextOn("SI");
                    tb1.setTextOff("NO");
                    tb2=(ToggleButton)rootView.findViewById(R.id.tb2);
                    tb3.setVisibility(View.INVISIBLE);
                    tb3=(ToggleButton)rootView.findViewById(R.id.tb3);
                    tb3.setVisibility(View.INVISIBLE);
                    tb4=(ToggleButton)rootView.findViewById(R.id.tb4);
                    tb4.setVisibility(View.INVISIBLE);
                    tb5=(ToggleButton)rootView.findViewById(R.id.tb5);
                    tb5.setVisibility(View.INVISIBLE);
                    currentPosition += 1;
                    setCurrentSize(getCurrentSize()-1);
                    break;
                case 1:
                    resetCurrentSize();
                    Fragment fragment= new Sec_result();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    break;
                default:break;
            }

        }
    }

    private void generateCustomButtons() {
        tb1=(ToggleButton)rootView.findViewById(R.id.tb1);
        tb1.setChecked(false);
        tb1.setText("No");
        tb1.setTextOn("SI");
        tb1.setTextOff("NO");
        tb2=(ToggleButton)rootView.findViewById(R.id.tb2);
        tb2.setChecked(false);
        tb2.setText("No");
        tb2.setTextOn("SI");
        tb2.setTextOff("NO");
        tb3=(ToggleButton)rootView.findViewById(R.id.tb3);
        tb3.setChecked(false);
        tb3.setText("No");
        tb3.setTextOn("SI");
        tb3.setTextOff("NO");
        tb4=(ToggleButton)rootView.findViewById(R.id.tb4);
        tb4.setChecked(false);
        tb4.setText("No");
        tb4.setTextOn("SI");
        tb4.setTextOff("NO");
        tb5=(ToggleButton)rootView.findViewById(R.id.tb5);
        tb5.setChecked(false);
        tb5.setText("No");
        tb5.setTextOn("SI");
        tb5.setTextOff("NO");
    }

    /*-------------get & set values for table size---------------------------*/
    public int getCurrentSize() {
        return tableSize;
    }
    public void setCurrentSize(int size) {
        this.tableSize = size;
    }
    public void resetCurrentSize() {
        tableSize = 0;
    }
    /*-----------------Count for question result--------------------------------*/
    public void getResultByUser(){
        if(tb1.isChecked() && getCurrentSize()>1){
            if(querysAnswer.lista.get(currentPosition).equals("A")){
                art++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("C")){
                adm++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("D")){
                def++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("E")){
                cien++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("H")){
                hum++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("I")){
                ing++;
            }
            if(querysAnswer.lista.get(currentPosition).equals("S")){
                med++;
            }
        }
        if(tb2.isChecked() && getCurrentSize()>2){
            if(querysAnswer.lista.get(currentPosition+1).equals("A")){
                art++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("C")){
                adm++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("D")){
                def++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("E")){
                cien++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("H")){
                hum++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("I")){
                ing++;
            }
            if(querysAnswer.lista.get(currentPosition+1).equals("S")){
                med++;
            }
        }
        if(tb3.isChecked() && getCurrentSize()>3){
            if(querysAnswer.lista.get(currentPosition+2).equals("A")){
                art++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("C")){
                adm++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("D")){
                def++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("E")){
                cien++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("H")){
                hum++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("I")){
                ing++;
            }
            if(querysAnswer.lista.get(currentPosition+2).equals("S")){
                med++;
            }
        }
        if(tb4.isChecked() && getCurrentSize()>4){
            if(querysAnswer.lista.get(currentPosition+3).equals("A")){
                art++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("C")){
                adm++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("D")){
                def++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("E")){
                cien++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("H")){
                hum++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("I")){
                ing++;
            }
            if(querysAnswer.lista.get(currentPosition+3).equals("S")){
                med++;
            }
        }
        if(tb5.isChecked() && getCurrentSize()>5){
            if(querysAnswer.lista.get(currentPosition+4).equals("A")){
                art++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("C")){
                adm++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("D")){
                def++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("E")){
                cien++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("H")){
                hum++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("I")){
                ing++;
            }
            if(querysAnswer.lista.get(currentPosition+4).equals("S")){
                med++;
            }
        }
        Log.i("Ingeniera", "value" + ing);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        status[0] = querys.lista.get(currentPosition-5);
        status[1] = querys.lista.get(currentPosition-4);
        status[2] = querys.lista.get(currentPosition-3);
        status[3] = querys.lista.get(currentPosition-2);
        status[4] = querys.lista.get(currentPosition-1);
        outState.putStringArray("values", status);

    }
}
