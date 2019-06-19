package com.example.opencv_imageprocessing_android.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opencv_imageprocessing_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment2 extends Fragment implements View.OnClickListener {
    View view;
    private FloatingActionButton mtwitter, mgmail,mgithub;
    public Fragment2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.fragment2,container,false);
        mgithub= view.findViewById(R.id.github);
        mtwitter= view.findViewById(R.id.twitter);
        mgmail= view.findViewById(R.id.gmail);

        mgithub.setOnClickListener(this);
        mtwitter.setOnClickListener(this);
        mgmail.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.github:
                String url = "https://github.com/qwertyF0";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.twitter:
                String url2 = "https://twitter.com/qwertyF0";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2));
                startActivity(i2);
                break;
            case R.id.gmail:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "santobixua@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, ""));
        }
    }
}
