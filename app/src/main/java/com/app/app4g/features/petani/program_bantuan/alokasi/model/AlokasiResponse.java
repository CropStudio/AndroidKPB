package com.app.app4g.features.petani.program_bantuan.alokasi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlokasiResponse {
    @SerializedName("result")
    private List<com.app.app4g.features.petani.program_bantuan.alokasi.model.Result> result ;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("rc")
    private String mRc;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getmRm() {
        return mRm;
    }

    public void setmRm(String mRm) {
        this.mRm = mRm;
    }

    public Boolean getmStatus() {
        return mStatus;
    }

    public void setmStatus(Boolean mStatus) {
        this.mStatus = mStatus;
    }

    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

}
