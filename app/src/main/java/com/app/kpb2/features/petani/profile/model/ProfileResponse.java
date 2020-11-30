package com.app.kpb2.features.petani.profile.model;

import com.google.gson.annotations.SerializedName;

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
    private Boolean success;

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
