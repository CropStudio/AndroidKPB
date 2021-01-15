package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class HasilPanen {

    @SerializedName("hasil")
    private String hasil;
    @SerializedName("jumlah")
    private int jumlah;
    @SerializedName("harga")
    private int harga;
    @SerializedName("total")
    private int total;
    @SerializedName("satuan")
    private String satuan;

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
