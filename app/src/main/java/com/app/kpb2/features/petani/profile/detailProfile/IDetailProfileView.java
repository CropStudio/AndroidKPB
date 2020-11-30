package com.app.kpb2.features.petani.profile.detailProfile;

import com.app.kpb2.features.petani.profile.model.profile;

public interface IDetailProfileView {
    void initViews();

    void onDataReady(profile profile);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();

    void showAnakList();

    void hideAnakList();

    void showTanggunganList();

    void hideTanggunganList();
}
