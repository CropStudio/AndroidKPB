package com.app.kpb2.features.pasar_bebas;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.cart.model.CartResponse;
import com.app.kpb2.features.e_commerce.IEcommerceView;
import com.app.kpb2.features.e_commerce.model.RutResponse;
import com.app.kpb2.features.pasar_bebas.model.Items;
import com.app.kpb2.features.pasar_bebas.model.PasarBebasResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PasarBebasPresenter {
    final IPasarBebasView view;
    public final Retrofit restService;

    public PasarBebasPresenter(IPasarBebasView view) {
        this.view = view;
        restService = RestService.getRetrofitInstancePasarBebas();
    }

    void getCart(String nik,String token , String idUser) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).getCart(idUser)
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("Messg",""+ response);
                        if(response.body() != null ){
                            if (response.body().getStatus())
                                view.onDataCartReady(response.body().getCart());
                            else
                                view.onRequestFailed(response.body().getRc());
                        }else
                            view.onNetworkError(response.message());

                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }


    void showProduct(String nik, String token) {
        Log.d("niknya" , nik + token);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("x-access-token", token)
//                    .header("username", nik)
//                    .method(original.method(), original.body())
//                    .build();
//
//            return chain.proceed(request);
//        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).showProduct()
                .enqueue(new Callback<PasarBebasResponse>() {
                    @Override
                    public void onResponse(Call<PasarBebasResponse> call, Response<PasarBebasResponse> response) {

                        Log.d("responpasar", new Gson().toJson(response));
                        if (response.body().getmStatus())
                            view.onDataReady(response.body());
                        else
                            view.onRequestFailed(response.body().getmRc());

                    }

                    @Override
                    public void onFailure(Call<PasarBebasResponse> call, Throwable t) {
                        Log.d("responpasar", String.valueOf(t.getCause()));
                        Log.d("responpasar2", String.valueOf(call.request()));
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }


                });
    }

    void createCart(String nik,String token, String idUser ,Items item, ImageView img) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("idBarang", item.get_id());
        params.put("idPenjual", item.getIdUser());
        params.put("jumlahBeli", 1);
        params.put("idUser", idUser);
        params.put("idPemesan", idUser);
        Log.d("barangnya" , new Gson().toJson(params));
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).createCart(params)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        Log.d("responCart", new Gson().toJson(response.body()));
                        if (response.body().getSuccess())
                            view.createCartSukses(img);
                        else
                            view.onRequestFailed(response.body().getCode());

                    }

                    @Override
                    public void onFailure(Call<CommonRespon> call, Throwable t) {
                        Log.d("responpasar", String.valueOf(t.getCause()));
                        Log.d("responpasar2", String.valueOf(call.request()));


                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }


                });
    }
}
