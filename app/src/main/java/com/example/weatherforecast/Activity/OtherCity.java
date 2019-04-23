package com.example.weatherforecast.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherforecast.Fragment.Next5dFragment;
import com.example.weatherforecast.Fragment.Next24hFragment;
import com.example.weatherforecast.Fragment.NowFragment;
import com.example.weatherforecast.R;

public class OtherCity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public String data = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_city);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container_);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_);
        tabLayout.setupWithViewPager(mViewPager);

        intent = getIntent();
        if (intent != null) {
            String ID = intent.getStringExtra("id");
            Toast.makeText(this, "ID: " + ID, Toast.LENGTH_SHORT).show();

            data = ID;
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
                    fragment = new NowFragment();
                    break;

                case 1:
                    fragment = new Next24hFragment();
                    break;

                case 2:
                    fragment = new Next5dFragment();
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
