package com.app.app4g.features.transaksi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Transaksi {

    @SerializedName("_id")
    private String _id ;

    @SerializedName("namaTransaksi")
    private String namaTransaksi ;

    @SerializedName("status")
    private String status ;

    @SerializedName("verifiedKur")
    private Boolean verifiedKur ;

    @SerializedName("statusKur")
    private Boolean statusKur ;

    @SerializedName("created_at")
    private String created_at ;

    @SerializedName("idtransaksi")
    private String idtransaksi ;

    @SerializedName("nik")
    private String nik ;

    @SerializedName("grandtotal")
    private long grandtotal ;

    @SerializedName("detailTransaksi")
    private List<DetailTransaksi> detailTransaksi ;

    public Boolean getStatusKur() {
        return statusKur;
    }

    public void setStatusKur(Boolean statusKur) {
        this.statusKur = statusKur;
    }

    public Boolean getVerifiedKur() {
        return verifiedKur;
    }

    public void setVerifiedKur(Boolean verifiedKur) {
        this.verifiedKur = verifiedKur;
    }

    public List<DetailTransaksi> getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(List<DetailTransaksi> detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }

    public String getNamaTransaksi() {
        return namaTransaksi;
    }

    public void setNamaTransaksi(String namaTransaksi) {
        this.namaTransaksi = namaTransaksi;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIdtransaksi() {
        return idtransaksi;
    }

    public void setIdtransaksi(String idtransaksi) {
        this.idtransaksi = idtransaksi;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public long getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(long grandtotal) {
        this.grandtotal = grandtotal;
    }
}
