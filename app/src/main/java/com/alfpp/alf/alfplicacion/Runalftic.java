package com.alfpp.alf.alfplicacion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.Locale;

public class Runalftic extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    //******************INICIALIZACION VARIABLES******************************//
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    String TextSpeech;
    double latitudIni,longitudIni,distancia,velMedia, tiempo;
    private Chronometer chronometer;
    boolean Initiated, Paused;
    DecimalFormat df;
    LocationManager mlocManager;
    MyLocationListener mlocListener;
    boolean lock;
    int contador;
    float pesoUser;
    float edadUser;
    TextView tvVeloc, tvAltu, tvDist,tvVelocGps,tvVo2;
    TextToSpeech t1;
    AlertDialog.Builder dialogo;
    public GoogleMap map;
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    boolean primero;
    long timeWhenStopped;
    Locale locSpanish;
    //******************FIN INICIALIZACION VARIABLES******************************//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runalftic);
        Bundle bundle = getIntent().getExtras();
        TextView nombre = (TextView) findViewById(R.id.tv_nombre_header);
        nombre.setText(bundle.getString("Usuario"));

        latitudIni = 0;
        longitudIni=0;
        distancia = 0;
        Initiated = false;
        lock = false;
        timeWhenStopped = 0;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout2));
        mNavigationDrawerFragment.selectItem(1);
        IniciaMapa();
        IniciaLocation();
        IniciaDialogo();



        df = new DecimalFormat("0.00");
        tvAltu = (TextView) findViewById(R.id.tv_altu);
        tvDist = (TextView) findViewById(R.id.tv_dist);
        tvVeloc = (TextView) findViewById(R.id.tv_veloc);
        tvVelocGps = (TextView) findViewById(R.id.tv_velocGps);
        tvVo2 = (TextView) findViewById(R.id.tv_vo2);
        chronometer = (Chronometer) findViewById(R.id.chrono);
        chronometer.setTextSize(60);
        chronometer.setTextColor(-1);
        locSpanish= new Locale("spa", "ESP");
        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result=t1.setLanguage(locSpanish);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        // Log.e("error", "This Language is not supported");
                    }
                    else{
                        //ConvertTextToSpeech();
                    }
                }
                //else
                // Log.e("error", "Initilization Failed!");
            }
        });

        //***********CONFIGURACION BOTONES******************//
        final ImageButton Resume = (ImageButton) findViewById(R.id.bt_resume);
        final ImageButton Parada = (ImageButton) findViewById(R.id.bt_stop);
        final ImageButton Pause = (ImageButton) findViewById(R.id.bt_pause);
        final ImageButton Inicio = (ImageButton) findViewById(R.id.bt_start);
        Inicio.setVisibility(View.VISIBLE);
        Pause.setVisibility(View.INVISIBLE);
        Resume.setVisibility(View.INVISIBLE);


        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inicio.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.VISIBLE);
                Resume.setVisibility(View.INVISIBLE);
                Start();

            }

        });


        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inicio.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.INVISIBLE);
                Resume.setVisibility(View.VISIBLE);

                Paused();


            }

        });
        Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inicio.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.VISIBLE);
                Resume.setVisibility(View.INVISIBLE);
                Resume();


            }

        });

        Parada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Hacer aqui lo de los botones para evitar crear mas variables
                Inicio.setVisibility(View.VISIBLE);
                Pause.setVisibility(View.INVISIBLE);
                Resume.setVisibility(View.INVISIBLE);
                Stop();
            }

        });



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
        MostrarFragment(position);

    }
    private void MostrarFragment(int position){
        Intent intent=null;
        switch (position) {
            case 0:

                mTitle = getString(R.string.title_section1);
                intent = new Intent(Runalftic.this, Navegacion.class);
                intent.putExtra("Pantalla", "Sus Castas toas");
                intent.putExtra("Opcion", 0);

                break;

            case 1:

                break;

            case 2:
                mTitle = getString(R.string.title_section1);
                intent = new Intent(Runalftic.this, Navegacion.class);
                intent.putExtra("Pantalla", "Cooper");
                intent.putExtra("Opcion", 2);

                break;


            case 3:
                mTitle = getString(R.string.title_section4);
                intent = new Intent(Runalftic.this, Navegacion.class);
                intent.putExtra("Pantalla", "Historial");
                intent.putExtra("Opcion", 3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                intent = new Intent(Runalftic.this, Navegacion.class);
                intent.putExtra("Pantalla", "Perfil");
                intent.putExtra("selected_navigation_drawer_position", 4);
                break;

        }
        if(intent!=null){
            startActivity(intent);
            mlocManager.removeUpdates(mlocListener);
            finish();
        }


    }

    public void onSectionAttached(int number) {
        Intent intent=null;
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);

                break;
            case 2:
                mTitle = getString(R.string.title_section2);

                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
        if(intent!=null){
            startActivity(intent);
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
            getMenuInflater().inflate(R.menu.runalftic, menu);
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




    //INICIALIZACION DE LOCALIZACION
    private void IniciaLocation(){
        mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);
        mlocListener.setMainActivity(this);
        String provider = LocationManager.NETWORK_PROVIDER;
        Location fastLocation = mlocManager.getLastKnownLocation(provider);
        double latprincipio = fastLocation.getLatitude();
        double longprincipio = fastLocation.getLongitude();
        LatLng latLng = new LatLng(latprincipio,longprincipio);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

// Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }






    //************INICIALIZAMOS MAPFragment************///
    private void IniciaMapa(){
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapa)).getMap();
         map.setMyLocationEnabled(true);
        if (map!=null){
            Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
                    .title("Hamburg"));
            Marker kiel = map.addMarker(new MarkerOptions()
                    .position(KIEL)
                    .title("Kiel")
                    .snippet("Kiel is cool")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.punto)));
        }

    }


    private void IniciaDialogo(){
        dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Atencion");
        dialogo.setMessage("¿Hay una actividad en ejecución desea terminarla?");
        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                Stop();
            }
        });
        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogo1, int id) {
                // NADA
            }
        });
    }
    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        //text = et.getText().toString();
        String text = "";
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





    //FUNCION FINALIZACION DE CARRERA
    private void Start(){
        //PRIMERAVEZ QUE SE INICIALIZAN LAS latitud
            primero =true;
            TextSpeech = "Actividad Iniciada";
            t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
            contador = 0;


            //Inicializamos Variables
            tvAltu.setText("Altura \n(m):" + "\n" + "0.0");
            tvDist.setText("Distancia \n(Km):" + "\n" + "0.0");
            tvVeloc.setText("Velocidad  \n(Km/h):" + "\n" + "0.0");
            tvVelocGps.setText("Velocidad \n(Km/h):" + "\n" + "0.0");
            velMedia = 0;
            distancia = 0;

// Returns last known location, this is the fastest way to get a location fix.

            //String texto = "Localizaci?n Inicial: Latitud = "+latitudIni+" Longitud = "+longitudIni+" ";
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            Paused = false;
            Initiated = true;
            timeWhenStopped = 0;


    }

    //FUNCION FINALIZACION DE CARRERA
    private void Stop(){
        if(Initiated || Paused) {
            TextSpeech = "Actividad Finalizada Jodido Garulo";
            distancia = 0;
            t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
            chronometer.stop();
            Paused = false;
            Initiated = false;
        }


    }




    private void Paused(){
        Paused = true;
        TextSpeech="Activity Paused";
        t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
        //Al parar se vuelve a tomar coordenadas de origen
        primero = true;
        timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
        chronometer.stop();
    }

    private void Resume(){
        Paused = false;
        TextSpeech="Activity Resumed";
        t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();

    }




    private class MyLocationListener implements LocationListener {
        Runalftic activityRun;
        public Activity getMainActivity() {
            return activityRun;
        }

        public void setMainActivity(Runalftic mainActivity) {
            this.activityRun = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este m?todo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detecci?n de un cambio de ubicacion
            if(Initiated && !Paused) {
                if (lock) {
                    TextSpeech = "Activity Paused";
                    t1.speak(TextSpeech, 0, null, "lectura de Velocidad");
                } else {
                    if (loc.hasAccuracy()) {
                        if(primero){
                            latitudIni = loc.getLatitude();
                            longitudIni = loc.getLongitude();
                            primero = false;
                        }else {
                            lock = true;
                            String Sdistancia, Svelocidad, svelocidadGPS, sdistanciaDif;
                            String text1, text2;
                            double lat = loc.getLatitude();
                            double lon = loc.getLongitude();
                            double dist = getDistance(latitudIni, longitudIni, lat, lon);
                            LatLng latLng = new LatLng(lat, lon);
                            longitudIni = lon;
                            latitudIni = lat;
                            Marker Posicion = map.addMarker(new MarkerOptions().position(latLng)
                                    .title("Alf").snippet("Ha HA")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.punto)));
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            //String Text = "Mi ubicaci?n actual es: " + "\n Lat = "+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
                            //Calculo de distancia recorrida
                            double elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                            tiempo = elapsedMillis / 1000;

                            //DISTANCIA RECORRIDA


                            distancia = distancia + dist;//falta +distancia
                            Sdistancia = df.format(distancia);
                            sdistanciaDif = df.format(dist);
                            text1 = "Distancia  \n(Km):" + "\n" + Sdistancia;
                            tvDist.setText(text1);
                            text1 = "Distancia rec \n(Km):" + "\n" + sdistanciaDif;
                            tvVo2.setText(text1);


                            //Cambio latitud y longitud Inicial


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
                            lock = false;
                        }

                    }

                }

            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este m?todo se ejecuta cuando el GPS es desactivado
            //.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este m?todo se ejecuta cuando el GPS es activado
            //messageTextView.setText("GPS Activado");
        }


        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este m?todo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizaci?n (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temp?ralmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }


    }/* End of Class MyLocationListener */
}
