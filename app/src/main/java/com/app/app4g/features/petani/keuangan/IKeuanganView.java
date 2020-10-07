package com.app.app4g.features.petani.keuangan;

import android.app.Activity;

import com.app.app4g.features.petani.profile.model.profile;
import com.app.app4g.features.rut.model.BarangTidakAda;
import com.app.app4g.features.rut.model.Result;

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
