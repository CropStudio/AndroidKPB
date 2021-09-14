package com.app.kpb2.features.rut.kebutuhanRut;


import com.app.kpb2.features.petani.registrasi.model.FormModel.AreaResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class CreateKebutuhanPresenter {
    final ICreateKebutuhanRut view;
    public final Retrofit restService;

    public CreateKebutuhanPresenter(ICreateKebutuhanRut view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    public void getSpinner(String id, String key) {
        if (key.equals("subsektor")) {
            restService.newBuilder().client(RestService.getUnsafeOkHttpClient("nik" , "token").build()).build().create(NetworkService.class).getSubsektor()
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("komoditas")) {
            view.showLoadingIndicator();
            restService.newBuilder().client(RestService.getUnsafeOkHttpClient("nik" , "token").build()).build().create(NetworkService.class).getKomoditas(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        }
    }

//    public void createAset(String nik, String token, String body) {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("data", body);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
//            Request original = chain.request();
//            Request request = original.newBuilder()
//                    .header("Content-Type", "application/json")
//                    .header("x-access-token", token)
//                    .header("username", nik)
//                    .method(original.method(), original.body())
//                    .build();
//
//            return chain.proceed(request);
//        }).build();
//        view.showLoadingIndicator();
//        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik, params).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
//                view.hideLoadingIndicator();
//                if (response.body().getSuccess()) {
//                    view.onCreateAsetSuccess(response.body());
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                view.hideLoadingIndicator();
//                view.onNetworkError(t.getLocalizedMessage());
//            }
//        });
//    }


}
