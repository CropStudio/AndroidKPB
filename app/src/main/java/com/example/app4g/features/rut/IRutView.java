package com.example.app4g.features.rut;

import android.app.Activity;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.model.Cart;

/**
 * Created by omgimbot on 7/19/2019.
 */

public interface IRutView {

    void initView();

    void onCreateRut();

    void onCreateRutSuccess();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void onNetworkError(String cause);
}
