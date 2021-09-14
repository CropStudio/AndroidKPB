package com.app.kpb2.features.rut.createmt;

import com.app.kpb2.features.rut.createmt.model.DistincKomoditas;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.network.NetworkService;
import com.app.kpb2.network.RestService;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateMtPresenter {

    final ICreateMtView view;
    public final Retrofit restService;

    public CreateMtPresenter(ICreateMtView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }

    public void createMt(String nik, String token, String _id, String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("dataPermt", body);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
//                    .header("x-access-token", token)
//                    .header("username", nik)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).createMt(_id, nik, params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();

                view.onCreateMtSuccess(response.body());

//                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    public void getDistincKomoditas(String subsektor) {
        view.showLoadingIndicator();
//        Log.d("idsub", idSub);
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient("nik" , "token").build()).build().create(NetworkService.class).getDistincKomoditas(subsektor)
                .enqueue(new Callback<DistincKomoditas>() {
                    @Override
                    public void onResponse(Call<DistincKomoditas> call, Response<DistincKomoditas> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getStatus())
                            view.onDataReady(response.body().getResult());

                    }

                    @Override
                    public void onFailure(Call<DistincKomoditas> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
