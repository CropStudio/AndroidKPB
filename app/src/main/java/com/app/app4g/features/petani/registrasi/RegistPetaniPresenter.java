package com.app.app4g.features.petani.registrasi;


import com.app.app4g.common.CommonRespon;
import com.app.app4g.features.petani.registrasi.model.FormModel.AreaResponse;
import com.app.app4g.features.petani.registrasi.model.RegistModel;
import com.app.app4g.network.NetworkService;
import com.app.app4g.network.RestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by omgimbot on 7/19/2019.
 */

public class RegistPetaniPresenter {
    final IRegistPetaniView view;
    public final Retrofit restService;

    public RegistPetaniPresenter(IRegistPetaniView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }


    void daftarPetani(RegistModel registModel) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).daftarPetani(registModel)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(retrofit2.Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if (response.body().getSuccess())
                            view.onCreateSuccess();
                        else {
                            view.onCreateFailed(response.body().getmRm());
                        }

                    }

                    @Override
                    public void onFailure(retrofit2.Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

    void getArea(String id, String key) {
        if (key.equals("kabupaten")) {
//            view.showLoadingIndicator();
            restService.create(NetworkService.class).getKab(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("kecamatan")) {
//            view.showLoadingIndicator();
            restService.create(NetworkService.class).getKec(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("desa")) {
//            view.showLoadingIndicator();
            restService.create(NetworkService.class).getDesa(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("subsektor")) {
//            view.showLoadingIndicator();
            restService.create(NetworkService.class).getSubsektor()
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        } else if (key.equals("komoditas")) {
//            view.showLoadingIndicator();
            restService.create(NetworkService.class).getKomoditas(id)
                    .enqueue(new Callback<AreaResponse>() {
                        @Override
                        public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                            view.hideLoadingIndicator();
                            if (response.body().getStatus())
                                view.onDataReady(response.body().getResult(), key);

                        }

                        @Override
                        public void onFailure(Call<AreaResponse> call, Throwable t) {
                            view.hideLoadingIndicator();
                            view.onNetworkError(t.getLocalizedMessage());
                        }
                    });
        }
    }

//    void getSubsektor(String key) {
////            view.showLoadingIndicator();
//        restService.create(NetworkService.class).getSubsektor()
//                .enqueue(new Callback<KomoditasResponse>() {
//                    @Override
//                    public void onResponse(Call<KomoditasResponse> call, Response<KomoditasResponse> response) {
//                        view.hideLoadingIndicator();
//                        if (response.body().getStatus())
//                            view.onDataReady(response.body().getResult(), key);
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<KomoditasResponse> call, Throwable t) {
//                        view.hideLoadingIndicator();
//                        view.onNetworkError(t.getLocalizedMessage());
//                    }
//                });
//
//    }


}
