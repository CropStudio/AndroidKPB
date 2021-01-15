package com.app.kpb2.features.rut.createmt;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.users.login.model.LoginResponse;

import java.util.List;

public interface ICreateMtView {
    void initViews();

//    void onCreateAsetSuccess(LoginResponse profile);

    void onDataReady(List<String> result);

    void onCreateMtSuccess(LoginResponse profile);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);

    void gotoAsset();

    @RequiresApi(api = Build.VERSION_CODES.N)
    void gotoGetRut(List<AsetPetani> asetPetani);

    void CreateMt();
}
