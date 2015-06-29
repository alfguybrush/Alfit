package com.alfpp.alf.alfplicacion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class Navegacion extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private int seccion;
    AlertDialog.Builder dialogo;
    int id;
    BaseDatosAlfpp BD;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toast toast;
        toast = Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT);
        //toast.show();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.


        Bundle bundle = getIntent().getExtras();
        TextView nombre = (TextView) findViewById(R.id.tv_nombre_header);
        id =bundle.getInt("idUsuario");
        Toast toast1;
        toast1 = Toast.makeText(getApplicationContext(),Integer.toString(id), Toast.LENGTH_SHORT);
        toast1.show();

        BD = new BaseDatosAlfpp(getApplicationContext());
        String name = BD.getNombre(id);
        nombre.setText(name);
        seccion = bundle.getInt("Opcion");
        mNavigationDrawerFragment.selectItem(seccion);
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));




        toast1 = Toast.makeText(getApplicationContext(),bundle.getString("Pantalla"), Toast.LENGTH_SHORT);
        toast1.show();
    }
    private void IniciaDialogo(){
        dialogo = new AlertDialog.Builder(this);
        String encodedText = Html.fromHtml("Atenci&oacute;n").toString();
        dialogo.setTitle(encodedText);
        encodedText = Html.fromHtml("El Test de Cooper no puede pausarse, Si hay una actividad en curso terminar&aacute; \n¿Desea finalizar la actividad?").toString();
        dialogo.setMessage(encodedText);
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                Intent intent = new Intent(Navegacion.this, Runalftic.class);
                intent.putExtra("idUsuario",id);
                startActivity(intent);
                finish();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                // NADA
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        if (seccion == 2) {
            IniciaDialogo();
            dialogo.show();
        }else{
            Intent intent = new Intent(Navegacion.this, Runalftic.class);
            intent.putExtra("com.alfpp.alf.alfplicacion.Usuario", "defecto");
            startActivity(intent);
            finish();
        }

    }









    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        MostrarFragment(position );



    }

    public void onSectionAttached(int number) {

        Toast toast1;
        switch (number) {
            case 1:
                toast1 = Toast.makeText(getApplicationContext(),
                        "S1", Toast.LENGTH_SHORT);

                toast1.show();
                mTitle = getString(R.string.title_section1);


                break;
            case 2:
                toast1 = Toast.makeText(getApplicationContext(),
                        "S2", Toast.LENGTH_SHORT);

                toast1.show();
                mTitle = getString(R.string.title_section2);

                break;

            case 3:
                toast1 = Toast.makeText(getApplicationContext(),
                        "S3", Toast.LENGTH_SHORT);

                toast1.show();
                mTitle = getString(R.string.title_section3);

                break;
        }
    }

    private void MostrarFragment(int position){
        android.app.Fragment fragment = null;
        ActionBar actionBar = getSupportActionBar();
        Intent intent;
        switch (position){
            case 0://CabeceraPerfil
                fragment = new Perfil();
                mTitle = "Perfil";
                break;
            case 1://CarreraNormal
                intent = new Intent(Navegacion.this, Runalftic.class);
                intent.putExtra("idUsuario", id);
                startActivity(intent);
                finish();
                break;

            case 2:// Cooper
                fragment = new Cooper();
                mTitle="Test de Cooper";
                break;

            case 3:
                fragment = new ItemFragment();
                mTitle = "Historial";
                break;

            case 4://Vo2
                fragment = new QueEsVo2();
                mTitle = "Que es Vo2";


            break;
            case 5://Resumen
                intent = new Intent(Navegacion.this, Resumen.class);
                startActivity(intent);
                finish();


                break;
        }
        if (fragment != null){

            actionBar.setTitle(mTitle);
            Bundle bundle = new Bundle();
            bundle.putInt("id",1);
            fragment.setArguments(bundle);
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.containerdrawer,fragment).commit();

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */




}
