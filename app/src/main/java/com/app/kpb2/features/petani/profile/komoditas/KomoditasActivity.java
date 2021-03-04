package com.app.kpb2.features.petani.profile.komoditas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KomoditasActivity extends AppCompatActivity implements IKomoditasView, AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.mKomoditas)
    SearchableSpinner mKomoditas;

    @BindView(R.id.mSubmit)
    Button mSubmit;
    private LoginResponse mProfile;
    private String nik , token ;
    KomoditasPresenter presenter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komoditas);
        ButterKnife.bind(this);
        presenter = new KomoditasPresenter(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Profile");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDashBoard();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.initViews();
    }

    void gotoDashBoard(){
        startActivity(new Intent(this, MenuUtama.class));
        finish();
    }

    @Override
    public void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));

        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        presenter.getKomoditas(nik , token);
        mSubmit.setOnClickListener(v -> this.onCreateKomoditas());
    }

    @Override
    public void onCreateKomoditas() {
        JSONObject dataRoot = new JSONObject();
        JSONObject data = new JSONObject();
        JSONArray value = new JSONArray();
        JSONObject komoditas = new JSONObject();

        try {
            komoditas.put("nama" , mKomoditas.getSelectedItem().toString());
            value.put(komoditas);
            data.put("komoditas", value);
            dataRoot.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Datanya" , dataRoot.toString());
        presenter.onCreateKomoditas(nik, token, dataRoot.toString());
    }

    @Override
    public void onDataReady(List<Komoditas> result) {
//        Log.d("datanya" ,new Gson().toJson(result));
        ArrayList<String> list = new ArrayList<>();
        for (Komoditas value : result){
            list.add(value.getKomoditas());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKomoditas.setAdapter(adapter);
        mKomoditas.setOnItemSelectedListener(this);
        mKomoditas.setTitle("Cari Komoditas");

    }

    @Override
    public void onCreateKomoditasSuksess(LoginResponse profile) {
        presenter.storeProfile(profile);
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan", string -> {
            this.gotoKomoditas();

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void gotoKomoditas() {
        startActivity(new Intent(this , KomoditasActivity.class));
    }
}
