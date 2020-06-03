package com.app.app4g.features.rut.aset.model;

import com.google.gson.annotations.SerializedName;

public class Aset {
    @SerializedName("namaAset")
    private String namaAset ;

    @SerializedName("idSubsektor")
    private String idSubsektor ;

    @SerializedName("totalAset")
    private float totalAset ;

    public String getNamaAset() {
        return namaAset;
    }

    public void setNamaAset(String namaAset) {
        this.namaAset = namaAset;
    }

    public String getIdSubsektor() {
        return idSubsektor;
    }

    public void setIdSubsektor(String idSubsektor) {
        this.idSubsektor = idSubsektor;
    }

    public float getTotalAset() {
        return totalAset;
    }

    public void setTotalAset(float totalAset) {
        this.totalAset = totalAset;
    }
}
