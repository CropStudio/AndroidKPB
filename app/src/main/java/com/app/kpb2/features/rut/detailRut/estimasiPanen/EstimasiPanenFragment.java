package com.app.kpb2.features.rut.detailRut.estimasiPanen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.kpb2.R;
import com.app.kpb2.features.rut.model.EstimasiPanen;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EstimasiPanenFragment extends Fragment implements IEstimasiPanenView {
    @BindView(R.id.mGabahKering)
    TextView mGabahKering;
    @BindView(R.id.mGabahGiling)
    TextView mGabahGiling;
    @BindView(R.id.mBeras)
    TextView mBeras;
    EstimasiPanen estimasiPanen ;
    public static EstimasiPanenFragment newInstance() {
        EstimasiPanenFragment fragment = new EstimasiPanenFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimasi_panen, container, false);
        ButterKnife.bind(this,view);
        if (estimasiPanen  != null) {
            this.setData(estimasiPanen);
            this.initViews();
        }

        getActivity().getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        return view ;
    }

    @Override
    public void setData(EstimasiPanen estimasiPanen) {
        Log.d("estimasi" , ""+estimasiPanen.getBeras());
        this.estimasiPanen = estimasiPanen;
        if (this.estimasiPanen != null) {
//            this.initViews();
        }
    }

    @Override
    public void initViews() {
        mGabahKering.setText(String.valueOf(estimasiPanen.getGabahKeringPanen()));
        mGabahGiling.setText(String.valueOf(estimasiPanen.getGabahKeringGiling()));
        mBeras.setText(String.valueOf(estimasiPanen.getBeras()));
    }

}
