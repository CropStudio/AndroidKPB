package com.app.app4g.features.petani.profile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AsetPetani {
    @SerializedName("_id")
    public String _id ;

    @SerializedName("namaAset")
    public String namaAset ;

    @SerializedName("totalAset")
    public String totalAset ;

    @SerializedName("jenisAset")
    public String jenisAset ;

    @SerializedName("dataPermt")
    public List<dataMt> dataPermt ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNamaAset() {
        return namaAset;
    }

    public void setNamaAset(String namaAset) {
        this.namaAset = namaAset;
    }

    public String getTotalAset() {
        return totalAset;
    }

    public void setTotalAset(String totalAset) {
        this.totalAset = totalAset;
    }

    public String getJenisAset() {
        return jenisAset;
    }

    public void setJenisAset(String jenisAset) {
        this.jenisAset = jenisAset;
    }

    public List<dataMt> getDataPermt() {
        return dataPermt;
    }

    public void setDataPermt(List<dataMt> dataPermt) {
        this.dataPermt = dataPermt;
    }
}
