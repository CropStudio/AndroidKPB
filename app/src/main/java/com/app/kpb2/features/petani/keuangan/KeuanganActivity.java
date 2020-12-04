package com.app.kpb2.features.petani.keuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

public class KeuanganActivity extends AppCompatActivity implements IKeuanganView {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.mLabel)
    TextView mLabel;

    long Subtotal;
    ProgressDialog pDialog;
    LoginResponse mProfile;
    public KeuanganAdapter adapter;
    KeuanganPresenter presenter;
    String idKec, nik, token, idDesa, nomorrekening, idAset, idKab, namaBank;
    Number idKios, tahun, idPoktan;
    SweetAlertDialog sweetAlertDialog;
    Boolean LayoutStat = false;

    public List<Result> items = null;
    String label;
    Boolean statusSi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);
        ButterKnife.bind(this);
        presenter = new KeuanganPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Keuangan");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        nomorrekening = (mProfile.getResult().getProfile().getNomorRekening().contains(""))
                ? mProfile.getResult().getProfile().getNomorRekening() : mProfile.getResult().getProfile().getNomorRekening();
        if (!mProfile.getResult().getProfile().getNomorRekening().equals("")) {

            namaBank = (mProfile.getResult().getProfile().getBank().contains(" "))
                    ? mProfile.getResult().getProfile().getBank() : mProfile.getResult().getProfile().getBank();
        }
        idKios = mProfile.getResult().getProfile().getIdKios();
        idDesa = (mProfile.getResult().getProfile().getArea().getSub_district_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getSub_district_code() : mProfile.getResult().getProfile().getArea().getSub_district_code();
        idKec = (mProfile.getResult().getProfile().getArea().getDistrict_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getDistrict_code() : mProfile.getResult().getProfile().getArea().getDistrict_code();
        idKab = (mProfile.getResult().getProfile().getArea().getCity_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getCity_code() : mProfile.getResult().getProfile().getArea().getCity_code();

        this.initView();
        presenter.getAllRut(nik,token);


    }

    @Override
    public void initView() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void showLoadingIndicator() {
        sweetAlertDialog.show();
    }

    @Override
    public void hideLoadingIndicator() {
        sweetAlertDialog.dismiss();
    }

    @Override
    public void onDataReady(List<Result> result) {
        this.items = result;
        adapter = new KeuanganAdapter(items, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailed(String rm) {
        Log.d("requestFail", rm);
        SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
            this.goToDashboard();
        });
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void goToDashboard() {
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.goToDashboard();
        }
        return false;
        // Disable back button..............
    }
}
