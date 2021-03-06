package com.app.kpb2.features.e_commerce.model;

import com.google.gson.annotations.SerializedName;

public class Saldo {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("nik")
    private String nik;
    @SerializedName("jumlah")
    private long jumlah;
    @SerializedName("totChart")
    private int totcart;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getTotcart() {
        return totcart;
    }

    public void setTotcart(int totcart) {
        this.totcart = totcart;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }
}
