package com.app.kpb2.features.petani.profile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataMt implements Serializable {

    @SerializedName("_id")
    public String _id ;

    @SerializedName("masaTanam")
    public int masaTanam ;

    @SerializedName("namaKomoditas")
    public String namaKomoditas ;

    @SerializedName("subsektor")
    public String subsektor ;

    @SerializedName("jumlahAset")
    public String jumlahAset ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(int masaTanam) {
        this.masaTanam = masaTanam;
    }

    public String getNamaKomoditas() {
        return namaKomoditas;
    }

    public void setNamaKomoditas(String namaKomoditas) {
        this.namaKomoditas = namaKomoditas;
    }

    public String getSubsektor() {
        return subsektor;
    }

    public void setSubsektor(String subsektor) {
        this.subsektor = subsektor;
    }

    public String getJumlahAset() {
        return jumlahAset;
    }

    public void setJumlahAset(String jumlahAset) {
        this.jumlahAset = jumlahAset;
    }
}
