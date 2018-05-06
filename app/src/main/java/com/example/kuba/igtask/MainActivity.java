package com.example.kuba.igtask;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<Market.CurrentMarkets> marketList;
    private ListViewAdapter adapter;
    private getData task;
    private static Gson gson;

    static {
        gson = new GsonBuilder().create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner configurations
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("United Kingdom");
        categories.add("Germany");
        categories.add("France");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //executing a task which will download the data
        if (isNetworkConnected()) {
            task = new getData();
            task.execute("en_GB", "igi");
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
        }

        //setting adapter for list view
        marketList = new ArrayList<Market.CurrentMarkets>();
        ListView lview = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter(this, marketList);
        lview.setAdapter(adapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        if(item.equals("United Kingdom"))
        {
            if (isNetworkConnected()) {
            task = new getData();
            task.execute("en_GB","igi");
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        }
        if(item.equals("Germany"))
        {
            if (isNetworkConnected()) {
            task = new getData();
            task.execute("de_DE","dem");
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        }
        if(item.equals("France"))
        {
            if (isNetworkConnected()) {
            task = new getData();
            task.execute("fr_FR","frm");
            } else {
                Toast.makeText(getApplicationContext(),
                        "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    class getData extends AsyncTask<String, String, String> {

        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            //downloading data for specified country in args


                try {
                    URL url = new URL("https://api.ig.com/deal/samples/markets/ANDROID_PHONE/" + args[0] + "/" + args[1]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }


                return result.toString();

        }


        @Override
        protected void onPostExecute(String result) {


            super.onPostExecute(result);

            //parsing JSON string to Market class
            Market marks = gson.fromJson(result, Market.class);

            populateList(marks);

            adapter.notifyDataSetChanged();

        }

    }
    private void populateList(Market marks) {

        marketList.clear();

        for(int i=0; i<marks.getMarkets().size();i++) {
            marketList.add(marks.getMarkets().get(i));
        }

        //sorting markets alphabetically
        Collections.sort(marketList, new Comparator<Market.CurrentMarkets>() {
            @Override
            public int compare(Market.CurrentMarkets o1, Market.CurrentMarkets o2) {
                return o1.getInstrumentName().compareTo(o2.getInstrumentName());
            }
        });
    }

    public boolean isNetworkConnected() {
        final ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.getState() == NetworkInfo.State.CONNECTED;
    }
}

