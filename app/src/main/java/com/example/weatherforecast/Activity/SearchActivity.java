package com.example.weatherforecast.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weatherforecast.Adapter.CityApdapter;
import com.example.weatherforecast.Model.City;
import com.example.weatherforecast.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public ArrayList<City> listCity;
    TextView tvCancel;
    CityApdapter adapter;
    ListView listView;
    EditText edKeyword;
    public ArrayList<City> listCityByName;
    String result;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();

        EventHandler();
    }

    public void initView() {
        tvCancel = findViewById(R.id.tv_cancel);
        listCity = new ArrayList<City>();
        adapter = new CityApdapter(getApplicationContext(), listCity);
        listView = findViewById(R.id.lv_city);
        listView.setAdapter(adapter);
        edKeyword = findViewById(R.id.ed_keyword);
        btnSearch = findViewById(R.id.btnSearch);

        result = loadJSONFromAsset(this.getApplicationContext());
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open("city_list.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public void Search(String data, String keyword) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            listCity.clear();

            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String country = jsonObject.getString("country");
                String id = jsonObject.getString("id");

                JSONObject jsonObjectMain = jsonObject.getJSONObject("coord");
                String lat = jsonObjectMain.getString("lat");
                String lon = jsonObjectMain.getString("lon");

                String city = name.toLowerCase();
                String key = keyword.toLowerCase();

                if (city.contains(key))
                    listCity.add(new City(name, country, id, lat, lon));
            }
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void EventHandler() {
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Search(result, edKeyword.getText().toString());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChooseCity.class);
                intent.putExtra("city", listCity.get(position).Name);
                intent.putExtra("country", listCity.get(position).Country);
                intent.putExtra("id", listCity.get(position).ID);
                intent.putExtra("lat", listCity.get(position).Lat);
                intent.putExtra("lon",listCity.get(position).Lon);
                startActivity(intent);
            }
        });
    }
}
