package com.example.opencv_imageprocessing_android.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.opencv_imageprocessing_android.MainActivity;
import com.example.opencv_imageprocessing_android.R;
import com.example.opencv_imageprocessing_android.Secondimageprocess;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment1  extends Fragment  implements View.OnClickListener {
    View view;
    private FloatingActionButton button1;
    public Fragment1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        button1 = view.findViewById(R.id.unregisteredbutton);
        BottomAppBar bar = view.findViewById(R.id.bar);
        bar.setOnClickListener(this);
        button1.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.unregisteredbutton:
                Intent intent = new Intent(getActivity(), Secondimageprocess.class);
                startActivity(intent);
            break;
            case R.id.bar:
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent1);
            break;
        }
    }
}
