package com.app.kpb2.features.petani.profile.komoditas;

import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.petani.profile.detailProfile.IDetailProfileView;
import com.app.kpb2.features.petani.profile.komoditas.model.KomoditasResponse;
import com.app.kpb2.features.petani.profile.model.ProfileResponse;
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

public class KomoditasPresenter {
    final IKomoditasView view;
    public final Retrofit restService;

    public KomoditasPresenter(IKomoditasView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }
    void onCreateKomoditas(String nik,String token ,String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nama", body);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
//        System.out.println(body);

        System.out.println(params);
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).addKomoditas(nik,params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    Log.d("Result" , new Gson().toJson(response.body()));
                    view.onCreateKomoditasSuksess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    void onDeleteKomoditas(String nik, String token , String _id , int index) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).deleteKomoditas(nik,_id).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                Log.d("Responsnya", new Gson().toJson(response.body()));
                if (response.body().getSuccess()) {

                    view.onDeleteSuksess(response.body(),index);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    void getKomoditas(String nik ,String token) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getAllKomoditas().enqueue(new Callback<KomoditasResponse>() {
            @Override
            public void onResponse(Call<KomoditasResponse> call, retrofit2.Response<KomoditasResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onDataReady(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<KomoditasResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}
