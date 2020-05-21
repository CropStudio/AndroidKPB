package com.app.app4g.features.rut.createmt;

import com.app.app4g.features.petani.registrasi.model.FormModel.AreaResponse;
import com.app.app4g.features.petani.registrasi.model.FormModel.Result;
import com.app.app4g.features.users.login.model.LoginResponse;

import java.util.ArrayList;
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
