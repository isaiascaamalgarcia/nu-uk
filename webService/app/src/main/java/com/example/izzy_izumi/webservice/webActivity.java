package com.example.izzy_izumi.webservice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class webActivity extends ActionBarActivity {

    private EditText cod;
    private EditText nom;
    private EditText tel;
    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        cod = (EditText)findViewById(R.id.editText);
        nom = (EditText)findViewById(R.id.editText2);
        tel = (EditText)findViewById(R.id.editText3);
        boton = (Button)findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cod.getText().toString().trim().equalsIgnoreCase("") ||
                        !nom.getText().toString().trim().equalsIgnoreCase("") ||
                        !tel.getText().toString().trim().equalsIgnoreCase(""))

                    Toast.makeText(webActivity.this, "Hay info",Toast.LENGTH_LONG).show();
                   // new Insertar(webActivity.this).execute();
                else
                    Toast.makeText(webActivity.this, "Hay información por rellenar",Toast.LENGTH_LONG).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
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
