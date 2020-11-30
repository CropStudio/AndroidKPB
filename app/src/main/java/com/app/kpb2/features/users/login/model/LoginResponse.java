package com.app.kpb2.features.users.login.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private Users mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getRc() {return mRc; }

    public void setRc(String rc) { mRc = rc; }

    public  Users getResult() { return mResult; }

    public void setResult( Users result) {mResult = result; }

    public String getRm() { return mRm; }

    public void setRm(String rm) { mRm = rm; }
    public Boolean getSuccess() { return mStatus; }
    public void  setSuccess(Boolean success) { mStatus = success; }


}
