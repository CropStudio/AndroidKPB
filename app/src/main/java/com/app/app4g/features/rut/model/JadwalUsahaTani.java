package com.app.app4g.features.rut.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JadwalUsahaTani {
    @SerializedName("jadwal")
    private List<Jadwal> jadwal;

    @SerializedName("_id")
    private String _id;

    @SerializedName("masaTanam")
    private int masaTanam;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(int masaTanam) {
        this.masaTanam = masaTanam;
    }

    public List<Jadwal> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Jadwal> jadwal) {
        this.jadwal = jadwal;
    }

    public class Jadwal {
        @SerializedName("_id")
        private String _id;

        @SerializedName("namaJadwal")
        private String namaJadwal;

        @SerializedName("tanggal")
        private String tanggal;

        public String getNamaJadwal() {
            return namaJadwal;
        }

        public void setNamaJadwal(String namaJadwal) {
            this.namaJadwal = namaJadwal;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
