package com.app.kpb2.features.rut;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.petani.noRekening.Rekening;
import com.app.kpb2.features.petani.profile.model.DataMt;
import com.app.kpb2.features.petani.profile.model.profile;
import com.app.kpb2.features.petani.suratkuasa_pernyataan.SuratKuasaActivity;
import com.app.kpb2.features.rut.aset.AsetActivity;
import com.app.kpb2.features.rut.detailRut.MainDetailFragment;
import com.app.kpb2.features.rut.editRut.EditRutActivity;
import com.app.kpb2.features.rut.model.BarangTidakAda;
import com.app.kpb2.features.rut.model.BiayaTanam;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;
import com.app.kpb2.features.rut.model.KebutuhanSaprotan;
import com.app.kpb2.features.rut.model.Poktan;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.delight.android.webview.AdvancedWebView;

public class RutActivity extends AppCompatActivity implements IRutView, RutAdapter.onRutSelected, AdapterView.OnItemSelectedListener {
    //    String TEST_PAGE_URL = "http://192.168.1.14:8080/rut/";
    String TEST_PAGE_URL = "http://kpb.lampungprov.go.id/#/rut/";
    private AdvancedWebView mWebView;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.list_layout)
    LinearLayout list_layout;
    @BindView(R.id.mLabel)
    TextView mLabel;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    private LinkedHashMapAdapter<String, String> adapterSpinner;
    private LinkedHashMap<String, String> poktan;
    //    @BindView(R.id.layoutCheckout)
//    RelativeLayout layoutCheckout;
//    @BindView(R.id.mBtnTutup)
//    Button mBtnTutup;
    //    @BindView(R.id.mSubtotal)
//    TextView mSubtotal;
//    @BindView(R.id.txtSubtotal)
//    TextView txtSubtotal;
//    @BindView(R.id.mCheckout)
//    Button mCheckout;
//    @BindView(R.id.Layouttoolbar)
//    RelativeLayout Layouttoolbar;

    long Subtotal;
    ProgressDialog pDialog;
    LoginResponse mProfile;
    public RutAdapter adapter;
    RutPresenter presenter;
    String idKec, nik, token, idDesa, nomorrekening, idAset, idKab, namaBank, id_poktan;
    Number tahun, idPoktan, idKios;
    //    int idKios;
    SweetAlertDialog sweetAlertDialog;
    Boolean LayoutStat = false;
    List<Poktan> listPoktan;
    List<KebutuhanSaprotan> models;
    List<DataMt> dataMt;
    JSONObject data = new JSONObject();
    JSONArray DataJsonMt = new JSONArray();
    public List<Result> items = null;
    String label;
    Boolean statusSi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut);
        ButterKnife.bind(this);
        presenter = new RutPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rencana Usaha");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        token = (mProfile.getResult().getToken().contains(" "))
                ? mProfile.getResult().getToken() : mProfile.getResult().getToken();
        nomorrekening = (mProfile.getResult().getProfile().getNomorRekening().contains(""))
                ? mProfile.getResult().getProfile().getNomorRekening() : mProfile.getResult().getProfile().getNomorRekening();
        if (!mProfile.getResult().getProfile().getNomorRekening().equals("")) {

            namaBank = (mProfile.getResult().getProfile().getBank().contains(" "))
                    ? mProfile.getResult().getProfile().getBank() : mProfile.getResult().getProfile().getBank();
        }
        if (mProfile.getResult().getProfile().getIdKios() != null) {
            idKios = mProfile.getResult().getProfile().getIdKios();
//            idKios = Integer.parseInt(String.valueOf(mProfile.getResult().getProfile().getIdKios())) ;
        }
        idDesa = (mProfile.getResult().getProfile().getArea().getSub_district_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getSub_district_code() : mProfile.getResult().getProfile().getArea().getSub_district_code();
        idKec = (mProfile.getResult().getProfile().getArea().getDistrict_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getDistrict_code() : mProfile.getResult().getProfile().getArea().getDistrict_code();
        idKab = (mProfile.getResult().getProfile().getArea().getCity_code().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getCity_code() : mProfile.getResult().getProfile().getArea().getCity_code();
        idPoktan = mProfile.getResult().getProfile().getId_poktan();
        this.initView();
        dataMt = (List<DataMt>) getIntent().getExtras().getSerializable("data");
        idAset = getIntent().getExtras().getString("_id");
        if (idPoktan == null || idPoktan.equals("")) {
            presenter.ListPoktan(nik, token, idDesa);

        } else {

            Log.d("DATAMTNYA", new Gson().toJson(dataMt));
//        System.out.println(namaBank);
            if (dataMt != null) {
                for (DataMt datas : dataMt) {
                    try {
                        DataJsonMt.put(
                                new JSONObject()
                                        .put("masaTanam", datas.getMasaTanam())
                                        .put("jumlahAset", datas.getJumlahAset())
                                        .put("subsektor", datas.getSubsektor())
                                        .put("namaKomoditas", datas.getNamaKomoditas())

                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    data.put("nik", nik);
                    data.put("idAsset", idAset);
                    data.put("dataPermt", DataJsonMt);
                    data.put("idDesa", idDesa);
                    data.put("idKecamatan", idKec);
                    data.put("idKabupaten", idKab);
                    data.put("id_poktan", idPoktan);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String susbs = dataMt.get(0).getSubsektor();
                if (susbs.equals("Perkebunan")) {
                    label = "Usia Tanam";
                    mLabel.setText(label);
                } else if (susbs.equals("Peternakan")) {
                    label = "Pembibitan / Penggemukan";
                    mLabel.setText(label);
                } else {
                    label = "Masa Tanam";
                    mLabel.setText(label);
                }
                Log.d("BODY", new Gson().toJson(data));
                presenter.getRut(nik, token, data.toString());
            }


        }
    }

    @Override
    public void initView() {
//        mCheckout.setOnClickListener(view-> this.onSubmit());
//        mCheckout.setEnabled(false);
//        mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
//        mBtnTutup.setOnClickListener(view -> this.HideDetailKebutuhan());
//        mBtnTutup.setOnClickListener(view -> Toast.makeText(this, "Maaf , Menu ini sedang dalam masa pengembangan", Toast.LENGTH_SHORT).show());
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

//    @Override
//    public void onSubmit() {
//        if (App.getPref().getString(Prefs.PREF_NO_REKENING, "").equals("")) {
//            startActivity(new Intent(this, Rekening.class));
//            finish();
//        } else {
//            Toast.makeText(this, "Maaf Menu ini masih dalam pengembangan", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void clearLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            activity.getWindow().setStatusBarColor(Color.WHITE);
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
    public void onCekStatus(profile profile) {
        if (profile.getSi() != null)
            statusSi = profile.getSi().getStatus();
        else
            statusSi = null;
    }


    @Override
    public void onDataReady(List<Result> result) {
        this.items = result;
        Log.d("Hasil ", new Gson().toJson(result));
//        System.out.println(result.get(0).getKebutuhanSaprotan().get(0).get_id());
        adapter = new RutAdapter(items, this, this);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        presenter.onGetProfile(nik);
    }

    @Override
    public void onRequestFailed(String rm) {
        Log.d("requestFail", rm);
        SweetDialogs.commonError(this, "Gagal Memuat Permintaan", rm, string -> {
            this.goToDashboard();
        });
    }

    @Override
    public void onNetworkError(String cause) {
        Log.d("Error", cause);
        SweetDialogs.endpointError(this);
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
    public void goToDashboard() {
        Intent a = new Intent(this, AsetActivity.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed() {
        // ...
        if (LayoutStat)
            HideDetailKebutuhan();
        else
            this.goToDashboard();
    }

    @Override
    public void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, String waktuTanam, String tglPengambilan, String tglTrf) {
        Log.d("waktuTanam", waktuTanam);
        Log.d("tglPengambilan", tglPengambilan);
        Log.d("tglTrf", tglTrf);
        list_layout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        final RutPageAdapter adapter = new RutPageAdapter(getSupportFragmentManager());
        MainDetailFragment fragmentMainDetail = new MainDetailFragment();
        fragmentMainDetail.setData(kebutuhanSaprotans, biayaTanams, waktuTanam, tglPengambilan, tglTrf);
        adapter.addFragment(fragmentMainDetail);
        mListViewPager.setAdapter(adapter);
        LayoutStat = true;
    }


    @Override
    public void onSetuju(Result rut) {
        if (namaBank != null && namaBank.equals("Bank BNI")) {
            if (statusSi == null || !statusSi) {
                SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "Silahkan menyetujui surat kuasa dan pernyataan", string -> this.goToSuratKuasa(rut));
            }
        } else {
            SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda harus mengisi nomor rekening dan memilih kios terlebih dahulu", string -> this.goToRekening());
        }
        if (rut.getUpdated()) {
            if (nomorrekening == null && nomorrekening.equals("")) {
                SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda harus mengisi nomor rekening dan memilih kios terlebih dahulu", string -> this.goToRekening());
            } else if (idKios == null || idKios != null && idKios.equals(0)) {
                SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda belum memilih kios", string -> this.goToRekening());
            } else {
                rut.setNoRek(nomorrekening);
                rut.setBank(namaBank);
                rut.setNamaTransaksi(rut.getKomoditas() + "/" + rut.getMt());
                rut.setIdKios(idKios);
                rut.setIdKabupaten(idKab);
                SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?", "dengan mensetujui RUT berarti anda melakukan transaksi pembelian saprotan dengan mendebit saldo rekening anda pastikan saldo anda cukup  ?", "Berhasil memuat permintaan .", confirm -> {
                    SweetDialogs.confirmDialog(this, "Total yang harus di bayar", Utils.convertRupiah(String.valueOf(rut.getSubTotalKebutuhanSaprotan())), "Berhasil memuat permintaan", confirms -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            SweetDialogs.validasiRekening(this, "Anda menggunakan rekening ", namaBank + "\n" + nomorrekening, "Berhasil memuat permintaan .",
                                    onConfirmRekening -> {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            SweetDialogs.validasiKur(this, "Apakah anda ingin menggunakan Kur?", "", "Berhasil memuat permintaan .",
                                                    onConfirmKur -> {
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                            rut.setStatusKur(true);
                                                            presenter.createRut(nik, token, rut);
//                                                            Log.d("DATANYA", new Gson().toJson(rut.getKebutuhanSaprotan().get(0).get_id()));
                                                        }
                                                    },
                                                    onRejectKur -> {
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                            rut.setStatusKur(false);
                                                            presenter.createRut(nik, token, rut);
                                                        }
                                                    });
                                        }
                                    },
                                    onUbahRekening -> {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            this.goToRekening();
                                        }
                                    });
                        }
                    });

                });
            }
        } else {
            TopSnakbar.showWarning(this, "Anda harus mengubah kebutuhan saprotan terlebih dahulu , silahkan klik tombol ubah");
        }
//            }
//        }
//        else {
//            if (rut.getUpdated()) {
//                if (nomorrekening.equals("")) {
//                    SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda harus mengisi nomor rekening dan memilih kios terlebih dahulu", string -> this.goToRekening());
//                } else if (idKios!=null && idKios.equals(0)) {
//                    SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda belum memilih kios", string -> this.goToRekening());
//                } else {
//                    rut.setNoRek(nomorrekening);
//                    rut.setBank(namaBank);
//                    rut.setNamaTransaksi(rut.getKomoditas() + "/" + rut.getMt());
//                    rut.setIdKios(idKios);
//                    rut.setIdKabupaten(idKab);
//                    SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?", "dengan mensetujui RUT berarti anda melakukan transaksi pembelian saprotan dengan mendebit saldo rekening anda pastikan saldo anda cukup  ?", "Berhasil memuat permintaan .", confirm -> {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                            SweetDialogs.validasiRekening(this, "Anda menggunakan rekening ", namaBank + "\n" + nomorrekening, "Berhasil memuat permintaan .",
//                                    onConfirmRekening -> {
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                            SweetDialogs.validasiKur(this, "Apakah anda ingin menggunakan Kur?", "", "Berhasil memuat permintaan .",
//                                                    onConfirmKur -> {
//                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                                            rut.setStatusKur(true);
//                                                            presenter.createRut(nik, token, rut);
////                                                            Log.d("DATANYA", new Gson().toJson(rut.getKebutuhanSaprotan().get(0).get_id()));
//                                                        }
//                                                    },
//                                                    onRejectKur -> {
//                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                                            rut.setStatusKur(false);
//                                                            presenter.createRut(nik, token, rut);
//                                                        }
//                                                    });
//                                        }
//                                    },
//                                    onUbahRekening -> {
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                            this.goToRekening();
//                                        }
//                                    });
//                        }
//                    });
//                }
//            } else {
//                TopSnakbar.showWarning(this, "Anda harus mengubah kebutuhan saprotan terlebih dahulu , silahkan klik tombol ubah");
//            }
//        }
    }

    @Override
    public void goToRekening() {
        Intent i = new Intent(this, Rekening.class);
        i.putExtra("className", this.getClass().getSimpleName());
        i.putExtra("data", (Serializable) dataMt);
        i.putExtra("_id", idAset);
        startActivity(i);
        finish();
    }

    @Override
    public void goToSuratKuasa(Result rut) {
        Intent i = new Intent(this, SuratKuasaActivity.class);
        i.putExtra("dataMt", (Serializable) dataMt);
        i.putExtra("idAset", idAset);
        i.putExtra("nik", nik);
        i.putExtra("idMt", rut.getMt());
        startActivity(i);
    }


    @Override
    public void onEditRut(Result rut) {
//        System.out.println(rut.getKebutuhanSaprotan());
        Intent i = new Intent(this, EditRutActivity.class);
        i.putExtra("dataMt", (Serializable) dataMt);
        i.putExtra("idAset", idAset);
        i.putExtra("nik", nik);
        i.putExtra("idMt", rut.getMt());
        startActivity(i);
//        Intent i = new Intent(this , RutEditActivity.class);
//        i.putExtra("kebutuhanSaprotan", (Serializable) rut.getKebutuhanSaprotan());
//        startActivity(i);
//        finish();
    }

    @Override
    public void onCreateSuccess(String rm) {
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Memuat Permintaan", view -> this.recreate());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreateFailed(String rm, Result rut, List<BarangTidakAda> val) {
        Result ruts = rut;
        SweetDialogs.validasiListBarang(this, "Barang dibawah ini tidak ada, total harga kebutuhan saprotan akan disesuaikan. apakah anda ingin melanjutkan transaksi ?", val, "Berhasil memuat permintaan .",
                onConfirmKur -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        for (int i = 0; i < ruts.getKebutuhanSaprotan().size(); i++) {
                            for (BarangTidakAda value : val) {
                                if (ruts.getKebutuhanSaprotan().get(i).get_id().equals(value.getIdKebutuhanSaprotan()))
                                    ruts.getKebutuhanSaprotan().remove(i);
                            }
                        }
                        presenter.createRut(nik, token, ruts);
                    }
                });

//        Log.d("RESULTAPUS",new Gson().toJson(val));
    }

    //    @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void onCheckBox(int position) {
//        mSubtotal.setText(Utils.convertRupiah(String.valueOf(getSubtotal(position))));
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private long getSubtotal(int position) {
//        long subTotal = adapter.ruts.stream().filter(word -> word.isSelected == true).mapToLong(i -> i.getKebutuhanSaprotan().get(0).getSubTotal() + i.getKebutuhanSaprotan().get(1).getSubTotal() + i.getKebutuhanSaprotan().get(2).getSubTotal()).sum();
//        if (subTotal != 0) {
//            mCheckout.setEnabled(true);
//            mCheckout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
//        } else {
//            mCheckout.setEnabled(false);
//            mCheckout.setBackgroundColor(getResources().getColor(R.color.grey));
//        }
//        return subTotal;
//    }
//
    @Override
    public void HideDetailKebutuhan() {
        LayoutStat = false;
        list_layout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (LayoutStat)
                HideDetailKebutuhan();
            else
                this.goToDashboard();
        }
        return false;
        // Disable back button..............
    }

    @Override
    protected void onDestroy() {
//        hideLoadingIndicator();
        super.onDestroy();
    }

    @Override
    public void onListPoktanReady(List<Poktan> result) {
        poktan = new LinkedHashMap<String, String>();
        for (Poktan poktans : result) {
            poktan.put(poktans.get_id(), poktans.getNama());
        }

        this.DialogForm();
    }

    private void DialogForm() {

        dialog = new AlertDialog.Builder(RutActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.pilih_poktan_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("ANDA HARUS MENGISI POKTAN TERLEBIH DAHULU !");

        final Spinner spinnerPoktan = (Spinner) dialogView.findViewById(R.id.mSpinnerPoktan);
        adapterSpinner = new LinkedHashMapAdapter<String, String>(RutActivity.this, android.R.layout.simple_spinner_item, poktan);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPoktan.setAdapter(adapterSpinner);
        spinnerPoktan.setOnItemSelectedListener(this);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("spiner", String.valueOf(spinnerPoktan.getSelectedItem()));
                onCreatePoktan();
            }
        });

        dialog.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                goToDashboard();
            }
        });

        dialog.show();
    }

    @Override
    public void onCreatePoktan() {

        JSONObject dataRoot = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            data.put("id_poktan", id_poktan);
            dataRoot.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        presenter.onCreatePoktan(nik, token, dataRoot.toString());
    }

    @Override
    public void onCreatePoktanSuccess(LoginResponse profile) {
        presenter.storeProfile(profile);
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan", string -> {
            this.refresh();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Map.Entry<String, String> itemSubsektor = (Map.Entry<String, String>) parent.getSelectedItem();
        id_poktan = itemSubsektor.getKey();
        Log.d("NAMA POKTAN", id_poktan);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void refresh() {
        Intent i = new Intent(this, RutActivity.class);
        i.putExtra("data", (Serializable) dataMt);
        i.putExtra("_id", idAset);
        startActivity(i);
        finish();
    }
}
