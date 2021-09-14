package com.app.kpb2.features.pasar_bebas.transaksi.model;

import com.google.gson.annotations.SerializedName;

public class Transaksis {
    @SerializedName("orderId")
    private String order_id ;

    @SerializedName("grandTotal")
    private String gross_amount ;

    @SerializedName("payment_type")
    private String payment_type ;

    @SerializedName("type")
    private String type ;

    @SerializedName("channel")
    private String channel ;

    @SerializedName("idUnique")
    private String idUnique ;

    @SerializedName("statusPembayaran")
    private statusPembayaran transaction_status ;

    @SerializedName("statusBarang")
    private StatusBarang statusBarang ;

    @SerializedName("idPemesan")
    private String idPemesan;

    public String getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(String idUnique) {
        this.idUnique = idUnique;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getGross_amount() {
        return gross_amount;
    }

    public void setGross_amount(String gross_amount) {
        this.gross_amount = gross_amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public statusPembayaran getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(statusPembayaran transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public StatusBarang getStatusBarang() {
        return statusBarang;
    }

    public void setStatusBarang(StatusBarang statusBarang) {
        this.statusBarang = statusBarang;
    }

    public class statusPembayaran{
        @SerializedName("status")
        private String status ;

        @SerializedName("metode")
        private metode metode ;

        @SerializedName("created_at")
        private String created_at ;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Transaksis.metode getMetode() {
            return metode;
        }

        public void setMetode(Transaksis.metode metode) {
            this.metode = metode;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }

    public class metode{
        @SerializedName("va_numbers")
        private String va_numbers ;

        @SerializedName("store")
        private String store ;

        @SerializedName("payment_code")
        private String payment_code ;

        @SerializedName("payment_type")
        private String payment_type ;

        public String getVa_numbers() {
            return va_numbers;
        }

        public void setVa_numbers(String va_numbers) {
            this.va_numbers = va_numbers;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }
    }

    public class StatusBarang{
        @SerializedName("status")
        private int status ;

        @SerializedName("created_at")
        private String created_at ;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
