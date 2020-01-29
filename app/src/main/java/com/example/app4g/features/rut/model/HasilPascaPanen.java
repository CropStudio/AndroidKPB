package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class HasilPascaPanen {

    @SerializedName("hasilPanen")
    private String hasilPanen;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("pendapatanKotor")
    private long pendapatanKotor;

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

    public long getPendapatanKotor() {
        return pendapatanKotor;
    }

    public void setPendapatanKotor(long pendapatanKotor) {
        this.pendapatanKotor = pendapatanKotor;
    }
}
