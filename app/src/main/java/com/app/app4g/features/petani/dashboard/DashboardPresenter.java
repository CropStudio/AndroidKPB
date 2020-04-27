package com.app.app4g.features.petani.dashboard;

import com.app.app4g.common.CommonRespon;
import com.app.app4g.network.NetworkService;
import com.app.app4g.network.RestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardPresenter {

    final IDashboardView view;
    public final Retrofit restService;

    public DashboardPresenter(IDashboardView view) {
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void cekAppVersion(String app_id) {
        view.showLoadingIndicator();
        restService.create(NetworkService.class).cekVersion(app_id)
                .enqueue(new Callback<CommonRespon>() {
                    @Override
                    public void onResponse(Call<CommonRespon> call, Response<CommonRespon> response) {
                        view.hideLoadingIndicator();
                        if(response.body().getSuccess())
                            view.goUpdateVersion(response.body().getmRm());

                    }

                    @Override
                    public void onFailure(Call<CommonRespon> call, Throwable t) {
                        view.hideLoadingIndicator();
                        view.onNetworkError(t.getLocalizedMessage());
                    }
                });
    }

}
