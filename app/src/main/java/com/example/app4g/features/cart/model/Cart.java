package com.example.app4g.cart.model;

import com.example.app4g.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {
    @SerializedName("nik")
    private String nik;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("data")
    private List<Item> item;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }


}
