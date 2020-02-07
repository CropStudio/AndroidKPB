package com.example.app4g.features.rut;

import android.app.Activity;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;
import com.example.app4g.features.rut.model.Rut;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IRutView {

    void initView();

    void onSubmit();

    void clearLightStatusBar(Activity activity);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onDataReady(List<Rut> ruts);

    void onRequestFailed(String rm, String rc);

    void onNetworkError(String cause);

    void goToDashboard();

//    void onDataDetailReady(List<KebutuhanSaprotan> kebutuhanSaprotans);

    void HideDetailKebutuhan();
}
