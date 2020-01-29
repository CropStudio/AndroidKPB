package com.example.app4g.features.rut.detailRut;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app4g.R;
import com.example.app4g.features.rut.detailRut.estimasiPanen.EstimasiPanenFragment;
import com.example.app4g.features.rut.detailRut.kalenderTanam.KalenderTanamFragment;
import com.example.app4g.features.rut.detailRut.saprotan.KebutuhanSaprotanFragment;
import com.example.app4g.features.rut.detailRut.biayaTanam.BiayaTanamFragment;
import com.example.app4g.features.rut.model.BiayaTanam;
import com.example.app4g.features.rut.model.EstimasiPanen;
import com.example.app4g.features.rut.model.HasilPascaPanen;
import com.example.app4g.features.rut.model.KalenderTanam;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainDetailFragment extends Fragment implements IMainDetailView {

    List<KebutuhanSaprotan> kebutuhanSaprotans = new ArrayList<>();
    List<BiayaTanam> biayaTanams = new ArrayList<>();
    KalenderTanam kalenderTanam ;
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
            this.setData(kebutuhanSaprotans, biayaTanams,kalenderTanam,estimasiPanen,hasilPascaPanen);
            this.initViews();
        }
        return view;
    }

    @Override
    public void initViews() {
        KebutuhanSaprotanFragment saprotanFragment = new KebutuhanSaprotanFragment();
        KalenderTanamFragment kalenderTanamFragment = new KalenderTanamFragment();
        BiayaTanamFragment biayaTanamFragment = new BiayaTanamFragment();
        EstimasiPanenFragment estimasiPanenFragment = new EstimasiPanenFragment();
        saprotanFragment.setData(kebutuhanSaprotans);
        kalenderTanamFragment.setData(kalenderTanam);
        biayaTanamFragment.setData(biayaTanams);
        estimasiPanenFragment.setData(estimasiPanen);
        FragmentManager cfManager= getChildFragmentManager();
        PageFramentAdapter adapter = new PageFramentAdapter(cfManager);
        adapter.addFragment(saprotanFragment,"Saprotan");
        adapter.addFragment(kalenderTanamFragment,"Kalender Tanam");
        adapter.addFragment(biayaTanamFragment,"Biaya Tanam");
        adapter.addFragment(estimasiPanenFragment,"Estimasi Panen");

        mListViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mListViewPager);
    }


    @Override
    public void setData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams , KalenderTanam kalenderTanams, EstimasiPanen estimasiPanen, HasilPascaPanen hasilPascaPanen) {
        this.kebutuhanSaprotans = kebutuhanSaprotans;
        this.biayaTanams = biayaTanams;
        this.kalenderTanam = kalenderTanams;
        this.hasilPascaPanen = hasilPascaPanen;
        this.estimasiPanen = estimasiPanen;

    }


}
