package com.app.kpb2.features.data_produksi.createProduksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.features.data_produksi.DataProduksiActivity;
import com.app.kpb2.features.data_produksi.model.DataProduksi;
import com.app.kpb2.features.petani.profile.komoditas.KomoditasPresenter;
import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.features.petani.profile.komoditas.model.ListProduksi;
import com.app.kpb2.features.rut.aset.createaset.ICreateAsetView;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProduksiActivity extends AppCompatActivity implements ICreateProduksiView,AdapterView.OnItemSelectedListener  {
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.mKomoditas)
    SearchableSpinner mKomoditas;
    @BindView(R.id.mSpinerHasilProduksi)
    SearchableSpinner mSpinerHasilProduksi;
    @BindView(R.id.mSpinnerSiklus)
    Spinner mSpinnerSiklus;
    @BindView(R.id.mJumlah)
    TextView mJumlah;

    @BindView(R.id.mSubmit)
    Button mSubmit;
    private LoginResponse mProfile;
    private String nik , token   ;
    private Number idPoktan ;
    CreateProduksiPresenter presenter ;
    public LinkedHashMapAdapter<String, String> adapter;
    public LinkedHashMap<String, String> komoditas;

    Map<List<ListProduksi>, String> spinnerValueMap = new HashMap<List<ListProduksi>, String>();
    List<StringWithTag> itemList = new ArrayList<StringWithTag>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_produksi);
        ButterKnife.bind(this);
        presenter = new CreateProduksiPresenter(this) ;
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tambah Subsektor");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDashboard();
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

    @Override
    public void onDataReady(List<Komoditas> result) {
        Log.d("datanya" ,new Gson().toJson(result));
        for (Komoditas value : result){
            if(value.getListProduksi().size()> 0)
                spinnerValueMap.put(value.getListProduksi(),value.getKomoditas());
        }

        for (Map.Entry<List<ListProduksi>, String> entry : spinnerValueMap.entrySet()) {
            List<ListProduksi> key = entry.getKey();
            String value = entry.getValue();
            itemList.add(new StringWithTag(value, key));
        }
//        Collections.sort(itemList);
        ArrayAdapter<StringWithTag> adapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mKomoditas.setAdapter(adapter);
        mKomoditas.setOnItemSelectedListener(this);
        mKomoditas.setTitle("Cari Komoditas");

    }

    @Override
    public void onBackPressed() {
        // ...
        this.goToDashboard();
    }
    @Override
    public void onNetworkError(String cause) {
        Log.e("errornya", cause);
        SweetDialogs.endpointError(this);
    }

    @Override
    public void goToDashboard() {
        startActivity(new Intent(this, DataProduksiActivity.class));
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
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        idPoktan =  mProfile.getResult().getProfile().getId_poktan();
        presenter.getKomoditas(nik , token);
        mSubmit.setOnClickListener(v -> this.onCreateProduksi());
        ArrayList<String> listSiklus = new ArrayList<>();
        listSiklus.add("3 bulan");
        listSiklus.add("4 bulan");
        listSiklus.add("6 bulan");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSiklus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSiklus.setAdapter(adapter);
    }

    @Override
    public void onCreateProduksi() {
        if(idPoktan != null && !idPoktan.equals(0)){
            if(!mJumlah.getText().toString().equals("")){
                String[] hasilnya = mSpinerHasilProduksi.getSelectedItem().toString().trim().split("\\(+");
                DataProduksi model = new DataProduksi();
                model.setKomoditas(mKomoditas.getSelectedItem().toString());
                model.setNik(nik);
                model.setIdPoktan(String.valueOf(idPoktan));
                model.setHasilProduksi(hasilnya[0]);
                model.setJumlahProduksi(mJumlah.getText().toString());
                model.setSiklus(mSpinnerSiklus.getSelectedItem().toString());
                model.setSatuan(hasilnya[1].substring(0, hasilnya[1].length() - 1));
                presenter.onCreateProduksi(nik, token, model);
            }else
//                Toast.makeText(this, "Jumlah Hasil produksi tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                TopSnakbar.showWarning(this , "Jumlah Hasil produksi tidak boleh kosong !");
        }else
            SweetDialogs.commonWarning(this, "Gagal Memuat Permintaan" , "Anda Belum terdaftar dipoktan mana pun silahkan hubungi bina tani" , false);


    }

    @Override
    public void onCreateSuksess(){
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan", string -> {
            this.goToDashboard();

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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        StringWithTag swt = (StringWithTag) parent.getItemAtPosition(position);
        ArrayList<String> spinnerHasilProduksi = new ArrayList<>() ;
        List<ListProduksi> hasilProduksi = (List<ListProduksi>) swt.tag;
        for(ListProduksi val : hasilProduksi){
            spinnerHasilProduksi.add(val.getNama() + "(" + val.getSatuan() + ")");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerHasilProduksi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinerHasilProduksi.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private static class StringWithTag {
        public String string;
        public List<ListProduksi> tag;

        public StringWithTag(String string, List<ListProduksi> tag) {
            this.string = string;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return string;
        }
    }
}
