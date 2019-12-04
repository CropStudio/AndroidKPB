package com.example.app4g.features.gubernur;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app4g.R;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.features.users.login.Login;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;
import com.example.app4g.ui.SweetDialogs;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragmentGubernur extends AppCompatActivity {

    @BindView(R.id.mNama)
    TextView mNama;
    @BindView(R.id.mNik)
    TextView mNik;
    @BindView(R.id.mPhone)
    TextView mPhone;

    @BindView(R.id.logout)
    TextView Keluar;
//    @BindView(R.id.mAddress)
//    TextView mAddress;
//    @BindView(R.id.mPoktan)
//    TextView mPoktan;
//    @BindView(R.id.mMt1)
//    TextView mMt1;
//    @BindView(R.id.mMt2)
//    TextView mMt2;
//    @BindView(R.id.mMt3)
//    TextView mMt3;
//    @BindView(R.id.profile_image)
//    CircleImageView mProfileImg;
    LoginResponse mProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_gubernur);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initView();
        return ;
    }

    public void initView() {
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        String no_hp = (mProfile.getResult().getNo_hp().contains(" "))
                ? mProfile.getResult().getNo_hp() : mProfile.getResult().getNo_hp();

        mNik.setText(nik);
        mNama.setText(nama);
        mPhone.setText(no_hp);
//        mPhone.setEnabled(false);
    }
    @OnClick(R.id.logout)
    public void Logout(){
       App.getPref().clear();
        Intent intent=new Intent(ProfileFragmentGubernur.this,Login.class);
        startActivity(intent);

    }
}
