package com.app.kpb2.features.rut.aset;

import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.users.login.model.LoginResponse;

public interface IAsetView {
    void initViews();

    void goToAddAset();

    void goToDashboard();

    void goToCreateMt(AsetPetani aset);

    void onDeleteSuccess(LoginResponse response);

    void onRequestFailed(LoginResponse response);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
