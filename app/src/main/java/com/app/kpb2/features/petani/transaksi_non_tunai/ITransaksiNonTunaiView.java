package com.app.kpb2.features.petani.transaksi_non_tunai;

import android.app.Activity;

import com.app.kpb2.features.petani.profile.model.profile;
import com.app.kpb2.features.rut.model.BarangTidakAda;
import com.app.kpb2.features.rut.model.Poktan;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface ITransaksiNonTunaiView {

    void initView();

//    void onSubmit();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

    void HideDetailKebutuhan();
}
