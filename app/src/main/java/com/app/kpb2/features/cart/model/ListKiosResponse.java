package com.app.kpb2.features.cart.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListKiosResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("code")
    private int rc;
    @SerializedName("message")
    private String rm;

    @SerializedName("result")
    private List<Kios> result;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public List<Kios> getResult() {
        return result;
    }

    public void setResult(List<Kios> result) {
        this.result = result;
    }
}
