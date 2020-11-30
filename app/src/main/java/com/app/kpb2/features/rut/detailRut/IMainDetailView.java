package com.app.kpb2.features.rut.detailRut;

import com.app.kpb2.features.rut.model.BiayaTanam;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;
import com.app.kpb2.features.rut.model.KebutuhanSaprotan;

import java.util.List;

public interface IMainDetailView {
    void initViews();

    void setData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, List<JadwalUsahaTani> jadwalUsahaTani);
}
