package com.app.kpb2.features.transaksi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.transaksi.TransaksiActivity;
import com.app.kpb2.features.transaksi.model.DetailTransaksi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentTransaksiList extends Fragment implements ITrackerListView, TransaksiListAdapter.OnItemSelected {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<DetailTransaksi> transaksis = new ArrayList<>();
    private TransaksiListAdapter adapter;


    public static FragmentTransaksiList newInstance() {
        FragmentTransaksiList fragment = new FragmentTransaksiList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transaksi_list, container, false);
        ButterKnife.bind(this, rootView);
        if (transaksis.size() > 0) {
            if (mRecyclerView.getAdapter() == null) {
                adapter = new TransaksiListAdapter(getActivity(), transaksis, this);
                mRecyclerView.setAdapter(adapter);
                this.initViews();
                System.out.println("baru");
            } else {
                System.out.println("update");
                adapter.swap(this.transaksis);
            }
        }

        return rootView;
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
    public void setData(List<DetailTransaksi> transaksis) {
        System.out.println(new Gson().toJson(transaksis));
        this.transaksis = transaksis;
//        if (mRecyclerView != null) {
//
//
//        }
    }

    @Override
    public void onHide() {
        ((TransaksiActivity) getActivity()).hideDetailList();
    }

}
