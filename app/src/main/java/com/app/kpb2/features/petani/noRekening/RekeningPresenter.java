package com.app.kpb2.features.petani.noRekening;

import com.app.kpb2.features.petani.noRekening.model.KiosResponse;
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

        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onCreateRekeningSuksess(response.body(),noRek);
                }else{
                    view.onRequestFailed(response.body().getRm(),response.body().getRc());
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient("nik" , "token").build()).build().create(NetworkService.class).getKiosByArea(params)
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
