package com.example.app4g.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by omgimbot on 10/12/2019.
 */

public class Rut implements Serializable {
    @SerializedName("name")
    private String mName;

    @SerializedName("created_at")
    private String mCreated_at;

    @SerializedName("item")
    private List<Item> mItem;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCreated_at() {
        return mCreated_at;
    }

    public void setmCreated_at(String mCreated_at) {
        this.mCreated_at = mCreated_at;
    }

    public List<Item> getmItem() {
        return mItem;
    }

    public void setmItem(List<Item> mItem) {
        this.mItem = mItem;
    }
}
