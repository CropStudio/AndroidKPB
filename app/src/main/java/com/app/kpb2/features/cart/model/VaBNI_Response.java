package com.app.kpb2.features.cart.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VaBNI_Response {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("code")
    private int rc;
    @SerializedName("message")
    private String rm;
    @SerializedName("grandtotal")
    private String grandtotal;
    @SerializedName("uniquecode")
    private String uniquecode;
    @SerializedName("result")
    private VaBNI result;

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
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

    public VaBNI getResult() {
        return result;
    }

    public void setResult(VaBNI result) {
        this.result = result;
    }

    public class VaBNI{
        @SerializedName("virtual_account")
        private String virtual_account;
        @SerializedName("trx_id")
        private String trx_id;
        @SerializedName("grandTotal")
        private String grandTotal;
        @SerializedName("datetime_expired")
        private String datetime_expired;
        @SerializedName("orderId")
        private String orderId;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getVirtual_account() {
            return virtual_account;
        }

        public void setVirtual_account(String virtual_account) {
            this.virtual_account = virtual_account;
        }

        public String getTrx_id() {
            return trx_id;
        }

        public void setTrx_id(String trx_id) {
            this.trx_id = trx_id;
        }

        public String getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(String grandTotal) {
            this.grandTotal = grandTotal;
        }

        public String getDatetime_expired() {
            return datetime_expired;
        }

        public void setDatetime_expired(String datetime_expired) {
            this.datetime_expired = datetime_expired;
        }
    }
}
