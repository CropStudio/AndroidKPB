package com.example.app4g.network;

import com.example.app4g.features.cart.model.Cart;
import com.example.app4g.common.CommonResponse;
import com.example.app4g.features.e_commerce.model.RutResponse;
import com.example.app4g.features.e_commerce.model.Saldo;
import com.example.app4g.features.petani.profile.createprofile.model.ProfileResponse;
import com.example.app4g.common.CommonRespon;
import com.example.app4g.features.users.login.model.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetworkService {

    @POST("users/signin")
    Call<LoginResponse>signin();

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

    @GET("cekPetani/{nik}")
    Call<ProfileResponse>getPetani(@Path("nik") String nik);

    @GET("saldo/{nik}")
    Call<Saldo>getSaldo(@Path("nik") String nik);

    @FormUrlEncoded
    @POST("rut/create")
    Call<CommonResponse>createRut(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @PUT("petaniedit/{nik}")
    Call<CommonRespon>updateProfile(@Path("nik") String nik, @FieldMap Map<String, Object> data);

    @GET("/rutpoktans/{idKecamatan}/{nik}")
    Call<com.example.app4g.features.rut.model.RutResponse>getRut(@Path("idKecamatan") String idKecamatan ,@Path("nik") String nik );
}
