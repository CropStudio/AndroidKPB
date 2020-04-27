package com.app.app4g.features.e_commerce.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by omgimbot on 10/13/2019.
 */

public class Item implements Serializable {
    public boolean isSelected ;
    @SerializedName("_id")
    private String _id;
    @SerializedName("tipe")
    private String tipe;
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
    @SerializedName("distributor")
    private Distributor distributor;

    @SerializedName("kios")
    private String kios;

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKios() {
        return kios;
    }

    public void setKios(String kios) {
        this.kios = kios;
    }

    public int qty;

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

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
