package com.example.app4g.features.petani.profile.createprofile;

import com.example.app4g.common.CommonRespon;
import com.example.app4g.features.petani.profile.model.ProfileResponse;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ProfilePresenter {
    final IProfileView view;
    public final Retrofit restService;

    public ProfilePresenter(IProfileView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void storeNoKK(String noKK) {
        App.getPref().put(Prefs.PREF_NO_KK, noKK);
    }

    void onUpdateProfile(String nik,String token ,String body,String noKK) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("data", body);
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).updateProfile(nik,params).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onUpdateProfileSuccess(response.body(),noKK);
                }else{

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }
}
