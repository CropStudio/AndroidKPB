package com.app.kpb2.features.petani.program_bantuan.alokasi;


import android.util.Log;

import com.app.kpb2.features.petani.program_bantuan.alokasi.model.AlokasiResponse;
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

public class AlokasiPresenter {
    final IAlokasiView view;
    public final Retrofit restService;

    public AlokasiPresenter(IAlokasiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }



    void getAlokasi(String nik, String token ) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getAlokasi(nik)
                .enqueue(new Callback<AlokasiResponse>() {
                    @Override
                    public void onResponse(Call<AlokasiResponse> call, Response<AlokasiResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("Responalokasi" , String.valueOf(response.body()));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onRequestFailed(response.body().getmRm() , response.body().getmRc());
                    }

                    @Override
                    public void onFailure(Call<AlokasiResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
