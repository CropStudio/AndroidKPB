package com.app.app4g.features.rut.detailRut.kalenderTanam;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.features.rut.model.JadwalUsahaTani;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KalenderTanamFragment extends Fragment implements IKalenderTanamView {
    @BindView(R.id.tgl1)
    TextView tgl1;
    @BindView(R.id.tgl2)
    TextView tgl2;
    @BindView(R.id.tgl3)
    TextView tgl3;
    @BindView(R.id.tgl4)
    TextView tgl4;
    @BindView(R.id.tgl5)
    TextView tgl5;
    @BindView(R.id.tgl6)
    TextView tgl6;
    @BindView(R.id.tgl7)
    TextView tgl7;
    @BindView(R.id.tgl8)
    TextView tgl8;
    @BindView(R.id.tgl9)
    TextView tgl9;
    @BindView(R.id.tgl10)
    TextView tgl10;
    @BindView(R.id.tgl11)
    TextView tgl11;
    @BindView(R.id.tgl12)
    TextView tgl12;
    @BindView(R.id.tgl13)
    TextView tgl13;
    @BindView(R.id.tgl14)
    TextView tgl14;
    @BindView(R.id.tgl15)
    TextView tgl15;
    List<JadwalUsahaTani> jadwalUsahaTani;
    public KalenderTanamFragment() {
        // Required empty public constructor
    }

    public static KalenderTanamFragment newInstance() {
        KalenderTanamFragment fragment = new KalenderTanamFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kalender_tanam, container, false);
        ButterKnife.bind(this,view);
        if (jadwalUsahaTani != null) {
            this.setData(jadwalUsahaTani);
            this.initViews();
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view ;
    }

    @Override
    public void initViews(){
//        tgl1.setText(kalenderTanam.getTglPengairanAirkeLahan());
//        tgl2.setText(kalenderTanam.getTglPengolahanLahan());
//        tgl3.setText(kalenderTanam.getTglPersemaian());
//        tgl4.setText(kalenderTanam.getTglPenanaman());
//        tgl5.setText(kalenderTanam.getTglPemeliharaan());
//        tgl6.setText(kalenderTanam.getTglPemupukanDasar());
//        tgl7.setText(kalenderTanam.getTglPenyemprotanHerbisida());
//        tgl8.setText(kalenderTanam.getTglPenyemprotanFungsida1DanZpt1());
//        tgl9.setText(kalenderTanam.getTglPemupukan1());
//        tgl10.setText(kalenderTanam.getTglPemupukan2());
//        tgl11.setText(kalenderTanam.getTglPenyemprotanFungsida2DanZpt2());
//        tgl12.setText(kalenderTanam.getTglPenyemprotanInsektisida1DanGandasliBuah1());
//        tgl13.setText(kalenderTanam.getTglPenyemprotanFungsida3DanZpt3());
//        tgl14.setText(kalenderTanam.getTglPenyemprotanInsektisida2DanGandasliBuah2());
//        tgl15.setText(kalenderTanam.getTglPanen());
    }

    @Override
    public void setData(List<JadwalUsahaTani> jadwalUsahaTani) {
//        Log.d("pengolahan", kalenderTanam.getTglPengolahanLahan());
        this.jadwalUsahaTani = jadwalUsahaTani;
        if (this.jadwalUsahaTani != null) {
//            this.initViews();
        }
    }
}
