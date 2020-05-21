package com.app.app4g.features.rut.aset;

import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.features.users.login.model.LoginResponse;

public interface IAsetView {
    void initViews();

    void goToAddAset();

    void goToDashboard();

    void onDeleteSuccess(LoginResponse response);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
