package com.app.app4g.features.petani.noRekening;

import com.app.app4g.features.users.login.model.LoginResponse;

public interface IRekeningView {
    void initViews();


    void onCreateRekening();

    void onCreateRekeningSuksess(LoginResponse commonRespon, String noRek);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToRut();
}
