package com.app.kpb2.features.cart;

import android.app.Activity;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.cart.model.BankTfResponse;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.common.CommonResponse;
import com.app.kpb2.features.cart.model.Kios;
import com.app.kpb2.features.cart.model.Penyuluh;
import com.app.kpb2.features.cart.model.VaBNI_Response;

import java.util.List;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface ICartView {

    void initViews();

    void clearLightStatusBar(Activity activity);

    void onDataReady(List<Cart> carts);

    void onDataListKiosReady(List<Kios> kios);

    void onCheckout();

    void onCheckoutSuccess(CommonRespon response);

    void onCheckoutVaBNISuccess(VaBNI_Response response);

    void onRequestFailed(int rc, String rm);

    void onNetworkError(String cause);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();

    void goToCart();

    void showNotaBanktf(BankTfResponse result);

    void showTutorVaBNI(VaBNI_Response.VaBNI result);

    void showPaymentMethod();

    void showMidtrans(String url);

    void onDataPenyuluh(Penyuluh result);
}
