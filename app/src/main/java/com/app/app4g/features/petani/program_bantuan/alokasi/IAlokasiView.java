package com.app.app4g.features.petani.program_bantuan.alokasi;

import com.app.app4g.features.petani.program_bantuan.alokasi.model.Result;

import java.util.List;

public interface IAlokasiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();
}
