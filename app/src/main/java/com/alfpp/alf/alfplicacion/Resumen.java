package com.alfpp.alf.alfplicacion;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;


public class Resumen extends ActionBarActivity {
    LineChart chartAltura,chartIntensidad;
    HorizontalBarChart chartVelocidad;
    HorizontalBarChart chartTiempo;

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Resumen.this, Runalftic.class);
        intent.putExtra("com.alfpp.alf.alfplicacion.Usuario", "defecto");
        finish();
        startActivity(intent);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        Bundle bundle = getIntent().getExtras();
        //ArrayVelocidad ArrayIntensidad ArrayAltura
        ArrayList<Double> velocidad =(ArrayList<Double>) bundle.getSerializable("ArrayVelocidad");
        ArrayList<Double> altura =(ArrayList<Double>) bundle.getSerializable("ArrayAltura");
        ArrayList<Double> intensidad =(ArrayList<Double>) bundle.getSerializable("ArrayIntensidad");
        chartVelocidad = (HorizontalBarChart)findViewById(R.id.chart_velocidad);
        chartAltura = (LineChart) findViewById(R.id.chart_altura);
        chartIntensidad = (LineChart) findViewById(R.id.chart_intensidad);
        setDataBarChartVeloc(velocidad);
        setDataLineAltura(altura);
        setDataLineIntensidad(intensidad);

        chartAltura.setVisibility(View.INVISIBLE);
        chartIntensidad.setVisibility(View.INVISIBLE);

/*


        BarData data = new BarData(labels, dataSet);
        chart.setData(data);
        chart.setDescription("Velocidad Media por Kilometro");
*/

        ImageButton boton_velocidad = (ImageButton) findViewById(R.id.bt_grafica_velocidad);
        ImageButton boton_altura = (ImageButton) findViewById(R.id.bt_grafica_altura);
        ImageButton boton_intensidad = (ImageButton) findViewById(R.id.bt_grafica_intensidad);
        ImageButton boton_tiempo = (ImageButton) findViewById(R.id.bt_grafica_tiempo);
        boton_velocidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartVelocidad.setVisibility(View.VISIBLE);
                chartAltura.setVisibility(View.INVISIBLE);
                chartIntensidad.setVisibility(View.INVISIBLE);
            }

        });
        boton_altura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartVelocidad.setVisibility(View.INVISIBLE);
                chartAltura.setVisibility(View.VISIBLE);
                chartIntensidad.setVisibility(View.INVISIBLE);

            }

        });
        boton_intensidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartVelocidad.setVisibility(View.INVISIBLE);
                chartAltura.setVisibility(View.INVISIBLE);
                chartIntensidad.setVisibility(View.VISIBLE);
            }

        });



        // Setting Custom Color for the Scroll bar indicator of the Tab View
    }

    private void setDataLineAltura(ArrayList<Double> arrayValores){

        chartAltura.setDrawGridBackground(false);
        chartAltura.setDescription("Gráfica ejemplo Altura");

        chartAltura.setHighlightEnabled(true);
        chartAltura.setTouchEnabled(true);
        chartAltura.setDragEnabled(true);
        chartAltura.setScaleEnabled(true);

        chartAltura.setPinchZoom(true);

        LimitLine ll1 = new LimitLine(1500f, "Upper Limit");
        ll1.setLineWidth(10f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll1.setTextSize(20f);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll2.setTextSize(10f);

        YAxis leftAxis = chartAltura.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaxValue(1600F);
        leftAxis.setAxisMinValue(-50f);
        leftAxis.setStartAtZero(false);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        chartAltura.getAxisRight().setEnabled(false);
        ArrayList<Entry> yVals = new ArrayList<Entry>();


        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < arrayValores.size(); i++) {
            xVals.add((i) + "");
            yVals.add(new Entry(arrayValores.get(i).floatValue(), i));
        }
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        //set1.setColor(Color.BLACK);
        //.setCircleColor(Color.BLACK);
        set1.setLineWidth(5f);
        set1.setCircleSize(10f);
        set1.setValueTextSize(20f);
        set1.setFillAlpha(65);
        set1.setDrawCubic(true);
        set1.setFillColor(Color.CYAN);
        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData dataLine = new LineData(xVals, dataSets);

        // set data
        chartAltura.setData(dataLine);

    }
    private void setDataLineIntensidad(ArrayList<Double> arrayValores){

        chartIntensidad.setDrawGridBackground(false);
        chartIntensidad.setDescription("Gráfica ejemplo Altura");

        chartIntensidad.setHighlightEnabled(true);
        chartIntensidad.setTouchEnabled(true);
        chartIntensidad.setDragEnabled(true);
        chartIntensidad.setScaleEnabled(true);

        chartIntensidad.setPinchZoom(true);

        LimitLine ll1 = new LimitLine(40f, "Upper Limit");
        ll1.setLineWidth(10f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll1.setTextSize(20f);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.POS_RIGHT);
        ll2.setTextSize(10f);

        YAxis leftAxis = chartAltura.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaxValue(100F);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setStartAtZero(false);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        chartIntensidad.getAxisRight().setEnabled(false);
        ArrayList<Entry> yVals = new ArrayList<Entry>();


        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < arrayValores.size(); i++) {
            xVals.add((i) + "");
            yVals.add(new Entry(arrayValores.get(i).floatValue(), i));
        }
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        //set1.setColor(Color.BLACK);
        //.setCircleColor(Color.BLACK);
        set1.setLineWidth(5f);
        set1.setCircleSize(10f);
        set1.setValueTextSize(20f);
        set1.setFillAlpha(65);
        set1.setDrawCubic(true);
        set1.setFillColor(Color.CYAN);
        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData dataLine = new LineData(xVals, dataSets);

        // set data
        chartIntensidad.setData(dataLine);

    }

    private void setDataBarChartVeloc(ArrayList<Double> arrayValores){
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        for (int i = 0; i<arrayValores.size(); i++ ) {
            entries.add(new BarEntry(arrayValores.get(i).floatValue(), i));
            labels.add("Kilometro "+Integer.toString(i));
        }
        BarDataSet dataSet = new BarDataSet(entries,"Velocidad");
        BarData data = new BarData(labels, dataSet);
        data.setValueTextSize(20.5f);
        chartVelocidad.setData(data);
        chartVelocidad.setDescription("Velocidad Media por Kilometro");
        chartVelocidad.setDescriptionTextSize(20.5f);
        chartVelocidad.setScrollBarSize(5);
        chartVelocidad.animateY(1000);


    }
    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = (range + 1);
            float val = (float) (Math.random() * mult) + 3;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals.add(new Entry(val, i));
        }
        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        set1.enableDashedLine(10f, 5f, 0f);
        //set1.setColor(Color.BLACK);
        //.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleSize(3f);
        set1.setValueTextSize(9f);
        set1.setFillAlpha(65);
        set1.setDrawCubic(true);
        //set1.setFillColor(Color.BLACK);
//        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        chartAltura.setData(data);
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
