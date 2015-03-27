package com.example.nuuk.nuukappmobile;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    ProgressIndicatorbar mProgressIndicator3;
    ImageView img;
    float max = 1;
    float update = 0;
    boolean threadRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mProgressIndicator3 = (ProgressIndicatorbar) findViewById(R.id.determinate_progress_indicator3);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(),getWindowManager().getDefaultDisplay().getWidth());
        params2.setMargins(0,getWindowManager().getDefaultDisplay().getHeight()/20,0,0);
        mProgressIndicator3.setLayoutParams(params2);
        mProgressIndicator3.setForegroundColor(Color.parseColor("#4a148c"));
        mProgressIndicator3.setBackgroundColor(Color.parseColor("#ce93d8"));
        startThread();

    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadRunning = true;
                update = 0;
                while(update <= max){
                    update += 0.005;
                    updateProgressIndicatorValue();
                    try{
                        Thread.sleep(50);
                    }catch(Exception e){

                    }
                }
                threadRunning = false;
            }
        }).start();
    }

    private void updateProgressIndicatorValue() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mProgressIndicator3.setValue(update);

            }
        });
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
