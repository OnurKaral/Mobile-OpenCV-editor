package com.example.opencv_imageprocessing_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.opencv_imageprocessing_android.Adapters.ViewPagerAdapter;
import com.example.opencv_imageprocessing_android.Fragments.Fragment1;
import com.example.opencv_imageprocessing_android.Fragments.Fragment2;
import com.google.android.material.tabs.TabLayout;

public class UNRegistered extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unregistered);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new Fragment1(),"Anasayfa");
        adapter.AddFragment(new Fragment2(), "İletişim");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
