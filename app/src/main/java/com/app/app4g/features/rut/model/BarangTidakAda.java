package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class BarangTidakAda {
    @SerializedName("idKebutuhanSaprotan")
    private String idKebutuhanSaprotan;

    @SerializedName("nama")
    private String nama;

    public String getIdKebutuhanSaprotan() {
        return idKebutuhanSaprotan;
    }

    public void setIdKebutuhanSaprotan(String idKebutuhanSaprotan) {
        this.idKebutuhanSaprotan = idKebutuhanSaprotan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
