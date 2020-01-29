package com.example.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RutResponse {
    @SerializedName("result")
    private Result result;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("rc")
    private String mRc;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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
