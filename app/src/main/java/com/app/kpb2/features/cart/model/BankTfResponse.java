package com.app.kpb2.features.cart.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankTfResponse {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("code")
    private int rc;
    @SerializedName("message")
    private String rm;
    @SerializedName("grandtotal")
    private long grandtotal;
    @SerializedName("uniquecode")
    private String uniquecode;
    @SerializedName("result")
    private List<Checkout> result;

    public List<Checkout> getResult() {
        return result;
    }

    public void setResult(List<Checkout> result) {
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public long getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(long grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }


}
