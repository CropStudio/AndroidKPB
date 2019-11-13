package com.example.app4g.rut;

import android.app.Activity;
import android.widget.ImageView;

import com.example.app4g.rut.model.Rut;
import com.example.app4g.rut.model.RutResponse;
import com.example.app4g.rut.model.Saldo;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IRutView {
    void initViews();

    void goToCart();

    void clearLightStatusBar(Activity activity);

    void onDataReady(RutResponse ruts);

    void onDataSaldo(Saldo saldo);

    void onNetworkError(String cause);

    void onAddTocartSuccess(RutResponse ruts, ImageView img);

    void showLoadingIndicator();

    void hideLoadingIndicator();


    void goToDashboard();
}
