package com.example.app4g.features.petani.profile;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.petani.profile.model.ProfileResponse;
import com.example.app4g.features.petani.profile.model.response;

public interface IProfileView {
    void initViews();

    boolean isDataPribadiLayoutReady();

    void setDataPribadiVisible(boolean visible);

    boolean isDataKeluargaReady();

    void setDataKeluargaVisible(boolean visible);

    boolean isDataKepemilikanReady();

    void setDataKepemilikanVisible(boolean visible);

    void onSubmit();

    void onUpdateProfileSuccess(response response);

    void goToDashBoard();

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
