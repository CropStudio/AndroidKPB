package com.app.app4g.features.petani.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.MenuUtama;
import com.app.app4g.features.petani.dashboard.Dashboard;
import com.app.app4g.features.petani.noRekening.Rekening;
import com.app.app4g.features.petani.profile.createprofile.CreateProfile;
import com.app.app4g.features.petani.profile.detailProfile.DetailProfile;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;
import com.app.app4g.ui.SweetDialogs;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.mNik)
    TextView mNik;
    @BindView(R.id.mPhone)
    TextView mPhone;
    @BindView(R.id.mAddress)
    TextView mAddress;
    @BindView(R.id.mPoktan)
    TextView mPoktan;
    @BindView(R.id.mMt1)
    TextView mMt1;
    @BindView(R.id.mMt2)
    TextView mMt2;
    @BindView(R.id.mMt3)
    TextView mMt3;
    @BindView(R.id.mRekening)
    TextView mNorek;
    @BindView(R.id.mKios)
    TextView mKios;
    @BindView(R.id.mNamaBank)
    TextView mNamaBank;
    @BindView(R.id.mBtnUpdateRek)
    Button mBtnUpdateRek;
    @BindView(R.id.profile_image)
    CircleImageView mProfileImg;
    LoginResponse mProfile;
    Button detailsProfile;
    @BindView(R.id.mEditProfile)
    Button mEditProfile;
    @BindView(R.id.viewProfile)
    Button viewProfile;
    String noKK;
    @BindView(R.id.toolbar_default_in)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        mProfileImg.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.user_default_farmer));

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Data diri");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.color_default_blue));
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back_left));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MenuUtama.class));
                finish();
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
        initView();
    }

    public void goToUpdateProfile() {
        startActivity(new Intent(this, CreateProfile.class));
        finish();
    }

    @OnClick(R.id.mEditProfile)
    void onEditProfile() {
//        Toast.makeText(this, "Maaf menu ini belum tersedia", Toast.LENGTH_SHORT).show();
        goToUpdateProfile();
    }

    @OnClick(R.id.viewProfile)
    void onViewProfile() {
//        Toast.makeText(this, "Maaf menu ini belum tersedia", Toast.LENGTH_SHORT).show();
        if (noKK.equals("")) {
            SweetDialogs.commonWarningWithIntent(this, "Data Anda belum lengkap"  , "Anda harus melengkapi data terlebih dahulu !", string -> {
                goToUpdateProfile();
            });
        } else {
            startActivity(new Intent(this, DetailProfile.class));
            finish();
        }
    }

    public void initView() {
        String no_hp = "-";
        String nomorrekening= "-";
        String namaBank= "-";
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        noKK = App.getPref().getString(Prefs.PREF_NO_KK, "");
        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        String nama = (mProfile.getResult().getProfile().getNama().contains(" "))
                ? mProfile.getResult().getProfile().getNama() : mProfile.getResult().getProfile().getNama();
        if (mProfile.getResult().getProfile().getNo_hp() != null && !mProfile.getResult().getProfile().getNo_hp().equals(" ")) {
            no_hp = (mProfile.getResult().getProfile().getNo_hp().contains(""))
                    ? mProfile.getResult().getProfile().getNo_hp() : mProfile.getResult().getProfile().getNo_hp();

        } else
            no_hp = "-";

        String alamat =  mProfile.getResult().getProfile().getAddress();
        String kecamatan = mProfile.getResult().getProfile().getArea().getDistrict();
        String kabupaten = mProfile.getResult().getProfile().getArea().getCity();
        String provinsi =  mProfile.getResult().getProfile().getArea().getProvince();
        nomorrekening =  mProfile.getResult().getProfile().getNomorRekening();
        Number idKios = mProfile.getResult().getProfile().getIdKios();
        String namaKios = mProfile.getResult().getProfile().getNamaKios();
        if(idKios!=null)
            mKios.setText(String.valueOf(idKios));
        if (!mProfile.getResult().getProfile().getNomorRekening().equals("")) {

            namaBank = (mProfile.getResult().getProfile().getBank().contains(" "))
                    ? mProfile.getResult().getProfile().getBank() : mProfile.getResult().getProfile().getBank();
            mNamaBank.setText(namaBank);
            mNorek.setText(nomorrekening);
        }
        mNik.setText(nik);
        mNama.setText(nama);
        mAddress.setText(alamat + ", KEC. " + kecamatan + ", " + kabupaten + ", " + provinsi);
        mPoktan.setText("-");
        mPhone.setText(no_hp);
        mAddress.setEnabled(false);
        mPhone.setEnabled(false);
        mPoktan.setEnabled(false);
        mBtnUpdateRek.setOnClickListener(view -> this.goToUpdateRek());

    }

    void goToUpdateRek(){
        startActivity(new Intent(this, Rekening.class));
        finish();
    }
}
