package com.alfpp.alf.alfplicacion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.Locale;


public class CarreraNormal extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    String TextSpeech;
    double latitudIni,longitudIni,distancia,velMedia, tiempo;
    private Chronometer chronometer;
    boolean Initiated;
    DecimalFormat df;
    LocationManager mlocManager;
    MyLocationListener mlocListener;
    int contador;
    float pesoUser;
    float edadUser;
    TextView tvVeloc, tvAltu, tvDist,tvVelocGps;
    TextToSpeech t1;
    AlertDialog.Builder dialogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Atencion");
        dialogo.setMessage("¿Hay una actividad en ejecución desea terminarla?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                Stop();
            }
        });
        dialogo.setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogo1, int id){
                cancelar();
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Initiated = false;
        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);
        mlocListener.setMainActivity(CarreraNormal.this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mTitle ="CarreraNormal";
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        df = new DecimalFormat("0.00");
        tvAltu = (TextView) findViewById(R.id.tv_altu);
        tvDist = (TextView) findViewById(R.id.tv_dist);
        tvVeloc = (TextView) findViewById(R.id.tv_veloc);
        tvVelocGps = (TextView) findViewById(R.id.tv_velocGps);
        chronometer = (Chronometer) findViewById(R.id.chrono);
        chronometer.setTextSize(60);
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result=t1.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                       // Log.e("error", "This Language is not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
               //else
                   // Log.e("error", "Initilization Failed!");
            }
        });
        Button Pause = (Button) findViewById(R.id.bt_pause);
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextSpeech="Activity Paused";
                t1.speak(TextSpeech, 0, null,"lectura de Velocidad");

            }

        });
        Button Parada = (Button) findViewById(R.id.bt_stop);
        Parada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stop();
            }

        });
        Button Inicio = (Button) findViewById(R.id.bt_start);
        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String provider = LocationManager.NETWORK_PROVIDER;
                Location fastLocation = mlocManager.getLastKnownLocation(provider);
                if(fastLocation.hasAccuracy()) {
                    latitudIni = fastLocation.getLatitude();
                    longitudIni = fastLocation.getLongitude();
                    Initiated = true;
                    TextSpeech = "Activity Started";
                    t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
                    contador = 0;


                    //Inicializamos Variables
                    tvAltu.setText("Altura \n(m):" + "\n" + "0.0");
                    tvDist.setText("Distancia \n(Km):" + "\n" + "0.0");
                    tvVeloc.setText("Velocidad Media \n(Km/h):" + "\n" + "0.0");
                    tvVelocGps.setText("Velocidad \n(Km/h):" + "\n" + "0.0");
                    velMedia = 0;
                    distancia = 0;

// Returns last known location, this is the fastest way to get a location fix.

                    //String texto = "Localizaci�n Inicial: Latitud = "+latitudIni+" Longitud = "+longitudIni+" ";
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }
            }

        });

    }
    public void aceptar(){
        Stop();
    }
    public void cancelar(){
        //NADA

    }

    public void Stop(){
        if(Initiated) {
            TextSpeech = "Activity Stopped";
            t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
            chronometer.stop();
            Initiated = false;
        }

    }
    @Override
    public void onBackPressed()
    {
        if(Initiated) {
            dialogo.show();
        }
        else{
        super.onBackPressed();
        this.finish();
    }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                mNavigationDrawerFragment.setUp(
                        R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
                break;

            case 3:
                mTitle = getString(R.string.title_section3);
                break;
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
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((CarreraNormal) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }







    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        //text = et.getText().toString();
        String text = "It's Over Nine Thousand";
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            t1.speak(text, 0, null,"lectura de Velocidad");
        }else
            t1.speak(text, 0, null,"lectura de Velocidad");
    }

    public static double getDistance(double lat_a,double lng_a, double lat_b, double lon_b){
        double Radius = 6372.8; //Radio de la tierra Km
        double dLat = Math.toRadians(lat_b-lat_a);
        double dLon = Math.toRadians(lon_b-lng_a);
        double lat1 = Math.toRadians(lat_a);
        double lat2 = Math.toRadians(lat_b);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon /2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return  (Radius * c);
    }

    public static double getSpeedMinKm(double distance, double time){
        double minutos = time/360;
        double veloc = minutos/distance;
        return veloc;
    }
    //Distancia en Kilometros y tiempo en segundos. Se transforma a Km /h
    public static double getSpeedKmH(double distance, double time){
        double horas = time/3600;
        double veloc = distance/horas;
        return veloc;
    }

    public class MyLocationListener implements LocationListener {
        CarreraNormal activity_carreraNormal;
        public CarreraNormal getMainActivity() {
            return activity_carreraNormal;
        }

        public void setMainActivity(CarreraNormal mainActivity) {
            this.activity_carreraNormal = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este m�todo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detecci�n de un cambio de ubicacion
            if(Initiated) {
                if (loc.hasAccuracy()) {
                    String Sdistancia, Svelocidad, svelocidadGPS;
                    String text1, text2;
                    double lat = loc.getLatitude();
                    double lon = loc.getLongitude();
                    //String Text = "Mi ubicaci�n actual es: " + "\n Lat = "+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
                    //Calculo de distancia recorrida
                    double elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                    tiempo = elapsedMillis / 1000;

                    //DISTANCIA RECORRIDA

                    double dist = getDistance(latitudIni, longitudIni, lat, lon);
                    distancia = distancia + dist;
                    Sdistancia = df.format(distancia);
                    text1 = "Distancia \n(Km):" + "\n" + Sdistancia;
                    tvDist.setText(text1);


                    //Cambio latitud y longitud Inicial
                    longitudIni = lon;
                    latitudIni = lat;

                    // VELOCIDAD
                    double velocidadGPS = loc.getSpeed();
                    double velocMedia = getSpeedKmH(distancia, tiempo);
                    velocidadGPS = velocidadGPS * 3.6; //Paso a Km/h
                    Svelocidad = df.format(velocMedia);
                    svelocidadGPS = df.format(velocidadGPS);
                    text2 = "Velocidad Media\n(km/h)  " + "\n" + Svelocidad;
                    tvVeloc.setText(text2);
                    text2 = "Velocidad GPs\n" + svelocidadGPS;
                    tvVelocGps.setText(text2);


                    //Altura
                    if (loc.hasAltitude()) {

                        text2 = "Altura \n(m):" + "\n" + loc.getAltitude();
                        tvAltu.setText(text2);
                    }

                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es desactivado
            //.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es activado
            //messageTextView.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este m�todo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizaci�n (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temp�ralmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }


    }/* End of Class MyLocationListener */


}
