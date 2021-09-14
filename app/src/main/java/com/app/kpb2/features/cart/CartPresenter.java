package com.app.kpb2.features.cart;


import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.cart.model.BankTfResponse;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.common.CommonResponse;
import com.app.kpb2.features.cart.model.CartResponse;
import com.app.kpb2.features.cart.model.Checkout;
import com.app.kpb2.features.cart.model.ListKiosResponse;
import com.app.kpb2.features.cart.model.PenyuluResponse;
import com.app.kpb2.features.cart.model.VaBNI_Response;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.google.gson.Gson;

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

public class CartPresenter {
    final ICartView view;
    public final Retrofit restService;
    public final Retrofit restServiceKios;
    public final Retrofit resetServiceDevelop;

    public CartPresenter(ICartView view) {
        this.view = view;
        restService = RestService.getRetrofitInstancePasarBebas();
        restServiceKios = RestService.getRetrofitInstance();
        resetServiceDevelop = RestService.getRetrofitInstanceDevelop();
    }


    void getCart(String nik, String token, String idUser) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, token).build()).build().create(NetworkService.class).getCart(idUser)
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("Messg", "" + response);
                        if (response.body() != null) {
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getCart());
                            else
                                view.onRequestFailed(response.body().getRc(), response.body().getRm());
                        } else
                            view.onNetworkError(response.message());

                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void onCheckout(List<Checkout> data, String nik, String token) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("x-access-token", token)
                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        Log.d("Channelnya", data.get(0).getChannel());
        if (data.get(0).getChannel().startsWith("VA BNI")) {
            restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, token).build()).build().create(NetworkService.class).checkoutVA_BNI(data).enqueue(new Callback<VaBNI_Response>() {
                //        resetServiceDevelop.newBuilder().client(okHttpClient).build().create(NetworkService.class).checkout(data).enqueue(new Callback<CommonRespon>() {
                @Override
                public void onResponse(Call<VaBNI_Response> call, retrofit2.Response<VaBNI_Response> response) {
                    view.hideLoadingIndicator();
                    Log.i("MESSAGE", "" + response.body().getStatus());
                    if (response.body().getStatus()) {
                        view.onCheckoutVaBNISuccess(response.body());
                    } else {
                        view.onRequestFailed(response.body().getRc(), response.body().getRm());
                    }
                }

                @Override
                public void onFailure(Call<VaBNI_Response> call, Throwable t) {
                    view.hideLoadingIndicator();
                    view.onNetworkError(t.getLocalizedMessage());
                }
            });
        } else if (data.get(0).getChannel().equals("Transfer")) {
            restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, token).build()).build().create(NetworkService.class).checkoutBank_tf(data).enqueue(new Callback<BankTfResponse>() {
                //        resetServiceDevelop.newBuilder().client(okHttpClient).build().create(NetworkService.class).checkout(data).enqueue(new Callback<CommonRespon>() {
                @Override
                public void onResponse(Call<BankTfResponse> call, retrofit2.Response<BankTfResponse> response) {
                    view.hideLoadingIndicator();
                    Log.i("MESSAGEny", "" + new Gson().toJson(response.body()));
                    if (response.body().getStatus()) {
                        view.showNotaBanktf(response.body());
                    } else {
                        view.onRequestFailed(response.body().getRc(), response.body().getRm());
                    }
                }

                @Override
                public void onFailure(Call<BankTfResponse> call, Throwable t) {
                    Log.i("MESSAGEErr", "" + call.request());
                    Log.i("MESSAGEErr2", "" + t.getLocalizedMessage());
                    view.hideLoadingIndicator();
                    view.onNetworkError(t.getLocalizedMessage());
                }
            });
        } else {

            restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, token).build()).build().create(NetworkService.class).checkout(data).enqueue(new Callback<CommonRespon>() {
                //        resetServiceDevelop.newBuilder().client(okHttpClient).build().create(NetworkService.class).checkout(data).enqueue(new Callback<CommonRespon>() {
                @Override
                public void onResponse(Call<CommonRespon> call, retrofit2.Response<CommonRespon> response) {
                    view.hideLoadingIndicator();
                    Log.i("MESSAGE", "" + response.body().getSuccess());
                    if (response.body().getSuccess()) {
                        view.onCheckoutSuccess(response.body());
                    } else {
                        view.onRequestFailed(response.body().getCode(), response.body().getmRm());
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

    void getListKios(String nik) {
        view.showLoadingIndicator();
        restServiceKios.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, "token").build()).build().create(NetworkService.class).getListKios(nik)
                .enqueue(new Callback<ListKiosResponse>() {
                    @Override
                    public void onResponse(Call<ListKiosResponse> call, Response<ListKiosResponse> response) {
                        view.hideLoadingIndicator();
                        Log.d("Messg", "" + response);
                        if (response.body() != null) {
                            if (response.body().getStatus())
                                view.onDataListKiosReady(response.body().getResult());
                            else
                                view.onRequestFailed(response.body().getRc(), response.body().getRm());
                        } else
                            view.onNetworkError(response.message());

                    }

                    @Override
                    public void onFailure(Call<ListKiosResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });

    }

        void getIdPenyuluh(String nik ,String token , Number idPoktan) {
            view.showLoadingIndicator();
            restServiceKios.newBuilder().client(RestService.getUnsafeOkHttpClient(nik, token).build()).build().create(NetworkService.class).getPenyuluh(idPoktan)
                    .enqueue(new Callback<PenyuluResponse>() {
                        @Override
                        public void onResponse(Call<PenyuluResponse> call, Response<PenyuluResponse> response) {
                            view.hideLoadingIndicator();
                            Log.d("Messg", "" + response.body().getResult());
                            if (response.body() != null) {
                                if (response.body().getStatus())
                                    view.onDataPenyuluh(response.body().getResult());
                                else
                                    view.onRequestFailed(response.body().getRc(), response.body().getRm());
                            } else
                                view.onNetworkError(response.message());

                        }

                        @Override
                        public void onFailure(Call<PenyuluResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
    }


}
