package com.example.app4g.features.rut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.features.rut.model.Rut;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;
import com.example.app4g.ui.SweetDialogs;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RutActivity extends AppCompatActivity implements IRutView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mUrea)
    TextView mUrea;
    @BindView(R.id.mHci)
    TextView mHci;
    @BindView(R.id.mPhonska)
    TextView mPhonska;
    @BindView(R.id.mOrganik)
    TextView mOrganik;
    @BindView(R.id.mNpk)
    TextView mNpk;
    @BindView(R.id.mLuasLahan)
    TextView mLuasLahan;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    private Rut ruts;
    private RutPresenter presenter;
    SweetAlertDialog sweetAlertDialog;

    String nik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut);
        ButterKnife.bind(this);
        this.initView();
        mToolbar.setTitle("RUT (Rencana Usaha Tani)");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.c_white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new RutPresenter(this);

    }

    @Override
    public void initView() {
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        mSubmit.setOnClickListener(view -> this.onCreateRut());
    }

    @Override
    public void onCreateRut() {
        ruts = new Rut();
        ruts.setHci(mHci.getText().toString());
        ruts.setUrea(mUrea.getText().toString());
        ruts.setPhonska(mPhonska.getText().toString());
        ruts.setOrganik(mOrganik.getText().toString());
        ruts.setNpk(mNpk.getText().toString());
        ruts.setLuas_lahan(mLuasLahan.getText().toString());
        presenter.createRut(nik, ruts);
    }

    @Override
    public void onCreateRutSuccess() {
        SweetDialogs.commonSuccess(this,"Berhasil Menambahkan Rut" , true);
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
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }
}