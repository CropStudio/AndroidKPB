package com.app.app4g.features.transaksi.fragment;

import com.app.app4g.features.transaksi.model.DetailTransaksi;

import java.util.List;

public interface ITrackerListView {
    void initViews();

    void setData(List<DetailTransaksi> transaksis);

    void onHide();
}
