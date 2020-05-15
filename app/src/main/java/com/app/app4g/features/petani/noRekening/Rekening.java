package com.app.app4g.features.petani.noRekening;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.profile.model.DataMt;
import com.app.app4g.features.rut.RutActivity;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Rekening extends AppCompatActivity implements IRekeningView{
    @BindView(R.id.mNorek)
    EditText mNorek ;
    @BindView(R.id.mSubmit)
    Button mSubmit ;
    @BindView(R.id.mRadioGroupBank)
    RadioGroup mRadioGroupBank ;
    RekeningPresenter presenter ;
    SweetAlertDialog sweetAlertDialog;
    private LoginResponse mProfile;
    private String nik, nama, alamat, token,Bank , idAset;
    List<DataMt> dataMt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekening);
        ButterKnife.bind(this);
        presenter = new RekeningPresenter(this);
        dataMt = (List<DataMt>) getIntent().getExtras().getSerializable("data");
        idAset = getIntent().getExtras().getString("_id");
        System.out.println(new Gson().toJson(dataMt));
        System.out.println(idAset);
        this.initViews();

    }

    @Override
    public void initViews() {
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        mSubmit.setOnClickListener(view -> SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?" , "Pastikan no rekening anda sudah benar!" , "Data Berhasil disimpan .", string -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                this.onCreateRekening();
            }
        }));
    }

    @Override
    public void onCreateRekening(){
        Bank = ((RadioButton) findViewById(mRadioGroupBank.getCheckedRadioButtonId())).getText().toString();
        JSONObject dataRoot = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            data.put("nomorRekening" , mNorek.getText().toString());
            data.put("bank" , Bank);
            dataRoot.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        presenter.onCreateRekening(nik,token,dataRoot.toString(),mNorek.getText().toString());

    }

    @Override
    public void onCreateRekeningSuksess(LoginResponse profile, String noRek){
        presenter.storeProfile(profile);
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan" , string -> {
            this.goToRut();
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
    public void goToRut(){
        Intent i = new Intent(this, RutActivity.class);
        i.putExtra("data", (Serializable) dataMt);
        i.putExtra("_id", idAset);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.goToRut();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.goToRut();
    }
}
