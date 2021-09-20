package com.app.kpb2.features.petani.transaksi_non_tunai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.Utils.LinkedHashMapAdapter;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.petani.MenuUtama;
import com.app.kpb2.features.petani.dashboard.Dashboard;
import com.app.kpb2.features.petani.noRekening.Rekening;
import com.app.kpb2.features.petani.profile.komoditas.model.Komoditas;
import com.app.kpb2.features.petani.profile.model.profile;
import com.app.kpb2.features.petani.suratkuasa_pernyataan.SuratKuasaActivity;
import com.app.kpb2.features.rut.RutActivity;
import com.app.kpb2.features.rut.RutAdapter;
import com.app.kpb2.features.rut.RutPageAdapter;
import com.app.kpb2.features.rut.RutPresenter;
import com.app.kpb2.features.rut.aset.AsetActivity;
import com.app.kpb2.features.rut.detailRut.MainDetailFragment;
import com.app.kpb2.features.rut.model.BarangTidakAda;
import com.app.kpb2.features.rut.model.BiayaTanam;
import com.app.kpb2.features.rut.model.KebutuhanSaprotan;
import com.app.kpb2.features.rut.model.Result;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;
import com.app.kpb2.ui.SweetDialogs;
import com.app.kpb2.ui.TopSnakbar;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiNonTunaiActivity extends AppCompatActivity implements ITransaksiNonTunaiView, RutAdapter.onRutSelected, AdapterView.OnItemSelectedListener {
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
    public RutAdapter adapter;
    SweetAlertDialog sweetAlertDialog;
    TransaksiNonTunaiPresenter presenter;
    Boolean LayoutStat = false;
    private String nik, token, nomorrekening, namaBank, idKab;
    Number idKios , idPoktan;
    View dialogView;
    AlertDialog.Builder dialog;
    private ArrayList<String> listBank = new ArrayList<>();
    Boolean statusSi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_non_tunai);
        ButterKnife.bind(this);
        presenter = new TransaksiNonTunaiPresenter(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Transaksi Non Tunai");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Toast.makeText(this, getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
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
        idKab = mProfile.getResult().getProfile().getArea().getCity_code();
        idPoktan = mProfile.getResult().getProfile().getId_poktan();
        this.initView();


    }

    @Override
    public void initView() {
        mLabel.setText("TRANSAKSI");
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        sweetAlertDialog.setCancelable(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
        presenter.getTransaksi(nik, token);

    }

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
    public void onDataReady(List<Result> result) {

        adapter = new RutAdapter(result, this, this);
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
        Intent a = new Intent(this, MenuUtama.class);
        startActivity(a);
        finish();
    }

    @Override
    public void refresh() {
        Intent a = new Intent(this, TransaksiNonTunaiActivity.class);
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
//
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
    public void onCekStatus(profile profile) {
        if (profile.getSi() != null)
            statusSi = profile.getSi().getStatus();
        else
            statusSi = null;
    }

    @Override
    public void onSetuju(Result rut) {
        if(namaBank != null && namaBank.equals("Bank LAMPUNG")){
            SweetDialogs.commonError(this , "Transaksi dengan Bank Lampung belum bisa di lakukan" , false);
        }else{
        if (namaBank != null && namaBank.equals("Bank BNI")) {
            if (statusSi == null || !statusSi) {
                SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "Silahkan menyetujui surat kuasa dan pernyataan", string -> this.goToSuratKuasa(rut));
            }
        }
        if (nomorrekening == null && nomorrekening.equals("")) {
            SweetDialogs.commonWarningWithIntent(this, "Data anda belum lengkap !", "anda harus mengisi nomor rekening dan memilih kios terlebih dahulu", string -> this.goToRekening());
        } else {
            rut.setNoRek(nomorrekening);
            rut.setBank(namaBank);
            rut.setNamaTransaksi(rut.getKomoditas() + "/" + rut.getMt());
            rut.setIdKabupaten(idKab);
            rut.setIdPoktan(String.valueOf(idPoktan));
            SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?", "dengan mensetujui RUT berarti anda melakukan transaksi pembelian saprotan dengan mendebit saldo rekening anda pastikan saldo anda cukup  .", "Berhasil memuat permintaan .", confirm -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
                }
            });

        }
}

    }

    @Override
    public void goToRekening() {
        Intent i = new Intent(this, Rekening.class);
        i.putExtra("className", this.getClass().getSimpleName());
        startActivity(i);
        finish();
    }

    @Override
    public void goToSuratKuasa(Result rut) {
        Intent i = new Intent(this, SuratKuasaActivity.class);
        i.putExtra("className", this.getClass().getSimpleName());
//        i.putExtra("dataMt", (Serializable) dataMt);
//        i.putExtra("idAset", idAset);
//        i.putExtra("nik", nik);
//        i.putExtra("idMt", rut.getMt());
        startActivity(i);
    }

    @Override
    public void onEditRut(Result rut) {

    }


    @Override
    public void onCreateSuccess(String rm) {
        SweetDialogs.commonSuccessWithIntent(this, "Berhasil Memuat Permintaan", view -> this.refresh());
    }


    @Override
    public void onCreateFailed(String rm, String rc, Result rut, List<BarangTidakAda> val) {
        Result ruts = rut;
        if (rc.equals(App.getApplication().getString(R.string.invalidToken))) {
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    rm);
        } else {
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
        }
    }


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

    private void DialogForm(Result rut) {
        listBank.add("Bank BNI");
        listBank.add("Bank MANDIRI");
        listBank.add("Bank BRI");
        listBank.add("Bank LAMPUNG");

        dialog = new AlertDialog.Builder(TransaksiNonTunaiActivity.this);
        dialogView = getLayoutInflater().inflate(R.layout.pilih_poktan_dialog, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("ANDA HARUS MEMILIH BANK TERLEBIH DAHULU !");

        final Spinner spinnerBank = (Spinner) dialogView.findViewById(R.id.mSpinnerPoktan);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(TransaksiNonTunaiActivity.this, android.R.layout.simple_spinner_item, listBank);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBank.setAdapter(adapter);
        spinnerBank.setOnItemSelectedListener(this);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.d("spiner", String.valueOf(spinnerBank.getSelectedItem().toString()));
//                Toast.makeText(TransaksiNonTunaiActivity.this, spinnerBank.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                rut.setNoRek(nomorrekening);
                rut.setBank(spinnerBank.getSelectedItem().toString());
                rut.setNamaTransaksi(rut.getKomoditas() + "/" + rut.getMt());
                rut.setIdKios(idKios);
                rut.setIdKabupaten(idKab);
                presenter.createRut(nik, token, rut);

            }
        });

        dialog.setNegativeButton("kembali", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                goToDashboard();
            }
        });

        dialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
