package com.app.kpb2.features.petani.transaksi_non_tunai;


import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.petani.profile.model.ProfileResponse;
import com.app.kpb2.features.rut.IRutView;
import com.app.kpb2.features.rut.model.PoktanResponse;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.rut.model.RutResponse;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
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

public class TransaksiNonTunaiPresenter {
    final ITransaksiNonTunaiView view;
    public final Retrofit restService;

    public TransaksiNonTunaiPresenter(ITransaksiNonTunaiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getTransaksiNonTunai(nik)
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
                        System.out.println(new Gson().toJson(response.body()));
//                        Log.d("GENERETE RUT " , new Gson().toJson(response.body()));
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


    void createRut(String nik, String token, Result rut) {
//        System.out.println(body);
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).transaksiNonTunai(rut)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> CommonRespon) {
                        view.hideLoadingIndicator();
//                        Log.d("Responnya" , new Gson().toJson(CommonRespon.body()));
                        if (CommonRespon.body().getSuccess())
                            view.onCreateSuccess(CommonRespon.body().getmRm());
                        else
                            view.onCreateFailed(CommonRespon.body().getmRm(),CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
//                        Log.d("ksini" , "");
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void onGetProfile(String nik) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getProfilePetani(nik)
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(CommonRespon.body().getmResult()));
                        view.onCekStatus(response.body().getResult());

                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }



}
