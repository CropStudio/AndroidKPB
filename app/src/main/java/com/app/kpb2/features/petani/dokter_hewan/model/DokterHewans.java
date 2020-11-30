package com.app.kpb2.features.petani.dokter_hewan.model;

import com.google.gson.annotations.SerializedName;

public class DokterHewans {

    @SerializedName("namaDokterHewan")
    private String namaDokterHewan ;

    @SerializedName("nomorTelp")
    private String nomorTelp ;

    @SerializedName("idKab")
    private String idKab ;

    @SerializedName("namaKabupaten")
    private String namaKabupaten ;


    public String getNamaDokterHewan() {
        return namaDokterHewan;
    }

    public void setNamaDokterHewan(String namaDokterHewan) {
        this.namaDokterHewan = namaDokterHewan;
    }

    public String getNomorTelp() {
        return nomorTelp;
    }

    public void setNomorTelp(String nomorTelp) {
        this.nomorTelp = nomorTelp;
    }

    public String getIdKab() {
        return idKab;
    }

    public void setIdKab(String idKab) {
        this.idKab = idKab;
    }

    public String getNamaKabupaten() {
        return namaKabupaten;
    }

    public void setNamaKabupaten(String namaKabupaten) {
        this.namaKabupaten = namaKabupaten;
    }
}
