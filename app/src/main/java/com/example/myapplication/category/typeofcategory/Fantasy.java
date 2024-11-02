package com.example.myapplication.category.typeofcategory;

import androidx.annotation.NonNull;

public class Fantasy {

    private String title;
    private String info;
    private int imageResourceId;

    public Fantasy(String title, String info, int imageResourceId) {
        this.title = title;
        this.info = info;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}
