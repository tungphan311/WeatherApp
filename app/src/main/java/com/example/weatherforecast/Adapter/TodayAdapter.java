package com.example.weatherforecast.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherforecast.Model.Weather;
import com.example.weatherforecast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TodayAdapter extends BaseAdapter {
    Context context;
    ArrayList<Weather> listWeather;

    public TodayAdapter(Context context, ArrayList<Weather> listWeather) {
        this.context = context;
        this.listWeather = listWeather;
    }

    @Override
    public int getCount() {
        return listWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return listWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.lvweather, null);

        TextView tvHour, tvMinTemp, tvMaxTemp, tvIconPhrase, tvHum, tvWind;
        ImageView imgIcon;

        tvHour = view.findViewById(R.id.hour);
        tvMinTemp = view.findViewById(R.id.mintemp);
        tvMaxTemp = view.findViewById(R.id.maxtemp);
        tvIconPhrase = view.findViewById(R.id.iconphrase);
        tvHum = view.findViewById(R.id.hum);
        tvWind = view.findViewById(R.id.wind);
        imgIcon = view.findViewById(R.id.weathericon);

        Weather weather = listWeather.get(position);

        tvHour.setText(weather.hour);
        tvMinTemp.setText(weather.mintemp + (char)0x00B0 + "C");
        tvMaxTemp.setText(weather.maxtemp + (char)0x00B0 + "C");
        tvIconPhrase.setText("Weather Condition: " + weather.iconphrase);
        tvHum.setText("Humidity: " + weather.hum + "%");
        tvWind.setText("Wind speed: " + weather.wind + " m/s");
        Picasso.with(context).load("http://openweathermap.org/img/w/" + weather.icon +".png").into(imgIcon);

        return view;
    }

}
