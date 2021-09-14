package com.app.kpb2.features.pasar_bebas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Items {

    @SerializedName("_id")
    private String _id;

    @SerializedName("namaBarang")
    private String namaBarang;

    @SerializedName("hargaBarang")
    private Long hargaBarang;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("idUser")
    private String idUser;

    @SerializedName("foto")
    private List<Foto> foto;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("stok")
    private int stok;

    @SerializedName("idDistributor")
    private String idDistributor;

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

    public long getFeePenyuluh() {
        return feePenyuluh;
    }

    public void setFeePenyuluh(long feePenyuluh) {
        this.feePenyuluh = feePenyuluh;
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

    public String getIdDistributor() {
        return idDistributor;
    }

    public void setIdDistributor(String idDistributor) {
        this.idDistributor = idDistributor;
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

    public Long getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(Long hargaBarang) {
        this.hargaBarang = hargaBarang;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<Foto> getFoto() {
        return foto;
    }

    public void setFoto(List<Foto> foto) {
        this.foto = foto;
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public class Foto{
        @SerializedName("_id")
        private String _id;

        @SerializedName("namaFile")
        private String namaFile;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getNamaFile() {
            return namaFile;
        }

        public void setNamaFile(String namaFile) {
            this.namaFile = namaFile;
        }
    }
}
