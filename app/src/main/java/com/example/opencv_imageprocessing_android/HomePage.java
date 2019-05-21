package com.example.opencv_imageprocessing_android;

import android.content.Intent;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        FloatingActionButton birinci, ikinci,Kamera;
        Float translationY = 100f;

        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
    Boolean isMenuOpen = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

            initFabMenu();







            // create a notification

        BottomAppBar bar =(BottomAppBar)findViewById(R.id.bar);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.ikinci);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,FirstImageProcess.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton1 = (FloatingActionButton)findViewById(R.id.birinci);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,Secondimageprocess.class);
                startActivity(intent);
            }
        });


        setSupportActionBar(bar);
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    private  void initFabMenu(){
        birinci = findViewById(R.id.birinci);
        ikinci =findViewById(R.id.ikinci);
        Kamera = findViewById(R.id.Kamera);

        birinci.setAlpha(0f);
        ikinci.setAlpha(0f);

        birinci.setTranslationY(translationY);
        ikinci.setTranslationY(translationY);

        Kamera.setOnClickListener(this);
        birinci.setOnClickListener(this);
        ikinci.setOnClickListener(this);
    }

    private void openMenu(){
            isMenuOpen= !isMenuOpen;

            Kamera.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(300).start();
            birinci.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
            ikinci.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
    }
    private  void closeMenu(){
            isMenuOpen = !isMenuOpen;
        Kamera.animate().setInterpolator(overshootInterpolator).rotation(0f).setDuration(300).start();
        birinci.animate().translationY(translationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
        ikinci.animate().translationY(translationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
    }
    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.Kamera:
                    if(isMenuOpen){
                        closeMenu();
                    }else {
                        openMenu();
                    }
                    break;
                case R.id.birinci:
                    break;
                case R.id.ikinci:
                    break;

            }
    }
}
