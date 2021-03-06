package com.app.kpb2.features.rut.detailRut;

import android.os.Bundle;

import com.app.kpb2.features.rut.model.JadwalUsahaTani;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.kpb2.R;
import com.app.kpb2.features.rut.detailRut.estimasiPanen.EstimasiPanenFragment;
import com.app.kpb2.features.rut.detailRut.kalenderTanam.KalenderTanamFragment;
import com.app.kpb2.features.rut.detailRut.saprotan.KebutuhanSaprotanFragment;
import com.app.kpb2.features.rut.detailRut.biayaTanam.BiayaTanamFragment;
import com.app.kpb2.features.rut.model.BiayaTanam;
import com.app.kpb2.features.rut.model.EstimasiPanen;
import com.app.kpb2.features.rut.model.HasilPascaPanen;
import com.app.kpb2.features.rut.model.KebutuhanSaprotan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainDetailFragment extends Fragment implements IMainDetailView {

    List<KebutuhanSaprotan> kebutuhanSaprotans = new ArrayList<>();
    List<BiayaTanam> biayaTanams = new ArrayList<>();
    String waktuTanam , tglPengambilan, tglTrf ;
    EstimasiPanen estimasiPanen ;
    HasilPascaPanen hasilPascaPanen ;

    @BindView(R.id.list_viewpager)
    ViewPager mListViewPager;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;

    public static MainDetailFragment newInstance() {
        MainDetailFragment fragment = new MainDetailFragment();
        return fragment;
    }

    public MainDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_detail, container, false);
        ButterKnife.bind(this, view);

        if (kebutuhanSaprotans.size() > 0) {
            this.setData(kebutuhanSaprotans, biayaTanams, waktuTanam ,tglPengambilan,tglTrf);
            this.initViews();
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view;
    }

    @Override
    public void initViews() {
        KebutuhanSaprotanFragment saprotanFragment = new KebutuhanSaprotanFragment();
        KalenderTanamFragment kalenderTanamFragment = new KalenderTanamFragment();
        BiayaTanamFragment biayaTanamFragment = new BiayaTanamFragment();
        EstimasiPanenFragment estimasiPanenFragment = new EstimasiPanenFragment();
        saprotanFragment.setData(kebutuhanSaprotans);
        kalenderTanamFragment.setData(waktuTanam , tglPengambilan , tglTrf);
        biayaTanamFragment.setData(biayaTanams);
//        estimasiPanenFragment.setData(estimasiPanen);
        FragmentManager cfManager= getChildFragmentManager();
        PageFramentAdapter adapter = new PageFramentAdapter(cfManager);
        adapter.addFragment(saprotanFragment,"Saprotan");
        adapter.addFragment(kalenderTanamFragment,"Kalender Tanam");
        adapter.addFragment(biayaTanamFragment,"Biaya Tanam");
//        adapter.addFragment(estimasiPanenFragment,"Estimasi Panen");

        mListViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mListViewPager);
    }


    @Override
    public void setData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, String waktuTanam, String tglPengambilan, String tglTrf) {
        this.kebutuhanSaprotans = kebutuhanSaprotans;
        this.biayaTanams = biayaTanams;
        this.waktuTanam = waktuTanam;
        this.tglPengambilan = tglPengambilan;
        this.tglTrf = tglTrf;

    }


}
