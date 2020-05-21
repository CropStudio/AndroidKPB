package com.app.app4g.features.petani.noRekening;

import com.app.app4g.features.petani.noRekening.model.KiosResponse;
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

public class RekeningPresenter {
    final IRekeningView view;
    public final Retrofit restService;

    public RekeningPresenter(IRekeningView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }

    void onCreateRekening(String nik,String token ,String body,String noRek) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onCreateRekeningSuksess(response.body(),noRek);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    public void getKios(String idKabupaten,String idDesa,String idKecamatan) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id_desa", idDesa);
        params.put("id_kecamtan", idKecamatan);
        params.put("id_kabupaten", idKabupaten);
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getKiosByArea(params)
                .enqueue(new Callback<KiosResponse>() {
                    @Override
                    public void onResponse(Call<KiosResponse> call, Response<KiosResponse> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getStatus())
                            view.onDataReady(response.body().getResult());

                    }

                    @Override
                    public void onFailure(Call<KiosResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
