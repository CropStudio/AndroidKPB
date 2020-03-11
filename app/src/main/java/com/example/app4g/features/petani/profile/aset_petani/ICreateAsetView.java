package com.example.app4g.features.petani.profile.aset_petani;

import com.example.app4g.features.petani.registrasi.model.FormModel.Result;

import java.util.List;

public interface ICreateAsetView {
    void initViews();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result, String key);

    void onNetworkError(String cause);
}
