package com.app.kpb2.features.petani.profile.createprofile;

import com.app.kpb2.features.users.login.model.LoginResponse;

public interface IProfileView {
    void initViews();

    boolean isDataPribadiLayoutReady();

    void setDataPribadiVisible(boolean visible);

    boolean isDataKeluargaReady();

    void setDataKeluargaVisible(boolean visible);

    boolean isDataKepemilikanReady();

    void setDataKepemilikanVisible(boolean visible);

    void onSubmit();

    void onUpdateProfileSuccess(LoginResponse commonRespon, String noKK);

    void onUpdateProfileFailed(String rc, String rm);

    void goToDashBoard();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();
}
