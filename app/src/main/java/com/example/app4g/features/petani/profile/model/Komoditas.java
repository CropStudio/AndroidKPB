package com.example.app4g.features.petani.profile.model;

import com.google.gson.annotations.SerializedName;

public class Komoditas {

    @SerializedName("masaTanam")
    public String masaTanam ;

    @SerializedName("subsektor")
    public String subsektor ;

    @SerializedName("namaKomoditas")
    public String namaKomoditas ;

    @SerializedName("luasLahan")
    public String luasLahan ;

    public String getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(String luasLahan) {
        this.luasLahan = luasLahan;
    }

    public String getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(String masaTanam) {
        this.masaTanam = masaTanam;
    }

    public String getSubsektor() {
        return subsektor;
    }

    public void setSubsektor(String subsektor) {
        this.subsektor = subsektor;
    }

    public String getNamaKomoditas() {
        return namaKomoditas;
    }

    public void setNamaKomoditas(String namaKomoditas) {
        this.namaKomoditas = namaKomoditas;
    }
}
