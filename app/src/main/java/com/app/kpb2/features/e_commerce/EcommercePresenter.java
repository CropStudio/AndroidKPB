package com.app.kpb2.features.e_commerce;


import android.util.Log;
import android.widget.ImageView;

import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.features.e_commerce.model.Item;
import com.app.kpb2.features.e_commerce.model.RutResponse;
import com.app.kpb2.features.e_commerce.model.Saldo;

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

public class EcommercePresenter {
    //    private final String TAG = DetailLaporanPresenter.class.getSimpleName();
    final IEcommerceView view;
    public final Retrofit restService;

    public EcommercePresenter(IEcommerceView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void showProduct(String nik, String token) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).showProduct()
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(CommonRespon.body()));
                        if (response.body().getSuccess())
                            view.onDataReady(response.body());
                        else
                            view.onRequestFailed();

                    }

                    @Override
                    public void onFailure(Call<RutResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getSaldo(String nik, String token) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getSaldo(nik)
                .enqueue(new Callback<Saldo>() {
                    @Override
                    public void onResponse(Call<Saldo> call, Response<Saldo> response) {
                        view.hideLoadingIndicator();
                        Log.d("Saldo", ""+response.body().getStatus());
                        if (response.body().getStatus() == null)
                            view.onDataSaldo(response.body());
                        else
                            view.onRequestFailed();

                    }

                    @Override
                    public void onFailure(Call<Saldo> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void createCart(String nik, Item rut, ImageView img,String token) {
        //Log.d("namaitem",rut.getNamaItem());
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        HashMap<String, Object> params = new HashMap<>();
        params.put("nik", nik);
        params.put("id", rut.get_id());
        params.put("namaBarang", rut.getNamaItem());
        params.put("harga", rut.getHarga());
        params.put("foto", rut.getFoto());
        view.showLoadingIndicator();
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).createCart(params).enqueue(new Callback<RutResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RutResponse> call, Response<RutResponse> response) {
                view.hideLoadingIndicator();
                Log.i("MESSAGE", "" + response.body());
                if (response.body().getSuccess()) {
                    view.onAddTocartSuccess(response.body(), img);
                }else{
                    view.onRequestFailed();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RutResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

}
