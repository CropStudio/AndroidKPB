package com.app.kpb2.features.petani.program_bantuan.bantuan;

import com.app.kpb2.features.petani.program_bantuan.bantuan.model.Result;

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
