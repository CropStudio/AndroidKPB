package com.app.kpb2.features.rut.kebutuhanRut;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.kpb2.R;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.features.petani.registrasi.model.FormModel.Result;
import com.app.kpb2.server.App;
import com.app.kpb2.ui.TopSnakbar;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateKebutuhanRut extends AppCompatActivity implements ICreateKebutuhanRut, AdapterView.OnItemSelectedListener {
    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.mSubsektor)
    Spinner mSubsektor;

    @BindView(R.id.mKomoditas)
    Spinner mKomoditas;

    @BindView(R.id.LayoutLuasTanah)
    CardView LayoutLuasTanah;

    @BindView(R.id.LayoutBanyakKomoditas)
    CardView LayoutBanyakKomoditas;

    @BindView(R.id.mMt1)
    EditText mMt1;

//    @BindView(R.id.mMt2)
//    EditText mMt2;
//
//    @BindView(R.id.mMt3)
//    EditText mMt3;
    String subsektors, komoditass;

    public String key_subsektors = "subsektor";
    public String key_komoditas = "komoditas";
    CreateKebutuhanPresenter presenter;
    private LinkedHashMapAdapter<String, String> adapter;
    private LinkedHashMap<String, String> subsektor;
    private LinkedHashMap<String, String> komoditas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kebutuhan_rut);
        ButterKnife.bind(this);
        presenter = new CreateKebutuhanPresenter(this);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        this.initViews();

    }

    @Override
    public void initViews() {
        presenter.getSpinner("", key_subsektors);
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        mSubsektor.setOnItemSelectedListener(this);
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
//        SweetDialogs.endpointError(this);
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
}
