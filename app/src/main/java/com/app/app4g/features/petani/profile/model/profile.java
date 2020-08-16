package com.app.app4g.features.petani.profile.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class profile implements Serializable {
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

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("asetPetani")
    private List<AsetPetani> asetPetani;

    @SerializedName("luas_tanah")
    private LuasLahan luasLahan;

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

    @SerializedName("nomorRekening")
    private String nomorRekening;

    @SerializedName("idKios")
    private Number idKios;

    @SerializedName("namaKios")
    private String namaKios;

    @SerializedName("bank")
    private String bank;

    public String getNamaKios() {
        return namaKios;
    }

    public void setNamaKios(String namaKios) {
        this.namaKios = namaKios;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Number getIdKios() {
        return idKios;
    }

    public void setIdKios(Number idKios) {
        this.idKios = idKios;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public List<AsetPetani> getAsetPetani() {
        return asetPetani;
    }

    public void setAsetPetani(List<AsetPetani> asetPetani) {
        this.asetPetani = asetPetani;
    }

    public LuasLahan getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(LuasLahan luasLahan) {
        this.luasLahan = luasLahan;
    }

    @SerializedName("area")
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String phone_number) {
        this.no_hp = phone_number;
    }

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
                int compareQuantity = Integer.parseInt(o.getAnakKe());

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

    public class Area {
        @SerializedName("city")
        private String city;

        @SerializedName("city_code")
        private String city_code;

        @SerializedName("district")
        private String district;

        @SerializedName("district_code")
        private String district_code;

        @SerializedName("province")
        private String province;

        @SerializedName("province_code")
        private String province_code;

        @SerializedName("sub_district")
        private String sub_district;

        @SerializedName("sub_district_code")
        private String sub_district_code;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getSub_district() {
            return sub_district;
        }

        public void setSub_district(String sub_district) {
            this.sub_district = sub_district;
        }

        public String getSub_district_code() {
            return sub_district_code;
        }

        public void setSub_district_code(String sub_district_code) {
            this.sub_district_code = sub_district_code;
        }
    }
}
