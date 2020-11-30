package com.app.kpb2.features.petani.program_bantuan.bantuan;


import com.app.kpb2.features.petani.program_bantuan.bantuan.model.BantuanResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class BantuanPresenter {
    final IBantuanView view;
    public final Retrofit restService;

    public BantuanPresenter(IBantuanView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }



    void getBantuan(String nik, String token ) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getBantuan(nik)
                .enqueue(new Callback<BantuanResponse>() {
                    @Override
                    public void onResponse(Call<BantuanResponse> call, Response<BantuanResponse> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onRequestFailed(response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<BantuanResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
