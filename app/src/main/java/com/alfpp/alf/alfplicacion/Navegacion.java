package com.alfpp.alf.alfplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class Navegacion extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toast toast;
        toast = Toast.makeText(getApplicationContext(),"1", Toast.LENGTH_SHORT);
        //toast.show();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mTitle ="Principal";
        // Set up the drawer.
        Bundle bundle = getIntent().getExtras();
        mNavigationDrawerFragment.selectItem(bundle.getInt("Opcion"));
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        Toast toast1;
        toast1 = Toast.makeText(getApplicationContext(),"1", Toast.LENGTH_SHORT);
        //toast1.show();

        toast1 = Toast.makeText(getApplicationContext(),bundle.getString("Pantalla"), Toast.LENGTH_SHORT);
        toast1.show();
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Navegacion.this, Runalftic.class);
        startActivity(intent);
        finish();

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
                mNavigationDrawerFragment.setUp(
                        R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));

                break;

            case 3:
                toast1 = Toast.makeText(getApplicationContext(),
                        "S3", Toast.LENGTH_SHORT);

                toast1.show();
                mTitle = getString(R.string.title_section3);
                mNavigationDrawerFragment.setUp(
                        R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
                break;
        }
    }

    private void MostrarFragment(int position){
        android.app.Fragment fragment = null;
        Toast toast1;
        switch (position){
            case 0://CabeceraPerfil
                fragment = new Perfil();
                toast1 = Toast.makeText(getApplicationContext(),"Perfil", Toast.LENGTH_SHORT);
                //toast1.show();
                break;
            case 1://CarreraNormal
                Intent intent = new Intent(Navegacion.this, Runalftic.class);
                startActivity(intent);
                finish();
                break;

            case 2://TestCooper
                fragment = new Cooper();
                toast1 = Toast.makeText(getApplicationContext(),"Sesion", Toast.LENGTH_SHORT);
                //toast1.show();
                break;

            case 3:
                break;

            case 4://Historial
                fragment = new ItemFragment();

            break;
            case 5://PERFIL->Acerca De

                break;
        }
        if (fragment != null){
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
