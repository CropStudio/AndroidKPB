package com.app.app4g.features.petani.profile.detailProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.petani.profile.model.profile;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.CustomDrawable;
import com.app.app4g.ui.SweetDialogs;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProfile extends AppCompatActivity implements IDetailProfileView, View.OnClickListener {
    SweetAlertDialog sweetAlertDialog;
    String nik;
    DetailProfilePresenter presenter;

    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.mNik)
    TextView mNik;
    @BindView(R.id.mAddress)
    TextView mAddress;
    @BindView(R.id.mNokk)
    TextView mNokk;

    @BindView(R.id.mTmptLahir)
    TextView mTmptLahir;
    @BindView(R.id.mPendTerakhir)
    TextView mPendTerakhir;
    @BindView(R.id.mNamaIbu)
    TextView mNamaIbu;
    @BindView(R.id.mKepalaKeluarga)
    TextView mKepalaKeluarga;
    @BindView(R.id.mStatusRumah)
    TextView mStatusRumah;
    @BindView(R.id.mStatusLahan)
    TextView mStatusLahan;
    @BindView(R.id.mFasilitasListrik)
    TextView mFasilitasListrik;
    @BindView(R.id.mJenisKendaraan)
    TextView mJenisKendaraan;
    @BindView(R.id.mJenisTernak)
    TextView mJenisTernak;
    @BindView(R.id.mKetTernak)
    TextView mKetTernak;
    @BindView(R.id.mPekerjanSelainTani)
    TextView mPekerjanSelainTani;
    @BindView(R.id.mPekerjanPenghuniRumah)
    TextView mPekerjanPenghuniRumah;
    @BindView(R.id.mAgama)
    TextView mAgama;
    @BindView(R.id.mTglLahir)
    TextView mTglLahir;
    @BindView(R.id.mPerkawinan)
    TextView mPerkawinan;
    @BindView(R.id.mGender)
    TextView mGender;
    @BindView(R.id.mLayoutAnak)
    LinearLayout mLayoutAnak;
    @BindView(R.id.LayoutTanggungan)
    LinearLayout mLayoutTanggungan;

    @BindView(R.id.collapseAnak)
    LinearLayout mCollapseAnak;

    @BindView(R.id.collapseTanggungan)
    LinearLayout mCollapseTanggungan;

    @BindView(R.id.imgCollapseAnak)
    ImageButton imgCollapseAnak;

    @BindView(R.id.imgCollapseTanggungan)
    ImageButton imgCollapseTanggungan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);
        ButterKnife.bind(this);
        presenter = new DetailProfilePresenter(this);
        LoginResponse mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        this.initViews();
        presenter.onGetProfile(nik);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

    }

    @Override
    public void initViews() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("Loading ...");
        mCollapseAnak.setOnClickListener(this);
        mCollapseTanggungan.setOnClickListener(this);
        this.hideAnakList();
        this.hideTanggunganList();
    }

    @Override
    public void onDataReady(profile profile) {
        List<com.app.app4g.features.petani.profile.model.profile.dataTambahan.DataAnak> anaks = new ArrayList<>();
        List<com.app.app4g.features.petani.profile.model.profile.dataTambahan.TanggunganLain> tanggungans = new ArrayList<>();
        anaks = profile.getDataTambahan().getDataAnak();
        tanggungans = profile.getDataTambahan().getTanggunganLain();
        mNama.setText(profile.getNama());
        mNik.setText(profile.getNik());
        mNokk.setText(profile.getNoKk());
        mAddress.setText(profile.getAddress());
        mTmptLahir.setText(profile.getTempatLahir());
        mPendTerakhir.setText(profile.getPendidikanTerakhir());
        mNamaIbu.setText(profile.getNamaIbuKandung());
        mKepalaKeluarga.setText(profile.getDataTambahan().getNamaKepalaKeluarga());
        mStatusRumah.setText(profile.getDataKepemilikan().getStatusRumah());
        mStatusLahan.setText(profile.getDataKepemilikan().getStatusLahan());
        mFasilitasListrik.setText(profile.getDataKepemilikan().getFasilitasListrik());
        mJenisKendaraan.setText(profile.getDataKepemilikan().getKendaraan());
        mJenisTernak.setText(profile.getDataKepemilikan().getTernak().getNamaTernak());
        mKetTernak.setText(profile.getDataKepemilikan().getTernak().getKeteranganTernak());
        mPekerjanSelainTani.setText(profile.getDataKepemilikan().getPekerjaan().getPekerjanSelainTani());
        mPekerjanPenghuniRumah.setText(profile.getDataKepemilikan().getPekerjaan().getPekerjanPenghuniRumah());
        mAgama.setText(profile.getAgama());
        mTglLahir.setText(profile.getTglLahir());
        mPerkawinan.setText(profile.getStatusPerkawinan());
        mGender.setText(profile.getJenisKelamin());
        Collections.sort(anaks, Collections.reverseOrder());
        for (com.app.app4g.features.petani.profile.model.profile.dataTambahan.DataAnak anak : anaks) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.data_anak_field, null);
            final TextView mAnakKe = rowView.findViewById(R.id.mAnakKe);
            final TextView mNamaAnaks = rowView.findViewById(R.id.mNamaAnaks);
            final TextView mTglLahirAnaks = rowView.findViewById(R.id.mTglLahirAnaks);
            final TextView mTmptLahirAnaks = rowView.findViewById(R.id.mTmptLahirAnaks);
            final TextView mPendTerakhirAnaks = rowView.findViewById(R.id.mPendTerakhirAnaks);
            final ImageButton deleteAnak = rowView.findViewById(R.id.deleteAnak);
            deleteAnak.setVisibility(View.GONE);
            mAnakKe.setText("Anak ke " + anak.getAnakKe());
            mNamaAnaks.setText(anak.getNamaAnak());
            mTglLahirAnaks.setText(anak.getTglLahirAnak());
            mTmptLahirAnaks.setText(anak.getTempatLahir());
            mPendTerakhirAnaks.setText(anak.getPendidikanSekarang());
            mNamaAnaks.setEnabled(false);
            mTglLahirAnaks.setEnabled(false);
            mTmptLahirAnaks.setEnabled(false);
            mPendTerakhirAnaks.setEnabled(false);
            mLayoutAnak.addView(rowView, 0);
        }
        for (com.app.app4g.features.petani.profile.model.profile.dataTambahan.TanggunganLain tanggungan : tanggungans) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.data_tanggungan_field, null);
            final TextView mNama = rowView.findViewById(R.id.mNama);
            final TextView mTglLahir = rowView.findViewById(R.id.mTglLahir);
            final TextView mTmptLahir = rowView.findViewById(R.id.mTmptLahir);
            final TextView mTxtHubKeluarga = rowView.findViewById(R.id.mHubKeluarga);
            final ImageButton deleteTanggungan = rowView.findViewById(R.id.deleteTanggungan);
            deleteTanggungan.setVisibility(View.GONE);
            mNama.setText(tanggungan.getNamaLengkap());
            mTglLahir.setText(tanggungan.getTglLahir());
            mTmptLahir.setText(tanggungan.getTempatLahir());
            mTxtHubKeluarga.setText(tanggungan.getHubungandDenganKepalaKeluarga());
            mNama.setEnabled(false);
            mTglLahir.setEnabled(false);
            mTmptLahir.setEnabled(false);
            mTxtHubKeluarga.setEnabled(false);
            mLayoutTanggungan.addView(rowView, 0);
        }


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
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            goToDashboard();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    public void onBack(View view) {
        this.goToDashboard();
    }

    @Override
    public void showAnakList() {
        imgCollapseAnak.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_black, 18, GoogleMaterial.Icon.gmd_keyboard_arrow_down
        ));
        mLayoutAnak.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAnakList() {

        mLayoutAnak.setVisibility(View.GONE);
        imgCollapseAnak.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_black, 18, GoogleMaterial.Icon.gmd_keyboard_arrow_up
        ));
    }

    @Override
    public void showTanggunganList() {
        imgCollapseTanggungan.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_black, 18, GoogleMaterial.Icon.gmd_keyboard_arrow_down
        ));
        mLayoutTanggungan.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTanggunganList() {

        mLayoutTanggungan.setVisibility(View.GONE);
        imgCollapseTanggungan.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_black, 18, GoogleMaterial.Icon.gmd_keyboard_arrow_up
        ));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.collapseAnak:
                if (mLayoutAnak.getVisibility() == View.VISIBLE)
                    this.hideAnakList();
                else this.showAnakList();
                break;

            case R.id.collapseTanggungan:
                if (mLayoutTanggungan.getVisibility() == View.VISIBLE)
                    this.hideTanggunganList();
                else this.showTanggunganList();
                break;
        }
    }
}
