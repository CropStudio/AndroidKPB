package com.app.kpb2.features.cart.model;

import com.app.kpb2.features.petani.registrasi.model.Area;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Penyuluh {
    @SerializedName("penyuluh")
    private List<penyuluh> penyuluh;

    public List<Penyuluh.penyuluh> getPenyuluh() {
        return penyuluh;
    }

    public void setPenyuluh(List<Penyuluh.penyuluh> penyuluh) {
        this.penyuluh = penyuluh;
    }

    public class penyuluh {
        @SerializedName("_id")
        private String _id;
        @SerializedName("verifikasiBroker")
        private String verifikasiBroker;
        @SerializedName("idPenyuluh")
        private String idPenyuluh;
        @SerializedName("alamat")
        private String alamat;
        @SerializedName("nama")
        private String nama;
        @SerializedName("nomorTelp")
        private String nomorTelp;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getVerifikasiBroker() {
            return verifikasiBroker;
        }

        public void setVerifikasiBroker(String verifikasiBroker) {
            this.verifikasiBroker = verifikasiBroker;
        }

        public String getIdPenyuluh() {
            return idPenyuluh;
        }

        public void setIdPenyuluh(String idPenyuluh) {
            this.idPenyuluh = idPenyuluh;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getNomorTelp() {
            return nomorTelp;
        }

        public void setNomorTelp(String nomorTelp) {
            this.nomorTelp = nomorTelp;
        }
    }
}
