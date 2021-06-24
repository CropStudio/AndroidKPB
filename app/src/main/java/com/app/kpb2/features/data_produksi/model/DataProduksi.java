package com.app.kpb2.features.data_produksi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataProduksi implements Serializable {
    @SerializedName("_id")
    private String _id;

    @SerializedName("komoditas")
    private String komoditas;

    @SerializedName("hasilProduksi")
    private String hasilProduksi;

    @SerializedName("jumlahProduksi")
    private String jumlahProduksi;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("siklus")
    private String siklus;

    @SerializedName("idPoktan")
    private String idPoktan;

    @SerializedName("nik")
    private String nik;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getHasilProduksi() {
        return hasilProduksi;
    }

    public void setHasilProduksi(String hasilProduksi) {
        this.hasilProduksi = hasilProduksi;
    }

    public String getJumlahProduksi() {
        return jumlahProduksi;
    }

    public void setJumlahProduksi(String jumlahProduksi) {
        this.jumlahProduksi = jumlahProduksi;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getSiklus() {
        return siklus;
    }

    public void setSiklus(String siklus) {
        this.siklus = siklus;
    }

    public String getIdPoktan() {
        return idPoktan;
    }

    public void setIdPoktan(String idPoktan) {
        this.idPoktan = idPoktan;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
}
