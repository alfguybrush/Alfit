package com.alfpp.alf.alfplicacion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class TestCooper extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static double getVO2max(double dist,double kgs){
        //EL VO2 max va en funciòn de la distancia (km) recorrida en los 12 minutos.
        double VO2 = 22.351*dist-11.288;
        VO2 = VO2*kgs;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_test_cooper, menu);
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
