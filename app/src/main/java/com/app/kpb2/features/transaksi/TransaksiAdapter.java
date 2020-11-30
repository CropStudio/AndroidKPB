package com.app.kpb2.features.transaksi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.Utils.Utils;
import com.app.kpb2.features.transaksi.model.DetailTransaksi;
import com.app.kpb2.features.transaksi.model.Transaksi;

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
        String namaTransaksi  = transaksi.getNamaTransaksi().replace("/"," Masa Tanam ");
        holder.mNamaTransaksi.setText(namaTransaksi);
        holder.mIdTransaksi.setText(transaksi.getIdtransaksi());
        holder.mTime.setText(Utils.convertMongoDate(transaksi.getCreated_at()));
        holder.mTotal.setText(Utils.convertRupiah(String.valueOf(transaksi.getGrandtotal())));
        holder.mBtnDetail.setOnClickListener(view ->transaksiListener.showList(transaksi.getDetailTransaksi()));
        if(transaksi.getStatusKur() != null) {
            if (transaksi.getStatusKur()) {
                holder.layoutKur.setVisibility(View.VISIBLE);
                if (transaksi.getVerifiedKur()) {
                    holder.indicator.setImageResource(R.drawable.shape_indicator_active);
                    holder.mStatuskur.setText("Kur disetujui");
                } else {
                    holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
                    holder.mStatuskur.setText("Proses pengajuan Kur");
                }
            } else
                holder.layoutKur.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        if (transaksis == null)
            return 0;
        else
            return transaksis.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mNamaTransaksi , mIdTransaksi , mTime,mTotal,mCount ,mStatuskur;
        final Button mBtnDetail ;
        final ImageView indicator ;
        final LinearLayout layoutKur;
        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mNamaTransaksi = view.findViewById(R.id.mNamaTransaksi);
            mIdTransaksi = view.findViewById(R.id.mIdTransaksi);
            mTime = view.findViewById(R.id.mTime);
            mTotal = view.findViewById(R.id.mTotal);
            mBtnDetail = view.findViewById(R.id.mBtnDetail);
            indicator = view.findViewById(R.id.indicator);
            mStatuskur = view.findViewById(R.id.mStatusKur);
            layoutKur = view.findViewById(R.id.layoutKur);
        }

    }
}
