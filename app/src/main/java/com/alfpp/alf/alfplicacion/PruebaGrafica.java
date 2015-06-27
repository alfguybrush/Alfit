package com.alfpp.alf.alfplicacion;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;



public class PruebaGrafica extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_grafica);

        // Declaring Your View and Variables

        int Numboftabs =2;


            // Creating The Toolbar and setting it as the Toolbar for the activity



/*

            // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
            adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

            // Assigning ViewPager View and setting the adapter
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            // Assiging the Sliding Tab Layout View
            tabs = (SlidingTabLayout) findViewById(R.id.tabs);
            tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
            spec=tabs.newTabSpec("mitab2");
            spec.setContent(R.id.tab2);

            spec.setIndicator("Intensidad");
            tabs.addTab(spec);
            spec.setContent(R.id.tab1);
            spec.setIndicator("Velocidad");
            tabs.addTab(spec);
            tabs.setCurrentTab(0);

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
            BarChart chart = new BarChart(this);
            //setContentView(chart);
            BarData data = new BarData(labels, dataSet);
            chart.setData(data);
            chart.setDescription("Velocidad Media por Kilometro");*/
            // Setting Custom Color for the Scroll bar indicator of the Tab View
            /*tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.primary);
                }
            });

            // Setting the ViewPager For the SlidingTabsLayout
            tabs.setViewPager(pager);
*/


        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
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
