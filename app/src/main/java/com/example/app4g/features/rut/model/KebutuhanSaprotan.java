package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class KebutuhanSaprotan {
    @SerializedName("_id")
    private String _id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("harga")
    private String harga;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("id")
    private String id;

    @SerializedName("hargaSubsidi")
    private String hargaSubsidi;

    @SerializedName("subTotal")
    private long subTotal;

    @SerializedName("jatahSubsidi")
    private String jatahSubsidi;

    @SerializedName("jumlahNonSubsidi")
    private String jumlahNonSubsidi;

    @SerializedName("luasLahan")
    private String luasLahan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHargaSubsidi() {
        return hargaSubsidi;
    }

    public void setHargaSubsidi(String hargaSubsidi) {
        this.hargaSubsidi = hargaSubsidi;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public String getJatahSubsidi() {
        return jatahSubsidi;
    }

    public void setJatahSubsidi(String jatahSubsidi) {
        this.jatahSubsidi = jatahSubsidi;
    }

    public String getJumlahNonSubsidi() {
        return jumlahNonSubsidi;
    }

    public void setJumlahNonSubsidi(String jumlahNonSubsidi) {
        this.jumlahNonSubsidi = jumlahNonSubsidi;
    }

    public String getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(String luasLahan) {
        this.luasLahan = luasLahan;
    }

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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
