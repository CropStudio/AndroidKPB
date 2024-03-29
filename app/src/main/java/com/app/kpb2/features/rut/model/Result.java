package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable  {

    //extra field
    @SerializedName("idKios")
    private Number idKios;

    @SerializedName("idKabupaten")
    private String idKabupaten;

    //end
    @SerializedName("_id")
    private String _id;

    @SerializedName("tahun")
    private String tahun;

    @SerializedName("subTotalSaprotan")
    private String subTotalSaprotan;

    @SerializedName("updated")
    private Boolean updated;

    @SerializedName("nik")
    private String nik;

    @SerializedName("mt")
    private String mt;

    @SerializedName("idAsset")
    private String idAsset;

    @SerializedName("jumlahAset")
    private String jumlahAset;

    @SerializedName("komoditas")
    private String komoditas;

    @SerializedName("idDesa")
    private String idDesa;

    @SerializedName("idPenyuluh")
    private String idPenyuluh;

    @SerializedName("subsektor")
    private String subsektor;

    @SerializedName("statusSetuju")
    private Boolean statusSetuju;

    @SerializedName("statusKur")
    private Boolean statusKur;

    @SerializedName("verifiedKur")
    private Boolean verifiedKur;

    @SerializedName("jumlahHasilPanen")
    private String jumlahHasilPanen;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("hargaSatuan")
    private String hargaSatuan;

    @SerializedName("hasilPanen")
    private List<HasilPanen> hasilPanen;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("standarUntuk")
    private String standarUntuk;

    @SerializedName("satuanAsset")
    private String satuanAsset;

    @SerializedName("subTotalGarapDanPemeliharaan")
    private String subTotalGarapDanPemeliharaan;

    @SerializedName("subTotalKebutuhanSaprotan")
    private String subTotalKebutuhanSaprotan;

    @SerializedName("subTotalBiayaUsahaTani")
    private String subTotalBiayaUsahaTani;

    @SerializedName("namaTransaksi")
    private String namaTransaksi;

    @SerializedName("subPrediksiPendapatan")
    private String subPrediksiPendapatan;

    @SerializedName("pendapatanKotor") //estimasi pendapatan = pendapatanKotor
    private String pendapatanKotor;

    @SerializedName("kebutuhanSaprotan")
    private List<KebutuhanSaprotan> KebutuhanSaprotan;

    @SerializedName("garapDanPemeliharaan")
    private List<BiayaTanam> garapDanPemeliharaan;

    @SerializedName("jadwalUsahaTani")
    private List<JadwalUsahaTani> jadwalUsahaTani;

    @SerializedName("waktuTanam")
    private String waktuTanam;

    @SerializedName("tanggalPengambilanPupuk")
    private String tanggalPengambilanPupuk;

    @SerializedName("tanggalTransfer")
    private String tanggalTransfer;

    @SerializedName("bank")
    private String bank;

    @SerializedName("noRek")
    private String noRek;

    @SerializedName("idPoktan")
    private String idPoktan;

    public String getIdPoktan() {
        return idPoktan;
    }

    public void setIdPoktan(String idPoktan) {
        this.idPoktan = idPoktan;
    }

    public Boolean getVerifiedKur() {
        return verifiedKur;
    }

    public void setVerifiedKur(Boolean verifiedKur) {
        this.verifiedKur = verifiedKur;
    }

    public Boolean getStatusKur() {
        return statusKur;
    }

    public void setStatusKur(Boolean statusKur) {
        this.statusKur = statusKur;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public Boolean getStatusSetuju() {
        return statusSetuju;
    }

    public void setStatusSetuju(Boolean statusSetuju) {
        this.statusSetuju = statusSetuju;
    }

    public Number getIdKios() {
        return idKios;
    }

    public void setIdKios(Number idKios) {
        this.idKios = idKios;
    }

    public String getIdKabupaten() {
        return idKabupaten;
    }

    public void setIdKabupaten(String idKabupaten) {
        this.idKabupaten = idKabupaten;
    }

    public String getSubTotalGarapDanPemeliharaan() {
        return subTotalGarapDanPemeliharaan;
    }

    public void setSubTotalGarapDanPemeliharaan(String subTotalGarapDanPemeliharaan) {
        this.subTotalGarapDanPemeliharaan = subTotalGarapDanPemeliharaan;
    }

    public String getSubTotalKebutuhanSaprotan() {
        return subTotalKebutuhanSaprotan;
    }

    public void setSubTotalKebutuhanSaprotan(String subTotalKebutuhanSaprotan) {
        this.subTotalKebutuhanSaprotan = subTotalKebutuhanSaprotan;
    }

    public String getSubTotalBiayaUsahaTani() {
        return subTotalBiayaUsahaTani;
    }

    public void setSubTotalBiayaUsahaTani(String subTotalBiayaUsahaTani) {
        this.subTotalBiayaUsahaTani = subTotalBiayaUsahaTani;
    }

    public String getSubPrediksiPendapatan() {
        return subPrediksiPendapatan;
    }

    public void setSubPrediksiPendapatan(String subPrediksiPendapatan) {
        this.subPrediksiPendapatan = subPrediksiPendapatan;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

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

    public List<HasilPanen> getHasilPanen() {
        return hasilPanen;
    }

    public void setHasilPanen(List<HasilPanen> hasilPanen) {
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

    public String getWaktuTanam() {
        return waktuTanam;
    }

    public void setWaktuTanam(String waktuTanam) {
        this.waktuTanam = waktuTanam;
    }

    public String getTanggalPengambilanPupuk() {
        return tanggalPengambilanPupuk;
    }

    public void setTanggalPengambilanPupuk(String tanggalPengambilanPupuk) {
        this.tanggalPengambilanPupuk = tanggalPengambilanPupuk;
    }

    public String getTanggalTransfer() {
        return tanggalTransfer;
    }

    public void setTanggalTransfer(String tanggalTransfer) {
        this.tanggalTransfer = tanggalTransfer;
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

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getSubTotalSaprotan() {
        return subTotalSaprotan;
    }

    public void setSubTotalSaprotan(String subTotalSaprotan) {
        this.subTotalSaprotan = subTotalSaprotan;
    }

    public Boolean getUpdated() {
        return updated;
    }

    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }

    public String getIdPenyuluh() {
        return idPenyuluh;
    }

    public void setIdPenyuluh(String idPenyuluh) {
        this.idPenyuluh = idPenyuluh;
    }
}