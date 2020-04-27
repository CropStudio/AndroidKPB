package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rut {
    @SerializedName("jenisTanaman")
    private String jenisTanaman;

    @SerializedName("masaTanam")
    private String masaTanam;

    @SerializedName("kebutuhanSaprotan")
    private List<KebutuhanSaprotan> KebutuhanSaprotan;

    @SerializedName("garapDanPemeliharaan")
    private List<BiayaTanam> BiayaTanam;

    @SerializedName("jadwalUsahaTani")
    private KalenderTanam KalenderTanam;

    @SerializedName("status")
    private String status;

    @SerializedName("subTotalSaprotan")
    private String subTotalSaprotan;

    @SerializedName("subTotalGarapDanPemeliharaan")
    private String subTotalGarapDanPemeliharaan;

    @SerializedName("subPrediksiPendapatan")
    private String subPrediksiPendapatan;

    @SerializedName("subPendapatanKotor")
    private String subPendapatanKotor;

    @SerializedName("nomorRekeningPetani")
    private String nomorRekening;

    @SerializedName("Bank")
    private String bank;

    @SerializedName("nik")
    private String nik;

    @SerializedName("idPenuyuluh")
    private String idPenuyuluh;

    @SerializedName("idDesa")
    private String idDesa;

    @SerializedName("tahun")
    private Number tahun;

    @SerializedName("idPoktan")
    private Number idPoktan;

    @SerializedName("idKios")
    private Number idKios;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getIdPenuyuluh() {
        return idPenuyuluh;
    }

    public void setIdPenuyuluh(String idPenuyuluh) {
        this.idPenuyuluh = idPenuyuluh;
    }

    public String getIdDesa() {
        return idDesa;
    }

    public void setIdDesa(String idDesa) {
        this.idDesa = idDesa;
    }

    public Number getTahun() {
        return tahun;
    }

    public void setTahun(Number tahun) {
        this.tahun = tahun;
    }

    public Number getIdPoktan() {
        return idPoktan;
    }

    public void setIdPoktan(Number idPoktan) {
        this.idPoktan = idPoktan;
    }

    public Number getIdKios() {
        return idKios;
    }

    public void setIdKios(Number idKios) {
        this.idKios = idKios;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getSubTotalSaprotan() {
        return subTotalSaprotan;
    }

    public void setSubTotalSaprotan(String subTotalSaprotan) {
        this.subTotalSaprotan = subTotalSaprotan;
    }

    public String getSubTotalGarapDanPemeliharaan() {
        return subTotalGarapDanPemeliharaan;
    }

    public void setSubTotalGarapDanPemeliharaan(String subTotalGarapDanPemeliharaan) {
        this.subTotalGarapDanPemeliharaan = subTotalGarapDanPemeliharaan;
    }

    public String getSubPrediksiPendapatan() {
        return subPrediksiPendapatan;
    }

    public void setSubPrediksiPendapatan(String subPrediksiPendapatan) {
        this.subPrediksiPendapatan = subPrediksiPendapatan;
    }

    public String getSubPendapatanKotor() {
        return subPendapatanKotor;
    }

    public void setSubPendapatanKotor(String subPendapatanKotor) {
        this.subPendapatanKotor = subPendapatanKotor;
    }

    public com.app.app4g.features.rut.model.KalenderTanam getKalenderTanam() {
        return KalenderTanam;
    }

    public void setKalenderTanam(com.app.app4g.features.rut.model.KalenderTanam kalenderTanam) {
        KalenderTanam = kalenderTanam;
    }

    public List<BiayaTanam> getBiayaTanam() {
        return BiayaTanam;
    }

    public void setBiayaTanam(List<BiayaTanam> biayaTanam) {
        BiayaTanam = biayaTanam;
    }

    public boolean isSelected ;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(String masaTanam) {
        this.masaTanam = masaTanam;
    }

    public List<com.app.app4g.features.rut.model.KebutuhanSaprotan> getKebutuhanSaprotan() {
        return KebutuhanSaprotan;
    }

    public void setKebutuhanSaprotan(List<com.app.app4g.features.rut.model.KebutuhanSaprotan> kebutuhanSaprotan) {
        KebutuhanSaprotan = kebutuhanSaprotan;
    }
}
