package com.example.opencv_imageprocessing_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opencv_imageprocessing_android.Adapters.Adapter;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
        private RecyclerView mRecyclerView;
        private Adapter mAdapter;
        private ProgressBar progressBar;
        private RecyclerView.LayoutManager mLayoutManager;
        private BottomAppBar bottomAppBar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.upload_bar:
                Intent intent = new Intent(HomePage.this,UploadActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

        FloatingActionButton birinci, ikinci,Kamera,ucuncu;
        Float translationY = 100f;

        private DatabaseReference DatabaseREF;
        private List<Upload> Uploads;

        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        Boolean isMenuOpen = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);
            initFabMenu();
            setSupportActionBar(bottomAppBar);

            mRecyclerView = findViewById(R.id.RV);
            progressBar = findViewById(R.id.progress_bar);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            Uploads = new ArrayList<>();
            DatabaseREF = FirebaseDatabase.getInstance().getReference("uploads");
            DatabaseREF.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Upload upload = snapshot.getValue(Upload.class);
                    Uploads.add(upload);
                }
                    mAdapter = new Adapter(HomePage.this,Uploads);
                    mRecyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(HomePage.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

 //****************************************************************************************************
        BottomAppBar bar = findViewById(R.id.bar);
        FloatingActionButton floatingActionButton = findViewById(R.id.ikinci);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,FirstImageProcess.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton1 = findViewById(R.id.birinci);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,HoughCircleTransform.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatingActionButton2 = findViewById(R.id.ucuncu);
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
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.app_barmenu,menu);
        return true;
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
