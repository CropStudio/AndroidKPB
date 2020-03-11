package com.example.app4g.features.petani.registrasi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app4g.R;
import com.example.app4g.Utils.LinkedHashMapAdapter;
import com.example.app4g.features.petani.registrasi.model.Area;
import com.example.app4g.features.petani.registrasi.model.FormModel.Result;
import com.example.app4g.features.petani.profile.model.LuasLahan;
import com.example.app4g.features.petani.registrasi.model.RegistModel;
import com.example.app4g.features.users.login.Login;
import com.example.app4g.server.App;
import com.example.app4g.ui.SweetDialogs;
import com.example.app4g.ui.TopSnakbar;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistPetaniActivity extends AppCompatActivity implements IRegistPetaniView, MaterialSpinner.OnItemSelectedListener, AdapterView.OnItemSelectedListener {
    SweetAlertDialog sweetAlertDialog;
    RegistPetaniPresenter presenter;
    private LinkedHashMapAdapter<String, String> adapter;
    private LinkedHashMap<String, String> provinsi;
    private LinkedHashMap<String, String> kabupaten;
    private LinkedHashMap<String, String> kecamatan;
    private LinkedHashMap<String, String> desa;
    private LinkedHashMap<String, String> subsektor;
    private LinkedHashMap<String, String> komoditas;
    public static final String key_provinsi = "provinsi";
    public static final String key_kabupaten = "kabupaten";
    public static final String key_kecamatan = "kecamatan";
    public static final String key_desa = "desa";
    public static final String key_subsektor = "subsektor";
    public static final String key_komoditas = "komoditas";

    String idprov, provinsis, idkabs ,kabupatens, idkecs,kecamatans, iddesas,desas, subsektors, komoditass;

    @BindView(R.id.mProv)
    Spinner mProv;

    @BindView(R.id.mKab)
    Spinner mKab;

    @BindView(R.id.mKec)
    Spinner mKec;

    @BindView(R.id.mDesa)
    Spinner mDesa;

    @BindView(R.id.mSubsektor)
    Spinner mSubsektor;

    @BindView(R.id.mKomoditas)
    Spinner mKomoditas;

    @BindView(R.id.mNama)
    EditText mNama;

    @BindView(R.id.mNik)
    EditText mNik;

    @BindView(R.id.mAlamat)
    EditText mAlamat;

    @BindView(R.id.mPassword)
    EditText mPassword;

    @BindView(R.id.mRePassword)
    EditText mRePassword;

    @BindView(R.id.mMt1)
    EditText mMt1;

    @BindView(R.id.mMt2)
    EditText mMt2;

    @BindView(R.id.mMt3)
    EditText mMt3;

    @BindView(R.id.mSubmit)
    Button mSubmit;

    @BindView(R.id.LayoutLuasTanah)
    CardView LayoutLuasTanah;

    @BindView(R.id.LayoutBanyakKomoditas)
    CardView LayoutBanyakKomoditas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_petani);
        ButterKnife.bind(this);
        presenter = new RegistPetaniPresenter(this);
        this.initViews();
    }

    @Override
    public void initViews() {
        presenter.getArea("", key_subsektor);
        mSubmit.setOnClickListener(view -> this.onRegisPetani());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        provinsi = new LinkedHashMap<String, String>();
        provinsi.put("18", "Lampung");
        provinsi.put("16", "Sumatera Selatan");
        adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, provinsi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProv.setAdapter(adapter);
        mProv.setOnItemSelectedListener(this);
        mKab.setOnItemSelectedListener(this);
        mKec.setOnItemSelectedListener(this);
        mDesa.setOnItemSelectedListener(this);
        mSubsektor.setOnItemSelectedListener(this);
    }

    @Override
    public void onRegisPetani() {
        String nama = mNama.getText().toString();
        String nik = mNik.getText().toString();
//        provi = mProv.getSelectedItem().toString();
//        kabupaten = mKab.getSelectedItem().toString();
//        kecamatan = mKec.getSelectedItem().toString();
//        desa = mDesa.getSelectedItem().toString();
        String alamat = mAlamat.getText().toString();
        String password = mPassword.getText().toString();
        String rePassword = mRePassword.getText().toString();
        String mt1 = mMt1.getText().toString();
        String mt2 = mMt2.getText().toString();
        String mt3 = mMt3.getText().toString();
        JSONObject luas_lahan = new JSONObject();
        RegistModel model = new RegistModel();
        LuasLahan modelLahan= new LuasLahan();
        Area modelArea= new Area();
        if (!nama.equals(""))
            if (!nik.equals(""))
                if (!provinsi.equals(""))
                    if (!kabupaten.equals(""))
                        if (!kecamatan.equals(""))
                            if (!desa.equals(""))
                                if (!alamat.equals(""))
                                    if (!password.equals(""))
                                        if (rePassword.equals(password)) {
                                            new AlertDialog.Builder(this)
                                                    .setTitle("SYARAT DAN KETENTUAN")
                                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            model.setNama(nama);
                                                            model.setNik(nik);
                                                            modelArea.setProvince_code(idprov);
                                                            modelArea.setProvince(provinsis);
                                                            modelArea.setCity_code(idkabs);
                                                            modelArea.setCity(kabupatens);
                                                            modelArea.setDistrict_code(idkecs);
                                                            modelArea.setDistrict(kecamatans);
                                                            modelArea.setSub_district_code(iddesas);
                                                            modelArea.setSub_district(desas);
                                                            model.setArea(modelArea);
                                                            model.setAddress(alamat);
                                                            model.setPassword(password);
                                                            modelLahan.setMt1(mt1);
                                                            modelLahan.setMt2(mt2);
                                                            modelLahan.setMt3(mt3);
                                                            model.setLuasLahan(modelLahan);
//                                                            model.setSubsektor(subsektors);
//                                                            model.setKomoditas(komoditass);
                                                            presenter.daftarPetani(model);
                                                            Log.d("Result", new Gson().toJson(model));
                                                        }
                                                    })
                                                    .setNegativeButton("No", null)
                                                    .setMessage(Html.fromHtml(String.format(App.getApplication().getString(R.string.termCondition))))
//                                                    .setMessage(Html.fromHtml(String.format("<b><p>1. Membuka rekening Bank pada Bank yang telah bekerjasama dengan aplikasi Kartu Petani Berjaya ( Bank Lampung, Bank Mandiri, Bank BRI dan Bank BNI ). </p></b>")))
                                                    .show();

                                        } else TopSnakbar.showWarning(this,
                                                "Password anda tidak sama");
                                    else TopSnakbar.showWarning(this,
                                            "Harap isi password anda");
                                else TopSnakbar.showWarning(this,
                                        "Harap isi alamat anda sesuai e-Ktp");
                            else TopSnakbar.showWarning(this,
                                    "Harap pilih desa anda");
                        else TopSnakbar.showWarning(this,
                                "Harap pilih kecamatan anda");
                    else TopSnakbar.showWarning(this,
                            "Harap pilih kabupaten anda");
                else TopSnakbar.showWarning(this,
                        "Harap pilih provinsi anda");
            else TopSnakbar.showWarning(this,
                    "Harap isi NIK anda sesuai e-Ktp");
        else TopSnakbar.showWarning(this,
                "Harap isi nama lengkap anda sesuai e-Ktp");


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
        kabupaten = new LinkedHashMap<String, String>();
        kecamatan = new LinkedHashMap<String, String>();
        desa = new LinkedHashMap<String, String>();
        subsektor = new LinkedHashMap<String, String>();
        komoditas = new LinkedHashMap<String, String>();
        if (key.equals(key_kabupaten)) {
            for (Result kab : result) {
                System.out.println(kab.getNama());
                kabupaten.put(kab.getId_kab(), kab.getNama());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, kabupaten);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mKab.setAdapter(adapter);
            mKab.setOnItemSelectedListener(this);
        } else if (key.equals(key_kecamatan)) {
            for (Result kab : result) {
                System.out.println(kab.getNama());
                kecamatan.put(kab.getId_kec(), kab.getNama());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, kecamatan);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mKec.setAdapter(adapter);
            mKec.setOnItemSelectedListener(this);
        } else if (key.equals(key_desa)) {
            for (Result kab : result) {
                System.out.println(kab.getNama());
                desa.put(kab.getId_kel(), kab.getNama());
            }

            adapter = new LinkedHashMapAdapter<String, String>(this, android.R.layout.simple_spinner_item, desa);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mDesa.setAdapter(adapter);
            mDesa.setOnItemSelectedListener(this);
        } else if (key.equals(key_subsektor)) {
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
    public void onCreateSuccess() {
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Registrasi , Silahkan Masuk dengan nik dan password anda",string -> {
            this.goBack();
        });
    }

    @Override
    public void onCreateFailed(String rm) {
        SweetDialogs.commonWarning(this,"Gagal Memuat Permintaan" , rm , false);
    }



    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        TopSnakbar.showWarning(this, "Koneksi Anda Bermasalah !");
//        SweetDialogs.endpointError(this);
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
    public void goBack() {
        Intent a = new Intent(this, Login.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        this.goBack();
        super.onBackPressed();
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        Log.d("itemProv", item.toString());


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.mProv:
                Map.Entry<String, String> itemProv = (Map.Entry<String, String>) mProv.getSelectedItem();
                presenter.getArea(itemProv.getKey(), key_kabupaten);
                idprov = itemProv.getKey() ;
                provinsis = itemProv.getValue();
                Log.d("provinsi", itemProv.getKey());
                break;
            case R.id.mKab:
                Map.Entry<String, String> itemKab = (Map.Entry<String, String>) mKab.getSelectedItem();
                presenter.getArea(itemKab.getKey(), key_kecamatan);
                idkabs = itemKab.getKey();
                kabupatens = itemKab.getValue();
                Log.d("kabupaten", itemKab.getKey());
                break;
            case R.id.mKec:
                Map.Entry<String, String> itemKec = (Map.Entry<String, String>) mKec.getSelectedItem();
                presenter.getArea(itemKec.getKey(), key_desa);
                idkecs = itemKec.getKey();
                kecamatans = itemKec.getValue();
                Log.d("kecamatan", itemKec.getKey());
                break;
            case R.id.mDesa:
                Map.Entry<String, String> itemDesa = (Map.Entry<String, String>) mDesa.getSelectedItem();
                iddesas = itemDesa.getKey();
                desas = itemDesa.getValue();
                Log.d("Desa", itemDesa.getKey());
                break;
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
                presenter.getArea(itemSubsektor.getKey(), key_komoditas);
                break;
            case R.id.mKomoditas:
                Map.Entry<String, String> itemKomoditas = (Map.Entry<String, String>) mKomoditas.getSelectedItem();
                komoditass = itemKomoditas.getValue();

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
