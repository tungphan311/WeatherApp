package com.example.weatherforecast.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weatherforecast.Model.City;
import com.example.weatherforecast.R;

import java.util.ArrayList;

public class CityApdapter extends BaseAdapter {
    Context context;
    ArrayList<City> listCity;

    public CityApdapter(Context context, ArrayList<City> listCity) {
        this.context = context;
        this.listCity = listCity;
    }

    @Override
    public int getCount() {
        return listCity.size();
    }

    @Override
    public Object getItem(int position) {
        return listCity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_city, null);

        City city = listCity.get(position);

        TextView tvCityName = view.findViewById(R.id.cityname);

        tvCityName.setText(city.Name + ", " + city.Country);

        return view;
    }
}
