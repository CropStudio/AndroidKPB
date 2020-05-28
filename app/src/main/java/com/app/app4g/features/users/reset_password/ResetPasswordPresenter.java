package com.app.app4g.features.users.reset_password;


import android.util.Log;

import com.app.app4g.features.rut.aset.IAsetView;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.network.NetworkService;
import com.app.app4g.network.RestService;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
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

public class ResetPasswordPresenter {
    final IResetPasswordView view;
    public final Retrofit restService;

    public ResetPasswordPresenter(IResetPasswordView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }


    public void onResetPassword(String nik, String token,String password) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("password", password);
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).onResetPassword(nik, params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onResetSuccess(response.body());
                    Log.d("RESPONNYA" ,new Gson().toJson(response.body()));
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
