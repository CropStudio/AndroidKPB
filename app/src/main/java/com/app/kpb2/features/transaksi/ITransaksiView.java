package com.app.kpb2.features.transaksi;

import android.app.Activity;

import com.app.kpb2.features.transaksi.model.Transaksi;

import java.util.List;

public interface ITransaksiView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void onDataReady(List<Transaksi> result);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);


    void hideDetailList();

    void goToDashboard();

    void refresh();
}
