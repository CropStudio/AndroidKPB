package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class Rut {

    @SerializedName("urea")
    private String urea;
    @SerializedName("hci")
    private String hci;
    @SerializedName("organik")
    private String organik;
    @SerializedName("npk")
    private String npk;
    @SerializedName("phonska")
    private String phonska;
    @SerializedName("luas_lahan")
    private String luas_lahan;


    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getHci() {
        return hci;
    }

    public void setHci(String hci) {
        this.hci = hci;
    }

    public String getOrganik() {
        return organik;
    }

    public void setOrganik(String organik) {
        this.organik = organik;
    }

    public String getNpk() {
        return npk;
    }

    public void setNpk(String npk) {
        this.npk = npk;
    }

    public String getPhonska() {
        return phonska;
    }

    public void setPhonska(String phonska) {
        this.phonska = phonska;
    }

    public String getLuas_lahan() {
        return luas_lahan;
    }

    public void setLuas_lahan(String luas_lahan) {
        this.luas_lahan = luas_lahan;
    }
}
