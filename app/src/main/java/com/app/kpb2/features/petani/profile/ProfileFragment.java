package com.app.kpb2.features.petani.profile;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.GsonHelper;
import com.app.kpb2.features.petani.profile.createprofile.CreateProfile;
import com.app.kpb2.features.users.login.model.LoginResponse;
import com.app.kpb2.server.App;
import com.app.kpb2.session.Prefs;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    ImageButton detailsProfile;
    @BindView(R.id.mEditProfile)
    ImageButton mEditProfile;
    String noKK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        ButterKnife.bind(this, view);
        mProfileImg.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.user_default_farmer));
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initView();

//        detailsProfile = view.findViewById(R.id.viewProfile);
//        detailsProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (noKK.equals("")) {
//                    SweetDialogs.commonWarningWithIntent(getActivity(), "Data Anda belum lengkap"  , "Anda harus melengkapi data terlebih dahulu !", string -> {
//                        goToUpdateProfile();
//                    });
//                } else {
//                    startActivity(new Intent(getActivity(), DetailProfile.class));
//                    getActivity().finish();
//                }
////                Toast.makeText(getActivity(), "Maaf menu ini sementara belum tersedia !", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    public void goToUpdateProfile() {
        startActivity(new Intent(getActivity(), CreateProfile.class));
        getActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_fragment);
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
