package com.example.app4g.rut;


import android.media.Image;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;
import com.example.app4g.rut.model.Item;
import com.example.app4g.rut.model.Rut;
import com.example.app4g.rut.model.RutResponse;
import com.example.app4g.rut.model.Saldo;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class RutPresenter {
    //    private final String TAG = DetailLaporanPresenter.class.getSimpleName();
    final IRutView view;
    public final Retrofit restService;

    public RutPresenter(IRutView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void showProduct() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("x-access-token", token)
//                    .header("username", username)
//                    .method(original.method(), original.body())
//                    .build();
//
//            return chain.proceed(request);
//        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).showProduct()
                .enqueue(new Callback<RutResponse>() {
                    @Override
                    public void onResponse(Call<RutResponse> call, Response<RutResponse> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(response.body()));
                        view.onDataReady(response.body());

                    }

                    @Override
                    public void onFailure(Call<RutResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
    void getSaldo(String nik) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("x-access-token", token)
//                    .header("username", username)
//                    .method(original.method(), original.body())
//                    .build();
//
//            return chain.proceed(request);
//        }).build();
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getSaldo(nik)
                .enqueue(new Callback<Saldo>() {
                    @Override
                    public void onResponse(Call<Saldo> call, Response<Saldo> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(response.body()));
                        view.onDataSaldo(response.body());

                    }

                    @Override
                    public void onFailure(Call<Saldo> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
    void createCart(String nik , Item rut , ImageView img) {
        //Log.d("namaitem",rut.getNamaItem());
        HashMap<String, Object> params = new HashMap<>();
        params.put("nik",nik);
        params.put("id", rut.get_id());
        params.put("namaBarang", rut.getNamaItem());
        params.put("harga", rut.getHarga());
        params.put("foto", rut.getFoto());
        view.showLoadingIndicator();
        restService.create(NetworkService.class).createCart(params).enqueue(new Callback<RutResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RutResponse> call, Response<RutResponse> response) {
                view.hideLoadingIndicator();
                Log.i("MESSAGE" , ""+response.body());
                if (response.body().getSuccess()) {
                    view.onAddTocartSuccess(response.body(),img);
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
