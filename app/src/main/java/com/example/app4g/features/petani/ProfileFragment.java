package com.example.app4g.features.petani;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.session.SessionManager;
import com.example.app4g.features.users.login.Login;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    public SharedPreferences prefs;
    public SessionManager session;

    String strId, strNik, strNotelp, strNama, strRole, strToken,strPotoPropil ,namaPoktan,alamat,mt1,mt2,mt3,kecamatan,kabupaten,kota,provinsi;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        ButterKnife.bind(this, view);

        prefs = getActivity().getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        isLogin();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_fragment);
    }

    public void isLogin(){
        // Session manager
        session = new SessionManager(getActivity());
        //Session Login
        if(session.isLoggedIn()){
            strId       = prefs.getString("id","");
            strNik      = prefs.getString("nik","");
            strNotelp   = prefs.getString("notelp", "");
            strNama     = prefs.getString("nama", "");
            strRole     = prefs.getString("role", "");
            strToken    = prefs.getString("token", "");
            strPotoPropil=prefs.getString("pp","");
            namaPoktan     = prefs.getString("nama_poktan","");
            alamat     = prefs.getString("alamat","");
            mt1     = prefs.getString("mt1","");
            mt2     = prefs.getString("mt2","");
            mt3     = prefs.getString("mt3","");
            kecamatan     = prefs.getString("kecamatan","");
            kabupaten     = prefs.getString("kabupaten","");
            kota     = prefs.getString("kota","");
            provinsi     = prefs.getString("provinsi","");

            mNik.setText(strNik);
            mNama.setText(strNama);
            mPhone.setText(strNotelp);
            mAddress.setText(alamat +", KEC. "+kecamatan+", KAB. "+kabupaten+", "+provinsi);
            mPoktan.setText(namaPoktan);
            mMt1.setText(mt1);
            mMt2.setText(mt2);
            mMt3.setText(mt3);
            mAddress.setEnabled(false);
            mPhone.setEnabled(false);
            mPoktan.setEnabled(false);

        }else{
            Intent a = new Intent(getActivity().getApplicationContext(), Login.class);
            startActivity(a);
            getActivity().finish();
        }
    }
}
