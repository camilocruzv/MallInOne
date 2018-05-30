package com.example.carlosmario.mallinone_app.models;

public class ListMall {

    private String name, imageMall, id;

    public ListMall(String id, String name, String imageMall) {
        this.id = id;
        this.name = name;
        this.imageMall = imageMall;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
