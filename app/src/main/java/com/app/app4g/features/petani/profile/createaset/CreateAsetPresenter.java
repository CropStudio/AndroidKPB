package com.app.app4g.features.petani.profile.createaset;


import com.app.app4g.features.petani.registrasi.model.FormModel.AreaResponse;
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

public class CreateAsetPresenter {
    final ICreateAsetView view;
    public final Retrofit restService;

    public CreateAsetPresenter(ICreateAsetView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }

    public void getSpinner(String id, String key) {
        if (key.equals("subsektor")) {
            restService.create(NetworkService.class).getSubsektor()
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("komoditas")) {
            view.showLoadingIndicator();
            restService.create(NetworkService.class).getKomoditas(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        }
    }

    public void createAset(String nik, String token, String body) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik, params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onCreateAsetSuccess(response.body());
                } else {

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
