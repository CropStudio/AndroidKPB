package com.example.app4g.features.cart;


import android.util.Log;

import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.common.CommonResponse;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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


    void getCart(String nik,String token) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getCart(nik)
                .enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        view.hideLoadingIndicator();
                        Log.d("Messg",""+ response);
                        if(response.body().getStatus() == null)
                            view.onDataReady(response.body());
                        else
                            view.onRequestFailed(response.body().getRc(),response.body().getRm());

                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void onCheckout(String body,String nik , String token) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        System.out.println(body);
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).checkout(params).enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, retrofit2.Response<CommonResponse> response) {
                view.hideLoadingIndicator();
                Log.i("MESSAGE", "" + response.body().getSuccess());
                if (response.body().getSuccess()) {
                    view.onCheckoutSuccess(response.body());
                }else{
                    view.onRequestFailed(response.body().getRc(),response.body().getRm());
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }


}
