package com.mohamad.gallery.Model;

public class Gallery {
    String id,name,imageUrl;

    public Gallery(String id,String name, String imageUrl) {
        this.id=id;
        this.name = name;
        this.imageUrl = imageUrl;

    }
    public Gallery(String name, String imageUrl) {

        this.name = name;
        this.imageUrl = imageUrl;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Gallery(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
