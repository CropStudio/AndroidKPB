package com.app.app4g.features.transaksi.fragment;

/**
 * Created by hynra [github.com/hynra] on 04/04/2018.
 */

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.app4g.R;
import com.app.app4g.Utils.Utils;
import com.app.app4g.features.transaksi.model.DetailTransaksi;

import java.util.List;

public class TransaksiListAdapter extends RecyclerView.Adapter<TransaksiListAdapter.ViewHolder> {

    public interface OnItemSelected {
        void onHide();
    }

    private List<DetailTransaksi> transaksis;
    private OnItemSelected listener;
    private int color;
    private Context context;

    public TransaksiListAdapter(Context context,List<DetailTransaksi> trackers, OnItemSelected listener) {
        this.transaksis = trackers;
        this.listener = listener;
        this.color = color;
        this.context = context;
    }

    public void swap(List<DetailTransaksi> datas) {
        if (datas == null || datas.size() == 0)
            return;
        if (transaksis != null && transaksis.size() > 0)
            transaksis.clear();
        transaksis.addAll(datas);
        notifyDataSetChanged();

    }

    @Override
    public TransaksiListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_transaksi, parent, false);

        TransaksiListAdapter.ViewHolder viewHolder = new TransaksiListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TransaksiListAdapter.ViewHolder holder, final int position) {

        final DetailTransaksi transaksi = transaksis.get(position);
        holder.mCount.setText(String.valueOf(position+1));
        holder.mNama.setText(transaksi.getNama());
        if(transaksi.getSubsidi())
            holder.mStatus.setText("SUBSIDI");
        else
            holder.mStatus.setText("TIDAK SUBSIDI");
//        if(transaksi.getStatus() == 0) {
//            holder.mStatusPengiriman.setText("Belum di transfer");
//            holder.mIndicator.setImageResource(R.drawable.shape_indicator_unactive);
//        }else if(transaksi.getStatus() == 1){
//            holder.mStatusPengiriman.setText("Sudah di transfer");
//            holder.mIndicator.setImageResource(R.drawable.shape_indicator_active);
//        }

        holder.mStatusPengiriman.setText(transaksi.getStatusApp().getKeterangan());
        if(transaksi.getStatusApp().getId()==0)
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_unactive);
        else if(transaksi.getStatusApp().getId()==1 ||transaksi.getStatusApp().getId()==2 || transaksi.getStatusApp().getId()==3 )
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_orange);
        else  if(transaksi.getStatusApp().getId()==4)
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_blue);
        else  if(transaksi.getStatusApp().getId()==5)
            holder.mIndicator.setImageResource(R.drawable.shape_indicator_active);


        holder.mJumlah.setText(String.valueOf(transaksi.getJumlah()));
        holder.mHarga.setText(Utils.convertRupiah(String.valueOf(transaksi.getHarga())));
        holder.mTotal.setText(Utils.convertRupiah(String.valueOf(transaksi.getTotal())));


    }


    @Override
    public int getItemCount() {
        return transaksis.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mNama, mStatus, mJumlah, mHarga, mTotal,mCount,mStatusPengiriman;
        final ImageView mIndicator;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mCount = view.findViewById(R.id.mCount);
            mStatus = view.findViewById(R.id.mStatus);
            mJumlah = view.findViewById(R.id.mJumlah);
            mHarga = view.findViewById(R.id.mHarga);
            mTotal = view.findViewById(R.id.mTotal);
            mStatusPengiriman = view.findViewById(R.id.mStatusPengiriman);
            mIndicator = view.findViewById(R.id.indicator);

        }
    }


}