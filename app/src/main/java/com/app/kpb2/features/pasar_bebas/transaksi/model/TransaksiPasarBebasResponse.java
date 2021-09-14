package com.app.kpb2.features.pasar_bebas.transaksi.model;

import com.app.kpb2.features.transaksi.model.Transaksi;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransaksiPasarBebasResponse {

    @SerializedName("code")
    private String mRc;

    @SerializedName("result")
    private List<Transaksis> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public List<Transaksis> getmResult() {
        return mResult;
    }

    public void setmResult(List<Transaksis> mResult) {
        this.mResult = mResult;
    }

    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
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
