package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("dataRUT")
    private List<Rut> rut;

    public List<Rut> getRut() {
        return rut;
    }

    public void setRut(List<Rut> rut) {
        this.rut = rut;
    }
}
