package com.app.kpb2.common;

import com.app.kpb2.features.rut.model.BarangTidakAda;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommonRespon {
    @SerializedName("rc")
    private String mRc;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    @SerializedName("result")
    private List<BarangTidakAda> value;

    public List<BarangTidakAda> getValue() {
        return value;
    }

    public void setValue(List<BarangTidakAda> value) {
        this.value = value;
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

    public Boolean getSuccess() {
        return mStatus;
    }

    public void setSuccess(Boolean mStatus) {
        this.mStatus = mStatus;
    }


}
