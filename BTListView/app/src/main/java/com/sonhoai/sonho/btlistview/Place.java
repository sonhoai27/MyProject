package com.sonhoai.sonho.btlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
public class Place {
    private String placeName;
    private Bitmap picPlace;

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {

        this.placeName = placeName;
    }

    public Bitmap getPicPlace() {
        return picPlace;
    }

    public void setPicPlace(Bitmap picPlace) {
        this.picPlace = picPlace;
    }
}
