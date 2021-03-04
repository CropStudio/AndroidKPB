package com.app.kpb2.features.petani.program_bantuan.alokasi;

import com.app.kpb2.features.petani.program_bantuan.alokasi.model.Result;

import java.util.List;

public interface IAlokasiView {
    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm ,String rc);

    void onNetworkError(String cause);

    void goToDashboard();
}
