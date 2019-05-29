package com.example.opencv_imageprocessing_android.Cards;

import com.example.opencv_imageprocessing_android.R;
import java.util.Locale;

public class ImagesData {
    private int Resim;
    private String title;
    private String subTitle;

    public ImagesData(int id) {
        Resim = R.drawable.list_image;
        title = String.format(Locale.ENGLISH, "Title %d Goes Here", id);
        subTitle = String.format(Locale.ENGLISH, "Sub title %d goes here", id);
    }

    public int getImageDrawable() {
        return Resim;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }
}

