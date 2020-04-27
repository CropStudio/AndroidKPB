package com.app.app4g.features.petani.dashboard;

public interface IDashboardView {
    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void initViews();

    void goUpdateVersion(String rm);

    void goUpdateApps();
}
