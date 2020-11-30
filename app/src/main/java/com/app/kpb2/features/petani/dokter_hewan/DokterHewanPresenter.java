package com.app.kpb2.features.petani.dokter_hewan;


import com.app.kpb2.features.petani.dokter_hewan.model.DokterHewanResponse;
import com.app.kpb2.features.petani.keuangan.IKeuanganView;
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

public class DokterHewanPresenter {
    final IDokterHewanView view;
    public final Retrofit restService;

    public DokterHewanPresenter(IDokterHewanView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void getDokterHewan(String nik, String token , String idKab ) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getDataDokterHewan(idKab)
                .enqueue(new Callback<DokterHewanResponse>() {
                    @Override
                    public void onResponse(Call<DokterHewanResponse> call, Response<DokterHewanResponse> response) {
                        view.hideLoadingIndicator();
                        view.onDataReady(response.body().getResult());
                    }

                    @Override
                    public void onFailure(Call<DokterHewanResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }



}
