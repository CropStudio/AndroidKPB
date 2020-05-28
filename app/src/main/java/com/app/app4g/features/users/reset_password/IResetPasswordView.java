package com.app.app4g.features.users.reset_password;

import com.app.app4g.features.users.login.model.LoginResponse;

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
