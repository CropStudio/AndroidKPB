package com.app.kpb2.features.petani.dashboard;

public interface IDashboardView {
    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void initViews();

    void goUpdateVersion(String rm);

    void goUpdateApps();
}
