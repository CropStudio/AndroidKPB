package com.app.app4g.features.petani.keuangan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.rut.model.BiayaTanam;
import com.app.app4g.features.rut.model.JadwalUsahaTani;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.app.app4g.features.rut.model.Result;
import com.google.gson.Gson;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class KeuanganAdapter extends RecyclerView.Adapter<KeuanganAdapter.ViewHolder> {
    public List<Result> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public KeuanganAdapter(List<Result> data, Activity context) {
        this.ruts = data;
        this.context = context;
    }

    @Override
    public KeuanganAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_keuangan, parent, false);
        KeuanganAdapter.ViewHolder viewHolder = new KeuanganAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final KeuanganAdapter.ViewHolder holder, final int position) {
        final Result rut = ruts.get(position);
        holder.mMt.setText(String.valueOf(position + 1));
        holder.mKomoditas.setText(rut.getKomoditas());
        holder.mTotalSaprotan.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalKebutuhanSaprotan())));
        holder.mTotalPendapatan.setText(Utils.convertRupiah(String.valueOf(rut.getPendapatanKotor())));
        holder.mTotalBudidaya.setText(Utils.convertRupiah(String.valueOf(rut.getSubTotalBiayaUsahaTani())));
        holder.mTotalKeuntungan.setText(Utils.convertRupiah(String.valueOf(rut.getSubPrediksiPendapatan())));

        for (KebutuhanSaprotan kebutuhanSaprotan : rut.getKebutuhanSaprotan()) {
            System.out.println(kebutuhanSaprotan.get_id());
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item_kebutuhan_saprotan, null);
            final TextView mNamaPupuk = rowView.findViewById(R.id.mNamaPupuk);
            final TextView mNamaBarang = rowView.findViewById(R.id.mNamaBarang);
            final TextView mJumlah = rowView.findViewById(R.id.mJumlah);
            final TextView mHargaSubsidi = rowView.findViewById(R.id.mHargaSubsidi);
            final TextView mHargaNormal = rowView.findViewById(R.id.mHargaNormal);
            final LinearLayout statusSubsidi = rowView.findViewById(R.id.statusSubsidi);
            statusSubsidi.setVisibility(View.GONE);
            mNamaPupuk.setText(kebutuhanSaprotan.getNama());
            if (kebutuhanSaprotan.getSelected() != null) {
                System.out.println(new Gson().toJson(kebutuhanSaprotan.getSelected().getHarga()));
                mNamaBarang.setText(kebutuhanSaprotan.getSelected().getNamaBarang());
                mHargaNormal.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSelected().getHarga())));
                mHargaSubsidi.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSelected().getHargaSubsidi())));
//            holder.mSubTotal.setText(Utils.convertRupiah(String.valueOf(kebutuhanSaprotan.getSubTotal())));
            } else {
                mNamaBarang.setText(kebutuhanSaprotan.getListBarang().get(0).getNamaBarang());
            }
            mJumlah.setText(String.valueOf(kebutuhanSaprotan.getJumlah()));
            holder.layoutKeubuthanSaprotan.addView(rowView, 0);
        }

        for (BiayaTanam biayatanam : rut.getGarapDanPemeliharaan()) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.item_biaya_tanam, null);
            final TextView mJenis = rowView.findViewById(R.id.mJenis);
            final TextView mJumlah = rowView.findViewById(R.id.mJumlah);
            final TextView mHarga = rowView.findViewById(R.id.mHarga);
            final LinearLayout status = rowView.findViewById(R.id.status);
            status.setVisibility(View.GONE);
            mJenis.setText(biayatanam.getJenis());
            mHarga.setText(Utils.convertRupiah(biayatanam.getHarga()));
            mJumlah.setText(biayatanam.getJumlah());
            holder.layoutBiayaTanam.addView(rowView, 0);
        }

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
        LinearLayout layoutKeubuthanSaprotan, layoutBiayaTanam;
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
            mStatusBar = view.findViewById(R.id.mStatusBar);
            layoutKeubuthanSaprotan = view.findViewById(R.id.layoutKeubuthanSaprotan);
            layoutBiayaTanam = view.findViewById(R.id.layoutBiayaTanam);

        }

    }
}
