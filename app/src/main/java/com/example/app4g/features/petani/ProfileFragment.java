package com.example.app4g.features.petani;

import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.Utils.GsonHelper;
import com.example.app4g.features.users.login.model.LoginResponse;
import com.example.app4g.server.App;
import com.example.app4g.session.Prefs;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initView();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_fragment);
    }

    public void initView() {
        mProfile = (LoginResponse) GsonHelper.parseGson(
                App.getPref().getString(Prefs.PREF_STORE_PROFILE, ""),
                new LoginResponse()
        );
//        String user_photo = (mProfile.getResult().getUser_photo().contains(" "))
//                ? mProfile.getResult().getUser_photo() : mProfile.getResult().getUser_photo();
        String nik = (mProfile.getResult().getNik().contains(" "))
                ? mProfile.getResult().getNik() : mProfile.getResult().getNik();
        String nama = (mProfile.getResult().getNama().contains(" "))
                ? mProfile.getResult().getNama() : mProfile.getResult().getNama();
        String no_hp = (mProfile.getResult().getNo_hp().contains(" "))
                ? mProfile.getResult().getNo_hp() : mProfile.getResult().getNo_hp();
        String alamat = (mProfile.getResult().getAlamat().contains(" "))
                ? mProfile.getResult().getAlamat() : mProfile.getResult().getAlamat();
        String kecamatan = (mProfile.getResult().getKecamatan().contains(" "))
                ? mProfile.getResult().getKecamatan() : mProfile.getResult().getKecamatan();
        String kabupaten = (mProfile.getResult().getKabupaten().contains(" "))
                ? mProfile.getResult().getKabupaten() : mProfile.getResult().getKabupaten();
        String provinsi = (mProfile.getResult().getProvinsi().contains(" "))
                ? mProfile.getResult().getProvinsi() : mProfile.getResult().getProvinsi();
        String namaPoktan = (mProfile.getResult().getNama_poktan().contains(" "))
                ? mProfile.getResult().getNama_poktan() : mProfile.getResult().getNama_poktan();
        String mt1 = (mProfile.getResult().getMt1().contains(" "))
                ? mProfile.getResult().getMt1() : mProfile.getResult().getMt1();
        String mt2 = (mProfile.getResult().getMt2().contains(" "))
                ? mProfile.getResult().getMt2() : mProfile.getResult().getMt2();
        String mt3 = (mProfile.getResult().getMt3().contains(" "))
                ? mProfile.getResult().getMt3() : mProfile.getResult().getMt3();

        mNik.setText(nik);
        mNama.setText(nama);
        mPhone.setText(no_hp);
        mAddress.setText(alamat + ", KEC. " + kecamatan + ", KAB. " + kabupaten + ", " + provinsi);
        mPoktan.setText(namaPoktan);
        mMt1.setText(mt1);
        mMt2.setText(mt2);
        mMt3.setText(mt3);
        mAddress.setEnabled(false);
        mPhone.setEnabled(false);
        mPoktan.setEnabled(false);
    }
}
