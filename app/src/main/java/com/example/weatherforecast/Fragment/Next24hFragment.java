package com.example.weatherforecast.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.Activity.MainActivity;
import com.example.weatherforecast.Activity.OtherCity;
import com.example.weatherforecast.Adapter.TodayAdapter;
import com.example.weatherforecast.Model.Weather;
import com.example.weatherforecast.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Next24hFragment extends Fragment {
    ArrayList<Weather> listWeather;
    TodayAdapter adapter;
    ListView listView;
    OtherCity main;

    public Next24hFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tomorrow, container, false);

        initView(view);

        main = (OtherCity) getActivity();

        initData(main.data);

        return view;
    }

    public void initView(View view) {
        listWeather = new ArrayList<Weather>();
        adapter = new TodayAdapter(getActivity().getApplicationContext(), listWeather);
        listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    public void initData(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/forecast/hourly?id=" + data + "&appid=b72ce368d7a441149f85cdddf363df06&cnt=24&units=metric";
        //String url = "https://api.openweathermap.org/data/2.5/forecast/hourly?lat=" + data + "&appid=b72ce368d7a441149f85cdddf363df06&cnt=24&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObjectList = jsonArray.getJSONObject(i);

                        String time = jsonObjectList.getString("dt");
                        long l = Long.valueOf(time);
                        Date date = new Date(l*1000L);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        String hour = simpleDateFormat.format(date);

                        JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("main");
                        String minTemp = jsonObjectTemp.getString("temp_min");
                        String maxTemp = jsonObjectTemp.getString("temp_max");
                        Double max = Double.valueOf(maxTemp);
                        String MaxTemp = String.valueOf(max.intValue());
                        Double min = Double.valueOf(minTemp);
                        String MinTemp = String.valueOf(min.intValue());
                        String hum = jsonObjectTemp.getString("humidity");

                        JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                        String status = jsonObjectWeather.getString("description");
                        String icon = jsonObjectWeather.getString("icon");

                        JSONObject jsonObjectWind = jsonObjectList.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");

                        listWeather.add(new Weather(hour, icon, MinTemp, MaxTemp, status, hum, wind));
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}
