package com.app.app4g.features.petani.profile.createaset;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.Utils.LinkedHashMapAdapter;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.petani.profile.model.AsetPetani;
import com.app.app4g.features.petani.registrasi.model.FormModel.Result;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;
import com.app.app4g.ui.TopSnakbar;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAset extends AppCompatActivity implements ICreateAsetView, AdapterView.OnItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mAddAset)
    Button mAddAset;

    @BindView(R.id.mTambah)
    Button mTambah;

    @BindView(R.id.container)
    LinearLayout parent_data;

    @BindView(R.id.CardForm)
    CardView CardForm;

    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.mSubsektor)
    Spinner mSubsektor;

    @BindView(R.id.mKomoditas)
    Spinner mKomoditas;

    @BindView(R.id.LayoutLuasTanah)
    CardView LayoutLuasTanah;

    @BindView(R.id.LayoutBanyakKomoditas)
    CardView LayoutBanyakKomoditas;

    @BindView(R.id.mLuasLahan)
    EditText mLuasLahan;

    @BindView(R.id.mJmlhKomoditas)
    TextView mJmlhKomoditas;

    @BindView(R.id.layoutImageDesc)
    ImageView layoutImageDesc;


    @BindView(R.id.LayoutBtnTambahAset)
    LinearLayout LayoutBtnTambahAset;

//    @BindView(R.id.mSubmit)
//    Button mSubmit;

    LoginResponse mProfile;
    JSONArray DataAset = new JSONArray();
    private String subsektors, komoditass, nik, token;
    public String key_subsektors = "subsektor";
    public String key_komoditas = "komoditas";
    CreateAsetPresenter presenter;
    private LinkedHashMapAdapter<String, String> adapter;
    private LinkedHashMap<String, String> subsektor;
    private LinkedHashMap<String, String> komoditas;
    private List<AsetPetani> asetPetani;
    View rowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_aset);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Data Aset");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.c_black));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new CreateAsetPresenter(this);
        this.initViews();
    }



    void AddForm() {
        CardForm.setVisibility(View.VISIBLE);
        LayoutBtnTambahAset.setVisibility(View.GONE);
        parent_data.setVisibility(View.GONE);
    }

    void HideForm() {
        CardForm.setVisibility(View.GONE);
        LayoutBtnTambahAset.setVisibility(View.VISIBLE);
    }

    public void onAddAset(View v) {
        switch (v.getId()) {
            case R.id.mAddAset:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                rowView = inflater.inflate(R.layout.data_asset_field, null);
                final TextView mSubsektor = rowView.findViewById(R.id.mSubsektor);
                final TextView mJenisAset = rowView.findViewById(R.id.mJenisAset);
                final TextView mJmlhAset = rowView.findViewById(R.id.mJmlhAset);
                mSubsektor.setText(subsektors);
                mJenisAset.setText(komoditass);
                if (subsektors.equals("Tanaman Pangan") || subsektors.equals("Perkebunan")
                        || subsektors.equals("Hortikultura"))
                    mJmlhAset.setText(mLuasLahan.getText().toString());
                else
                    mJmlhAset.setText(mJmlhKomoditas.getText().toString());
                mSubsektor.setEnabled(false);
                mJenisAset.setEnabled(false);
                mJmlhAset.setEnabled(false);
                try {
                    DataAset.put(
                            new JSONObject()
                                    .put("namaAset", mSubsektor.getText().toString())
                                    .put("jenisAset", mJenisAset.getText().toString())
                                    .put("totalAset", mJmlhAset.getText().toString())

                    );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject dataRoot = new JSONObject();
                JSONObject asetPetani = new JSONObject();
                try {
                    asetPetani.put("asetPetani", DataAset);
                    dataRoot.put("data", asetPetani);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                presenter.createAset(nik, token, dataRoot.toString());
//                parent_data.addView(rowView, 0);
////                Toast.makeText(this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
//                HideForm();
                parent_data.addView(rowView, 0);
                HideForm();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onDeleteAset(View v) {
        JSONObject dataRoot = new JSONObject();
        JSONObject asetPetani = new JSONObject();
        try {
            asetPetani.put("asetPetani", DataAset);
            dataRoot.put("data", asetPetani);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView _id = ((View) v.getParent()).findViewById(R.id.mId);
        parent_data.removeView((View) v.getParent());
        for (int i = 0; i < DataAset.length(); i++) {
            try {
                if (_id.getText().toString().equals(DataAset.getJSONObject(i).getString("_id"))) {
                    DataAset.remove(i);
                    presenter.createAset(nik, token, dataRoot.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


//    void onSubmit() {
//        JSONObject dataRoot = new JSONObject();
//        JSONObject asetPetani = new JSONObject();
//        try {
//            asetPetani.put("asetPetani", DataAset);
//            dataRoot.put("data", asetPetani);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        presenter.createAset(nik, token, dataRoot.toString());
//    }

    @Override
    public void onCreateAsetSuccess(LoginResponse profile) {
        presenter.storeProfile(profile);

        SweetDialogs.commonSuccessWithIntent(this, profile.getRm(), string -> {
            this.refreshPage();
        });
    }

    @Override
    public void refreshPage() {
        startActivity(new Intent(this, CreateAset.class));
        finish();
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
        if (mProfile.getResult().getProfile().getAsetPetani() != null) {
            asetPetani = (mProfile.getResult().getProfile().getAsetPetani().contains(" "))
                    ? mProfile.getResult().getProfile().getAsetPetani() : mProfile.getResult().getProfile().getAsetPetani();
            if(asetPetani.size() > 0) {
                this.showDataAset();
                layoutImageDesc.setVisibility(View.GONE);
            }else
                layoutImageDesc.setVisibility(View.VISIBLE);
        }

        mTambah.setOnClickListener(view -> AddForm());
//        mSubmit.setOnClickListener(view -> onSubmit());
        presenter.getSpinner("", key_subsektors);
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        mSubsektor.setOnItemSelectedListener(this);
    }

    @Override
    public void showDataAset() {
        for (AsetPetani data : asetPetani) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.data_asset_field, null);
            final TextView mSubsektor = rowView.findViewById(R.id.mSubsektor);
            final TextView mJenisAset = rowView.findViewById(R.id.mJenisAset);
            final TextView mJmlhAset = rowView.findViewById(R.id.mJmlhAset);
            final TextView mId = rowView.findViewById(R.id.mId);
            mId.setText(data.get_id());
            mSubsektor.setText(data.getNamaAset());
            mJenisAset.setText(data.getJenisAset());
            if (data.getNamaAset().equals("Tanaman Pangan") || data.getNamaAset().equals("Perkebunan")
                    || data.getNamaAset().equals("Hortikultura"))
                mJmlhAset.setText(data.getTotalAset());
            else
                mJmlhAset.setText(data.getTotalAset());
            mSubsektor.setEnabled(false);
            mJenisAset.setEnabled(false);
            mJmlhAset.setEnabled(false);
            try {
                DataAset.put(
                        new JSONObject()
                                .put("namaAset", mSubsektor.getText().toString())
                                .put("jenisAset", mJenisAset.getText().toString())
                                .put("_id", mId.getText().toString())
                                .put("totalAset", mJmlhAset.getText().toString())

                );

            } catch (JSONException e) {
                e.printStackTrace();
            }
            parent_data.addView(rowView, 0);

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
                System.out.println(subsektors.getName());
                subsektor.put(subsektors.getId(), subsektors.getName());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, subsektor);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSubsektor.setAdapter(adapter);
            mSubsektor.setOnItemSelectedListener(this);
        } else if (key.equals(key_komoditas)) {
            for (Result komoditass : result) {
//                System.out.println(subsektors.getName());
                komoditas.put(komoditass.getId(), komoditass.getName());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, komoditas);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mKomoditas.setAdapter(adapter);
            mKomoditas.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        TopSnakbar.showWarning(this, "Koneksi Anda Bermasalah !");
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
                presenter.getSpinner(itemSubsektor.getKey(), key_komoditas);
                break;
            case R.id.mKomoditas:
                Map.Entry<String, String> itemKomoditas = (Map.Entry<String, String>) mKomoditas.getSelectedItem();
                komoditass = itemKomoditas.getValue();
//                Toast.makeText(this, mMt1.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
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
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }
}
