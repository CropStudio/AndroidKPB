package com.app.app4g.features.rut.detailRut.saprotan;

import com.app.app4g.features.rut.model.KebutuhanSaprotan;

import java.util.List;

public interface IKebutuhanSaprotanView {
    void initViews();

    void setData(List<KebutuhanSaprotan> kebutuhanSaprotans);
}
