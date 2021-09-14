package com.app.kpb2.features.pasar_bebas;

import android.app.Activity;
import android.widget.ImageView;

import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.features.pasar_bebas.model.Items;
import com.app.kpb2.features.pasar_bebas.model.PasarBebasResponse;

import java.util.List;

public interface IPasarBebasView {
    void initViews();

    void clearLightStatusBar(Activity activity);

    void onDataReady(PasarBebasResponse items);

    void onNetworkError(String cause);

    void onRequestFailed(int Code);

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void goToDashboard();

    void createCartSukses(ImageView image);

    void goToCart();

    void goToTransaksi();

    void onDataCartReady(List<Cart> result);
}
