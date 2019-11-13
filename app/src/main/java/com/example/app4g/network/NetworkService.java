package com.example.app4g.network;

import com.example.app4g.cart.model.Cart;
import com.example.app4g.cart.model.Checkout;
import com.example.app4g.common.CommonResponse;
import com.example.app4g.rut.model.Rut;
import com.example.app4g.rut.model.RutResponse;
import com.example.app4g.rut.model.Saldo;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NetworkService {

    @FormUrlEncoded
    @POST("cart/create")
    Call<RutResponse>createCart(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("transaksi")
    Call<CommonResponse>checkout(@FieldMap Map<String, Object> data);

    @GET("barang")
    Call<RutResponse>showProduct();

    @GET("cart/{nik}")
    Call<Cart>getCart(@Path("nik") String nik);

    @GET("saldo/{nik}")
    Call<Saldo>getSaldo(@Path("nik") String nik);

}
