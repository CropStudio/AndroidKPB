package com.app.kpb2.features.data_produksi.createProduksi;

import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

public interface ICreateProduksiView {
    void initViews();


    void onCreateProduksi();

    void onDataReady(List<Komoditas> result);

//    void onCreateKomoditasSuksess(LoginResponse profile);

    void onNetworkError(String cause);

    void onCreateSuksess();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();
}
