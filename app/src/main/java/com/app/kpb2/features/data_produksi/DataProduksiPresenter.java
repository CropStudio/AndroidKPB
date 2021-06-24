package com.app.kpb2.features.data_produksi;


import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.rut.aset.IAsetView;
import com.app.kpb2.features.data_produksi.model.DataProduksiResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
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

public class DataProduksiPresenter {
    final IDataProduksiView view;
    public final Retrofit restService;

    public DataProduksiPresenter(IDataProduksiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(DataProduksiResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }

    public void getDataProduksi(String nik, String token) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getDataProduksi(nik).enqueue(new Callback<DataProduksiResponse>() {
            @Override
            public void onResponse(Call<DataProduksiResponse> call, Response<DataProduksiResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onDataReady(response.body().getResult());
                    Log.d("RESPONNYA" ,new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<DataProduksiResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }


    void onDeleteProduksi(String nik, String token , String _id , int index) {
        Log.d("idProduksi" , _id);
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("data", body);
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

//        System.out.println(params);
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).deleteDataProduksi(_id).enqueue(new Callback<CommonRespon>() {
            @Override
            public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    Log.d("Responsnya" , new Gson().toJson(response.body()));
                    view.onDeleteSuksess(index);
                }
            }

            @Override
            public void onFailure(Call<CommonRespon> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    void onEditProduksi(String nik, String token , String _id , int index) {
        Log.d("idProduksi" , _id);
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("data", body);
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

//        System.out.println(params);
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).deleteDataProduksi(_id).enqueue(new Callback<CommonRespon>() {
            @Override
            public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    Log.d("Responsnya" , new Gson().toJson(response.body()));
                    view.onDeleteSuksess(index);
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
