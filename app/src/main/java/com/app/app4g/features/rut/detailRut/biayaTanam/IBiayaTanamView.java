package com.app.app4g.features.rut.detailRut.biayaTanam;

import com.app.app4g.features.rut.model.BiayaTanam;

import java.util.List;

public interface IBiayaTanamView {
    void initViews();

    void setData(List<BiayaTanam> biayaTanams);
}