package com.example.app4g.features.users.login;

import com.example.app4g.features.users.login.model.LoginResponse;

public interface ILoginView {
    void initViews();

    void onSigninSuccess(LoginResponse response);

    void onSigninFailed(String rm);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboardPetani();

    void goToDashboardGubernur();
}
