package com.app.kpb2.features.cart;

import android.app.Activity;

import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.common.CommonResponse;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface ICartView {

    void initViews();

    void clearLightStatusBar(Activity activity);

    void onDataReady(Cart carts);

    void onCheckout();

    void onCheckoutSuccess(CommonResponse response);

    void onRequestFailed(String rc, String rm);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();

    void goToCart();
}
