package com.app.kpb2.features.users.login.view;

public interface ILoginview {
    void onClearText();
    void onLoginResult(Boolean result, String msg);
    void onSetProgressBarVisibility(int visibility);
}
