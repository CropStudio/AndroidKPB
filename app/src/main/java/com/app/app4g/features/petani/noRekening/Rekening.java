package com.app.app4g.features.petani.noRekening;

import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.Utils.LinkedHashMapAdapter;
import com.app.app4g.features.petani.noRekening.model.Kios;
import com.app.app4g.features.petani.profile.Profile;
import com.app.app4g.features.petani.profile.model.DataMt;
import com.app.app4g.features.rut.RutActivity;
import com.app.app4g.features.rut.createmt.CreateMtPresenter;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;
import com.app.app4g.ui.TopSnakbar;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekening extends AppCompatActivity implements IRekeningView, AdapterView.OnItemSelectedListener, View.OnClickListener {
    @BindView(R.id.mNorek)
    EditText mNorek;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    @BindView(R.id.mSpinnerKios)
    Spinner mSpinnerKios;
    @BindView(R.id.mRadioGroupBank)
    RadioGroup mRadioGroupBank;
    @BindView(R.id.r1)
    RadioButton r1;
    @BindView(R.id.r2)
    RadioButton r2;
    @BindView(R.id.r3)
    RadioButton r3;
    @BindView(R.id.r4)
    RadioButton r4;
    RekeningPresenter presenter;
    SweetAlertDialog sweetAlertDialog;
    private LoginResponse mProfile;
    private String nik, nama, alamat, token, Bank, idAset, idDesa, idKec, idKab , noRek , namaKios , namaBank;
    private Number idKios;
    List<DataMt> dataMt;
    ArrayList<String> spinnerKios = new ArrayList<>();
    private LinkedHashMapAdapter<String, String> adapter;
    private LinkedHashMap<String, String> kios;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    Bundle bundle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekening);
        ButterKnife.bind(this);
        presenter = new RekeningPresenter(this);
        Intent intent = this.getIntent();
        bundle = intent.getExtras();
        if(bundle!=null) {
            dataMt = (List<DataMt>) getIntent().getExtras().getSerializable("data");
            idAset = getIntent().getExtras().getString("_id");
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rekening");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        this.initViews();
    }

    @Override
    public void initViews() {
//        Bank = ((RadioButton) findViewById(mRadioGroupBank.getCheckedRadioButtonId())).getText().toString();
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idDesa = (mProfile.getResult().getProfile().getArea().getSub_district_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getSub_district_code() : mProfile.getResult().getProfile().getArea().getSub_district_code();
        idKec = (mProfile.getResult().getProfile().getArea().getDistrict_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getDistrict_code() : mProfile.getResult().getProfile().getArea().getDistrict_code();
        idKab = (mProfile.getResult().getProfile().getArea().getCity_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getCity_code() : mProfile.getResult().getProfile().getArea().getCity_code();
        noRek = (mProfile.getResult().getProfile().getNomorRekening().contains(" "))
                ? mProfile.getResult().getProfile().getNomorRekening() : mProfile.getResult().getProfile().getNomorRekening();
        namaBank =  mProfile.getResult().getProfile().getBank();
        if(!noRek.equals("")) {
            mNorek.setText(noRek);
            if(namaBank.equals("Bank BNI")){
                r1.setChecked(true);
            }else if(namaBank.equals("Bank MANDIRI")){
                r2.setChecked(true);
            }else if(namaBank.equals("Bank BRI")){
                r3.setChecked(true);
            }else if(namaBank.equals("Bank LAMPUNG")){
                r4.setChecked(true);
            }


        }
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        presenter.getKios(idKab, idDesa, idKec);
        mSubmit.setOnClickListener(this);

    }

    @Override
    public void onCreateRekening() {
        Bank = ((RadioButton) findViewById(mRadioGroupBank.getCheckedRadioButtonId())).getText().toString();
        JSONObject dataRoot = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            data.put("nomorRekening", mNorek.getText().toString());
            data.put("idKios", idKios);
            data.put("bank", Bank);
            dataRoot.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        presenter.onCreateRekening(nik, token, dataRoot.toString(), mNorek.getText().toString());
    }

    @Override
    public void onDataReady(List<Kios> result) {
        kios = new LinkedHashMap<>();
        for (Kios value : result) {
            kios.put(value.getId(), value.getName());
        }
        adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, kios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerKios.setAdapter(adapter);
        mSpinnerKios.setOnItemSelectedListener(this);
    }

    @Override
    public void onCreateRekeningSuksess(LoginResponse profile, String noRek) {
//        profile.getResult().getProfile().setNamaKios(namaKios);
        presenter.storeProfile(profile);
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan", string -> {
            if(bundle!=null)
                this.goToRut();
            else
                this.goToProfile();

        });
    }

    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
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
    public void goToRut() {
        Intent i = new Intent(this, RutActivity.class);
        i.putExtra("data", (Serializable) dataMt);
        i.putExtra("_id", idAset);
        startActivity(i);
        finish();
    }

    @Override
    public void goToProfile() {
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if(bundle!=null)
                this.goToRut();
            else
                this.goToProfile();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.goToRut();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Map.Entry<String, String> itemSubsektor = (Map.Entry<String, String>) mSpinnerKios.getSelectedItem();
        idKios = Integer.parseInt(itemSubsektor.getKey());
        namaKios = itemSubsektor.getValue();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mSubmit:
                if (mRadioGroupBank.getCheckedRadioButtonId() != -1 && !mNorek.getText().toString().equals("")) {
                    SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?", "Pastikan no rekening anda sudah benar!", "Data Berhasil disimpan.", string -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            this.onCreateRekening();
                        }
                    });
                } else
                    TopSnakbar.showWarning(this, "Pastikan anda sudah memilih Bank dan mengisi nomor rekening");
                break;
        }
    }
}
