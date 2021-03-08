package com.app.kpb2.features.petani.dashboard;

import com.app.kpb2.features.petani.noRekening.model.Balance;

public interface IDashboardView {
    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void initViews();

    void goUpdateVersion(String rm);

    void onDataReady(Balance result);

    void goUpdateApps();
}
