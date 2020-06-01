package com.app.app4g.features.transaksi.model;

import com.google.gson.annotations.SerializedName;

public class DetailTransaksi {
    @SerializedName("_id")
    private String _id ;

    @SerializedName("created_at")
    private String created_at ;

    @SerializedName("idBarang")
    private String idBarang ;

    @SerializedName("jumlah")
    private long jumlah ;

    @SerializedName("subsidi")
    private Boolean subsidi ;

    @SerializedName("harga")
    private long harga ;

    @SerializedName("nama")
    private String nama ;

    @SerializedName("idPabrikan")
    private String idPabrikan ;

    @SerializedName("idKios")
    private Number idKios ;

    @SerializedName("idtransaksi")
    private String  idtransaksi ;

    @SerializedName("total")
    private long total ;

    @SerializedName("idDistributor")
    private String idDistributor ;

    @SerializedName("status")
    private int status ;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        this.jumlah = jumlah;
    }

    public Boolean getSubsidi() {
        return subsidi;
    }

    public void setSubsidi(Boolean subsidi) {
        this.subsidi = subsidi;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPabrikan() {
        return idPabrikan;
    }

    public void setIdPabrikan(String idPabrikan) {
        this.idPabrikan = idPabrikan;
    }

    public Number getIdKios() {
        return idKios;
    }

    public void setIdKios(Number idKios) {
        this.idKios = idKios;
    }

    public String getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(String idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(String idDistributor) {
        this.idDistributor = idDistributor;
    }
}
