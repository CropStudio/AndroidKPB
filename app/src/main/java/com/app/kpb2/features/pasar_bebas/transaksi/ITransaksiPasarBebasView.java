package com.app.kpb2.features.pasar_bebas.transaksi;

import android.app.Activity;

import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebas;
import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebasResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.Transaksis;
import com.app.kpb2.features.transaksi.model.Transaksi;

import java.util.List;

public interface ITransaksiPasarBebasView {
    void initView();

    void clearLightStatusBar(Activity activity);

//    void onDataReady(List<TransaksiPasarBebas> result);

    void onDataReady(List<Transaksis> result);

    void beforeRequest(TransaksiPasarBebasResponse result);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onRequestFailed(String code , String message);

    void onNetworkError(String cause);

    void showList(List<TransaksiPasarBebas> result , Transaksis transaksis);

    void goToDashboard();

    void refresh();
}
