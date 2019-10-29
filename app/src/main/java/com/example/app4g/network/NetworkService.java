package com.example.app4g.network;

import com.example.app4g.cart.model.Cart;
import com.example.app4g.rut.model.Rut;
import com.example.app4g.rut.model.RutResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {

    @FormUrlEncoded
    @POST("cart/create")
    Call<RutResponse>createCart(@FieldMap Map<String, Object> params);

    @GET("barang")
    Call<RutResponse>getSkema();

    @GET("cart/{nik}")
    Call<Cart>getCart(@Path("nik") String nik);

}
