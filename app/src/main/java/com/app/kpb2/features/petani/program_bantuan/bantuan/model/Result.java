package com.app.kpb2.features.petani.program_bantuan.bantuan.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("nik")
    private String nik;
    @SerializedName("komoditas")
    private String komoditas;
    @SerializedName("tahun")
    private String tahun;
    @SerializedName("mt")
    private String mt;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("satuan")
    private String satuan;


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
