package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rut {
    @SerializedName("nik")
    private String nik;

    @SerializedName("idAsset")
    private String idAsset;

    @SerializedName("jumlahAset")
    private String jumlahAset;

    @SerializedName("komoditas")
    private String komoditas;

    @SerializedName("idDesa")
    private String idDesa;

    @SerializedName("subsektor")
    private String subsektor;

    @SerializedName("area")
    private String area;

    @SerializedName("jumlahHasilPanen")
    private String jumlahHasilPanen;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("hargaSatuan")
    private String hargaSatuan;

    @SerializedName("hasilPanen")
    private String hasilPanen;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("standarUntuk")
    private String standarUntuk;

    @SerializedName("satuanAsset")
    private String satuanAsset;

    @SerializedName("pendapatanKotor")
    private String pendapatanKotor;

    @SerializedName("kebutuhanSaprotan")
    private List<KebutuhanSaprotan> KebutuhanSaprotan;

    @SerializedName("garapDanPemeliharaan")
    private List<BiayaTanam> garapDanPemeliharaan;

    @SerializedName("jadwalUsahaTani")
    private List<JadwalUsahaTani> jadwalUsahaTani;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getIdAsset() {
        return idAsset;
    }

    public void setIdAsset(String idAsset) {
        this.idAsset = idAsset;
    }

    public String getJumlahAset() {
        return jumlahAset;
    }

    public void setJumlahAset(String jumlahAset) {
        this.jumlahAset = jumlahAset;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getIdDesa() {
        return idDesa;
    }

    public void setIdDesa(String idDesa) {
        this.idDesa = idDesa;
    }

    public String getSubsektor() {
        return subsektor;
    }

    public void setSubsektor(String subsektor) {
        this.subsektor = subsektor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getJumlahHasilPanen() {
        return jumlahHasilPanen;
    }

    public void setJumlahHasilPanen(String jumlahHasilPanen) {
        this.jumlahHasilPanen = jumlahHasilPanen;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(String hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public String getHasilPanen() {
        return hasilPanen;
    }

    public void setHasilPanen(String hasilPanen) {
        this.hasilPanen = hasilPanen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStandarUntuk() {
        return standarUntuk;
    }

    public void setStandarUntuk(String standarUntuk) {
        this.standarUntuk = standarUntuk;
    }

    public String getSatuanAsset() {
        return satuanAsset;
    }

    public void setSatuanAsset(String satuanAsset) {
        this.satuanAsset = satuanAsset;
    }

    public String getPendapatanKotor() {
        return pendapatanKotor;
    }

    public void setPendapatanKotor(String pendapatanKotor) {
        this.pendapatanKotor = pendapatanKotor;
    }

    public List<com.app.kpb2.features.rut.model.KebutuhanSaprotan> getKebutuhanSaprotan() {
        return KebutuhanSaprotan;
    }

    public void setKebutuhanSaprotan(List<com.app.kpb2.features.rut.model.KebutuhanSaprotan> kebutuhanSaprotan) {
        KebutuhanSaprotan = kebutuhanSaprotan;
    }

    public List<BiayaTanam> getGarapDanPemeliharaan() {
        return garapDanPemeliharaan;
    }

    public void setGarapDanPemeliharaan(List<BiayaTanam> garapDanPemeliharaan) {
        this.garapDanPemeliharaan = garapDanPemeliharaan;
    }

    public List<JadwalUsahaTani> getJadwalUsahaTani() {
        return jadwalUsahaTani;
    }

    public void setJadwalUsahaTani(List<JadwalUsahaTani> jadwalUsahaTani) {
        this.jadwalUsahaTani = jadwalUsahaTani;
    }
}
