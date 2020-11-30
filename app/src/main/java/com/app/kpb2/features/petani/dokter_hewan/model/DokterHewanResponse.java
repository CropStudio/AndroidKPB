package com.app.kpb2.features.petani.dokter_hewan.model;

import com.google.gson.annotations.SerializedName;

public class DokterHewanResponse {
    @SerializedName("status")
    private Boolean status ;

    @SerializedName("message")
    private String message ;

    @SerializedName("result")
    private DokterHewans result ;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DokterHewans getResult() {
        return result;
    }

    public void setResult(DokterHewans result) {
        this.result = result;
    }
}
