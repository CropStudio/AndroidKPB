package com.example.app4g.cart;


import android.util.Log;

import com.example.app4g.cart.model.Cart;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;
import com.example.app4g.rut.model.Item;
import com.example.app4g.rut.model.RutResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class CartPresenter {
    final ICartView view;
    public final Retrofit restService;

    public CartPresenter(ICartView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void getCart(String nik) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("x-access-token", token)
//                    .header("username", username)
//                    .method(original.method(), original.body())
//                    .build();
//
//            return chain.proceed(request);
//        }).build();
        restService.create(NetworkService.class).getCart(nik)
                .enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(response.body()));
                        view.onDataReady(response.body());

                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }



}
