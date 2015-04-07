package com.example.nuuk.nuukappmobile;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Vector;

/**
 * Created by Izzy-Izumi on 04/04/2015.
 */
public class Selected_school extends FragmentActivity{
    ImageView img;
    Bitmap bitmap;
    Pager_schools mPagerAdapter;
    ActionBar abar;
    private String[] tabs = { "", ""};
    final int[] ICONS = new int[] {
            R.drawable.ic_launcher,
            R.drawable.ic_angular,
    };
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_selectedschool);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          //      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        img = (ImageView)findViewById(R.id.schoollogo_round);
        new LoadImage().execute("https://pbs.twimg.com/profile_images/537990267572191232/Tcpm1Ty2.jpeg");
        initializePagin();

    }

    private void initializePagin() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, frag_infoschools.class.getName()));
        fragments.add(Fragment.instantiate(this,frag_carrersonschool.class.getName()));
        mPagerAdapter = new Pager_schools(getSupportFragmentManager(),fragments,this);
        pager = (ViewPager)findViewById(R.id.schoolpager);
        //abar = getActionBar();
        pager.setAdapter(mPagerAdapter);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.rgb(220,115,255);
            }
        });
        slidingTabLayout.setViewPager(pager);
        //abar.setHomeButtonEnabled(false);
        //abar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }


    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
            }else{
                Toast.makeText(Selected_school.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
