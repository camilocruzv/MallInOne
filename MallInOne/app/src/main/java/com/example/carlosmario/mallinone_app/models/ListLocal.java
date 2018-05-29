package com.example.carlosmario.mallinone_app.models;

public class ListLocal {

    private String name, imageLocal;

    public ListLocal(String name, String imageLocal) {
        this.name = name;
        this.imageLocal = imageLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getimageLocal() {
        return imageLocal;
    }

    public void setimageLocal(String imageLocal) {
        this.imageLocal = imageLocal;
    }
}
