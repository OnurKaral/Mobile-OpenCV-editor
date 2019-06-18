package com.example.opencv_imageprocessing_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.opencv_imageprocessing_android.Adapters.ViewPagerAdapter;
import com.example.opencv_imageprocessing_android.Fragments.Fragment1;
import com.example.opencv_imageprocessing_android.Fragments.Fragment2;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.tabs.TabLayout;

public class UNRegistered extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button Button1;
    private ImageView resim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unregistered);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new Fragment1(),"Anasayfa");
        adapter.AddFragment(new Fragment2(), "İletişim");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }
}
