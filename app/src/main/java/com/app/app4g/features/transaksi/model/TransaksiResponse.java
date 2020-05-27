package com.app.app4g.features.transaksi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiResponse {

    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private List<Transaksi> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

    public List<Transaksi> getmResult() {
        return mResult;
    }

    public void setmResult(List<Transaksi> mResult) {
        this.mResult = mResult;
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
}
