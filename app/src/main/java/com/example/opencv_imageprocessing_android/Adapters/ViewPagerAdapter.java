package com.example.opencv_imageprocessing_android.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> Fragmentstitles = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return Fragmentstitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Fragmentstitles.get(position);
    }
    public void AddFragment(Fragment fragment,String Title){
        fragments.add(fragment);
        Fragmentstitles.add(Title);
    }
}
