package com.app.app4g.features.petani.program_bantuan.bantuan;

import com.app.app4g.features.petani.program_bantuan.bantuan.model.Result;

import java.util.List;

public interface IBantuanView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToProgramBantuan();
}
