package com.app.app4g.features.rut.aset.createaset;

import com.app.app4g.features.petani.registrasi.model.FormModel.Result;
import com.app.app4g.features.users.login.model.LoginResponse;

import java.util.List;

public interface ICreateAsetView {
    void onCreateAsetSuccess(LoginResponse profile);

    void goToDashboard();

    void refreshPage();

    void initViews();

    void onSubmit();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result, String key);

    void onNetworkError(String cause);
}
