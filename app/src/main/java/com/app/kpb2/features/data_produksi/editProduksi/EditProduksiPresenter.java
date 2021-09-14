package com.app.kpb2.features.data_produksi.editProduksi;

import android.util.Log;

import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.data_produksi.createProduksi.ICreateProduksiView;
import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.petani.profile.komoditas.model.KomoditasResponse;
import com.app.kpb2.features.users.login.model.LoginResponse;
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

public class EditProduksiPresenter {
    final IEditProduksiView view;
    public final Retrofit restService;

    public EditProduksiPresenter(IEditProduksiView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }
    void storeProfile(LoginResponse profile) {
        App.getPref().put(Prefs.PREF_STORE_PROFILE, new Gson().toJson(profile));
    }
    void onCreateProduksi(String nik, String token , DataProduksi model) {
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
        restService.newBuilder().client(RestService.getUnsafeOkHttpClient(nik , token).build()).build().create(NetworkService.class).editDataProduksi(model.get_id(),model).enqueue(new Callback<CommonRespon>() {
            @Override
            public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    Log.d("Responsnya" , new Gson().toJson(response.body()));
                    view.onEditSuksess();
                }
            }

            @Override
            public void onFailure(Call<CommonRespon> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }

    void getKomoditas(String nik ,String token) {
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
        restService.newBuilder().client(okHttpClient).build().create(NetworkService.class).getAllKomoditas().enqueue(new Callback<KomoditasResponse>() {
            @Override
            public void onResponse(Call<KomoditasResponse> call, Response<KomoditasResponse> response) {
                view.hideLoadingIndicator();
                if (response.body().getSuccess()) {
                    view.onDataReady(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<KomoditasResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }


}
