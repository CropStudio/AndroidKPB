package com.example.app4g.features.rut;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.features.rut.model.BiayaTanam;
import com.example.app4g.features.rut.model.EstimasiPanen;
import com.example.app4g.features.rut.model.HasilPascaPanen;
import com.example.app4g.features.rut.model.KalenderTanam;
import com.example.app4g.features.rut.model.KebutuhanSaprotan;
import com.example.app4g.features.rut.model.Rut;

import java.util.List;

public class RutAdapter extends RecyclerView.Adapter<RutAdapter.ViewHolder> {
    public List<Rut> ruts;
//    private List<Rut> filterList;;
    private RutAdapter.onRutSelected rutListener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onRutSelected {
        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, KalenderTanam kalenderTanams , EstimasiPanen estimasiPanen , HasilPascaPanen hasilPascaPanen);
        void onCheckBox(int position);
    }

    public RutAdapter(List<Rut> data, Activity context, onRutSelected rutListener) {
        this.ruts = data;
        this.context = context;
        this.rutListener = rutListener;
    }


    @Override
    public RutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rut, parent, false);

        RutAdapter.ViewHolder viewHolder = new RutAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RutAdapter.ViewHolder holder, final int position) {
        final Rut rut = ruts.get(position);
//        holder.itemView.setOnClickListener(view -> rutListener.onSelect(rut));
        holder.mMt.setText("Masa Tanam "+rut.getMasaTanam());
        holder.mJenisTanaman.setText(rut.getJenisTanaman());
        holder.mStatus.setText(rut.getStatus());
//        holder.mBtnKebutuhan.setOnClickListener(view->holder.ShowDetailKebutuhan(rut.getKebutuhanSaprotan()));
//        holder.mBtnTutup.setOnClickListener(view->holder.HideDetailKebutuhan());
        holder.mBtnKebutuhan.setOnClickListener(view->rutListener.onDetailData(rut.getKebutuhanSaprotan(),rut.getBiayaTanam(),rut.getKalenderTanam(),rut.getKalenderTanam().getPerkiraanJumlahPanen(),rut.getKalenderTanam().getHasilPascaPanen()));
        holder.mCheckBox.setOnCheckedChangeListener(null);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rut.setSelected(true);
                } else {
                    rut.setSelected(false);
                }
//                rut.setQty(Integer.parseInt(holder.mQty.getNumber()));
                rutListener.onCheckBox(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mMt, mJenisTanaman, mStatus, mToko, mSubsidi;
        Button mBtnKebutuhan ;
        CheckBox mCheckBox;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
            mJenisTanaman = view.findViewById(R.id.mJenisTanaman);
            mCheckBox = view.findViewById(R.id.mCheckbox);
            mStatus = view.findViewById(R.id.mStatus);
            mBtnKebutuhan = view.findViewById(R.id.mBtnKebutuhan);
        }

    }
}
