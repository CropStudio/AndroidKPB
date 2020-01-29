package com.example.app4g.features.rut.detailRut.saprotan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app4g.R;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KebutuhanSaprotanFragment extends Fragment implements IKebutuhanSaprotanView{
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    List<KebutuhanSaprotan> kebutuhanSaprotans = new ArrayList<>();
    String JenisTanaman = "" ;
    private KebutuhanSaprotanAdapter adapter;
//    @BindView(R.id.mTabLayout)
//    TabLayout mTabLayout;
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KebutuhanSaprotanFragment newInstance() {
        KebutuhanSaprotanFragment fragment = new KebutuhanSaprotanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kebutuhan_saprotan, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter
        if (kebutuhanSaprotans.size() > 0) {
            this.setData(kebutuhanSaprotans);
        }
        return view;
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
    public void setData(List<KebutuhanSaprotan> kebutuhanSaprotans) {
        this.kebutuhanSaprotans = kebutuhanSaprotans;
        if (mRecyclerView != null) {
            if (mRecyclerView.getAdapter() == null) {
                adapter = new KebutuhanSaprotanAdapter(kebutuhanSaprotans);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                this.initViews();
            }
//            else {
//                adapter.swap(this.kebutuhanSaprotans);
//            }
        }
    }

}
