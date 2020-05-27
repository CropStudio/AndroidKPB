package com.app.app4g.features.transaksi;


import com.app.app4g.common.CommonRespon;
import com.app.app4g.features.rut.IRutView;
import com.app.app4g.features.rut.model.Result;
import com.app.app4g.features.transaksi.model.TransaksiResponse;
import com.app.app4g.network.NetworkService;
import com.app.app4g.network.RestService;
import com.google.gson.Gson;

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

public class TransaksiPresenter {
    final ITransaksiView view;
    public final Retrofit restService;

    public TransaksiPresenter(ITransaksiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void getTransaksi(String nik, String token) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getTransaksi(nik)
                .enqueue(new Callback<TransaksiResponse>() {
                    @Override
                    public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                        view.hideLoadingIndicator();
                        System.out.println(new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getmResult());
                    }

                    @Override
                    public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
