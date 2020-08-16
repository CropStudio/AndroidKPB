package com.app.app4g.features.rut;

import android.app.Activity;

import com.app.app4g.features.rut.model.BarangTidakAda;
import com.app.app4g.features.rut.model.Result;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IRutView {

    void initView();

//    void onSubmit();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();


    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

//    void onDataDetailReady(List<KebutuhanSaprotan> kebutuhanSaprotans);

    void goToRekening();

    void onCreateSuccess(String rm);

    void onCreateFailed(String rm , Result rut, List<BarangTidakAda> val);

    void HideDetailKebutuhan();
}
