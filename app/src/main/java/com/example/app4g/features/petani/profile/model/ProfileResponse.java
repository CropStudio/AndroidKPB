package com.example.app4g.features.petani.profile.model;

import com.example.app4g.features.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omgimbot on 10/13/2019.
 */

public class ProfileResponse {
    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private profile result;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public profile getResult() {
        return result;
    }

    public void setResult(profile result) {
        this.result = result;
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
