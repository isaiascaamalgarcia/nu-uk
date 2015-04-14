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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Vector;

/**
 * Created by Izzy-Izumi on 04/04/2015.
 */
public class Selected_school extends Fragment{
    ImageView img;
    Bitmap bitmap;
    Pager_schools mPagerAdapter;
    ActionBar abar;
    private String informacion;
    private String []info;
    private String[] tabs = { "", ""};
    private TextView escuela,lema;

    /*public Selected_school(String informacion)
    {
        this.informacion=informacion;
        info=this.informacion.split(",");
    }*/

    final int[] ICONS = new int[] {
            R.drawable.ic_launcher,
            R.drawable.ic_angular,
    };
    ViewPager pager;
    View rootView;
    @Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_selectedschool);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          //      WindowManager.LayoutParams.FLAG_FULLSCREEN);
        img = (ImageView)findViewById(R.id.schoollogo_round);
        new LoadImage().execute("https://pbs.twimg.com/profile_images/537990267572191232/Tcpm1Ty2.jpeg");
        initializePagin();

    }*/


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        rootView = inflater.inflate(R.layout.lay_selectedschool, container, false);
        this.informacion = getArguments().getString("escuelaInf");
        img = (ImageView)rootView.findViewById(R.id.schoollogo_round);
        new LoadImage().execute("https://pbs.twimg.com/profile_images/537990267572191232/Tcpm1Ty2.jpeg");
        initializePagin();
        escuela=(TextView)rootView.findViewById(R.id._escuela);
        lema=(TextView)rootView.findViewById(R.id._lema);
        info = this.informacion.split(",");
        escuela.setText(info[2]);
        lema.setText("");
        return rootView;
    }



    private void initializePagin() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(getActivity(), frag_infoschools.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(),frag_carrersonschool.class.getName()));
        fragments.add(Fragment.instantiate(getActivity(),Map.class.getName()));
        mPagerAdapter = new Pager_schools(getActivity().getSupportFragmentManager(),fragments,getActivity(),this.informacion);
        pager = (ViewPager)rootView.findViewById(R.id.schoolpager);
        pager.setAdapter(mPagerAdapter);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, 0);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return Color.rgb(220,115,255);
            }
        });
        slidingTabLayout.setViewPager(pager);
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
                //Toast.makeText(Selected_school.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
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
    }*/
}
