package com.app.kpb2.features.petani.noRekening.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KiosResponse {
    @SerializedName("status")
    private Boolean status ;

    @SerializedName("message")
    private String message ;

    @SerializedName("rc")
    private String rc ;

    @SerializedName("result")
    private List<Kios> result ;

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

    public List<Kios> getResult() {
        return result;
    }

    public void setResult(List<Kios> result) {
        this.result = result;
    }
}
