package com.app.app4g.features.rut.detailRut.kalenderTanam;

import com.app.app4g.features.rut.model.JadwalUsahaTani;

import java.util.List;

public interface IKalenderTanamView {



    void setData(List<JadwalUsahaTani> jadwalUsahaTani);
    void initViews();
}
