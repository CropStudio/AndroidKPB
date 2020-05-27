package com.app.app4g.features.transaksi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.rut.model.BiayaTanam;
import com.app.app4g.features.rut.model.JadwalUsahaTani;
import com.app.app4g.features.rut.model.KebutuhanSaprotan;
import com.app.app4g.features.transaksi.model.DetailTransaksi;
import com.app.app4g.features.transaksi.model.Transaksi;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {
    public List<Transaksi> transaksis;
//    private List<Rut> filterList;;
    private TransaksiAdapter.onTransaksitSelected transaksiListener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onTransaksitSelected {
        void showList(List<DetailTransaksi> result);
    }

    public TransaksiAdapter(List<Transaksi> data, Activity context, onTransaksitSelected transaksiListener) {
        this.transaksis = data;
        this.context = context;
        this.transaksiListener = transaksiListener;
    }


    @Override
    public TransaksiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);

        TransaksiAdapter.ViewHolder viewHolder = new TransaksiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final TransaksiAdapter.ViewHolder holder, final int position) {
        final Transaksi transaksi = transaksis.get(position);
        holder.mCount.setText(String.valueOf(position+1));
        holder.mNamaTransaksi.setText(transaksi.getNamaTransaksi());
        holder.mIdTransaksi.setText(transaksi.getIdtransaksi());
        holder.mTime.setText(Utils.convertMongoDate(transaksi.getCreated_at()));
        holder.mTotal.setText(Utils.convertRupiah(String.valueOf(transaksi.getGrandtotal())));
        holder.mBtnDetail.setOnClickListener(view ->transaksiListener.showList(transaksi.getDetailTransaksi()));
    }



    @Override
    public int getItemCount() {
        if (transaksis == null)
            return 0;
        else
            return transaksis.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mNamaTransaksi , mIdTransaksi , mTime,mTotal,mCount ;
        final Button mBtnDetail ;
        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mNamaTransaksi = view.findViewById(R.id.mNamaTransaksi);
            mIdTransaksi = view.findViewById(R.id.mIdTransaksi);
            mTime = view.findViewById(R.id.mTime);
            mTotal = view.findViewById(R.id.mTotal);
            mBtnDetail = view.findViewById(R.id.mBtnDetail);
        }

    }
}
