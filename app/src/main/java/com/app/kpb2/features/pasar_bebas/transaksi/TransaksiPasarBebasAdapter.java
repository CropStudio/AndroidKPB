package com.app.kpb2.features.pasar_bebas.transaksi;

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
import com.app.kpb2.features.pasar_bebas.transaksi.model.Transaksis;

import java.util.List;

public class TransaksiPasarBebasAdapter extends RecyclerView.Adapter<TransaksiPasarBebasAdapter.ViewHolder> {
    public List<Transaksis> transaksis;
    //    private List<Rut> filterList;;
    private TransaksiPasarBebasAdapter.onTransaksitSelected transaksiListener;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;


    public interface onTransaksitSelected {
        void onDetailTransaksi(Transaksis result);
    }

    public TransaksiPasarBebasAdapter(List<Transaksis> data, Activity context, onTransaksitSelected transaksiListener) {
        this.transaksis = data;
        this.context = context;
        this.transaksiListener = transaksiListener;
    }


    @Override
    public TransaksiPasarBebasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);

        TransaksiPasarBebasAdapter.ViewHolder viewHolder = new TransaksiPasarBebasAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final TransaksiPasarBebasAdapter.ViewHolder holder, final int position) {
        final Transaksis transaksi = transaksis.get(position);
        holder.mCount.setText(String.valueOf(position + 1));
//        String namaTransaksi = transaksi.getNamaBarang();
        holder.mNamaTransaksi.setText("Nomor Transaksi");
        if(transaksi.getTransaction_status().getMetode().getPayment_type().equals("cstore")){
            holder.paymentType.setText(transaksi.getTransaction_status().getMetode().getPayment_type() +" = "+transaksi.getTransaction_status().getMetode().getStore());
        }else{
            holder.paymentType.setText(transaksi.getTransaction_status().getMetode().getPayment_type());
        }
        holder.mIdTransaksi.setText(transaksi.getOrder_id());


        holder.mTime.setText(Utils.convertMongoDate(transaksi.getStatusBarang().getCreated_at()));
//        holder.mTotal.setText(Utils.convertRupiah(String.valueOf(transaksi.getGross_amount())));
//        if(transaksi.getPayment_type().equals("")){
//
//        }
        long totalHarga = Long.parseLong(transaksi.getGross_amount()) - Integer.parseInt(transaksi.getIdUnique());
        holder.mTotal.setText(Utils.convertRupiah(String.valueOf(totalHarga)));
        holder.mBtnDetail.setOnClickListener(view -> transaksiListener.onDetailTransaksi(transaksi));

        if (transaksi.getTransaction_status().getStatus().equals("pending")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_unactive);
            holder.mStatuskur.setText("Tertunda");
        } else if (transaksi.getTransaction_status().getStatus().startsWith("Waiting")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatuskur.setText("Menunggu pembayaran");
        } else if (transaksi.getTransaction_status().getStatus().startsWith("cancel")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatuskur.setText("Dibatalkan");
        }
        else if (transaksi.getTransaction_status().getStatus().startsWith("deny")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatuskur.setText("Ditolak");
        }

        else if (transaksi.getTransaction_status().getStatus().startsWith("expire")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatuskur.setText("Kadaluarsa");
        }
        else if (transaksi.getTransaction_status().getStatus().startsWith("refund")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatuskur.setText("Dikembalikan");
        }else {
            holder.indicator.setImageResource(R.drawable.shape_indicator_active);
            holder.mStatuskur.setText("Transaksi Berhasil");
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

        final TextView mNamaTransaksi, mIdTransaksi, mTime, mTotal, mCount, mStatuskur , paymentType;
        final Button mBtnDetail;
        final ImageView indicator;
        final LinearLayout layoutKur;

        ViewHolder(View view) {
            super(view);
            mCount = view.findViewById(R.id.mCount);
            mNamaTransaksi = view.findViewById(R.id.mNamaTransaksi);
            mIdTransaksi = view.findViewById(R.id.mIdTransaksi);
            paymentType = view.findViewById(R.id.paymentType);
            mTime = view.findViewById(R.id.mTime);
            mTotal = view.findViewById(R.id.mTotal);
            mBtnDetail = view.findViewById(R.id.mBtnDetail);
            indicator = view.findViewById(R.id.indicator);
            mStatuskur = view.findViewById(R.id.mStatusKur);
            layoutKur = view.findViewById(R.id.layoutKur);
        }

    }
}
