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

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {



        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

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
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.Kamera);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,FirstImageProcess.class);
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
}

