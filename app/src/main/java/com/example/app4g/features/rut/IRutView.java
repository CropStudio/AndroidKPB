package com.example.app4g.features.rut;

import android.app.Activity;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.model.Cart;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IRutView {
    void initViews();

    void clearLightStatusBar(Activity activity);

    void onDataReady(Cart carts);

    void onCheckout();

    void onCheckoutSuccess(CommonResponse response);

    void onCheckoutFail(CommonResponse response);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();

    void goToCart();
}
