package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class KebutuhanSaprotan implements Serializable {

    @SerializedName("nama")
    private String nama;

    @SerializedName("jumlah")
    private long jumlah;

    @SerializedName("subsidi")
    private Boolean subsidi;

    @SerializedName("listBarang")
    private List<ListBarang> listBarang;

    @SerializedName("selected")
    private ListBarang selected;

    @SerializedName("subTotal")
    private Long subTotal;

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

    public List<ListBarang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<ListBarang> listBarang) {
        this.listBarang = listBarang;
    }




}
