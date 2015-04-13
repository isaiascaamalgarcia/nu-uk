package com.example.nuuk.nuukappmobile;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import java.util.List;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class Pager_schools extends FragmentStatePagerAdapter {

    private int[] imageResId = {
            R.drawable.ic_info,
            R.drawable.ic_majors,
            R.drawable.ic_map
    };
    String varString;
    Context ctx;
    private List<Fragment> fragments;

    public Pager_schools(FragmentManager fm, List<Fragment> fragments,Context ctx,String var) {
        super(fm);
        this.fragments = fragments;
        this.ctx = ctx;
        this.varString = var;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                Fragment finfoschool = new frag_infoschools();
                Bundle inf= new Bundle();
                inf.putString("QUERY",varString);
                finfoschool.setArguments(inf);
                return finfoschool;

            case 1:
                Fragment finfoschool1 = new frag_carrersonschool();
                Bundle inf1= new Bundle();
                inf1.putString("QUERYC",varString);
                finfoschool1.setArguments(inf1);
                return finfoschool1;
            case 2:
                Fragment finfoschool2 = new Map();
                Bundle inf2= new Bundle();
                inf2.putString("QUERYM",varString);
                finfoschool2.setArguments(inf2);
                return finfoschool2;

            default: return this.fragments.get(i);
        }

    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        Drawable image = this.ctx.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
