package com.app.kpb2.features.users.login;

import com.app.kpb2.features.users.login.model.LoginResponse;

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
