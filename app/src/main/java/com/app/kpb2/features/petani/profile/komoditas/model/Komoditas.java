package com.app.kpb2.features.petani.profile.komoditas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Komoditas {
    @SerializedName("name")
    public String komoditas ;

    @SerializedName("nama")
    public String namaKomoditas ;

    @SerializedName("_id")
    public String _id ;

    @SerializedName("id")
    public String id ;

    @SerializedName("subsector_id")
    public String subsector_id ;

    @SerializedName("satuan")
    public String satuan ;

    @SerializedName("type")
    public List<Type> type ;

    @SerializedName("listProduksi")
    public List<ListProduksi> listProduksi ;

    public Komoditas(String id, String komoditas) {
        super();
        this.id = id;
        this.komoditas = komoditas;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubsector_id() {
        return subsector_id;
    }

    public void setSubsector_id(String subsector_id) {
        this.subsector_id = subsector_id;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public List<Type> getType() {
        return type;
    }

    public void setType(List<Type> type) {
        this.type = type;
    }

    public List<ListProduksi> getListProduksi() {
        return listProduksi;
    }

    public void setListProduksi(List<ListProduksi> listProduksi) {
        this.listProduksi = listProduksi;
    }

    public String getNamaKomoditas() {
        return namaKomoditas;
    }

    public void setNamaKomoditas(String namaKomoditas) {
        this.namaKomoditas = namaKomoditas;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }
}
