package com.app.kpb2.features.users.reset_password;

import com.app.kpb2.features.users.login.model.LoginResponse;

public interface IResetPasswordView {
    void initViews();

    void onResetPassword();

    void onResetSuccess(LoginResponse response);

    void onLogout();

    void goToDashboard();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
