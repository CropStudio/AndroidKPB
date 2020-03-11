package com.example.app4g.features.users.registrasi.presenter;

public interface IRegisterPresenter {
    void clear();
    void doRegistrasi(String nik, String nama, String role, String password);
    void setProgressBarVisiblity(int visiblity);
}