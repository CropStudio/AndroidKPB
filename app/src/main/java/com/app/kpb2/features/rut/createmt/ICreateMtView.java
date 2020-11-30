package com.app.kpb2.features.rut.createmt;

import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

public interface ICreateMtView {
    void initViews();

//    void onCreateAsetSuccess(LoginResponse profile);

    void onDataReady(List<String> result);

    void onCreateMtSuccess(LoginResponse profile);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);

    void gotoAsset();

    void CreateMt();
}
