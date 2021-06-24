package com.app.kpb2.features.petani.profile.komoditas.model;

import com.google.gson.annotations.SerializedName;

public class ListProduksi {

    @SerializedName("_id")
    public String _id ;

    @SerializedName("nama")
    public String nama ;

    @SerializedName("satuan")
    public String satuan ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
