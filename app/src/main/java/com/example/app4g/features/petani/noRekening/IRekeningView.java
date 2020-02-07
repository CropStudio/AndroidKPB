package com.example.app4g.features.petani.noRekening;

import com.example.app4g.common.CommonRespon;

public interface IRekeningView {
    void initViews();


    void onCreateRekening();

    void onCreateRekeningSuksess(CommonRespon commonRespon, String noRek);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToRut();
}
