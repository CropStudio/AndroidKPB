package com.app.kpb2.features.rut.createmt.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistincKomoditas {
    @SerializedName("status")
    private Boolean status ;

    @SerializedName("rc")
    private String rc ;

    @SerializedName("message")
    private String message ;

    @SerializedName("result")
    private List<String> result ;

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
