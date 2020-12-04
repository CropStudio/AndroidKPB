package com.app.kpb2.features.petani.program_bantuan.autp;


import com.app.kpb2.features.petani.program_bantuan.autp.model.AutpResponse;
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

public class AutpPresenter {
    final IAutpView view;
    public final Retrofit restService;

    public AutpPresenter(IAutpView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }



    void getAutp(String nik, String token ) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getAutp(nik)
                .enqueue(new Callback<AutpResponse>() {
                    @Override
                    public void onResponse(Call<AutpResponse> call, Response<AutpResponse> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onRequestFailed(response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<AutpResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
