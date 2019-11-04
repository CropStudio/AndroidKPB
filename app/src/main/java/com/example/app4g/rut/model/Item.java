package com.example.app4g.rut.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by omgimbot on 10/13/2019.
 */

public class Item implements Serializable {
    @SerializedName("_id")
    private String _id;
    @SerializedName("namaBarang")
    private String namaItem;
    @SerializedName("harga")
    private long harga;
    @SerializedName("satuan")
    private String satuan;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("foto")
    private String foto;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("stok")
    private long stok;

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @SerializedName("distributor")
    private Distributor distributor;

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public long getStok() {
        return stok;
    }

    public void setStok(long stok) {
        this.stok = stok;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public class Distributor{
        @SerializedName("nama")
        private String nama;

        @SerializedName("_id")
        private String id;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
