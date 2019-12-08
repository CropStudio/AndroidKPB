package com.example.app4g.features.users.login.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("role")
    private String role;

    @SerializedName("hashed_password")
    private String hashed_password;

    @SerializedName("update_at")
    private String update_at;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("user_photo")
    private String user_photo;

    @SerializedName("token")
    private String token;

    @SerializedName("nama_poktan")
    private String nama_poktan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("mt1")
    private String mt1;

    @SerializedName("mt2")
    private String mt2;

    @SerializedName("mt3")
    private String mt3;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("kabupaten")
    private String kabupaten;

    @SerializedName("kota")
    private String kota;

    @SerializedName("provinsi")
    private String provinsi;

    @SerializedName("noKk")
    private String noKk;

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
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

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNama_poktan() {
        return nama_poktan;
    }

    public void setNama_poktan(String nama_poktan) {
        this.nama_poktan = nama_poktan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMt1() {
        return mt1;
    }

    public void setMt1(String mt1) {
        this.mt1 = mt1;
    }

    public String getMt2() {
        return mt2;
    }

    public void setMt2(String mt2) {
        this.mt2 = mt2;
    }

    public String getMt3() {
        return mt3;
    }

    public void setMt3(String mt3) {
        this.mt3 = mt3;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
}
