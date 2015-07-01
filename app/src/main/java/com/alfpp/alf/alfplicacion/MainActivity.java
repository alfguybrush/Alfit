package com.alfpp.alf.alfplicacion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    TextView titulo;
    BaseDatosAlfpp BD;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BD = new BaseDatosAlfpp(getApplicationContext());
        ImageButton Inicio = (ImageButton) findViewById(R.id.imageButton);
        CharSequence colors[] = new CharSequence[]{"red", "green", "blue", "black"};
/*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;

                switch (which) {
                    case 0:
                        intent = new Intent(MainActivity.this, Navegacion.class);
                        intent.putExtra("Pantalla", "COOPER");
                        intent.putExtra("Opcion", 2);

                        break;

                    case 1:
                        intent = new Intent(MainActivity.this, Navegacion.class);
                        intent.putExtra("Pantalla", "Que es Vo2");
                        intent.putExtra("Opcion", 4);
                        break;

                    case 2:
                        intent = new Intent(MainActivity.this, MainActivity.class);

                        break;

                }
                if (intent != null) {
                    intent.putExtra("idUsuario", 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
        builder.show();
        */
        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }

        });

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = BD.getUsuario(1);
                if (user == null) {
                    Intent intent = new Intent(MainActivity.this, SignIn.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, Runalftic.class);

                    intent.putExtra("Usuario", user);
                    intent.putExtra("idUsuario", 1);
                    startActivity(intent);
                    finish();
                }

            }
        });

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
