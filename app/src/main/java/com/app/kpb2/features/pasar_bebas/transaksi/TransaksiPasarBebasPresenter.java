package com.app.kpb2.features.pasar_bebas.transaksi;


import android.util.Log;

import com.app.kpb2.features.cart.model.CartResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.DetailTransaksiResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.Transaksis;
import com.app.kpb2.features.transaksi.ITransaksiView;
import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebasResponse;
import com.app.kpb2.features.transaksi.model.Transaksi;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
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

public class TransaksiPasarBebasPresenter {
    final ITransaksiPasarBebasView view;
    public final Retrofit restService;

    public TransaksiPasarBebasPresenter(ITransaksiPasarBebasView view) {
        this.view = view;
        restService = RestService.getRetrofitInstancePasarBebas();
    }

    void beforeView(String nik, String token , String idUser) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).beforeRequest()
                .enqueue(new Callback<TransaksiPasarBebasResponse>() {
                    @Override
                    public void onResponse(Call<TransaksiPasarBebasResponse> call, Response<TransaksiPasarBebasResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("beforeR",new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                            view.beforeRequest(response.body());
                        else
                            view.onRequestFailed(response.body().getmRc() , response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<TransaksiPasarBebasResponse> call, Throwable t) {
//                        Log.d("respontransaksi",new Gson().toJson(call.request()));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getTransaksi(String nik, String token , String idUser) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).getTransaksiPasarBebas(idUser)
                .enqueue(new Callback<TransaksiPasarBebasResponse>() {
                    @Override
                    public void onResponse(Call<TransaksiPasarBebasResponse> call, Response<TransaksiPasarBebasResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("respontransaksis",new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body().getmResult());
                        else
                            view.onRequestFailed(response.body().getmRc() , response.body().getmRm());
                    }

                    @Override
                    public void onFailure(Call<TransaksiPasarBebasResponse> call, Throwable t) {
                        Log.d("respontransaksi",new Gson().toJson(call.request()));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getDetailTransaksi(String nik, String token , Transaksis transaksis,String idUser) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).getDetailTransaksi(transaksis.getOrder_id(),idUser)
                .enqueue(new Callback<DetailTransaksiResponse>() {
                    @Override
                    public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("detailTransaksi",new Gson().toJson(response.body()));
                        if (response.body().getmStatus())
                                view.showList(response.body().getmResult() , transaksis );
                        else
                            view.onRequestFailed(response.body().getmRc() , "");
                    }

                    @Override
                    public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
//                        Log.d("respontransaksi",new Gson().toJson(call.request()));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


}
