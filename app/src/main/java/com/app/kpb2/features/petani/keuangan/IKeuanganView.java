package com.app.kpb2.features.petani.keuangan;

import android.app.Activity;

import com.app.kpb2.features.rut.model.Result;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IKeuanganView {

    void initView();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();



    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

//    void onDataDetailReady(List<KebutuhanSaprotan> kebutuhanSaprotans);

}
