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

import com.app.app4g.R;
import com.app.app4g.Utils.GsonHelper;
import com.app.app4g.features.petani.dashboard.Dashboard;
import com.app.app4g.features.petani.profile.createprofile.CreateProfile;
import com.app.app4g.features.users.login.model.LoginResponse;
import com.app.app4g.server.App;
import com.app.app4g.session.Prefs;

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
    @BindView(R.id.profile_image)
    CircleImageView mProfileImg;
    LoginResponse mProfile;
    Button detailsProfile;
    @BindView(R.id.mEditProfile)
    Button mEditProfile;
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
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
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
        goToUpdateProfile();
    }

    public void initView() {
        String no_hp;
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );

        noKK = App.getPref().getString(Prefs.PREF_NO_KK, "");
        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        if ((mProfile.getResult().getProfile().getNo_hp() != null)) {
            no_hp = (mProfile.getResult().getProfile().getNo_hp().contains(""))
                    ? mProfile.getResult().getProfile().getNo_hp() : mProfile.getResult().getProfile().getNo_hp();
        } else
            no_hp = "-";


        String alamat = (mProfile.getResult().getProfile().getAddress().contains(" "))
                ? mProfile.getResult().getProfile().getAddress() : mProfile.getResult().getProfile().getAddress();
        String kecamatan = (mProfile.getResult().getProfile().getArea().getDistrict().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getDistrict() : mProfile.getResult().getProfile().getArea().getDistrict();
        String kabupaten = (mProfile.getResult().getProfile().getArea().getCity().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getCity() : mProfile.getResult().getProfile().getArea().getCity();
        String provinsi = (mProfile.getResult().getProfile().getArea().getProvince().contains(" "))
                ? mProfile.getResult().getProfile().getArea().getProvince() : mProfile.getResult().getProfile().getArea().getProvince();
//        String namaPoktan = (mProfile.getResult().getNama_poktan().contains(" "))
//                ? mProfile.getResult().getNama_poktan() : mProfile.getResult().getNama_poktan();
//        String mt1 = (mProfile.getResult().getMt1().contains(" "))
//                ? mProfile.getResult().getMt1() : mProfile.getResult().getMt1();
//        String mt2 = (mProfile.getResult().getMt2().contains(" "))
//                ? mProfile.getResult().getMt2() : mProfile.getResult().getMt2();
//        String mt3 = (mProfile.getResult().getMt3().contains(" "))
//                ? mProfile.getResult().getMt3() : mProfile.getResult().getMt3();
//        String idKec = (mProfile.getResult().getIdKecamatan().contains(" "))
//                ? mProfile.getResult().getIdKecamatan() : mProfile.getResult().getIdKecamatan();
        mNik.setText(nik);
        mNama.setText(nama);
        mAddress.setText(alamat + ", KEC. " + kecamatan + ", " + kabupaten + ", " + provinsi);
        mPoktan.setText("-");
        mPhone.setText(no_hp);
//        mMt1.setText(mt1);
//        mMt2.setText(mt2);
//        mMt3.setText(mt3);
        mAddress.setEnabled(false);
        mPhone.setEnabled(false);
        mPoktan.setEnabled(false);

    }
}
