package com.example.app4g.features.rut.detailRut;

import com.example.app4g.features.rut.model.BiayaTanam;
import com.example.app4g.features.rut.model.EstimasiPanen;
import com.example.app4g.features.rut.model.HasilPascaPanen;
import com.example.app4g.features.rut.model.KalenderTanam;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;

import java.util.List;

public interface IMainDetailView {
    void initViews();
    void setData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, KalenderTanam kalenderTanam, EstimasiPanen estimasiPanen, HasilPascaPanen hasilPascaPanen);


}
