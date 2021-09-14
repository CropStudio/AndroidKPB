package com.app.kpb2.features.rut;


import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.petani.profile.model.ProfileResponse;
import com.app.kpb2.features.rut.model.PoktanResponse;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.rut.model.RutResponse;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.google.gson.Gson;

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
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).createRut(rut)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> CommonRespon) {
                        view.hideLoadingIndicator();
                        if (CommonRespon.body().getSuccess())
                            view.onCreateSuccess(CommonRespon.body().getmRm());
                        else
                            view.onCreateFailed(CommonRespon.body().getmRm(), rut, CommonRespon.body().getValue());

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getRut(String nik, String token, String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);

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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).getRut(nik, params)
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
//                        System.out.println(new Gson().toJson(response.body()));
                        Log.d("GENERETE RUT " , new Gson().toJson(response.body()));
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

    void onGetProfile(String nik) {
        view.showLoadingIndicator();
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , "token").build()).build().create(NetworkService.class).getProfilePetani(nik)
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

    void ListPoktan(String nik, String token, String desa) {
        Log.d("iddesa", desa);
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).getPoktan(desa)
                .enqueue(new Callback<PoktanResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<PoktanResponse> call, Response<PoktanResponse> CommonRespon) {
                        view.hideLoadingIndicator();
                        view.onListPoktanReady(CommonRespon.body().getResult());


                    }

                    @Override
                    public void onFailure(retrofit2.Call<PoktanResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void onCreatePoktan(String nik,String token ,String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onCreatePoktanSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }


}
