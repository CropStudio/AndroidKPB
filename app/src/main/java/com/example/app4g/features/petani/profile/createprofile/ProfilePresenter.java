package com.example.app4g.features.petani.profile.createprofile;

import android.util.Log;

import com.example.app4g.features.petani.profile.createprofile.model.ProfileResponse;
import com.example.app4g.features.petani.profile.createprofile.model.response;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfilePresenter {
    final IProfileView view;
    public final Retrofit restService;

    public ProfilePresenter(IProfileView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void onUpdateProfile(String nik ,String body) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();
        view.showLoadingIndicator();
//        System.out.println(body);
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<response>() {
            @Override
            public void onResponse(Call<response> call, Response<response> response) {
                view.hideLoadingIndicator();
                Log.i("MESSAGE", "" + response.body().getSuccess());
                if (response.body().getSuccess()) {
                    view.onUpdateProfileSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<response> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}
