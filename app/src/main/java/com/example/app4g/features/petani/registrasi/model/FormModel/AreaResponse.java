package com.example.app4g.features.petani.registrasi.model.FormModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaResponse {
    @SerializedName("status")
    private Boolean status ;

    @SerializedName("message")
    private List<Result> result ;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
