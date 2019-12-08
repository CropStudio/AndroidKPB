package com.example.app4g.features.petani.detailProfile;

import com.example.app4g.features.petani.profile.model.profile;

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
