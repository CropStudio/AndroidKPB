package com.app.kpb2;

import android.graphics.drawable.Drawable;

public class ScreenItem {

    String Title, Description;
    Drawable ScreenImg;

    public ScreenItem(String title, String description, Drawable screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenImg(Drawable screenImg) {
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public Drawable getScreenImg() {
        return ScreenImg;
    }

}
