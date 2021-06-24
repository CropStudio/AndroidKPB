package com.app.kpb2.features.data_produksi.model;

import com.app.kpb2.features.e_commerce.model.Item;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by omgimbot on 10/13/2019.
 */

public class DataProduksiResponse {
    @SerializedName("rc")
    private String mRc;

    @SerializedName("result")
    private List<DataProduksi> mResult;

    @SerializedName("message")
    private String mRm;

    @SerializedName("status")
    private Boolean mStatus;

    public String getRc() {return mRc; }

    public void setRc(String rc) { mRc = rc; }

    public  List<DataProduksi> getResult() { return mResult; }

    public void setResult( List<DataProduksi> result) {mResult = result; }

    public String getRm() { return mRm; }

    public void setRm(String rm) { mRm = rm; }
    public Boolean getSuccess() { return mStatus; }
    public void  setSuccess(Boolean success) { mStatus = success; }
}
