package com.app.kpb2.features.rut.kebutuhanRut;

import com.app.kpb2.features.petani.registrasi.model.FormModel.Result;

import java.util.List;

public interface ICreateKebutuhanRut {
    void initViews();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result, String key);

    void onNetworkError(String cause);
}
