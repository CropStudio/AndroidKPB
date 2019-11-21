package com.example.app4g.features.users.login.model;

import com.example.app4g.features.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
