package com.example.nuuk.nuukappmobile;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Izzy-Izumi on 05/04/2015.
 */
public class Pager_schools extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public Pager_schools(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

}
