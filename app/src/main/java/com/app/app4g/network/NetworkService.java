package com.app.app4g.network;

import com.app.app4g.features.cart.model.Cart;
import com.app.app4g.common.CommonResponse;
import com.app.app4g.features.e_commerce.model.RutResponse;
import com.app.app4g.features.e_commerce.model.Saldo;
import com.app.app4g.features.petani.program_bantuan.alokasi.model.AlokasiResponse;
import com.app.app4g.features.petani.noRekening.model.KiosResponse;
import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.features.petani.profile.model.ProfileResponse;
import com.app.app4g.common.CommonRespon;
import com.app.app4g.features.petani.program_bantuan.autp.model.AutpResponse;
import com.app.app4g.features.petani.program_bantuan.bantuan.model.BantuanResponse;
import com.app.app4g.features.petani.registrasi.model.FormModel.AreaResponse;
import com.app.app4g.features.petani.registrasi.model.RegistModel;
import com.app.app4g.features.rut.createmt.model.DistincKomoditas;
import com.app.app4g.features.rut.model.Result;
import com.app.app4g.features.transaksi.model.TransaksiResponse;
import com.app.app4g.features.users.login.model.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NetworkService {

    @POST("users/signin")
    Call<LoginResponse> signin();

    @FormUrlEncoded
    @POST("cart/create")
    Call<RutResponse> createCart(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("transaksi")
    Call<CommonResponse> checkout(@FieldMap Map<String, Object> data);

    @GET("barang")
    Call<RutResponse> showProduct();

    @GET("cart/{nik}")
    Call<Cart> getCart(@Path("nik") String nik);

    @GET("profile/{nik}")
    Call<ProfileResponse> getProfilePetani(@Path("nik") String nik);

    @GET("saldo/{nik}")
    Call<Saldo> getSaldo(@Path("nik") String nik);

    @POST("transaksirutpetani")
    Call<CommonRespon> createRut(@Body Result rut);

    @FormUrlEncoded
    @PUT("petaniedit/{nik}")
    Call<LoginResponse> updateProfile(@Path("nik") String nik, @FieldMap Map<String, Object> data);

    @FormUrlEncoded
    @POST("rutpetani/{nik}")
    Call<com.app.app4g.features.rut.model.RutResponse> getRut(@Path("nik") String nik, @FieldMap Map<String, Object> data);

    @GET("getrutpetanibynikdantahun")
    Call<com.app.app4g.features.rut.model.RutResponse> getAllRut(@QueryMap Map<String, String> params);

    @GET("kabupaten/{id}")
    Call<AreaResponse> getKab(@Path("id") String id);

    @GET("kecamatan/{id}")
    Call<AreaResponse> getKec(@Path("id") String id);

    @GET("desa/{id}")
    Call<AreaResponse> getDesa(@Path("id") String id);

    @GET("subsektor")
    Call<AreaResponse> getSubsektor();

    @GET("komoditasbysub/{idSub}")
    Call<AreaResponse> getKomoditas(@Path("idSub") String idSub);

    @GET("distinctkomoditas/{name}")
    Call<DistincKomoditas> getDistincKomoditas(@Path("name") String name);

    @GET("cekversion/{id}")
    Call<CommonRespon> cekVersion(@Path("id") String id);

    @GET("getalokasipetani/{nik}")
    Call<AlokasiResponse> getAlokasi(@Path("nik") String nik);

    @GET("getautp/{nik}")
    Call<AutpResponse> getAutp(@Path("nik") String nik);

    @GET("getbantuanpetani/{nik}")
    Call<BantuanResponse> getBantuan(@Path("nik") String nik);

    @GET("transaksipetanibynik/{nik}")
    Call<TransaksiResponse> getTransaksi(@Path("nik") String nik);

    @POST("users/signupandcreate")
    Call<CommonRespon> daftarPetani(@Body RegistModel registModel);

    @PUT("petani/updateaset/{nik}")
    Call<LoginResponse> updateAset(@Path("nik") String nik, @Body AsetPetani model);

    @FormUrlEncoded
    @PUT("petani/createasset/{id}/{nik}")
    Call<LoginResponse> createMt(@Path("id") String id, @Path("nik") String nik, @FieldMap Map<String, Object> data);

    @PUT("hapusasetpetani/{idasset}/{nik}")
    Call<LoginResponse> deleteAset(@Path("idasset") String idasset, @Path("nik") String nik);

    @FormUrlEncoded
    @POST("getkiosbyarea")
    Call<KiosResponse> getKiosByArea(@FieldMap Map<String, Object> data);

    @FormUrlEncoded
    @PUT("updatepassword/{nik}")
    Call<LoginResponse> onResetPassword(@Path("nik") String nik, @FieldMap Map<String, Object> data);
}
