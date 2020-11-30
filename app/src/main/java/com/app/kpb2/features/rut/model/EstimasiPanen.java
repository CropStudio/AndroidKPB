package com.app.kpb2.features.rut.model;

import com.google.gson.annotations.SerializedName;

public class EstimasiPanen {

    @SerializedName("gabahKeringPanen")
    private double gabahKeringPanen;

    @SerializedName("gabahKeringGiling")
    private double gabahKeringGiling;

    @SerializedName("beras")
    private double beras;

    public double getGabahKeringPanen() {
        return gabahKeringPanen;
    }

    public void setGabahKeringPanen(double gabahKeringPanen) {
        this.gabahKeringPanen = gabahKeringPanen;
    }

    public double getGabahKeringGiling() {
        return gabahKeringGiling;
    }

    public void setGabahKeringGiling(double gabahKeringGiling) {
        this.gabahKeringGiling = gabahKeringGiling;
    }

    public double getBeras() {
        return beras;
    }

    public void setBeras(double beras) {
        this.beras = beras;
    }
}
