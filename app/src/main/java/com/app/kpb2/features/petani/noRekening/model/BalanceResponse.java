package com.app.kpb2.features.petani.noRekening.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BalanceResponse {

    @SerializedName("status")
    private Boolean status ;

    @SerializedName("message")
    private String message ;

    @SerializedName("rc")
    private String rc ;

    @SerializedName("result")
    private Balance result ;

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

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public Balance getResult() {
        return result;
    }

    public void setResult(Balance result) {
        this.result = result;
    }
}
