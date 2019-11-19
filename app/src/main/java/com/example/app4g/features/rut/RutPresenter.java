package com.example.app4g.features.rut;


import android.util.Log;
import android.widget.ImageView;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.ICartView;
import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.features.e_commerce.model.Item;
import com.example.app4g.features.e_commerce.model.RutResponse;
import com.example.app4g.features.rut.model.Rut;
import com.example.app4g.network.NetworkService;
import com.example.app4g.network.RestService;

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

public class RutPresenter {
    final IRutView view;
    public final Retrofit restService;

    public RutPresenter(IRutView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void createRut(String nik , Rut ruts ) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nik",nik);
        params.put("id", ruts.getHci());
        params.put("namaBarang", ruts.getNpk());
        params.put("harga", ruts.getOrganik());
        params.put("foto", ruts.getPhonska());
        params.put("foto", ruts.getUrea());
        params.put("foto", ruts.getLuas_lahan());
        view.showLoadingIndicator();
        restService.create(NetworkService.class).createRut(params).enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CommonResponse> call, Response<CommonResponse> response) {
                view.hideLoadingIndicator();
                Log.i("MESSAGE" , ""+response.body());
                if (response.body().getSuccess()) {
                    view.onCreateRutSuccess();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CommonResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.onNetworkError(t.getLocalizedMessage());
            }
        });
    }



}
