package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rut {
    @SerializedName("jenisTanaman")
    private String jenisTanaman;

    @SerializedName("masaTanam")
    private String masaTanam;

    @SerializedName("kebutuhanSaprotan")
    private List<KebutuhanSaprotan> KebutuhanSaprotan;

    @SerializedName("garapDanPemeliharaan")
    private List<BiayaTanam> BiayaTanam;

    @SerializedName("jadwalUsahaTani")
    private KalenderTanam KalenderTanam;

    @SerializedName("status")
    private String status;



    public com.example.app4g.features.rut.model.KalenderTanam getKalenderTanam() {
        return KalenderTanam;
    }

    public void setKalenderTanam(com.example.app4g.features.rut.model.KalenderTanam kalenderTanam) {
        KalenderTanam = kalenderTanam;
    }

    public List<BiayaTanam> getBiayaTanam() {
        return BiayaTanam;
    }

    public void setBiayaTanam(List<BiayaTanam> biayaTanam) {
        BiayaTanam = biayaTanam;
    }

    public boolean isSelected ;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(String masaTanam) {
        this.masaTanam = masaTanam;
    }

    public List<com.example.app4g.features.rut.model.KebutuhanSaprotan> getKebutuhanSaprotan() {
        return KebutuhanSaprotan;
    }

    public void setKebutuhanSaprotan(List<com.example.app4g.features.rut.model.KebutuhanSaprotan> kebutuhanSaprotan) {
        KebutuhanSaprotan = kebutuhanSaprotan;
    }
}
