package com.app.app4g.features.petani.program_bantuan.alokasi.model;

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
    @SerializedName("luasLahan")
    private float luasLahan;
    @SerializedName("urea")
    private float urea;
    @SerializedName("npk")
    private float npk;
    @SerializedName("za")
    private float za;
    @SerializedName("sp36")
    private float sp36;
    @SerializedName("organik")
    private float organik;

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

    public float getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(float luasLahan) {
        this.luasLahan = luasLahan;
    }

    public float getUrea() {
        return urea;
    }

    public void setUrea(float urea) {
        this.urea = urea;
    }

    public float getNpk() {
        return npk;
    }

    public void setNpk(float npk) {
        this.npk = npk;
    }

    public float getZa() {
        return za;
    }

    public void setZa(float za) {
        this.za = za;
    }

    public float getSp36() {
        return sp36;
    }

    public void setSp36(float sp36) {
        this.sp36 = sp36;
    }

    public float getOrganik() {
        return organik;
    }

    public void setOrganik(float organik) {
        this.organik = organik;
    }
}
