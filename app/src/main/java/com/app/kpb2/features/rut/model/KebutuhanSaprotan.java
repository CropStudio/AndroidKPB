package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class KebutuhanSaprotan implements Serializable {

    @SerializedName("_id")
    private String _id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("sisa")
    private String sisa;

    @SerializedName("total")
    private String total;

    @SerializedName("sisaDari")
    private String sisaDari;

    @SerializedName("sudahAmbil")
    private String sudahAmbil;

    @SerializedName("jumlah")
    private float jumlah;

    @SerializedName("subsidi")
    private Boolean subsidi;

    @SerializedName("listBarang")
    private List<ListBarang> listBarang;

    @SerializedName("selected")
    private ListBarang selected;

    @SerializedName("subTotal")
    private Long subTotal;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Long subTotal) {
        this.subTotal = subTotal;
    }

    public ListBarang getSelected() {
        return selected;
    }

    public void setSelected(ListBarang selected) {
        this.selected = selected;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public Boolean getSubsidi() {
        return subsidi;
    }

    public void setSubsidi(Boolean subsidi) {
        this.subsidi = subsidi;
    }

    public List<ListBarang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<ListBarang> listBarang) {
        this.listBarang = listBarang;
    }


    public String getSisa() {
        return sisa;
    }

    public void setSisa(String sisa) {
        this.sisa = sisa;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSisaDari() {
        return sisaDari;
    }

    public void setSisaDari(String sisaDari) {
        this.sisaDari = sisaDari;
    }

    public String getSudahAmbil() {
        return sudahAmbil;
    }

    public void setSudahAmbil(String sudahAmbil) {
        this.sudahAmbil = sudahAmbil;
    }
}
