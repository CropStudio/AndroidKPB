package com.app.kpb2.features.rut.detailRut.kalenderTanam;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.kpb2.R;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KalenderTanamFragment extends Fragment implements IKalenderTanamView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<JadwalUsahaTani> jadwals;
    private KalenderTanamAdapter adapter;
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
        if (jadwals != null) {
            this.setData(jadwals);
            this.initViews();
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view ;
    }

    @Override
    public void initViews(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    @Override
    public void setData(List<JadwalUsahaTani> jadwalUsahaTani) {
        this.jadwals = jadwalUsahaTani;
        if (mRecyclerView != null) {
            if (mRecyclerView.getAdapter() == null) {
                adapter = new KalenderTanamAdapter(jadwalUsahaTani.get(0).getJadwal());
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                this.initViews();
            }
        }
    }
}
