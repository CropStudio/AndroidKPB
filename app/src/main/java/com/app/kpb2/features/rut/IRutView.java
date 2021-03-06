package com.app.kpb2.features.rut;

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

public interface IRutView {

    void initView();

//    void onSubmit();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();


    void onCekStatus(profile profile);

    void onDataReady(List<Result> result);

    void onRequestFailed(String rm);

    void onNetworkError(String cause);

    void goToDashboard();

//    void onDataDetailReady(List<KebutuhanSaprotan> kebutuhanSaprotans);

    void goToRekening();

    void goToSuratKuasa(Result rut);

    void onCreateSuccess(String rm);

    void onCreateFailed(String rm , Result rut, List<BarangTidakAda> val);

    void HideDetailKebutuhan();

    void onListPoktanReady(List<Poktan> result);

    void onCreatePoktan();

    void onCreatePoktanSuccess(LoginResponse profile);

    void refresh();
}
