package com.example.app4g.features.users.login.presenter;

public interface ILoginPresenter {

    boolean isLoggedIn();

    void storeAccessToken(String token);

    void storeProfile(String data);

    void clear();
    void doLogin(String nik, String passwd);
    void setProgressBarVisiblity(int visiblity);
}
