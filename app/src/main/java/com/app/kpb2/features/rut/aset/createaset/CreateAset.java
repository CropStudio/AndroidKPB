package com.app.kpb2.features.rut.aset.createaset;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.features.petani.profile.model.AsetPetani;
import com.app.kpb2.features.petani.registrasi.model.FormModel.Result;
import com.app.kpb2.features.rut.aset.AsetActivity;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
import com.google.android.material.textfield.TextInputLayout;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAset extends AppCompatActivity implements ICreateAsetView, AdapterView.OnItemSelectedListener {


    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.mSubsektor)
    Spinner mSubsektor;

    @BindView(R.id.mKomoditas)
    Spinner mKomoditas;

    @BindView(R.id.LayoutLuasTanah)
    TextInputLayout LayoutLuasTanah;

    @BindView(R.id.LayoutBanyakKomoditas)
    TextInputLayout LayoutBanyakKomoditas;

    @BindView(R.id.mLuasLahan)
    EditText mLuasLahan;

    @BindView(R.id.mJmlhKomoditas)
    EditText mJmlhKomoditas;


    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    LoginResponse mProfile;
    JSONArray DataAset = new JSONArray();
    JSONArray newAset = new JSONArray();
    private String subsektors, komoditass, nik, token, idSubsektor;
    public String key_subsektors = "subsektor";
    public String key_komoditas = "komoditas";
    CreateAsetPresenter presenter;
    private LinkedHashMapAdapter<String, String> adapter;
    private LinkedHashMap<String, String> subsektor;
    private LinkedHashMap<String, String> komoditas;
    private List<AsetPetani> asetsPetani = null;
    View rowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aset);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tambah Subsektor");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new CreateAsetPresenter(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        this.initViews();
    }


    @Override
    public void onCreateAsetSuccess(LoginResponse profile) {
        presenter.storeProfile(profile);

        SweetDialogs.commonSuccessWithIntent(this, profile.getRm(), string -> {
            this.goToDashboard();
        });
    }

    @Override
    public void refreshPage() {
        startActivity(new Intent(this, CreateAset.class));
        finish();
    }

    @Override
    public void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        if (mProfile.getResult().getProfile().getAsetPetani() != null) {
            asetsPetani = (mProfile.getResult().getProfile().getAsetPetani().contains(null))
                    ? mProfile.getResult().getProfile().getAsetPetani() : mProfile.getResult().getProfile().getAsetPetani();
        }
        mSubmit.setOnClickListener(view -> onSubmit());
        presenter.getSpinner("", key_subsektors);

        mSubsektor.setOnItemSelectedListener(this);
    }

    @Override
    public void onSubmit() {
        AsetPetani model = new AsetPetani();
        String totalAset = "";
        String idSub = idSubsektor;
        String namaAset = subsektors;


        if (namaAset.equals("Tanaman Pangan") || namaAset.equals("Perkebunan")
                || namaAset.equals("Hortikultura")) {
            totalAset = mLuasLahan.getText().toString();
        } else
            totalAset = mJmlhKomoditas.getText().toString();
//
        if (!totalAset.equals("")) {
            model.setIdSubsektor(idSub);
            model.setNamaAset(namaAset);
            model.setTotalAset(totalAset);
            presenter.createAset(nik, token, model);
        } else {
            TopSnakbar.showWarning(this, "Anda belum mengisi luas lahan / banyaknya komoditas");
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
    public void onDataReady(List<Result> result, String key) {
        subsektor = new LinkedHashMap<String, String>();
        komoditas = new LinkedHashMap<String, String>();
        if (key.equals(key_subsektors)) {
            for (Result subsektors : result) {
                subsektor.put(subsektors.getId(), subsektors.getName());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, R.layout.spinnermt, subsektor);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSubsektor.setAdapter(adapter);
            mSubsektor.setOnItemSelectedListener(this);
//        } else if (key.equals(key_komoditas)) {
//            for (Result komoditass : result) {
////                System.out.println(subsektors.getName());
//                komoditas.put(komoditass.getId(), komoditass.getName());
//            }
//
//            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, komoditas);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            mKomoditas.setAdapter(adapter);
//            mKomoditas.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.mSubsektor:
                Map.Entry<String, String> itemSubsektor = (Map.Entry<String, String>) mSubsektor.getSelectedItem();

                if (itemSubsektor.getValue().equals("Tanaman Pangan") || itemSubsektor.getValue().equals("Perkebunan")
                        || itemSubsektor.getValue().equals("Hortikultura")) {
                    LayoutLuasTanah.setVisibility(View.VISIBLE);
                    LayoutBanyakKomoditas.setVisibility(View.GONE);
                } else {
                    LayoutLuasTanah.setVisibility(View.GONE);
                    LayoutBanyakKomoditas.setVisibility(View.VISIBLE);
                }
                subsektors = itemSubsektor.getValue();
                idSubsektor = itemSubsektor.getKey();
//                presenter.getSpinner(itemSubsektor.getKey(), key_komoditas);
                break;
//            case R.id.mKomoditas:
//                Map.Entry<String, String> itemKomoditas = (Map.Entry<String, String>) mKomoditas.getSelectedItem();
//                komoditass = itemKomoditas.getValue();
////                Toast.makeText(this, mMt1.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, AsetActivity.class);
        startActivity(i);
        finish();
    }

}
