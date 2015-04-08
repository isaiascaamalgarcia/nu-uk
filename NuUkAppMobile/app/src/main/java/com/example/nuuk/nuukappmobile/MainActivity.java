package com.example.nuuk.nuukappmobile;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.nuuk.nuukappmobile.NuukClass.ColumnsTables;
import com.example.nuuk.nuukappmobile.SQLite.UpdateRecords;

public class MainActivity extends Activity {

    ProgressIndicatorbar mProgressIndicator3;
    ImageView img;
    float max = 1;
    float update = 0;
    boolean threadRunning = false;
    ColumnsTables columnas= new ColumnsTables();

    private UpdateRecords download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_main);
        mProgressIndicator3 = (ProgressIndicatorbar) findViewById(R.id.determinate_progress_indicator3);
        int _W = getWindowManager().getDefaultDisplay().getWidth();
        int _H = getWindowManager().getDefaultDisplay().getHeight();
        FrameLayout.LayoutParams params2;
        if(_W > _H ){
            params2 = new FrameLayout.LayoutParams(_H,_H);
        }
        else{
            params2 = new FrameLayout.LayoutParams(_W,_W);
        }
        params2.gravity = Gravity.CENTER;
        mProgressIndicator3.setLayoutParams(params2);
        mProgressIndicator3.setForegroundColor(Color.parseColor("#4a148c"));
        mProgressIndicator3.setBackgroundColor(Color.parseColor("#ce93d8"));
        startThread();
        download= new UpdateRecords(this,"carrera",columnas.getTableCarrera());
        download= new UpdateRecords(this,"encuesta",columnas.getTableEncuesta());
        download= new UpdateRecords(this,"escuela",columnas.getTableEscuela());
        download= new UpdateRecords(this,"localidad",columnas.getTableLocalidad());
        download= new UpdateRecords(this,"municipio",columnas.getTableMunicipio());
        download= new UpdateRecords(this,"relacion_escuela",columnas.getTableRelacionEscuela());
        download= new UpdateRecords(this,"resultado_sugerencia",columnas.getTableResultado_Sugerencias());
        download= new UpdateRecords(this,"tipo",columnas.getTableTipo());
        download= new UpdateRecords(this,"usuario",columnas.getTableUsuario());
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
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
                finish();
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
