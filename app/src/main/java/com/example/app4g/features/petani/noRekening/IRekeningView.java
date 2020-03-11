package com.example.app4g.features.petani.noRekening;

import com.example.app4g.common.CommonRespon;
import com.example.app4g.features.petani.profile.model.ProfileResponse;
import com.example.app4g.features.users.login.model.LoginResponse;

public interface IRekeningView {
    void initViews();


    void onCreateRekening();

    void onCreateRekeningSuksess(LoginResponse commonRespon, String noRek);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToRut();
}
