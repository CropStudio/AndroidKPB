package com.app.app4g.features.petani.profile.detailProfile;

import com.app.app4g.features.petani.profile.model.ProfileResponse;
import com.app.app4g.network.NetworkService;
import com.app.app4g.network.RestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailProfilePresenter {
    final IDetailProfileView view;
    public final Retrofit restService;

    public DetailProfilePresenter(IDetailProfileView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void onGetProfile(String nik) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).getProfilePetani(nik)
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                        view.hideLoadingIndicator();
                        //Log.d("Messg", String.valueOf(CommonRespon.body().getmResult()));
                        view.onDataReady(response.body().getResult());

                    }

                    @Override
                    public void onFailure(Call<ProfileResponse> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }
}
