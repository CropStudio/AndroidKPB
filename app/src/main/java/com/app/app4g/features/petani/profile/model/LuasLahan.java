package com.app.app4g.features.petani.profile.model;

import com.google.gson.annotations.SerializedName;

public class LuasLahan {

    @SerializedName("luas")
    public String mt1 ;

    @SerializedName("mt2_planting_area")
    public String mt2 ;

    @SerializedName("mt3_planting_area")
    public String mt3 ;

    public String getMt1() {
        return mt1;
    }

    public void setMt1(String mt1) {
        this.mt1 = mt1;
    }

    public String getMt2() {
        return mt2;
    }

    public void setMt2(String mt2) {
        this.mt2 = mt2;
    }

    public String getMt3() {
        return mt3;
    }

    public void setMt3(String mt3) {
        this.mt3 = mt3;
    }
}
