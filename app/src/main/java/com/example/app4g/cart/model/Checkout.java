package com.example.app4g.cart.model;

import com.example.app4g.rut.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Checkout {
    @SerializedName("nik")
    private String nik;
    @SerializedName("grandtotal")
    private long grandtotal;
    @SerializedName("item")
    private List<Item> item;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public long getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(long grandtotal) {
        this.grandtotal = grandtotal;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
