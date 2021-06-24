package com.app.kpb2.features.data_produksi;

import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

public interface IDataProduksiView {
    void initViews();

    void onDeleteSuksess(int index);

    void goToAdd();

    void onDataReady(List<DataProduksi> dataProduksis);

    void goToDashboard();

    void refresh();

    void onRequestFailed(String mRc);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();
}
