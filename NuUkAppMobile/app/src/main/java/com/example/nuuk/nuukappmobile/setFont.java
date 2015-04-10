package com.example.nuuk.nuukappmobile;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TextView;

import java.util.ConcurrentModificationException;

/**
 * Created by Izzy-Izumi on 10/04/2015.
 */
public class setFont {
    public void setFontTextView(TextView tv, Context cntx){
        Typeface font = Typeface.createFromAsset(cntx.getAssets(), "fonts/dungeon.ttf");
        int pt = tv.getPaddingTop();
        int pb = tv.getPaddingBottom();
        tv.setTypeface(font);
        tv.setGravity(Gravity.CENTER);
        tv.setPadding(0,pt,0,pb);
    }
}
