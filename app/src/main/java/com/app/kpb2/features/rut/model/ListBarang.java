package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListBarang implements Serializable {
    @SerializedName("stok")
    private int nama;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("_id")
    private String _id;

    @SerializedName("namaBarang")
    private String namaBarang;

    @SerializedName("harga")
    private long harga;

    @SerializedName("hargaKios")
    private long hargaKios;

    @SerializedName("hargaSubsidi")
    private long hargaSubsidi;

    @SerializedName("id")
    private String id;

    @SerializedName("statusSubsidi")
    private Boolean statusSubsidi;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("foto")
    private String foto;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("pabrikan")
    private Pabrikan pabrikan;

    public int getNama() {
        return nama;
    }

    public void setNama(int nama) {
        this.nama = nama;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }



    public long getHargaKios() {
        return hargaKios;
    }

    public void setHargaKios(long hargaKios) {
        this.hargaKios = hargaKios;
    }

    public long getHargaSubsidi() {
        return hargaSubsidi;
    }

    public void setHargaSubsidi(long hargaSubsidi) {
        this.hargaSubsidi = hargaSubsidi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatusSubsidi() {
        return statusSubsidi;
    }

    public void setStatusSubsidi(Boolean statusSubsidi) {
        this.statusSubsidi = statusSubsidi;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Pabrikan getPabrikan() {
        return pabrikan;
    }

    public void setPabrikan(Pabrikan pabrikan) {
        this.pabrikan = pabrikan;
    }
}
