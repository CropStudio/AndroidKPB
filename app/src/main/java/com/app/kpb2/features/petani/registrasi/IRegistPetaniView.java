package com.app.kpb2.features.petani.registrasi;

import com.app.kpb2.features.petani.registrasi.model.FormModel.Result;

import java.util.List;

public interface IRegistPetaniView {
    void initViews();

    void onRegisPetani();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result, String key);

    void onCreateSuccess();

    void onCreateFailed(String rm);

    void onNetworkError(String cause);

    void goBack();
}
