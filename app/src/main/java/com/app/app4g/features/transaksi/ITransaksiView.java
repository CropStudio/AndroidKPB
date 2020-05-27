package com.app.app4g.features.transaksi;

import android.app.Activity;

import com.app.app4g.features.transaksi.model.Transaksi;

import java.util.List;

public interface ITransaksiView {
    void initView();

    void clearLightStatusBar(Activity activity);

    void onDataReady(List<Transaksi> result);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);


    void hideDetailList();
}
