package com.alfpp.alf.alfplicacion;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Cooper extends Fragment {
    double longitudIni,latitudIni,tiempo, distancia,tiempoMax,peso;
    Chronometer chronometer;
    Boolean Initiated = false;
    Boolean primero,terminado;
    DecimalFormat df;
    TextView tvDistancia,tvVelocidad;
    LocationManager mlocManager;
    MyLocationListener mlocListener;
    ImageButton start, stop;
    AlertDialog.Builder dialogo;
    int id;
    BaseDatosAlfpp BD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Bundle bundle = this.getArguments();
        id = bundle.getInt("id",-1);
        tiempoMax = 720; //720 sec = 12 min
        peso = 72; //Obtener de BD
        View V = inflater.inflate(R.layout.fragment_cooper, container, false);
        chronometer = (Chronometer) V.findViewById(R.id.chrono_cooper);
        chronometer.setTextSize(80);
        start = (ImageButton) V.findViewById(R.id.bt_start_cooper);
        stop = (ImageButton) V.findViewById(R.id.bt_stop_cooper);
        tvDistancia = (TextView) V.findViewById(R.id.tv_dist_cooper);
        tvVelocidad = (TextView) V.findViewById(R.id.tv_veloc_cooper);
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        df = new DecimalFormat("0.00");
        IniciaLocation();
        IniciaDialogo();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Start();



            }

        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.show();


            }

        });

        return V;

    }

    private void IniciaDialogo(){
        dialogo = new AlertDialog.Builder(getActivity());
        String encodedText = Html.fromHtml("Atenci&oacute;n").toString();
        dialogo.setTitle(encodedText);
        encodedText = Html.fromHtml("El Test de Cooper no puede pausarse, Â¿Desea finalizar la actividad?").toString();
        dialogo.setMessage(encodedText);
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

    private void Start(){
        Initiated = true;
        primero = true;
        terminado = false;
        start.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.VISIBLE);
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.start();

    }
    private void Stop(){
        start.setVisibility(View.VISIBLE);
        stop.setVisibility(View.INVISIBLE);
        Initiated = false;
        longitudIni = 0;
        latitudIni = 0;
        tiempo  =0;
        chronometer.stop();
        if (terminado){
            double vo2 =getVO2max(distancia,peso);
            BD = new BaseDatosAlfpp(getActivity().getApplicationContext());
            Usuario user;
            user = BD.getUsuario(id);
            user.setVo2(vo2);
        }
    }
    public Cooper() {
        // Required empty public constructor

    }

    public static double getVO2max(double dist,double kgs){
        //EL VO2 max va en funciòn de la distancia (km) recorrida en los 12 minutos.
        double VO2 = (dist*1000 - 504.9)/44.73;
        // COn esto saco el volumen en litrosVO2 = VO2*kgs;
        return VO2;

    }
    //Calculo Teórico del VO2Max en Hombres
    public static double getVO2TeoH(int nivel,int edad,char sexo){
        double V02;
        if(edad <20){
            if(nivel==1){//Excelente
                V02=3000;
            }else{
                if(nivel==2){//Bueno
                    V02=2850;
                }else{
                    if(nivel==3){//Medio
                        V02=2600;
                    }else{
                        if(nivel==4){//Bajo
                            V02=2400;
                        }else{//Muy Bajo
                            V02=2300;
                        }
                    }
                }
            }
        }else{
            if(edad<29){
                if(nivel==1){//Excelente
                    V02=2800;

                }else{
                    if(nivel==2){//Bueno
                        V02=2600;
                    }else{
                        if(nivel==3){//Medio
                            V02=2300;
                        }else{
                            if(nivel==4){//Bajo
                                V02=1900;

                            }else{//Muy Bajo
                                V02=1600;
                            }
                        }
                    }
                }

            }else{
                if(edad<39){
                    if(nivel==1){//Excelente
                        V02=2700;
                    }else{
                        if(nivel==2){//Bueno
                            V02=2500;
                        }else{
                            if(nivel==3){//Medio
                                V02=2100;
                            }else{
                                if(nivel==4){//Bajo
                                    V02=1700;
                                }else{//Muy Bajo
                                    V02=1500;
                                }
                            }
                        }
                    }

                }else{
                    if(edad<49){
                        if(nivel==1){//Excelente
                            V02=2500;
                        }else{
                            if(nivel==2){//Bueno
                                V02=2300;
                            }else{
                                if(nivel==3){//Medio
                                    V02=1900;
                                }else{
                                    if(nivel==4){//Bajo
                                        V02=1550;
                                    }else{//Muy Bajo
                                        V02=1400;
                                    }
                                }
                            }
                        }

                    }else{
                        if(nivel==1){//Excelente
                            V02=2400;
                        }else{
                            if(nivel==2){//Bueno
                                V02=2200;
                            }else{
                                if(nivel==3){//Medio
                                    V02=1800;
                                }else{
                                    if(nivel==4){//Bajo
                                        V02=1450;
                                    }else{//Muy Bajo
                                        V02=1300;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        return V02;
    }

    //Calculo Teórico del VO2Max en Mujeres
    public static double getVO2TeoM(int nivel,int edad,char sexo){
        double V02;
        if(edad <20){
            if(nivel==1){//Excelente
                V02=2300;
            }else{
                if(nivel==2){//Bueno
                    V02=2200;
                }else{
                    if(nivel==3){//Medio
                        V02=1950;
                    }else{
                        if(nivel==4){//Bajo
                            V02=1750;
                        }else{//Muy Bajo
                            V02=1700;
                        }
                    }
                }
            }
        }else{
            if(edad<29){
                if(nivel==1){//Excelente
                    V02=2700;

                }else{
                    if(nivel==2){//Bueno
                        V02=2450;
                    }else{
                        if(nivel==3){//Medio
                            V02=2000;
                        }else{
                            if(nivel==4){//Bajo
                                V02=1650;

                            }else{//Muy Bajo
                                V02=1500;
                            }
                        }
                    }
                }

            }else{
                if(edad<39){
                    if(nivel==1){//Excelente
                        V02=2500;
                    }else{
                        if(nivel==2){//Bueno
                            V02=2350;
                        }else{
                            if(nivel==3){//Medio
                                V02=1950;
                            }else{
                                if(nivel==4){//Bajo
                                    V02=1550;
                                }else{//Muy Bajo
                                    V02=1400;
                                }
                            }
                        }
                    }

                }else{
                    if(edad<49){
                        if(nivel==1){//Excelente
                            V02=2300;
                        }else{
                            if(nivel==2){//Bueno
                                V02=2100;
                            }else{
                                if(nivel==3){//Medio
                                    V02=1700;
                                }else{
                                    if(nivel==4){//Bajo
                                        V02=1350;
                                    }else{//Muy Bajo
                                        V02=1200;
                                    }
                                }
                            }
                        }

                    }else{
                        if(nivel==1){//Excelente
                            V02=2200;
                        }else{
                            if(nivel==2){//Bueno
                                V02=1950;
                            }else{
                                if(nivel==3){//Medio
                                    V02=1550;
                                }else{
                                    if(nivel==4){//Bajo
                                        V02=1250;
                                    }else{//Muy Bajo
                                        V02=1100;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        return V02;
    }
    private void IniciaLocation(){
        mlocManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) mlocListener);
        mlocListener.setMainActivity(getActivity());
        String provider = LocationManager.NETWORK_PROVIDER;

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
    public static double getSpeedKmH(double distance, double time){
        double horas = time/3600;
        double veloc = distance/horas;
        return veloc;
    }

    private class MyLocationListener implements LocationListener {
        Activity activityRun;
        public Activity getMainActivity() {
            return activityRun;
        }

        public void setMainActivity(Activity mainActivity) {
            this.activityRun = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este m?todo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detecci?n de un cambio de ubicacion
            if(Initiated ) {
                if (loc.hasAccuracy()) {
                    if (primero) {
                        latitudIni = loc.getLatitude();
                        longitudIni = loc.getLongitude();
                        primero = false;
                    } else {

                        String Sdistancia, Svelocidad;
                        String text1, text2;
                        double lat = loc.getLatitude();
                        double lon = loc.getLongitude();
                        double dist = getDistance(latitudIni, longitudIni, lat, lon);
                        longitudIni = lon;
                        latitudIni = lat;

                        //Calculo de distancia recorrida
                        double elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                        tiempo = elapsedMillis / 1000;
                        //DISTANCIA RECORRIDA


                        distancia = distancia + dist;//falta +distancia
                        Sdistancia = df.format(distancia);

                        text1 = "Distancia  \n(Km):" + "\n" + Sdistancia;
                        tvDistancia.setText(text1);


                        //Cambio latitud y longitud Inicial


                        // VELOCIDAD
                        double velocidadGPS = loc.getSpeed();
                        velocidadGPS = velocidadGPS * 3.6; //Paso a Km/h
                        Svelocidad = df.format(velocidadGPS);
                        text2 = "Velocidad \n(km/h)  " + "\n" + Svelocidad;
                        tvVelocidad.setText(text2);
                        if(tiempo >tiempoMax){
                            Stop();
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

