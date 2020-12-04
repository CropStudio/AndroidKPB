package com.app.kpb2.features.petani.keuangan;


import com.app.kpb2.features.rut.model.RutResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class KeuanganPresenter {
    final IKeuanganView view;
    public final Retrofit restService;

    public KeuanganPresenter(IKeuanganView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void getAllRut(String nik, String token ) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("nik", nik);
        params.put("tahun", "2020");
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getAllRut(params)
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
                        System.out.println(new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onRequestFailed(response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<RutResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }



}
