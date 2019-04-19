package com.example.weatherforecast.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherforecast.Model.OneDay;
import com.example.weatherforecast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SevendayAdapter extends BaseAdapter {
    Context context;
    ArrayList<OneDay> listDay;

    public SevendayAdapter(Context context, ArrayList<OneDay> listDay) {
        this.context = context;
        this.listDay = listDay;
    }

    @Override
    public int getCount() {
        return listDay.size();
    }

    @Override
    public Object getItem(int position) {
        return listDay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_row, null);
        OneDay day = listDay.get(position);

        TextView txtDay = view.findViewById(R.id.itemDate);
        TextView txtStatus = view.findViewById(R.id.itemDescription);
        TextView txtWind = view.findViewById(R.id.itemWind);
        TextView txtPressure = view.findViewById(R.id.itemPressure);
        TextView txtHumidity = view.findViewById(R.id.itemHumidity);
        TextView txtTemp = view.findViewById(R.id.itemTemperature);
        ImageView imgIcon = view.findViewById(R.id.itemIcon);

        txtDay.setText(day.Day);
        txtStatus.setText(day.Status);
        txtWind.setText(day.Wind);
        txtPressure.setText(day.Pressure);
        txtHumidity.setText(day.Humidity);
        txtTemp.setText(day.Temp);
        Picasso.with(context).load(day.Icon).into(imgIcon);

        return view;
    }
}
