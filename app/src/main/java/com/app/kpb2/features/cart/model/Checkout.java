package com.app.kpb2.features.cart.model;

import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.petani.profile.model.profile;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class    Checkout {

    @SerializedName("nama")
    private String nama;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("idUnique")
    private String idUnique;

    @SerializedName("jenisPembayaran")
    private String jenisPembayaran;

    @SerializedName("idPenjual")
    private String idPenjual;

    @SerializedName("idBarang")
    private String idBarang;

    @SerializedName("namaBarang")
    private String namaBarang;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("hargaBarang")
    private long hargaBarang;

    @SerializedName("jumlahBeli")
    private int jumlahBeli;

    @SerializedName("url")
    private String url;

    @SerializedName("subTotal")
    private long subTotal;

    @SerializedName("stok")
    private int stok;

    @SerializedName("idKios")
    private Number idKios;

    @SerializedName("channel")
    private String channel;

    @SerializedName("idDistributor")
    private String idDistributor;

    @SerializedName("idPenyuluh")
    private String idPenyuluh;

    @SerializedName("idPemesan")
    private String idPemesan;

    @SerializedName("orderId")
    private String orderId;

    @SerializedName("feeBita")
    private long feeBita;

    @SerializedName("feeKios")
    private long feeKios;

    @SerializedName("feePenyuluh")
    private long feePenyuluh;

    @SerializedName("hargaDistributor")
    private long hargaDistributor;

    @SerializedName("totalHargaDistributor")
    private long totalHargaDistributor;

    @SerializedName("totalFeeBita")
    private long totalFeeBita;

    @SerializedName("totalFeeKios")
    private long totalFeeKios;

    @SerializedName("totalFeePenyuluh")
    private long totalFeePenyuluh;

    @SerializedName("grandTotalWithIdUnique")
    private long grandTotalWithIdUnique;

    @SerializedName("area")
    private profile.Area area;

    public long getTotalFeePenyuluh() {
        return totalFeePenyuluh;
    }

    public void setTotalFeePenyuluh(long totalFeePenyuluh) {
        this.totalFeePenyuluh = totalFeePenyuluh;
    }

    public profile.Area getArea() {
        return area;
    }

    public void setArea(profile.Area area) {
        this.area = area;
    }

    public String getIdPenyuluh() {
        return idPenyuluh;
    }

    public void setIdPenyuluh(String idPenyuluh) {
        this.idPenyuluh = idPenyuluh;
    }

    public long getFeePenyuluh() {
        return feePenyuluh;
    }

    public void setFeePenyuluh(long feePenyuluh) {
        this.feePenyuluh = feePenyuluh;
    }

    public long getGrandTotalWithIdUnique() {
        return grandTotalWithIdUnique;
    }

    public void setGrandTotalWithIdUnique(long grandTotalWithIdUnique) {
        this.grandTotalWithIdUnique = grandTotalWithIdUnique;
    }

    public long getFeeBita() {
        return feeBita;
    }

    public void setFeeBita(long feeBita) {
        this.feeBita = feeBita;
    }

    public long getFeeKios() {
        return feeKios;
    }

    public void setFeeKios(long feeKios) {
        this.feeKios = feeKios;
    }

    public long getHargaDistributor() {
        return hargaDistributor;
    }

    public void setHargaDistributor(long hargaDistributor) {
        this.hargaDistributor = hargaDistributor;
    }

    public long getTotalHargaDistributor() {
        return totalHargaDistributor;
    }

    public void setTotalHargaDistributor(long totalHargaDistributor) {
        this.totalHargaDistributor = totalHargaDistributor;
    }

    public long getTotalFeeBita() {
        return totalFeeBita;
    }

    public void setTotalFeeBita(long totalFeeBita) {
        this.totalFeeBita = totalFeeBita;
    }

    public long getTotalFeeKios() {
        return totalFeeKios;
    }

    public void setTotalFeeKios(long totalFeeKios) {
        this.totalFeeKios = totalFeeKios;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(String idUnique) {
        this.idUnique = idUnique;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(String idDistributor) {
        this.idDistributor = idDistributor;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Number getIdKios() {
        return idKios;
    }

    public void setIdKios(Number idKios) {
        this.idKios = idKios;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getIdPenjual() {
        return idPenjual;
    }

    public void setIdPenjual(String idPenjual) {
        this.idPenjual = idPenjual;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public long getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(long hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }

    public long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
