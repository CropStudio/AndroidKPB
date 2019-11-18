package com.example.app4g.features.rut;


import android.util.Log;
import android.widget.ImageView;

import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.cart.ICartView;
import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.features.e_commerce.model.Item;
import com.example.app4g.features.e_commerce.model.RutResponse;
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
    final ICartView view;
    public final Retrofit restService;

    public RutPresenter(ICartView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void onCreateRut(String nik , Item rut ) {
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
                    //view.onAddTocartSuccess(response.body(),img);
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
