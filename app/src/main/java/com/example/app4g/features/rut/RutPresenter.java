package com.example.app4g.features.rut;


import android.util.Log;
import android.widget.ImageView;

import com.example.app4g.common.CommonRespon;
import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.ICartView;
import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.features.e_commerce.model.Item;
import com.example.app4g.features.rut.model.Rut;
import com.example.app4g.features.rut.model.RutResponse;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

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

public class RutPresenter {
    final IRutView view;
    public final Retrofit restService;

    public RutPresenter(IRutView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void createRut(String nik, String token, String body) {
//        System.out.println(body);
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("x-access-token", token)
                    .header("username", nik)
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).createRut(params)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onCreateSuccess("Success");

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getRut(String nik, String token, String idKec ) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getRut(nik)
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onRequestFailed(response.body().getmRm(), response.body().getmRc());
                    }

                    @Override
                    public void onFailure(Call<RutResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
