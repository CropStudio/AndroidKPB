package com.app.kpb2.features.petani.noRekening;

import com.app.kpb2.features.petani.noRekening.model.Kios;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

public interface IRekeningView {
    void initViews();


    void onCreateRekening();

    void onDataReady(List<Kios> result);

    void onCreateRekeningSuksess(LoginResponse commonRespon, String noRek);

    void onRequestFailed(String rm, String rc);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToRut();

    void goToProfile();

    void goToTransaksiNonTunai();
}
