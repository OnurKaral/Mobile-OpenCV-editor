package com.example.opencv_imageprocessing_android;

import android.content.Intent;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.opencv_imageprocessing_android.Cards.Adapter;
import com.example.opencv_imageprocessing_android.Cards.ImagesData;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        FloatingActionButton birinci, ikinci,Kamera,ucuncu;
        Float translationY = 100f;


        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
    Boolean isMenuOpen = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

            initFabMenu();


            mRecyclerView = findViewById(R.id.RV);

            List<ImagesData> dataModelList = new ArrayList<>();
            for (int i = 1; i <= 20; ++i) {
                dataModelList.add(new ImagesData(i));
            }

            // use this setting to improve performance if you know that changes

            // in content do not change the layout size of the RecyclerView

            mRecyclerView.setHasFixedSize(true);

            // use a linear layout manager

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // specify an adapter and pass in our data model list

            mAdapter = new Adapter(dataModelList, this);
            mRecyclerView.setAdapter(mAdapter);




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
                Intent intent = new Intent(HomePage.this,HoughCircleTransform.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton2 = (FloatingActionButton)findViewById(R.id.ucuncu);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
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
        ucuncu =findViewById(R.id.ucuncu);
        Kamera = findViewById(R.id.Kamera);

        birinci.setAlpha(0f);
        ikinci.setAlpha(0f);
        ucuncu.setAlpha(0f);

        birinci.setTranslationY(translationY);
        ikinci.setTranslationY(translationY);
        ucuncu.setTranslationY(translationY);

        Kamera.setOnClickListener(this);
        birinci.setOnClickListener(this);
        ikinci.setOnClickListener(this);
        ucuncu.setOnClickListener(this);
    }

    private void openMenu(){
            isMenuOpen= !isMenuOpen;

            Kamera.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(300).start();
            birinci.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
            ikinci.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
            ucuncu.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
    }
    private  void closeMenu(){
            isMenuOpen = !isMenuOpen;
        Kamera.animate().setInterpolator(overshootInterpolator).rotation(0f).setDuration(300).start();
        birinci.animate().translationY(translationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
        ikinci.animate().translationY(translationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
        ucuncu.animate().translationY(translationY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
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
                case R.id.ucuncu:
                    break;

            }
    }
}
