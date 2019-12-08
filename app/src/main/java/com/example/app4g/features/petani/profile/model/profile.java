package com.example.app4g.features.petani.profile.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class profile {
    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jenisKelamin")
    private String jenisKelamin;

    @SerializedName("address")
    private String address;

    @SerializedName("tempatLahir")
    private String tempatLahir;

    @SerializedName("tglLahir")
    private String tglLahir;

    @SerializedName("noKk")
    private String noKk;

    @SerializedName("agama")
    private String agama;

    @SerializedName("pendidikanTerakhir")
    private String pendidikanTerakhir;

    @SerializedName("statusPerkawinan")
    private String statusPerkawinan;

    @SerializedName("namaIbuKandung")
    private String namaIbuKandung;

    @SerializedName("dataTambahan")
    private dataTambahan dataTambahan;

    @SerializedName("dataKepemilikan")
    private dataKepemilikan dataKepemilikan;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getPendidikanTerakhir() {
        return pendidikanTerakhir;
    }

    public void setPendidikanTerakhir(String pendidikanTerakhir) {
        this.pendidikanTerakhir = pendidikanTerakhir;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getNamaIbuKandung() {
        return namaIbuKandung;
    }

    public void setNamaIbuKandung(String namaIbuKandung) {
        this.namaIbuKandung = namaIbuKandung;
    }

    public profile.dataTambahan getDataTambahan() {
        return dataTambahan;
    }

    public void setDataTambahan(profile.dataTambahan dataTambahan) {
        this.dataTambahan = dataTambahan;
    }

    public profile.dataKepemilikan getDataKepemilikan() {
        return dataKepemilikan;
    }

    public void setDataKepemilikan(profile.dataKepemilikan dataKepemilikan) {
        this.dataKepemilikan = dataKepemilikan;
    }

    public class dataKepemilikan {
        @SerializedName("statusRumah")
        private String statusRumah;

        @SerializedName("statusLahan")
        private String statusLahan;

        @SerializedName("fasilitasListrik")
        private String fasilitasListrik;

        @SerializedName("kendaraan")
        private String kendaraan;

        @SerializedName("ternak")
        private Ternak ternak;

        @SerializedName("pekerjaan")
        private Pekerjaan pekerjaan;

        public String getStatusRumah() {
            return statusRumah;
        }

        public void setStatusRumah(String statusRumah) {
            this.statusRumah = statusRumah;
        }

        public String getStatusLahan() {
            return statusLahan;
        }

        public void setStatusLahan(String statusLahan) {
            this.statusLahan = statusLahan;
        }

        public String getFasilitasListrik() {
            return fasilitasListrik;
        }

        public void setFasilitasListrik(String fasilitasListrik) {
            this.fasilitasListrik = fasilitasListrik;
        }

        public String getKendaraan() {
            return kendaraan;
        }

        public void setKendaraan(String kendaraan) {
            this.kendaraan = kendaraan;
        }

        public Ternak getTernak() {
            return ternak;
        }

        public void setTernak(Ternak ternak) {
            this.ternak = ternak;
        }

        public Pekerjaan getPekerjaan() {
            return pekerjaan;
        }

        public void setPekerjaan(Pekerjaan pekerjaan) {
            this.pekerjaan = pekerjaan;
        }

        public class Ternak {
            @SerializedName("namaTernak")
            private String namaTernak;

            @SerializedName("keteranganTernak")
            private String keteranganTernak;

            public String getKeteranganTernak() {
                return keteranganTernak;
            }

            public void setKeteranganTernak(String keteranganTernak) {
                this.keteranganTernak = keteranganTernak;
            }

            public String getNamaTernak() {
                return namaTernak;
            }

            public void setNamaTernak(String namaTernak) {
                this.namaTernak = namaTernak;
            }
        }

        public class Pekerjaan {
            @SerializedName("pekerjanSelainTani")
            private String pekerjanSelainTani;

            @SerializedName("pekerjanPenghuniRumah")
            private String pekerjanPenghuniRumah;

            public String getPekerjanSelainTani() {
                return pekerjanSelainTani;
            }

            public void setPekerjanSelainTani(String pekerjanSelainTani) {
                this.pekerjanSelainTani = pekerjanSelainTani;
            }

            public String getPekerjanPenghuniRumah() {
                return pekerjanPenghuniRumah;
            }

            public void setPekerjanPenghuniRumah(String pekerjanPenghuniRumah) {
                this.pekerjanPenghuniRumah = pekerjanPenghuniRumah;
            }
        }
    }

    public class dataTambahan {
        @SerializedName("namaKepalaKeluarga")
        private String namaKepalaKeluarga;

        @SerializedName("dataAnak")
        private List<DataAnak> dataAnak;

        @SerializedName("tanggunganLain")
        private List<TanggunganLain> tanggunganLain;

        public String getNamaKepalaKeluarga() {
            return namaKepalaKeluarga;
        }

        public void setNamaKepalaKeluarga(String namaKepalaKeluarga) {
            this.namaKepalaKeluarga = namaKepalaKeluarga;
        }

        public List<DataAnak> getDataAnak() {
            return dataAnak;
        }

        public void setDataAnak(List<DataAnak> dataAnak) {
            this.dataAnak = dataAnak;
        }

        public List<TanggunganLain> getTanggunganLain() {
            return tanggunganLain;
        }

        public void setTanggunganLain(List<TanggunganLain> tanggunganLain) {
            this.tanggunganLain = tanggunganLain;
        }

        public class DataAnak implements Comparable<DataAnak> {
            @SerializedName("tglLahirAnak")
            private String tglLahirAnak;

            @SerializedName("_id")
            private String _id;

            @SerializedName("pendidikanSekarang")
            private String pendidikanSekarang;

            @SerializedName("tempatLahir")
            private String tempatLahir;

            @SerializedName("namaAnak")
            private String namaAnak;

            @SerializedName("anakKe")
            private String anakKe;

            public String getTglLahirAnak() {
                return tglLahirAnak;
            }

            public void setTglLahirAnak(String tglLahirAnak) {
                this.tglLahirAnak = tglLahirAnak;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getPendidikanSekarang() {
                return pendidikanSekarang;
            }

            public void setPendidikanSekarang(String pendidikanSekarang) {
                this.pendidikanSekarang = pendidikanSekarang;
            }

            public String getTempatLahir() {
                return tempatLahir;
            }

            public void setTempatLahir(String tempatLahir) {
                this.tempatLahir = tempatLahir;
            }

            public String getNamaAnak() {
                return namaAnak;
            }

            public void setNamaAnak(String namaAnak) {
                this.namaAnak = namaAnak;
            }

            public String getAnakKe() {
                return anakKe;
            }

            public void setAnakKe(String anakKe) {
                this.anakKe = anakKe;
            }

            @Override
            public int compareTo(@NonNull DataAnak o) {
                int compareQuantity = Integer.parseInt(((DataAnak) o).getAnakKe());

                //ascending order
                return Integer.parseInt(this.anakKe) - compareQuantity;

            }
        }

        public class TanggunganLain {
            @SerializedName("namaLengkap")
            private String namaLengkap;

            @SerializedName("tempatLahir")
            private String tempatLahir;

            @SerializedName("tglLahir")
            private String tglLahir;

            @SerializedName("hubungandDenganKepalaKeluarga")
            private String hubungandDenganKepalaKeluarga;

            public String getNamaLengkap() {
                return namaLengkap;
            }

            public void setNamaLengkap(String namaLengkap) {
                this.namaLengkap = namaLengkap;
            }

            public String getTempatLahir() {
                return tempatLahir;
            }

            public void setTempatLahir(String tempatLahir) {
                this.tempatLahir = tempatLahir;
            }

            public String getTglLahir() {
                return tglLahir;
            }

            public void setTglLahir(String tglLahir) {
                this.tglLahir = tglLahir;
            }

            public String getHubungandDenganKepalaKeluarga() {
                return hubungandDenganKepalaKeluarga;
            }

            public void setHubungandDenganKepalaKeluarga(String hubungandDenganKepalaKeluarga) {
                this.hubungandDenganKepalaKeluarga = hubungandDenganKepalaKeluarga;
            }


        }
    }
}
