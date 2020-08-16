package com.app.app4g.features.petani.program_bantuan.autp;

import com.app.app4g.features.petani.program_bantuan.autp.model.Result;

import java.util.List;

public interface IAutpView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToProgramBantuan();
}
