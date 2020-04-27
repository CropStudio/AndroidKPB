package com.app.app4g.features.petani.anak.presenter;

public interface IAnakPresenter {
    void clear();
    void doAnak(String nama, String tglLahir, String jenisKelamin, String idUser);
    void setProgressBarVisiblity(int visiblity);
}
