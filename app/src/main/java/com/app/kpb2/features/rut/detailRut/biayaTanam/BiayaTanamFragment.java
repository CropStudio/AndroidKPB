package com.app.kpb2.features.rut.detailRut.biayaTanam;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.app.kpb2.R;
import com.app.kpb2.features.rut.model.BiayaTanam;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BiayaTanamFragment extends Fragment implements IBiayaTanamView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<BiayaTanam> biayaTanams = new ArrayList<>();
    private BiayaTanamAdapter adapter;
    public BiayaTanamFragment() {
        // Required empty public constructor
    }


    public static BiayaTanamFragment newInstance() {
        BiayaTanamFragment fragment = new BiayaTanamFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_biaya_tanam, container, false);
        ButterKnife.bind(this, view);
        if (biayaTanams.size() > 0) {
            this.setData(biayaTanams);
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view ;
    }

    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.clearFocus();
    }

    @Override
    public void setData(List<BiayaTanam> biayaTanams) {
        this.biayaTanams = biayaTanams;
        if (mRecyclerView != null) {
            if (mRecyclerView.getAdapter() == null) {
                adapter = new BiayaTanamAdapter(biayaTanams);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                this.initViews();
            }
        }
    }

}
