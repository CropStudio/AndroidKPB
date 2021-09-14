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

    @SerializedName("code")
    private int code;

    @SerializedName("midtrans")
    public midtrans midtrans;

    @SerializedName("result")
    private List<BarangTidakAda> value;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


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

    public CommonRespon.midtrans getMidtrans() {
        return midtrans;
    }

    public void setMidtrans(CommonRespon.midtrans midtrans) {
        this.midtrans = midtrans;
    }

    public class midtrans {
        @SerializedName("token")
        private String token;

        @SerializedName("redirect_url")
        private String redirect_url;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRedirect_url() {
            return redirect_url;
        }

        public void setRedirect_url(String redirect_url) {
            this.redirect_url = redirect_url;
        }
    }

}
