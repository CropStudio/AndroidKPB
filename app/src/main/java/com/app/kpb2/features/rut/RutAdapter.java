package com.app.kpb2.features.rut;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.rut.model.BiayaTanam;
import com.app.kpb2.features.rut.model.JadwalUsahaTani;
import com.app.kpb2.features.rut.model.KebutuhanSaprotan;
import com.app.kpb2.features.rut.model.Result;

import java.util.List;

public class RutAdapter extends RecyclerView.Adapter<RutAdapter.ViewHolder> {
    public List<Result> ruts;
    //    private List<Rut> filterList;;
    private RutAdapter.onRutSelected rutListener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onRutSelected {
        void onDetailData(List<KebutuhanSaprotan> kebutuhanSaprotans, List<BiayaTanam> biayaTanams, String WaktuTanam , String tglPengambilan , String tglTrf);

        //        void onCheckBox(int position);
        void onSetuju(Result rut);

        void onEditRut(Result rut);
    }

    public RutAdapter(List<Result> data, Activity context, onRutSelected rutListener) {
        this.ruts = data;
        this.context = context;
        this.rutListener = rutListener;
    }

    @Override
    public RutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rut, parent, false);
        RutAdapter.ViewHolder viewHolder = new RutAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final RutAdapter.ViewHolder holder, final int position) {
        final Result rut = ruts.get(position);
//        Toast.makeText(context, context.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        holder.mMt.setText(rut.getMt());
        holder.mKomoditas.setText(rut.getKomoditas());
        holder.mTotalSaprotan.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalKebutuhanSaprotan())));
        holder.mTotalPendapatan.setText(Utils.convertRupiah(String.valueOf(rut.getPendapatanKotor())));
        holder.mTotalBudidaya.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalBiayaUsahaTani())));
        holder.mTotalKeuntungan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPrediksiPendapatan())));
        if(context.getClass().getSimpleName().equals("TransaksiNonTunaiActivity")) {
            holder.mBtnEdit.setVisibility(View.GONE);
        }
        if (rut.getStatusSetuju()) {
            holder.mBtnSetuju.setEnabled(false);
            holder.mBtnEdit.setEnabled(false);
//            holder.mBtnSetuju.setBackgroundTintMode(context.getResources().getColor(R.color.grey));
//            holder.mBtnSetuju.setBackgroundColor(ColorStateList.valueOf(context.getResources().getColor(R.color.disable_blue)));
            holder.mBtnSetuju.setBackgroundColor(context.getResources().getColor(R.color.disable_blue));
            holder.mBtnEdit.setBackgroundColor(context.getResources().getColor(R.color.disable_blue));
        } else {
            holder.mBtnSetuju.setEnabled(true);
            holder.mBtnEdit.setEnabled(true);
        }
        holder.mBtnKebutuhan.setOnClickListener(view -> rutListener.onDetailData(rut.getKebutuhanSaprotan(), rut.getGarapDanPemeliharaan(), rut.getWaktuTanam() , rut.getTanggalPengambilanPupuk() , rut.getTanggalTransfer()));
//        holder.mTotalSaprotan.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalSaprotan())));
//        holder.mTotalBudidaya.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalGarapDanPemeliharaan())));
//        holder.mTotalPendapatan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPendapatanKotor())));
//        holder.mTotalKeuntungan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPrediksiPendapatan())));
        //        holder.mCheckBox.setOnCheckedChangeListener(null);
//        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    rut.setSelected(true);
//                } else {
//                    rut.setSelected(false);
//                }
////                rut.setQty(Integer.parseInt(holder.mQty.getNumber()));
//                rutListener.onCheckBox(position);
//            }
//        if(rut.getStatus().equals(""))
//            holder.mBtnSetuju.setEnabled(true);
//        else {
//            holder.mBtnSetuju.setEnabled(false);
//            holder.mBtnSetuju.setBackgroundColor(context.getResources().getColor(R.color.grey));

//        }
        holder.mBtnSetuju.setOnClickListener(view->rutListener.onSetuju(rut));
        holder.mBtnEdit.setOnClickListener(view -> rutListener.onEditRut(rut));
    }

    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mMt, mTotalSaprotan, mTotalBudidaya, mTotalPendapatan, mTotalKeuntungan, mStatus, mToko, mSubsidi, mKomoditas;
        Button mBtnKebutuhan, mBtnSetuju, mBtnEdit;
        View mStatusBar;
        CheckBox mCheckBox;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
//            mJenisTanaman = view.findViewById(R.id.mJenisTanaman);
//            mCheckBox = view.findViewById(R.id.mCheckbox);
            mStatus = view.findViewById(R.id.mStatus);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            mTotalSaprotan = view.findViewById(R.id.mTotalSaprotan);
            mTotalBudidaya = view.findViewById(R.id.mTotalBudidaya);
            mTotalPendapatan = view.findViewById(R.id.mTotalPendapatan);
            mTotalKeuntungan = view.findViewById(R.id.mTotalKeuntungan);
            mBtnKebutuhan = view.findViewById(R.id.mBtnKebutuhan);
            mStatusBar = view.findViewById(R.id.mStatusBar);
            mBtnSetuju = view.findViewById(R.id.mBtnSetuju);
            mBtnEdit = view.findViewById(R.id.mBtnEdit);
        }

    }
}
