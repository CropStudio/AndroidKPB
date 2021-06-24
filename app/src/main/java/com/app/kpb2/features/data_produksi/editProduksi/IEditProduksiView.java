package com.app.kpb2.features.data_produksi.editProduksi;

import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;

import java.util.List;

public interface IEditProduksiView {
    void initViews();


    void onEditProduksi();

    void onDataReady(List<Komoditas> result);

//    void onCreateKomoditasSuksess(LoginResponse profile);

    void onNetworkError(String cause);

    void onEditSuksess();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();
}
