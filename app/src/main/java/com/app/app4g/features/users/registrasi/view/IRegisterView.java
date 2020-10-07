package com.app.app4g.features.users.registrasi.view;

public interface IRegisterView {
    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onClearText();
    void onRegisterResult(Boolean result, String msg);
    void onSetProgressBarVisibility(int visibility);
}
