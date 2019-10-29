package com.example.app4g.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omgimbot on 10/13/2019.
 */

public class RutResponse {
    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private List<Item> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getRc() {return mRc; }

    public void setRc(String rc) { mRc = rc; }

    public  List<Item> getResult() { return mResult; }

    public void setResult( List<Item> result) {mResult = result; }

    public String getRm() { return mRm; }

    public void setRm(String rm) { mRm = rm; }
    public Boolean getSuccess() { return mStatus; }
    public void  setSuccess(Boolean success) { mStatus = success; }
}
