package com.alfpp.alf.alfplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class SignIn extends ActionBarActivity {
    int Sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button SigIn = (Button) findViewById(R.id.button_aceptar);


        SigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre,Apellidos, usuario, password;
                int anio, edad, anionac;
                double peso;
                Spinner spinner;
                Sexo = 0;
                spinner = (Spinner)findViewById(R.id.SpinnerSex);
                String SexoString = spinner.getSelectedItem().toString();
                Toast toast = Toast.makeText(getApplicationContext(), SexoString, Toast.LENGTH_SHORT);
                toast.show();

                EditText text = (EditText)findViewById(R.id.editName);
                Nombre = text.getText().toString();

                text = (EditText)findViewById(R.id.editSurName);
                Apellidos = text.getText().toString();

                text = (EditText)findViewById(R.id.editUser);
                usuario = text.getText().toString();

                 text = (EditText)findViewById(R.id.editFecha);
                anionac =Integer.parseInt(text.getText().toString()) ;

                text = (EditText)findViewById(R.id.editPeso);
                peso = Double.parseDouble(text.getText().toString());

                text = (EditText)findViewById(R.id.editPass);
                password = text.getText().toString();

                //Con el año de nacimiento y la fecha actual sacamos la edad (aprox)
                final Calendar c = Calendar.getInstance();
                anio = c.get(Calendar.YEAR);
                edad = anio - anionac;
                //Introduccion en la Base de Datos.

                BaseDatosAlfpp BD = new BaseDatosAlfpp(getApplicationContext());

                BD.insertaUsuario(Nombre, Apellidos, edad, peso ,usuario,password,Sexo);


                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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
