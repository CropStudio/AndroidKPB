package com.app.kpb2.features.petani.dokter_hewan;

import android.app.Activity;

import com.app.kpb2.features.petani.dokter_hewan.model.DokterHewans;
import com.app.kpb2.features.rut.model.Result;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IDokterHewanView {

    void initView();

    void showLoadingIndicator();

    void hideLoadingIndicator();


    void onDataReady(DokterHewans result);

    void onHubWa(String noHp);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();


}
