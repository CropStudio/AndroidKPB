package com.app.kpb2.network;

import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.common.CommonResponse;
import com.app.kpb2.features.e_commerce.model.RutResponse;
import com.app.kpb2.features.e_commerce.model.Saldo;
import com.app.kpb2.features.petani.dokter_hewan.model.DokterHewanResponse;
import com.app.kpb2.features.petani.noRekening.model.BalanceResponse;
import com.app.kpb2.features.petani.profile.komoditas.model.KomoditasResponse;
import com.app.kpb2.features.petani.program_bantuan.alokasi.model.AlokasiResponse;
import com.app.kpb2.features.petani.noRekening.model.KiosResponse;
import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.petani.profile.model.ProfileResponse;
import com.app.kpb2.common.CommonRespon;
import com.app.kpb2.features.petani.program_bantuan.autp.model.AutpResponse;
import com.app.kpb2.features.petani.program_bantuan.bantuan.model.BantuanResponse;
import com.app.kpb2.features.petani.registrasi.model.FormModel.AreaResponse;
import com.app.kpb2.features.petani.registrasi.model.RegistModel;
import com.app.kpb2.features.rut.createmt.model.DistincKomoditas;
import com.app.kpb2.features.rut.model.PoktanResponse;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.transaksi.model.TransaksiResponse;
import com.app.kpb2.features.users.login.model.LoginResponse;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @GET("getbalance/{norek}")
    Call<BalanceResponse> getBalance(@Path("norek") String norek);

    @GET("cart/{nik}")
    Call<Cart> getCart(@Path("nik") String nik);

    @GET("profile/{nik}")
    Call<ProfileResponse> getProfilePetani(@Path("nik") String nik);

    @GET("saldo/{nik}")
    Call<Saldo> getSaldo(@Path("nik") String nik);

    @POST("transaksirutpetani")
    Call<CommonRespon> createRut(@Body Result rut);

    @POST("transaksi-multi-beneficiary")
    Call<CommonRespon> transaksiNonTunai(@Body Result rut);

    @FormUrlEncoded
    @PUT("petaniedit/{nik}")
    Call<LoginResponse> updateProfile(@Path("nik") String nik, @FieldMap Map<String, Object> data);

    @FormUrlEncoded
    @POST("rutpetani/{nik}")
    Call<com.app.kpb2.features.rut.model.RutResponse> getRut(@Path("nik") String nik, @FieldMap Map<String, Object> data);

    //    @FormUrlEncoded
//    @Headers({"Content-Type:application/json"})
//    @POST("rutpetani/{nik}")
//    Call<com.app.kpb2.features.rut.model.RutResponse> getRut(@Path("nik") String nik, @Body JSONObject body);
    @GET("gettransaksipetanibypoktan/{nik}")
    Call<com.app.kpb2.features.rut.model.RutResponse> getTransaksiNonTunai(@Path("nik") String nik);

    @GET("getrutpetanibynikdantahun")
    Call<com.app.kpb2.features.rut.model.RutResponse> getAllRut(@QueryMap Map<String, String> params);

    @GET("datadokterhewan/{id}")
    Call<DokterHewanResponse> getDataDokterHewan(@Path("id") String id);

    @GET("kabupaten/{id}")
    Call<AreaResponse> getKab(@Path("id") String id);

    @GET("poktandesa/{id}")
    Call<PoktanResponse> getPoktan(@Path("id") String id);

    @GET("kecamatan/{id}")
    Call<AreaResponse> getKec(@Path("id") String id);

    @GET("desa/{id}")
    Call<AreaResponse> getDesa(@Path("id") String id);

    @GET("subsektor")
    Call<AreaResponse> getSubsektor();

    @GET("komoditasbysub/{idSub}")
    Call<AreaResponse> getKomoditas(@Path("idSub") String idSub);

    @GET("komoditas/")
    Call<KomoditasResponse> getAllKomoditas();

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
