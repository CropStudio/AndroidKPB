package com.app.app4g.data;

public class DataPoktan {

    String idPoktan, namaPoktan, kabupaten, kecamatan, desa;

    public DataPoktan(){}

    public DataPoktan(String idPoktan, String namaPoktan, String kabupaten, String kecamatan, String desa){
        this.idPoktan   = idPoktan;
        this.namaPoktan = namaPoktan;
        this.kabupaten  = kabupaten;
        this.kecamatan  = kecamatan;
        this.desa       = desa;
    }

    public String getIdPoktan() {
        return idPoktan;
    }

    public void setIdPoktan(String idPoktan) {
        this.idPoktan = idPoktan;
    }

    public String getNamaPoktan() {
        return namaPoktan;
    }

    public void setNamaPoktan(String namaPoktan) {
        this.namaPoktan = namaPoktan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }
}
