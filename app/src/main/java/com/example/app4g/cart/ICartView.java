package com.example.app4g.cart;

import android.app.Activity;

import com.example.app4g.cart.model.Cart;
import com.example.app4g.rut.model.RutResponse;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface ICartView {
    void initViews();

    void clearLightStatusBar(Activity activity);

    void onDataReady(Cart carts);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();
}
