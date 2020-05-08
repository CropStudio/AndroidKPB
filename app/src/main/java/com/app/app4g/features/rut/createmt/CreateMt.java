package com.app.app4g.features.rut.createmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.Utils.LinkedHashMapAdapter;
import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.features.petani.registrasi.model.FormModel.Result;
import com.app.app4g.features.rut.aset.AsetActivity;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;
import com.app.app4g.ui.TopSnakbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateMt extends AppCompatActivity implements ICreateMtView, AdapterView.OnItemSelectedListener, View.OnClickListener {
    String subsektor, idSubsektor, totalAset, _id;
    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.LayoutLuasTanah)
    TextInputLayout LayoutLuasTanah;

    @BindView(R.id.LayoutBanyakKomoditas)
    TextInputLayout LayoutBanyakKomoditas;

    @BindView(R.id.mJmlhKomoditas)
    EditText mJmlhKomoditas;

    @BindView(R.id.mLuasLahan)
    EditText mLuasLahan;

    @BindView(R.id.addMt)
    Button addMt;

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.mKomoditas)
    MaterialSpinner mKomoditas;

    @BindView(R.id.mMt)
    Spinner mMt;

    @BindView(R.id.containerMt)
    LinearLayout parent_datas;
    private List<AsetPetani> asetPetani;
    View rowView;
    LoginResponse mProfile;
    JSONArray dataMt = new JSONArray();
    ArrayList<String> spinnerKomoditas = new ArrayList<>();
    CreateMtPresenter presenter;
    private LinkedHashMap<String, String> mt;
    private LinkedHashMapAdapter<String, String> adapter;
    String idMt, valueMt, nik, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mt);
        ButterKnife.bind(this);
        presenter = new CreateMtPresenter(this);
        subsektor = getIntent().getExtras().getString("subsektor");
        _id = getIntent().getExtras().getString("_id");
        idSubsektor = getIntent().getExtras().getString("idSubsektor");
        totalAset = getIntent().getExtras().getString("totalAset");
        this.initViews();

    }

    @Override
    public void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        mSubmit.setOnClickListener(this);
        presenter.getKomoditas(idSubsektor);
        if (subsektor.equals("Tanaman Pangan") || subsektor.equals("Perkebunan")
                || subsektor.equals("Hortikultura")) {
            LayoutLuasTanah.setVisibility(View.VISIBLE);
            LayoutBanyakKomoditas.setVisibility(View.GONE);
        } else {
            LayoutBanyakKomoditas.setVisibility(View.VISIBLE);
            LayoutLuasTanah.setVisibility(View.GONE);
        }
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        asetPetani = (mProfile.getResult().getProfile().getAsetPetani().contains(" "))
                ? mProfile.getResult().getProfile().getAsetPetani() : mProfile.getResult().getProfile().getAsetPetani();
        mt = new LinkedHashMap<String, String>();
        mt.put("1", "Masa Tanam 1");
        mt.put("2", "Masa Tanam 2");
        mt.put("3", "Masa Tanam 3");
        mt.put("4", "Masa Tanam 4");
        mt.put("5", "Masa Tanam 5");
        mt.put("6", "Masa Tanam 6");
        mt.put("7", "Masa Tanam 7");
        mt.put("8", "Masa Tanam 8");
        mt.put("9", "Masa Tanam 9");
        mt.put("10", "Masa Tanam 10");
        adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, mt);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMt.setAdapter(adapter);
        mMt.setOnItemSelectedListener(this);

    }

    @Override
    public void onDataReady(List<Result> result) {
        for (Result komoditas : result) {
            spinnerKomoditas.add(komoditas.getName());
        }
        mKomoditas.setItems(spinnerKomoditas);

    }

    @Override
    public void onCreateMtSuccess(LoginResponse profile) {
        presenter.storeProfile(profile);

        SweetDialogs.commonSuccessWithIntent(this, profile.getRm(), string -> {
            this.gotoAsset();
        });
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
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }

        return false;
        // Disable back button..............
    }

    @Override
    public void gotoAsset() {
        startActivity(new Intent(this, AsetActivity.class));
        finish();
    }

    public void onAddMt(View v) {
        boolean ValidasiInput = true;
        switch (v.getId()) {
            case R.id.addMt:
                if (!mKomoditas.getText().toString().equals("Pilih Komoditas")) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(this.LAYOUT_INFLATER_SERVICE);
                    rowView = inflater.inflate(R.layout.data_mt_field, null);
                    final TextView mMt = rowView.findViewById(R.id.mMt);
                    final TextView mTotalAset = rowView.findViewById(R.id.mTotalAset);
                    mMt.setText(valueMt);
                    if (subsektor.equals("Tanaman Pangan") || subsektor.equals("Perkebunan")
                            || subsektor.equals("Hortikultura"))
                        mTotalAset.setText(mLuasLahan.getText().toString());
                    else
                        mTotalAset.setText(mJmlhKomoditas.getText().toString());

                    if (!mTotalAset.getText().toString().equals("")) {
                        if (dataMt != null) {
                            for (int i = 0; i < dataMt.length(); ++i) {
                                JSONObject rec = null;
                                try {
                                    rec = dataMt.getJSONObject(i);
                                    String masaTanam = rec.getString("masaTanam");
                                    if (masaTanam.equals(idMt)) {
                                        ValidasiInput = false;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (ValidasiInput) {
                                try {
                                    dataMt.put(
                                            new JSONObject()
                                                    .put("masaTanam", idMt)
                                                    .put("jumlahAset", mTotalAset.getText().toString())
                                                    .put("subsektor", subsektor)
                                                    .put("namaKomoditas", mKomoditas.getText().toString())

                                    );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                JSONObject dataPermt = new JSONObject();
                                try {
                                    dataPermt.put("dataPermt", dataMt);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d("DATAMT", dataPermt.toString());
//                                presenter.createMt(nik, token, _id, dataMt.toString());
                                parent_datas.addView(rowView, 0);
                                TopSnakbar.showSuccess(this, "Data masa tanam di tambahkan");
                            } else {
                                TopSnakbar.showWarning(this, "Anda tidak bisa memilih masa tanam yang sama");
                            }
                        } else {
                            TopSnakbar.showSuccess(this, "Data masa tanam di tambahkan");
                            try {
                                dataMt.put(
                                        new JSONObject()
                                                .put("masaTanam", idMt)
                                                .put("jumlahAset", mTotalAset.getText().toString())
                                                .put("subsektor", subsektor)
                                                .put("namaKomoditas", mKomoditas.getText().toString())

                                );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONObject dataPermt = new JSONObject();
                            try {
                                dataPermt.put("dataPermt", dataMt);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("DATAMT", dataPermt.toString());
                            parent_datas.addView(rowView, 0);
                        }

                    } else {
                        TopSnakbar.showWarning(this, "Harap isi Luas Lahan / Banyak komoditas !");
                    }

                } else {
                    TopSnakbar.showWarning(this, "Anda belum memilih komoditas ");
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.mMt:
                Map.Entry<String, String> itemMt = (Map.Entry<String, String>) mMt.getSelectedItem();
                idMt = itemMt.getKey();
                valueMt = itemMt.getValue();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void CreateMt(){
        presenter.createMt(nik, token, _id, dataMt.toString());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mSubmit:
                SweetDialogs.confirmDialog(this,"Apakah anda yakin ?" , "pastikan data masa tanam sudah benar !" , "Berhasil memuat permintaan" , string -> this.CreateMt());
                break;
        }
    }
}
