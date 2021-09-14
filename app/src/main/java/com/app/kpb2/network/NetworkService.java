package com.app.kpb2.network;

import com.app.kpb2.features.cart.model.BankTfResponse;
import com.app.kpb2.features.cart.model.Cart;
import com.app.kpb2.common.CommonResponse;
import com.app.kpb2.features.cart.model.CartResponse;
import com.app.kpb2.features.cart.model.Checkout;
import com.app.kpb2.features.cart.model.ListKiosResponse;
import com.app.kpb2.features.cart.model.PenyuluResponse;
import com.app.kpb2.features.cart.model.VaBNI_Response;
import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.data_produksi.model.DataProduksiResponse;
import com.app.kpb2.features.e_commerce.model.RutResponse;
import com.app.kpb2.features.e_commerce.model.Saldo;
import com.app.kpb2.features.pasar_bebas.model.PasarBebasResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.DetailTransaksiResponse;
import com.app.kpb2.features.pasar_bebas.transaksi.model.TransaksiPasarBebasResponse;
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

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    @POST("keranjang/input")
    Call<CommonRespon> createCart(@FieldMap Map<String, Object> params);

    @POST("transaction/non-tunai")
    Call<CommonRespon> checkout(@Body List<Checkout> checkout);

    @POST("transaction/non-tunai-bni-va")
    Call<VaBNI_Response> checkoutVA_BNI(@Body List<Checkout> checkout);

    @POST("transaction//tunai-abal-abal")
    Call<BankTfResponse> checkoutBank_tf(@Body List<Checkout> checkout);

    @GET("barang/get-data-barang")
    Call<PasarBebasResponse> showProduct();

    @GET("keranjang/get-data/{idUser}")
    Call<CartResponse> getCart(@Path("idUser") String idUser);

    @GET("getbalance/{norek}")
    Call<BalanceResponse> getBalance(@Path("norek") String norek);

    @GET("getkiospetani/{nik}")
    Call<ListKiosResponse> getListKios(@Path("nik") String nik);

    @GET("get-kios-by-id-poktan/{idPoktan}")
    Call<PenyuluResponse> getPenyuluh(@Path("idPoktan") Number idPoktan);

//    @GET("cart/{nik}")
//    Call<Cart> getCart(@Path("nik") String nik);

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
    @PUT("add-komoditas-petani/{nik}")
    Call<LoginResponse> addKomoditas(@Path("nik") String nik, @FieldMap Map<String, Object> data);

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

    @GET("get-data-produksi/{nik}")
    Call<DataProduksiResponse> getDataProduksi(@Path("nik") String nik);

    @DELETE("hapus-data-produksi-by-id/{id}")
    Call<CommonRespon> deleteDataProduksi(@Path("id") String id);

    @PUT("remove-komoditas-petani/{nik}/{id}")
    Call<LoginResponse> deleteKomoditas(@Path("nik") String nik,@Path("id") String id);

    @PUT("edit-data-produksi-by-id/{id}")
    Call<CommonRespon> editDataProduksi(@Path("id") String id,@Body DataProduksi model);

    @GET("transaksipetanibynik/{nik}")
    Call<TransaksiResponse> getTransaksi(@Path("nik") String nik);

    @GET("transaction/get-transaksi/{id}/false")
    Call<TransaksiPasarBebasResponse> getTransaksiPasarBebas(@Path("id") String id);

    @GET("transaction/get-transaksi-detail/{id}")
    Call<DetailTransaksiResponse> getDetailTransaksi(@Path("id") String id);

    @GET("transaction/get-transaksi-detail-by-idpemesan/{id}/{idpemesan}")
    Call<DetailTransaksiResponse> getDetailTransaksi(@Path("id") String id , @Path("idpemesan") String idpemesan);

    @GET("transaction/update-status-bni")
    Call<TransaksiPasarBebasResponse> beforeRequest();

    @POST("users/signupandcreate")
    Call<CommonRespon> daftarPetani(@Body RegistModel registModel);

    @POST("input-data-produksi")
    Call<CommonRespon> createProduksi(@Body DataProduksi model);

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
