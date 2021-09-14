package com.app.kpb2.features.pasar_bebas.transaksi.model;

import com.app.kpb2.features.transaksi.model.DetailTransaksi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiPasarBebas {

    @SerializedName("_id")
    private String _id ;

    @SerializedName("hargaBarang")
    private String hargaBarang ;

    @SerializedName("idBarang")
    private String idBarang ;

    @SerializedName("idPenjual")
    private Boolean idPenjual ;

    @SerializedName("idUser")
    private Boolean idUser ;

    @SerializedName("jenis")
    private String jenis ;

    @SerializedName("jenisPembayaran")
    private String jenisPembayaran ;

    @SerializedName("jumlahBeli")
    private String jumlahBeli ;

    @SerializedName("namaBarang")
    private String namaBarang ;

    @SerializedName("satuan")
    private String satuan ;

    @SerializedName("subTotal")
    private long subTotal ;

    @SerializedName("created_at")
    private String created_at ;

    @SerializedName("updated_at")
    private String updated_at ;

    @SerializedName("idTransaksi")
    private String idTransaksi ;

    @SerializedName("statusTransaksi")
    private statusTransaksi statusTransaksi ;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public Boolean getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(Boolean idPenjual) {
        this.idPenjual = idPenjual;
    }

    public Boolean getIdUser() {
        return idUser;
    }

    public void setIdUser(Boolean idUser) {
        this.idUser = idUser;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(String jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public TransaksiPasarBebas.statusTransaksi getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(TransaksiPasarBebas.statusTransaksi statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public class statusTransaksi{
        @SerializedName("created_at")
        private String created_at ;

        @SerializedName("updated_at")
        private String updated_at ;

        @SerializedName("status")
        private String status ;

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
