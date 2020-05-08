package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class BiayaTanam {
    @SerializedName("_id")
    private String _id;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("harga")
    private String harga;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("total")
    private String total;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
