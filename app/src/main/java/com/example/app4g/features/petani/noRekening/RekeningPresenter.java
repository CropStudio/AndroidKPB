package com.example.app4g.features.petani.noRekening;

import com.example.app4g.common.CommonRespon;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class RekeningPresenter {
    final IRekeningView view;
    public final Retrofit restService;

    public RekeningPresenter(IRekeningView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void storeNoRek(String noRek) {
        App.getPref().put(Prefs.PREF_NO_REKENING, noRek);
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<CommonRespon>() {
            @Override
            public void onResponse(Call<CommonRespon> call, retrofit2.Response<CommonRespon> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onCreateRekeningSuksess(response.body(),noRek);
                }
            }

            @Override
            public void onFailure(Call<CommonRespon> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}
