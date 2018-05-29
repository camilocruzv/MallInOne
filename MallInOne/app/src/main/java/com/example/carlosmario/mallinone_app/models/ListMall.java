package com.example.carlosmario.mallinone_app.models;

public class ListMall {

    private String name, imageMall;

    public ListMall(String name, String imageMall) {
        this.name = name;
        this.imageMall = imageMall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getimageMall() {
        return imageMall;
    }

    public void setimageMall(String imageMall) {
        this.imageMall = imageMall;
    }
}
