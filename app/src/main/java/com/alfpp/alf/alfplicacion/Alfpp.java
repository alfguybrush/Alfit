package com.alfpp.alf.alfplicacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;


public class Alfpp extends Activity implements OnClickListener {
    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.main);

        Button SigIn = (Button) findViewById(R.id.start_button);
        SigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viev) {
                Intent intent = new Intent(Alfpp.this, Navegacion.class);
                startActivity(intent);
            }
        });

        Button Inicio = (Button) findViewById(R.id.in_button);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                BaseDatosAlfpp BD = new BaseDatosAlfpp(getApplicationContext());

                Intent intent = new Intent(Alfpp.this, Runalftic.class);
                startActivity(intent);
                finish();
            }

        });





        /*
        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.reset_button)).setOnClickListener(this);

*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alfpp, menu);
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

    @Override
    public void onClick(View v) {

    }
}
