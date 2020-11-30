package com.app.kpb2.features.transaksi.fragment;

import com.app.kpb2.features.transaksi.model.DetailTransaksi;

import java.util.List;

public interface ITrackerListView {
    void initViews();

    void setData(List<DetailTransaksi> transaksis);

    void onHide();
}
