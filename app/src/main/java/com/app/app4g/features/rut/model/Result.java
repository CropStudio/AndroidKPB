package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("dataRUT")
    private List<Rut> rut;

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


    public List<Rut> getRut() {
        return rut;
    }

    public void setRut(List<Rut> rut) {
        this.rut = rut;
    }

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
}