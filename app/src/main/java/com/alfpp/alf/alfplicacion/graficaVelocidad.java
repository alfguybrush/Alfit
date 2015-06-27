package com.alfpp.alf.alfplicacion;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class graficaVelocidad extends Fragment {


    public graficaVelocidad() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(12.5f,0));
        entries.add(new BarEntry(10.5f,1));
        entries.add(new BarEntry(15.5f,2));
        entries.add(new BarEntry(16.5f,3));
        BarDataSet dataSet = new BarDataSet(entries,"Velocidad");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Kilometro 1");
        labels.add("Kilometro 2");
        labels.add("Kilometro 3");
        labels.add("Kilometro 4");
        HorizontalBarChart chart = new  HorizontalBarChart(getActivity());
        BarData data = new BarData(labels, dataSet);
        chart.setData(data);
        chart.setDescription("Velocidad Media por Kilometro");
        return chart;
        //return inflater.inflate(R.layout.fragment_grafica_velocidad, container, false);
    }


}
