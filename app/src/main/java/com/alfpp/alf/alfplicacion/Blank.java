package com.alfpp.alf.alfplicacion;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Blank extends ActionBarActivity {

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Blank.this, Runalftic.class);
        intent.putExtra("Usuario", "defecto");
        finish();
        startActivity(intent);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        Fragment fragment = new graficaVelocidad();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Contenedor_grafica,fragment).commit();
        ImageButton boton_velocidad = (ImageButton) findViewById(R.id.bt_grafica_velocidad);
        ImageButton boton_altura = (ImageButton) findViewById(R.id.bt_grafica_altura);
        ImageButton boton_intensidad = (ImageButton) findViewById(R.id.bt_grafica_intensidad);
        boton_velocidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new graficaVelocidad();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contenedor_grafica,fragment).commit();

            }

        });
        boton_altura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new graficaAltura();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.Contenedor_grafica,fragment).commit();

            }

        });



        // Setting Custom Color for the Scroll bar indicator of the Tab View
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blank, menu);
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
