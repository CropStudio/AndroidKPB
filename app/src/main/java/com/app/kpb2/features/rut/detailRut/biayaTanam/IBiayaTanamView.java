package com.app.kpb2.features.rut.detailRut.biayaTanam;

import com.app.kpb2.features.rut.model.BiayaTanam;

import java.util.List;

public interface IBiayaTanamView {
    void initViews();

    void setData(List<BiayaTanam> biayaTanams);
}
