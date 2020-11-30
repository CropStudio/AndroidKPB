package com.app.kpb2.features.rut.detailRut.saprotan;

import com.app.kpb2.features.rut.model.KebutuhanSaprotan;

import java.util.List;

public interface IKebutuhanSaprotanView {
    void initViews();

    void setData(List<KebutuhanSaprotan> kebutuhanSaprotans);
}
