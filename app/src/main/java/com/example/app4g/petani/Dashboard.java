package com.example.app4g.petani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app4g.R;
import com.example.app4g.petani.jatah.ListDataPupuk;
import com.example.app4g.session.SessionManager;
import com.example.app4g.webview.ECommerce;
import com.example.app4g.webview.InfoBeasiswa;
import com.example.app4g.webview.InfoKur;
import com.example.app4g.webview.KalenderTanam;
import com.example.app4g.webview.Kios;
import com.example.app4g.webview.PortalInformasi;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends Fragment {

    public SharedPreferences prefs;
    public SessionManager session;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        ButterKnife.bind(this, view);
        session = new SessionManager(getActivity());
        prefs = getActivity().getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dashboard);
    }

    @OnClick(R.id.cardPupuk)
    void infoPupuk(){
        Intent i = new Intent(getActivity(), ListDataPupuk.class);
        startActivity(i);
        getActivity().finish();
    }

    //OnClick function
    @OnClick(R.id.cardInfoBeasiswa)
    void infoBea(){
        Intent i = new Intent(getActivity(), InfoBeasiswa.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardInfoKur)
    void infoKur(){
        Intent i = new Intent(getActivity(), InfoKur.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardPortalInformasi)
    void portalInformasi(){
        Intent i = new Intent(getActivity(), PortalInformasi.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKios)
    void kios(){
        Intent i = new Intent(getActivity(), Kios.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKatam)
    void katam(){
        Intent i = new Intent(getActivity(), KalenderTanam.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardECommerce)
    void eCommerce(){
        Intent i = new Intent(getActivity(), ECommerce.class);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.cardKartuPetani)
    void kartuPetani(){
        Intent i = new Intent(getActivity(), KartuPetani.class);
        startActivity(i);
        getActivity().finish();
    }
}
