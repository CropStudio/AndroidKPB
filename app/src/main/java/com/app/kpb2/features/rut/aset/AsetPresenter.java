package com.app.kpb2.features.rut.aset;


import android.util.Log;

import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class AsetPresenter {
    final IAsetView view;
    public final Retrofit restService;

    public AsetPresenter(IAsetView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }


    public void deleteAset(String nik, String token, String idAset) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).deleteAset(idAset, nik).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onDeleteSuccess(response.body());
                    Log.d("RESPONNYA" ,new Gson().toJson(response.body()));
                }else
                    view.onRequestFailed(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }


}
