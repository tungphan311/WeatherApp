package com.example.weatherforecast.Fragment;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.Activity.ChooseCity;
import com.example.weatherforecast.Activity.MainActivity;
import com.example.weatherforecast.Activity.OtherCity;
import com.example.weatherforecast.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {
    TextView txtName, txtCurrentDate, txtTemp, txtHumidity, txtPressure, txtWind,
            txtVisibility, txtSunset, txtSunrise, txtDescription, tvStatus;
    ImageView imgWeatherIcon;
    Button btnYourCity;
    OtherCity activity;

    public NowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.now_fragment, container, false);

        initView(view);

        activity = (OtherCity) getActivity();
        //Toast.makeText(activity, "Activity.data: " + activity.data, Toast.LENGTH_SHORT).show();
        GetCurrentWeather(activity.data);

        initEvent();

        return view;
    }

    private void initView(View view) {
        txtName = view.findViewById(R.id.city_country_);
        txtCurrentDate = view.findViewById(R.id.current_date_);
        imgWeatherIcon = view.findViewById(R.id.weather_icon_);
        txtTemp = view.findViewById(R.id.temp_);
        txtHumidity = view.findViewById(R.id.hum_);
        txtPressure = view.findViewById(R.id.pressure_);
        txtWind = view.findViewById(R.id.wind_);
        txtVisibility = view.findViewById(R.id.visibility_);
        txtSunset = view.findViewById(R.id.sunset_);
        txtSunrise = view.findViewById(R.id.sunrise_);
        txtDescription = view.findViewById(R.id.weatherCondition);
        tvStatus = view.findViewById(R.id.idcaideo);
        btnYourCity = view.findViewById(R.id.btnHome);
    }

    public void GetCurrentWeather(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/weather?id=" + data + "&appid=b72ce368d7a441149f85cdddf363df06&units=metric";
        //String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + data + "&appid=b72ce368d7a441149f85cdddf363df06&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            txtName.setText(name);

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm");
                            String Day = simpleDateFormat.format(date);

                            txtCurrentDate.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            Picasso.with(getActivity().getApplicationContext()).load("http://openweathermap.org/img/w/" + icon +".png")
                                    .into(imgWeatherIcon);

                            String description = jsonObjectWeather.getString("description");

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String temp = jsonObjectMain.getString("temp");
                            String maxTemp = jsonObjectMain.getString("temp_max");
                            String minTemp = jsonObjectMain.getString("temp_min");
                            String humidity = jsonObjectMain.getString("humidity");
                            String pressure = jsonObjectMain.getString("pressure");
                            Double dou = Double.valueOf(temp);
                            String Temp = String.valueOf(dou.intValue());
                            Double max = Double.valueOf(maxTemp) + 1;
                            String MaxTemp = String.valueOf(max.intValue());
                            Double min = Double.valueOf(minTemp);
                            String MinTemp = String.valueOf(min.intValue());
                            txtTemp.setText(Temp);
                            txtHumidity.setText(humidity + "%");
                            txtPressure.setText(pressure + " hPa");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String speed = jsonObjectWind.getString("speed");
                            txtWind.setText(speed + " m/s");

                            Random rand = new Random();
                            int tamnhin = rand.nextInt(9) + 4;
                            String Visiblity = String.valueOf(tamnhin);
                            txtVisibility.setText(Visiblity + " km");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String sunrise = jsonObjectSys.getString("sunrise");
                            long rise = Long.valueOf(sunrise);
                            Date dRise = new Date(rise*1000L);
                            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                            String Sunrise = format.format(dRise);
                            txtSunrise.setText(Sunrise);

                            String sunset = jsonObjectSys.getString("sunset");
                            long set = Long.valueOf(sunset);
                            Date dSet = new Date(set*1000L);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                            String SunSet = dateFormat.format(dSet);
                            txtSunset.setText(SunSet);

                            tvStatus.setText(description);

                            txtDescription.setText("Weather Condition: " + description + ". Highest temperature: " + maxTemp + (char)0x00B0 + "C");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(stringRequest);
    }

    public void initEvent() {
        btnYourCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
