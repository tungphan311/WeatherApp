package com.example.weatherforecast.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherforecast.Model.FavoriteCity;
import com.example.weatherforecast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteCityAdapter extends BaseAdapter {
    Context context;
    ArrayList<FavoriteCity> list;

    public FavoriteCityAdapter(Context context, ArrayList<FavoriteCity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listview_favoritecity, null);

        FavoriteCity city = list.get(position);

        TextView tvName = view.findViewById(R.id.tv_city);
        TextView tvHour = view.findViewById(R.id.tv_hour);
        TextView tvTemp = view.findViewById(R.id.tv_temp);
        ImageView imgIcon = view.findViewById(R.id.img_icon);

        tvHour.setText(city.Date);
        tvName.setText(city.Name);
        tvTemp.setText(city.Temp + (char)0x00B0 + "C");
        Picasso.with(context).load(city.Icon).into(imgIcon);

        return view;
    }
}
