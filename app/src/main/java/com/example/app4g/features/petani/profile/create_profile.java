package com.example.app4g.features.petani.profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
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

import com.example.app4g.R;
import com.example.app4g.server.App;
import com.example.app4g.ui.CustomDrawable;
import com.example.app4g.ui.TopSnakbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.shuhart.stepview.StepView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import androidx.annotation.RequiresApi;
import butterknife.BindView;
import butterknife.ButterKnife;

public class create_profile extends AppCompatActivity implements IProfileView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private int currentStep = 0;

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
    Button mBtnDate;
    @BindView(R.id.mAgama)
    MaterialSpinner mAgama;
    @BindView(R.id.mPendTerakhir)
    MaterialSpinner mPendTerakhir;
    @BindView(R.id.mPerkawinan)
    MaterialSpinner mPerkawinan;
    @BindView(R.id.mNamaIbu)
    EditText mNamaIbu;

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
    CardView mCardAnak;
    @BindView(R.id.container)
    LinearLayout parent_anak;
    @BindView(R.id.mNamaAnak)
    TextView mNamaAnak;
    @BindView(R.id.SpinnerAnak)
    MaterialSpinner SpinnerAnak;
    @BindView(R.id.dateAnak)
    Button mBtnDateAnak;
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
    CardView mCardTanggungan;
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
    Button mBtnDateTanggungan;

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

    private Button deleteAnak;
    JSONObject dataTambahan = new JSONObject();
    JSONArray dataAnaks = new JSONArray();
    JSONArray dataTanggungans = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        this.initViews();
    }

    @Override
    public void initViews() {
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
        this.setDataPribadiVisible(true);

    }

    public void onSelect(View v) {
        JSONObject dataKeluarga = new JSONObject();

        try {
            dataKeluarga.put("namaKepalaKeluarga", mKepalaKeluarga.getText().toString());
            dataKeluarga.put("dataAnak", dataAnaks);
            dataKeluarga.put("tungganganLain", dataTanggungans);
            dataTambahan.put("dataTambahan", dataKeluarga);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(dataTambahan);

    }


    public void onAddField(View v) {
        if (v == mAddAnak) {
            if (!mNamaAnak.getText().toString().equals("") && !mTglLahirAnak.getText().toString().equals("") &&
                    !mTmptLahirAnak.getText().toString().equals("") && mPendTerakhirAnak.getSelectedIndex() != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.data_anak_field, null);
                final TextView mAnakKe = (TextView) rowView.findViewById(R.id.mAnakKe);
                final TextView mNamaAnaks = (TextView) rowView.findViewById(R.id.mNamaAnaks);
                final TextView mTglLahirAnaks = (TextView) rowView.findViewById(R.id.mTglLahirAnaks);
                final TextView mTmptLahirAnaks = (TextView) rowView.findViewById(R.id.mTmptLahirAnaks);
                final TextView mPendTerakhirAnaks = (TextView) rowView.findViewById(R.id.mPendTerakhirAnaks);
                deleteAnak = (Button) rowView.findViewById(R.id.deleteAnak);

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
            } else {
                TopSnakbar.showWarning(this, "mohon lengkapi data anak !");
            }
        }
        if (v == mAddTanggungan) {
            if (!mNamaLengkapTanggungan.getText().toString().equals("") && !mTglLahirTanggungan.getText().toString().equals("") &&
                    !mTmptLahirTanggungan.getText().toString().equals("") && mHubKeluarga.getSelectedIndex() != 0) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.data_tanggungan_field, null);
                final TextView mNama = (TextView) rowView.findViewById(R.id.mNama);
                final TextView mTglLahir = (TextView) rowView.findViewById(R.id.mTglLahir);
                final TextView mTmptLahir = (TextView) rowView.findViewById(R.id.mTmptLahir);
                final TextView mTxtHubKeluarga = (TextView) rowView.findViewById(R.id.mHubKeluarga);
                mNama.setText(mNamaLengkapTanggungan.getText().toString());
                mTglLahir.setText(mTglLahirTanggungan.getText().toString());
                mTmptLahir.setText(mTglLahirTanggungan.getText().toString());
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
            } else {
                TopSnakbar.showWarning(this, "mohon lengkapi data tanggungan lain anda !");
            }
        }

    }

    public void onDelete(View v) {
        if (v == deleteAnak) {
            TextView namaAnak = (TextView) ((View) v.getParent()).findViewById(R.id.mNamaAnaks);
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
        } else {
            TextView namaAnak = (TextView) ((View) v.getParent()).findViewById(R.id.mNama);
            parent_Tanggungan.removeView((View) v.getParent());
            for (int i = 0; i < dataTanggungans.length(); i++) {
                try {
                    if (namaAnak.getText().toString().equals(dataTanggungans.getJSONObject(i).getString("namaLengkap"))) {
                        dataTanggungans.remove(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        String kelamin = gender;
        String tmpt_lahir = mTempatLahir.getText().toString();
        String tgl_lahir = mTglLahir.getText().toString();
        String agama = mAgama.getText().toString();
        String pendidikan = mPendTerakhir.getText().toString();
        String perkawinan = mPerkawinan.getText().toString();
        String nama_ibu = mNamaIbu.getText().toString();


        if (!name.equals("")) {
            if (!nik.equals("")) {
                if (!alamat.equals("")) {
                    if (!tmpt_lahir.equals("")) {
                        if (!tgl_lahir.equals("")) {
                            if (!agama.equals("Agama")) {
                                if (!pendidikan.equals("Pendidikan Terakhir")) {
                                    if (!perkawinan.equals("Status Perkawinan")) {
                                        if (!nama_ibu.equals("")) {
                                            valid = true;
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
                }
                else if (radio_tanggungan.equals("TIDAK")) {
                    valid = true;
                }else TopSnakbar.showWarning(this,
                        "Harap isi semua kolom data tanggungan lain terlebih dahulu !");

            } else if(radio_anak.equals("TIDAK") && radio_tanggungan.equals("TIDAK")) {
                valid = true ;
            } else TopSnakbar.showWarning(this,
                    "Harap isi semua kolom data anak terlebih dahulu !");

        } else TopSnakbar.showWarning(this,
                "Harap isi nama kepala keluarga !");

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
        String kepalaKeluarga = mKepalaKeluarga.getText().toString();


        if (!kepalaKeluarga.equals("")) {
            valid = true;
        } else TopSnakbar.showWarning(this,
                "Harap isi semua kolom terlebih dahulu");

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
                        break;
                }
                break;
        }
    }
}
