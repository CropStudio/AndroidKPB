package com.app.kpb2.features.rut.detailRut.kalenderTanam;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KalenderTanamFragment extends Fragment implements IKalenderTanamView {
    @BindView(R.id.mWaktuTanam)
    TextView mWaktuTanam;

    @BindView(R.id.mTglPengambilan)
    TextView mTglPengambilan;

    @BindView(R.id.mTglTrf)
    TextView mTglTrf;

    private KalenderTanamAdapter adapter;
    String waktuTanam , tglPengambilan, tglTrf ;
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
        if (waktuTanam != null || !waktuTanam.equals("") && tglTrf != null || !tglTrf.equals("") && tglPengambilan != null || !tglPengambilan.equals("")) {
            this.setData(waktuTanam , tglPengambilan , tglTrf);
            this.initViews();
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view ;
    }

    @Override
    public void initViews(){
        mWaktuTanam.setText(Utils.convertMongoDate(waktuTanam));
        mTglPengambilan.setText(Utils.convertMongoDate(tglPengambilan));
        mTglTrf.setText(Utils.convertMongoDate(tglTrf));
    }

    @Override
    public void setData(String waktuTnaam, String tglPengambilan, String tglTrf) {
        this.waktuTanam = waktuTnaam;
        this.tglPengambilan = tglPengambilan;
        this.tglTrf = tglTrf;
//        this.initViews();
    }
}
