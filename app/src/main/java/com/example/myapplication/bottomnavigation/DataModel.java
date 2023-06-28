package com.example.myapplication.bottomnavigation;

public class DataModel {
    String title;
    int image_path;
    String dpr1;
    String dpr3;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }

    public DataModel(String title, int image_path, String dpr1, String dpr3) {
        this.title = title;
        this.image_path = image_path;
        this.dpr1 = dpr1;
        this.dpr3 = dpr3;
    }
}