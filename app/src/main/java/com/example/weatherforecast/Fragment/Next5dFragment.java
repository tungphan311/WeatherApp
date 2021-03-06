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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Next5dFragment extends Fragment {
    ArrayList<Weather> listWeather;
    TodayAdapter adapter;
    ListView listView;
    OtherCity other;

    public Next5dFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tomorrow, container, false);

        initView(view);

        other = (OtherCity) getActivity();
        initData(other.data);

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
        String url = "https://api.openweathermap.org/data/2.5/forecast?id=" + data + "&lang=en&mode=json&appid=3e29e62e2ddf6dd3d2ebd28aed069215&units=metric";
        //String url ="https://api.openweathermap.org/data/2.5/forecast?lat="+data +"&lang=en&mode=json&appid=3e29e62e2ddf6dd3d2ebd28aed069215&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    for (int i=0; i<jsonArray.length(); i+=8) {
                        JSONObject jsonObjectList = jsonArray.getJSONObject(i);

                        String time = jsonObjectList.getString("dt_txt");
                        String hour = time.substring(0,11);

                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

                        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM d");

                        String convertedDate = null;
                        try {
                            convertedDate = formatter.format(f.parse(hour));
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        hour=convertedDate;


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
