package com.example.app4g.features.petani.registrasi.model;

import com.example.app4g.features.petani.profile.model.LuasLahan;
import com.google.gson.annotations.SerializedName;

public class RegistModel {
    @SerializedName("nama")
    private String nama ;

    @SerializedName("nik")
    private String nik ;

    @SerializedName("address")
    private String address ;

    @SerializedName("password")
    private String password ;

//    @SerializedName("mt1")
//    private String mt1 ;
//
//    @SerializedName("mt2")
//    private String mt2 ;
//
//    @SerializedName("mt3")
//    private String mt3 ;

    @SerializedName("JmlhTernak")
    private String JmlhTernak ;

    @SerializedName("subsektor")
    private String subsektor ;

    @SerializedName("komoditas")
    private String komoditas ;

    @SerializedName("luas_lahan")
    private LuasLahan luasLahan ;

    @SerializedName("area")
    private Area area ;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public LuasLahan getLuasLahan() {
        return luasLahan;
    }

    public void setLuasLahan(LuasLahan luasLahan) {
        this.luasLahan = luasLahan;
    }

    public String getSubsektor() {
        return subsektor;
    }

    public void setSubsektor(String subsektor) {
        this.subsektor = subsektor;
    }

    public String getKomoditas() {
        return komoditas;
    }

    public void setKomoditas(String komoditas) {
        this.komoditas = komoditas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//    public String getAlamat() {
//        return address;
//    }
//
//    public void setAlamat(String address) {
//        this.address = address;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getMt1() {
//        return mt1;
//    }
//
//    public void setMt1(String mt1) {
//        this.mt1 = mt1;
//    }
//
//    public String getMt2() {
//        return mt2;
//    }
//
//    public void setMt2(String mt2) {
//        this.mt2 = mt2;
//    }
//
//    public String getMt3() {
//        return mt3;
//    }
//
//    public void setMt3(String mt3) {
//        this.mt3 = mt3;
//    }

    public String getJmlhTernak() {
        return JmlhTernak;
    }

    public void setJmlhTernak(String jmlhTernak) {
        JmlhTernak = jmlhTernak;
    }


}
