package com.example.carlosmario.mallinone_app.models;

public class ListLocal {

    private String name, imageLocal, mapLocal, id, idMall, highlighted;

    public ListLocal(String id, String name, String imageLocal, String mapLocal, String idMall, String highlighted) {
        this.id = id;
        this.name = name;
        this.imageLocal = imageLocal;
        this.mapLocal = mapLocal;
        this.idMall = idMall;
        this.highlighted = highlighted;
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

    public String getimageLocal() {
        return imageLocal;
    }

    public void setimageLocal(String imageLocal) {
        this.imageLocal = imageLocal;
    }

    public String getMapLocal() {
        return mapLocal;
    }

    public void setMapLocal(String mapLocal) {
        this.mapLocal = mapLocal;
    }

    public String getIdMall() {
        return idMall;
    }

    public void setIdMall(String idMall) {
        this.idMall = idMall;
    }

    public String getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(String highlighted) {
        this.highlighted = highlighted;
    }
}
