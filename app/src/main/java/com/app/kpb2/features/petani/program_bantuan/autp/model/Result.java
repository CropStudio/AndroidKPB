package com.app.kpb2.features.petani.program_bantuan.autp.model;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("nik")
    private String nik;
    @SerializedName("asuransi")
    private String asuransi;
    @SerializedName("tahun")
    private String tahun;
    @SerializedName("mt")
    private String mt;


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

    public String getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(String asuransi) {
        this.asuransi = asuransi;
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
}
