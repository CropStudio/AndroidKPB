package com.example.app4g.features.users.login.model;

import com.example.app4g.features.petani.profile.model.profile;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("nik")
    private String nik;

    @SerializedName("nomorRekening")
    private String nomorRekening;

    @SerializedName("nama")
    private String nama;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("role")
    private String role;

    @SerializedName("idDesa")
    private String idKecamatan;

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

    @SerializedName("profile")
    private profile profile;

    public com.example.app4g.features.petani.profile.model.profile getProfile() {
        return profile;
    }

    public void setProfile(com.example.app4g.features.petani.profile.model.profile profile) {
        this.profile = profile;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
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

}
