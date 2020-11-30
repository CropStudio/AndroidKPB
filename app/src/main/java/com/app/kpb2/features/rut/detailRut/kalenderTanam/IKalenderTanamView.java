package com.app.kpb2.features.rut.detailRut.kalenderTanam;

import com.app.kpb2.features.rut.model.JadwalUsahaTani;

import java.util.List;

public interface IKalenderTanamView {



    void setData(List<JadwalUsahaTani> jadwalUsahaTani);
    void initViews();
}
