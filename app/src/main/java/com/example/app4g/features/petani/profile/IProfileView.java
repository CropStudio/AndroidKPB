package com.example.app4g.features.petani.profile;

import com.example.app4g.common.CommonResponse;

public interface IProfileView {
    void initViews();

    boolean isDataPribadiLayoutReady();

    void setDataPribadiVisible(boolean visible);

    boolean isDataKeluargaReady();

    void setDataKeluargaVisible(boolean visible);

    boolean isDataKepemilikanReady();

    void setDataKepemilikanVisible(boolean visible);

    void onSubmit();

    void onUpdateProfileSuccess(CommonResponse response);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
