package com.example.app4g.users.registrasi.presenter;

import android.graphics.Bitmap;

public interface IRegisterPresenter {
    void clear();
    void doRegistrasi(String nik, String nama, String noHp, String role, String password);
    void setProgressBarVisiblity(int visiblity);
}