package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pabrikan implements Serializable {
    @SerializedName("nama")
    private String nama;

    @SerializedName("_id")
    private String _id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
