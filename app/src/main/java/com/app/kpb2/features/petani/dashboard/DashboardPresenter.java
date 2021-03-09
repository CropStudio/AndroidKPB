package com.app.kpb2.features.petani.dashboard;

import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.petani.noRekening.model.BalanceResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardPresenter {

    final IDashboardView view;
    public final Retrofit restService;

    public DashboardPresenter(IDashboardView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void cekAppVersion(String app_id) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).cekVersion(app_id)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if(response.body().getSuccess())
                            view.goUpdateVersion(response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getSaldo(String nik , String token ,String noRek) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getBalance(noRek)
                .enqueue(new Callback<BalanceResponse>() {
                    @Override
                    public void onResponse(Call<BalanceResponse> call, Response<BalanceResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("hann" , new Gson().toJson(response.body()));
                        if(response.body().getStatus())
                            view.onDataReady(response.body().getResult());
                        else
                            view.onGetSaldoFailed(response.body());
                    }

                    @Override
                    public void onFailure(Call<BalanceResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

}
