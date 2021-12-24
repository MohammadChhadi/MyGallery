package com.mohamad.gallery.Model;

public class Album {
    String caption,image;

    public Album(String caption, String image) {
        this.caption = caption;
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
