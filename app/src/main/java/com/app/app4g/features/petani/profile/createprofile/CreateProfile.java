package com.app.app4g.features.petani.profile.createprofile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.users.login.Login;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.CustomDrawable;
import com.app.app4g.ui.SweetDialogs;
import com.app.app4g.ui.TopSnakbar;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.shuhart.stepview.StepView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfile extends AppCompatActivity implements IProfileView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private int currentStep = 0;
    SweetAlertDialog sweetAlertDialog;
    @BindView(R.id.step_view)
    StepView mStepView;
    @BindView(R.id.next_button)
    ImageButton mNextButton;
    @BindView(R.id.back_button)
    ImageButton mPrevButton;
    @BindView(R.id.mSubmit)
    Button mSubmit;
    //datapribadi layout
    @BindView(R.id.mDataPribadiLayout)
    LinearLayout mDataPribadiLayout;
    @BindView(R.id.mName)
    EditText mName;
    @BindView(R.id.mNik)
    EditText mNik;
    @BindView(R.id.mAddress)
    EditText mAddress;
    @BindView(R.id.mNoHp)
    EditText mNoHp;
    @BindView(R.id.mRadioGender)
    RadioGroup mRadioGender;
    @BindView(R.id.mMale)
    RadioButton mMale;
    @BindView(R.id.mFemale)
    RadioButton mFemale;
    @BindView(R.id.mTempatLahir)
    EditText mTempatLahir;
    @BindView(R.id.mTglLahir)
    EditText mTglLahir;
    @BindView(R.id.mBtnDate)
    ImageButton mBtnDate;
    @BindView(R.id.mAgama)
    MaterialSpinner mAgama;
    @BindView(R.id.mPendTerakhir)
    MaterialSpinner mPendTerakhir;
    @BindView(R.id.mPerkawinan)
    MaterialSpinner mPerkawinan;
    @BindView(R.id.mNamaIbu)
    EditText mNamaIbu;
    @BindView(R.id.mNoKK)
    EditText mNoKK;

    //data keluarga
    @BindView(R.id.mDataKeluargaLayout)
    LinearLayout mDataKeluargaLayout;
    @BindView(R.id.mKepalaKeluarga)
    EditText mKepalaKeluarga;
    @BindView(R.id.mRadioAnak)
    RadioGroup mRadioAnak;
    @BindView(R.id.mTidak)
    RadioButton mTidak;
    @BindView(R.id.mYa)
    RadioButton mYa;
    @BindView(R.id.mRadioTanggungan)
    RadioGroup mRadioTanggungan;
    @BindView(R.id.mTidakTanggungan)
    RadioButton mTidakTanggungan;
    //Anak
    @BindView(R.id.CardAnak)
    LinearLayout mCardAnak;
    @BindView(R.id.container)
    LinearLayout parent_anak;
    @BindView(R.id.mNamaAnak)
    TextView mNamaAnak;
    @BindView(R.id.SpinnerAnak)
    MaterialSpinner SpinnerAnak;
    @BindView(R.id.dateAnak)
    ImageButton mBtnDateAnak;
    @BindView(R.id.mTglLahirAnak)
    EditText mTglLahirAnak;
    @BindView(R.id.mTmptLahirAnak)
    EditText mTmptLahirAnak;
    @BindView(R.id.mPendTerakhirAnak)
    MaterialSpinner mPendTerakhirAnak;
    @BindView(R.id.mAddAnak)
    Button mAddAnak;

    //Tanggungan
    @BindView(R.id.CardTanggungan)
    LinearLayout mCardTanggungan;
    @BindView(R.id.containerTanggungan)
    LinearLayout parent_Tanggungan;
    @BindView(R.id.mNamaLengkapTanggungan)
    EditText mNamaLengkapTanggungan;
    @BindView(R.id.mTglLahirTanggungan)
    EditText mTglLahirTanggungan;
    @BindView(R.id.mTmptLahirTanggungan)
    EditText mTmptLahirTanggungan;
    @BindView(R.id.mHubKeluarga)
    MaterialSpinner mHubKeluarga;
    @BindView(R.id.mAddTanggungan)
    Button mAddTanggungan;
    @BindView(R.id.mBtnDateTanggungan)
    ImageButton mBtnDateTanggungan;

    //data kepemilikan
    @BindView(R.id.mDataKepemilikanLayout)
    LinearLayout mDataKepemilikanLayout;
    @BindView(R.id.mStatusRumah)
    MaterialSpinner mStatusRumah;
    @BindView(R.id.mStatusLahan)
    MaterialSpinner mStatusLahan;
    @BindView(R.id.mFasilitasListrik)
    MaterialSpinner mFasilitasListrik;
    @BindView(R.id.mJenisKendaraan)
    MaterialSpinner mJenisKendaraan;
    @BindView(R.id.mJenisTernak)
    MaterialSpinner mJenisTernak;
    @BindView(R.id.mKetTernak)
    EditText mKetTernak;
    @BindView(R.id.mPekerjanSelainTani)
    EditText mPekerjanSelainTani;
    @BindView(R.id.mPekerjanPenghuniRumah)
    EditText mPekerjanPenghuniRumah;

    JSONArray dataAnaks = new JSONArray();
    JSONArray dataTanggungans = new JSONArray();
    String kelamin;
    ProfilePresenter presenter;
    private LoginResponse mProfile;
    private String nik, nama, alamat, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);
        presenter = new ProfilePresenter(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
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
        nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
//        alamat = (mProfile.getResult().getAlamat().contains(" "))
//                ? mProfile.getResult().getAlamat() : mProfile.getResult().getAlamat();
        mNik.setText(nik);
        mNik.setEnabled(false);
        mName.setText(nama);
        mAddress.setText(alamat);
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText(App.getApplication().getString(R.string.loading));
        mAgama.setItems(App.getApplication().getResources().getStringArray(R.array.agama));
        mPendTerakhirAnak.setItems(App.getApplication().getResources().getStringArray(R.array.PendidikanAnak));
        mHubKeluarga.setItems(App.getApplication().getResources().getStringArray(R.array.HubKeluarga));
        SpinnerAnak.setItems(App.getApplication().getResources().getStringArray(R.array.anak));
        mPendTerakhir.setItems(App.getApplication().getResources().getStringArray(R.array.pendidikan));
        mPerkawinan.setItems(App.getApplication().getResources().getStringArray(R.array.perkawinan));
        mStatusRumah.setItems(App.getApplication().getResources().getStringArray(R.array.statusRumah));
        mStatusLahan.setItems(App.getApplication().getResources().getStringArray(R.array.statusLahan));
        mFasilitasListrik.setItems(App.getApplication().getResources().getStringArray(R.array.fasilitasListrik));
        mJenisKendaraan.setItems(App.getApplication().getResources().getStringArray(R.array.kendaraan));
        mJenisTernak.setItems(App.getApplication().getResources().getStringArray(R.array.ternak));
        mNextButton.setOnClickListener(this);
        mPrevButton.setOnClickListener(this);
        mPrevButton.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_white, 30,
                GoogleMaterial.Icon.gmd_keyboard_arrow_left
        ));
        mNextButton.setImageDrawable(CustomDrawable.googleMaterialDrawable(
                this, R.color.c_white, 30,
                GoogleMaterial.Icon.gmd_keyboard_arrow_right
        ));
        mRadioAnak.setOnCheckedChangeListener(this);
        mRadioTanggungan.setOnCheckedChangeListener(this);
        mTidak.setChecked(true);
        mTidakTanggungan.setChecked(true);
        mSubmit.setOnClickListener(this);
        this.setDataPribadiVisible(true);

    }

    public void onSelect(View v) {
//        JSONObject dataKeluarga = new JSONObject();
//        try {
//            dataKeluarga.put("namaKepalaKeluarga", mKepalaKeluarga.getText().toString());
//            dataKeluarga.put("dataAnak", dataAnaks);
//            dataKeluarga.put("tungganganLain", dataTanggungans);
//            dataTambahan.put("dataTambahan", dataKeluarga);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println(dataTambahan);

    }


    public void onAddField(View v) {
        if (v == mAddAnak) {
            if (!mNamaAnak.getText().toString().equals("") && !mTglLahirAnak.getText().toString().equals("") &&
                    !mTmptLahirAnak.getText().toString().equals("") && !mPendTerakhirAnak.getText().toString().equals("Pendidikan Sekarang") && !SpinnerAnak.getText().toString().equals("Anak Ke") ) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.data_anak_field, null);
                final TextView mAnakKe = rowView.findViewById(R.id.mAnakKe);
                final TextView mNamaAnaks = rowView.findViewById(R.id.mNamaAnaks);
                final TextView mTglLahirAnaks = rowView.findViewById(R.id.mTglLahirAnaks);
                final TextView mTmptLahirAnaks = rowView.findViewById(R.id.mTmptLahirAnaks);
                final TextView mPendTerakhirAnaks = rowView.findViewById(R.id.mPendTerakhirAnaks);

                mAnakKe.setText(SpinnerAnak.getText().toString());
                mNamaAnaks.setText(mNamaAnak.getText().toString());
                mTglLahirAnaks.setText(mTglLahirAnak.getText().toString());
                mTmptLahirAnaks.setText(mTmptLahirAnak.getText().toString());
                mPendTerakhirAnaks.setText(mPendTerakhirAnak.getText().toString());
                mNamaAnaks.setEnabled(false);
                mTglLahirAnaks.setEnabled(false);
                mTmptLahirAnaks.setEnabled(false);
                mPendTerakhirAnaks.setEnabled(false);
                try {
                    dataAnaks.put(
                            new JSONObject()
                                    .put("anakKe", mAnakKe.getText().toString().substring(8))
                                    .put("namaAnak", mNamaAnaks.getText().toString())
                                    .put("tempatLahir", mTmptLahirAnaks.getText().toString())
                                    .put("tglLahirAnak", mTglLahirAnaks.getText().toString())
                                    .put("pendidikanSekarang", mPendTerakhirAnaks.getText().toString())
                    );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parent_anak.addView(rowView, 0);
                Toast.makeText(this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
            } else {
                //TopSnakbar.showWarning(this, "mohon lengkapi data anak !");
                SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data anak dan pastkan anda sudah menekan tombol tambah data !" ,false);
            }
        }
        if (v == mAddTanggungan) {
            if (!mNamaLengkapTanggungan.getText().toString().equals("") && !mTglLahirTanggungan.getText().toString().equals("") &&
                    !mTmptLahirTanggungan.getText().toString().equals("") && !mHubKeluarga.getText().toString().equals("Hubungan Keluarga")) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.data_tanggungan_field, null);
                final TextView mNama = rowView.findViewById(R.id.mNama);
                final TextView mTglLahir = rowView.findViewById(R.id.mTglLahir);
                final TextView mTmptLahir = rowView.findViewById(R.id.mTmptLahir);
                final TextView mTxtHubKeluarga = rowView.findViewById(R.id.mHubKeluarga);
                mNama.setText(mNamaLengkapTanggungan.getText().toString());
                mTglLahir.setText(mTglLahirTanggungan.getText().toString());
                mTmptLahir.setText(mTmptLahirTanggungan.getText().toString());
                mTxtHubKeluarga.setText(mHubKeluarga.getText().toString());
                mNama.setEnabled(false);
                mTglLahir.setEnabled(false);
                mTmptLahir.setEnabled(false);
                mTxtHubKeluarga.setEnabled(false);
                try {
                    dataTanggungans.put(
                            new JSONObject()
                                    .put("namaLengkap", mNama.getText().toString())
                                    .put("tempatLahir", mTglLahir.getText().toString())
                                    .put("tglLahir", mTmptLahir.getText().toString())
                                    .put("hubungandDenganKepalaKeluarga", mTxtHubKeluarga.getText().toString())
                    );

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parent_Tanggungan.addView(rowView, 0);
                Toast.makeText(this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
            } else {
                SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data tanggungan lain dan pastkan anda sudah menekan tombol tambah data !" ,false);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onDeleteAnak(View v) {
        TextView namaAnak = ((View) v.getParent()).findViewById(R.id.mNamaAnaks);
        parent_anak.removeView((View) v.getParent());
        for (int i = 0; i < dataAnaks.length(); i++) {
            try {
                if (namaAnak.getText().toString().equals(dataAnaks.getJSONObject(i).getString("namaAnak"))) {
                    dataAnaks.remove(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onDeleteTunggangan(View v) {
        TextView name = ((View) v.getParent()).findViewById(R.id.mNama);
        parent_Tanggungan.removeView((View) v.getParent());
        for (int i = 0; i < dataTanggungans.length(); i++) {
            try {
                if (name.getText().toString().equals(dataTanggungans.getJSONObject(i).getString("namaLengkap"))) {
                    dataTanggungans.remove(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean isDataPribadiLayoutReady() {
        boolean valid = false;
        String gender = ((RadioButton) findViewById(mRadioGender.getCheckedRadioButtonId())).getText().toString();
        String name = mName.getText().toString();
        String nik = mNik.getText().toString();
        String alamat = mAddress.getText().toString();
        kelamin = gender;
        String tmpt_lahir = mTempatLahir.getText().toString();
        String tgl_lahir = mTglLahir.getText().toString();
        String agama = mAgama.getText().toString();
        String pendidikan = mPendTerakhir.getText().toString();
        String perkawinan = mPerkawinan.getText().toString();
        String nama_ibu = mNamaIbu.getText().toString();
        String no_kk = mNoKK.getText().toString();


        if (!name.equals("")) {
            if (!nik.equals("")) {
                if (!alamat.equals("")) {
                    if (!tmpt_lahir.equals("")) {
                        if (!tgl_lahir.equals("")) {
                            if (!agama.equals("Agama")) {
                                if (!pendidikan.equals("Pendidikan Terakhir")) {
                                    if (!perkawinan.equals("Status Perkawinan")) {
                                        if (!nama_ibu.equals("")) {
                                            if (!no_kk.equals("")) {
                                                valid = true;
                                            } else TopSnakbar.showWarning(this,
                                                    "Harap isi No Kartu Keluarga");
                                        } else TopSnakbar.showWarning(this,
                                                "Harap isi nama ibu !");
                                    } else TopSnakbar.showWarning(this,
                                            "Harap Pilih status perkawinan anda!");
                                } else TopSnakbar.showWarning(this,
                                        "Harap Pilih pendidikan terakhir anda!");
                            } else TopSnakbar.showWarning(this,
                                    "Harap Pilih agama anda!");
                        } else TopSnakbar.showWarning(this,
                                "Harap isi tgl lahir sesuai E-KTP!");
                    } else TopSnakbar.showWarning(this,
                            "Harap isi tempat lahir sesuai E-KTP!");
                } else TopSnakbar.showWarning(this,
                        "Harap isi alamat sesuai E-KTP!");
            } else TopSnakbar.showWarning(this,
                    "Harap isi nik lengkap sesuai E-KTP!");
        } else TopSnakbar.showWarning(this,
                "Harap isi nama lengkap sesuai E-KTP!");

        return valid;
    }

    @Override
    public void setDataPribadiVisible(boolean visible) {
        if (visible) {
            mDataPribadiLayout.setVisibility(View.VISIBLE);
            this.setDataKeluargaVisible(false);
            this.setDataKepemilikanVisible(false);

        } else mDataPribadiLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean isDataKeluargaReady() {
        boolean valid = false;
        String kepalaKeluarga = mKepalaKeluarga.getText().toString();
        String radio_anak = ((RadioButton) findViewById(mRadioAnak.getCheckedRadioButtonId())).getText().toString();
        String radio_tanggungan = ((RadioButton) findViewById(mRadioTanggungan.getCheckedRadioButtonId())).getText().toString();

        if (!kepalaKeluarga.equals("")) {
            if (radio_anak.equals("YA") && !dataAnaks.isNull(0)) {
                if (radio_tanggungan.equals("YA") && !dataTanggungans.isNull(0)) {
                    valid = true;
                } else if (radio_tanggungan.equals("TIDAK")) {
                    valid = true;
                } else  SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data tanggungan lain dan pastkan anda sudah menekan tombol tambah data !" ,false);

            } else if (radio_anak.equals("TIDAK")) {
                if (radio_tanggungan.equals("TIDAK")) {
                    valid = true;
                }
                else if (radio_tanggungan.equals("YA") && !dataTanggungans.isNull(0)) {
                    valid = true;
                } else  SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data tanggungan lain dan pastkan anda sudah menekan tombol tambah data !" ,false);

            } else SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data anak dan pastkan anda sudah menekan tombol tambah data !" ,false);

        } else SweetDialogs.commonWarning(this,"DATA TIDAK LENGKAP !","Mohon cek kembali kelengkapan data anak dan pastkan anda sudah menekan tombol tambah data !" ,false);

        return valid;
    }

    @Override
    public void setDataKeluargaVisible(boolean visible) {
        if (visible) {
            mDataKeluargaLayout.setVisibility(View.VISIBLE);
            this.setDataPribadiVisible(false);
            this.setDataKepemilikanVisible(false);

        } else mDataKeluargaLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean isDataKepemilikanReady() {
        boolean valid = false;
        String status_rumah = mStatusRumah.getText().toString();
        String status_lahan = mStatusLahan.getText().toString();
        String fasilitas_listrik = mFasilitasListrik.getText().toString();
        String kendaraan_hak_milik = mJenisKendaraan.getText().toString();
        String jenis_ternak = mJenisTernak.getText().toString();
        String keterangan_ternak = mKetTernak.getText().toString();


        if (!status_rumah.equals("Status Kepemilikan Rumah")) {
            if (!status_lahan.equals("Status Kepemilikan Lahan")) {
                if (!fasilitas_listrik.equals("Status Fasilitas Listrik")) {
                    if (!kendaraan_hak_milik.equals("Jenis Kepemilikan Kendaraan")) {
                        if (!jenis_ternak.equals("Jenis Kepemilikan Ternak")) {
                            valid = true;
                        } else TopSnakbar.showWarning(this,
                                "Harap pilih jenis ternak terlebih dahulu !");
                    } else TopSnakbar.showWarning(this,
                            "Harap pilih kendaraan hak milik terlebih dahulu !");
                } else TopSnakbar.showWarning(this,
                        "Harap fasilitas listrik terlebih dahulu !");
            } else TopSnakbar.showWarning(this,
                    "Harap pilih status lahan terlebih dahulu !");
        } else TopSnakbar.showWarning(this,
                "Harap status kepemilikan rumah terlebih dahulu !");

        return valid;
    }

    @Override
    public void setDataKepemilikanVisible(boolean visible) {
        if (visible) {
            mDataKepemilikanLayout.setVisibility(View.VISIBLE);
            this.setDataPribadiVisible(false);
            this.setDataKeluargaVisible(false);

        } else mDataKepemilikanLayout.setVisibility(View.GONE);
    }

    @Override
    public void onSubmit() {
        JSONObject dataRoot = new JSONObject();
        JSONObject dataUser = new JSONObject();
        JSONObject dataKeluarga = new JSONObject();
        JSONObject dataKepemilikan = new JSONObject();
        JSONObject ternak = new JSONObject();
        JSONObject pekerjaan = new JSONObject();
        try {

            dataKeluarga.put("namaKepalaKeluarga", mKepalaKeluarga.getText().toString());
            dataKeluarga.put("dataAnak", dataAnaks);
            dataKeluarga.put("tanggunganLain", dataTanggungans);

            dataKepemilikan.put("statusRumah", mStatusRumah.getText().toString());
            dataKepemilikan.put("statusLahan", mStatusLahan.getText().toString());
            dataKepemilikan.put("fasilitasListrik", mFasilitasListrik.getText().toString());
            dataKepemilikan.put("kendaraan", mJenisKendaraan.getText().toString());
            ternak.put("namaTernak", mJenisTernak.getText().toString());
            ternak.put("keteranganTernak", mKetTernak.getText().toString());
            dataKepemilikan.put("ternak", ternak);
            pekerjaan.put("pekerjanSelainTani", mPekerjanSelainTani.getText().toString());
            pekerjaan.put("pekerjanPenghuniRumah", mPekerjanPenghuniRumah.getText().toString());
            dataKepemilikan.put("pekerjaan", pekerjaan);

            dataUser.put("nama", mName.getText().toString());
            dataUser.put("nik", mNik.getText().toString());
            dataUser.put("jenisKelamin", kelamin);
            dataUser.put("address", mAddress.getText().toString());
            dataUser.put("no_hp", mNoHp.getText().toString());
            dataUser.put("tempatLahir", mTempatLahir.getText().toString());
            dataUser.put("tglLahir", mTglLahir.getText().toString());
            dataUser.put("noKk", mNoKK.getText().toString());
            dataUser.put("agama", mAgama.getText().toString());
            dataUser.put("pendidikanTerakhir", mPendTerakhir.getText().toString());
            dataUser.put("statusPerkawinan", mPerkawinan.getText().toString());
            dataUser.put("namaIbuKandung", mNamaIbu.getText().toString());
            dataUser.put("dataTambahan", dataKeluarga);
            dataUser.put("dataKepemilikan", dataKepemilikan);
            dataRoot.put("data", dataUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Gson gson = new Gson();
//        String Data = gson.toJson(dataRoot);
        System.out.println(dataRoot);
        presenter.onUpdateProfile(nik,token, dataRoot.toString(),mNoKK.getText().toString());


    }

    @Override
    public void onUpdateProfileSuccess(LoginResponse profile, String noKK) {
//        App.getPref().clear();
        presenter.storeNoKK(noKK);
        App.getPref().put(Prefs.PREF_STORE_PROFILE,new Gson().toJson(profile));
        SweetDialogs.commonSuccessWithIntent(this, "Data Berhasil Tersimpan" , string -> {
            this.goToDashBoard();
        });

    }

    @Override
    public void onUpdateProfileFailed(String rc , String rm){
        if(rc.equals(Prefs.DEFAULT_INVALID_TOKEN))
            SweetDialogs.commonInvalidToken(this, "Gagal Memuat Permintaan",
                    rm);
        else
            SweetDialogs.commonWarning(this,"Gagal Memuat Permintaan" , rm , false);
    }

    @Override
    public void goToDashBoard(){
        startActivity(new Intent(this, Login.class));
        finish();
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


    public void showDateDialog(View v) {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                if (v == mBtnDate)
                    mTglLahir.setText(dateFormatter.format(newDate.getTime()));
                else if (v == mBtnDateAnak)
                    mTglLahirAnak.setText(dateFormatter.format(newDate.getTime()));
                else if (v == mBtnDateTanggungan)
                    mTglLahirTanggungan.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
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
    public void onBackPressed() {
        super.onBackPressed();
        goToDashboard();
    }

    @Override
    public void goToDashboard() {
        Intent i = new Intent(this, MenuUtama.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                if (currentStep < mStepView.getStepCount() - 1) {
                    switch (currentStep) {
                        case 0:
                            if (this.isDataPribadiLayoutReady()) {
                                currentStep++;
                                mStepView.go(currentStep, true);
                                this.setDataKeluargaVisible(true);
                            }
                            break;

                        case 1:
                            if (this.isDataKeluargaReady()) {
                                currentStep++;
                                mStepView.go(currentStep, true);
                                this.setDataKepemilikanVisible(true);
                            }
                            break;

                    }
                } else {
                    if (this.isDataKepemilikanReady()) {
                        mStepView.done(true);
                        mSubmit.setVisibility(View.VISIBLE);
                        TopSnakbar.showSuccess(this, "Kolom sudah semua terisi, silahkan melanjutkan pendaftaran");
                    }
                }
                break;
            case R.id.back_button:
                if (currentStep > 0) {
                    currentStep--;
                    if (currentStep == 0)
                        this.setDataPribadiVisible(true);
                    else if (currentStep == 1)
                        this.setDataKeluargaVisible(true);
                    else if (currentStep == 2)
                        this.setDataKepemilikanVisible(true);


                }
                mStepView.done(false);
                mStepView.go(currentStep, true);
                break;

            case R.id.mSubmit:
                SweetDialogs.confirmDialog(this, "Apakah Anda Yakin ?" , "Pastikan semua data anda sudah benar!" , "Data Berhasil disimpan .", string -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        this.onSubmit();
                    }
                });
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.mRadioAnak:
                switch (checkedId) {
                    case R.id.mYa:
                        mCardAnak.setVisibility(View.VISIBLE);
                        break;
                    case R.id.mTidak:
                        mCardAnak.setVisibility(View.GONE);
                        parent_anak.removeAllViews();
                        try {
                            dataAnaks = new JSONArray("[]");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;

            case R.id.mRadioTanggungan:
                switch (checkedId) {
                    case R.id.mYaTanggungan:
                        mCardTanggungan.setVisibility(View.VISIBLE);
                        break;
                    case R.id.mTidakTanggungan:
                        mCardTanggungan.setVisibility(View.GONE);
                        parent_Tanggungan.removeAllViews();
                        try {
                            dataTanggungans = new JSONArray("[]");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
        }
    }
}
