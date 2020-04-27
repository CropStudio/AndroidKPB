package com.app.app4g.features.cart.model;

import com.app.app4g.features.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("rc")
    private String rc;
    @SerializedName("message")
    private String rm;
    @SerializedName("nik")
    private String nik;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("cart")
    private List<Item> item;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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
