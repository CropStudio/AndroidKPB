package com.app.kpb2.features.pasar_bebas.model;

import com.app.kpb2.features.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PasarBebasResponse {
    @SerializedName("code")
    private int mRc;

    @SerializedName("result")
    private List<Items> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public int getmRc() {
        return mRc;
    }

    public void setmRc(int mRc) {
        this.mRc = mRc;
    }

    public List<Items> getmResult() {
        return mResult;
    }

    public void setmResult(List<Items> mResult) {
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
