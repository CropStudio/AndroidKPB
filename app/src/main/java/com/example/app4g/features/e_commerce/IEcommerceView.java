package com.example.app4g.e_commerce;

import android.app.Activity;
import android.widget.ImageView;

import com.example.app4g.e_commerce.model.RutResponse;
import com.example.app4g.e_commerce.model.Saldo;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IEcommerceView {
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
