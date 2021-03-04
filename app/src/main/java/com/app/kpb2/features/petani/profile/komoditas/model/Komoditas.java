package com.app.kpb2.features.petani.profile.komoditas.model;

import com.google.gson.annotations.SerializedName;

public class Komoditas {
    @SerializedName("name")
    public String komoditas ;

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }
}
