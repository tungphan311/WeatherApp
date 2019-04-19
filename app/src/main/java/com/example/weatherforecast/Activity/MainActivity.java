package com.example.weatherforecast.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.weatherforecast.Fragment.TodayFragment;
import com.example.weatherforecast.Fragment.TomorrowFragment;
import com.example.weatherforecast.R;
import com.example.weatherforecast.Fragment.SevenDaysFragment;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public String data;
    Intent intent;
    public boolean first;
    public String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            String lat = intent.getStringExtra("lat");
            String lon = intent.getStringExtra("lon");

            if (id != null) {
                data = id;
            }
            else {
                data = "1566083";
            }
        }
        else {
            data = "1566083";
        }

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new TodayFragment();
                    break;

                case 1:
                    fragment = new TomorrowFragment();
                    break;

                case 2:
                    fragment = new SevenDaysFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NOW";
                case 1:
                    return "NEXT 24 HOURS";
                case 2:
                    return "NEXT 5 DAYS";
            }
            return null;
        }
    }
}