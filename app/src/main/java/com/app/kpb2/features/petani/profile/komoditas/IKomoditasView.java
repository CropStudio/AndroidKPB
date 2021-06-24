package com.app.kpb2.features.petani.profile.komoditas;

import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public interface IKomoditasView {
    void initViews();


    void onCreateKomoditas();

    void onDataReady(List<Komoditas> result);

    void onCreateKomoditasSuksess(LoginResponse profile);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void gotoKomoditas();

    void onDeleteSuksess(LoginResponse profile, int index);
}
