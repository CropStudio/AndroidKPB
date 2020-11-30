package com.app.kpb2.features.petani.program_bantuan.bantuan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.program_bantuan.bantuan.model.Result;

import java.util.List;

public class BantuanAdapter extends RecyclerView.Adapter<BantuanAdapter.ViewHolder> {
    public List<com.app.kpb2.features.petani.program_bantuan.bantuan.model.Result> bantuans;
    Activity context;



    public BantuanAdapter(List<com.app.kpb2.features.petani.program_bantuan.bantuan.model.Result> data, Activity context) {
        this.bantuans = data;
        this.context = context;
    }

    @Override
    public BantuanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_bantuan, parent, false);
        BantuanAdapter.ViewHolder viewHolder = new BantuanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final BantuanAdapter.ViewHolder holder, final int position) {
        final Result bantuan = bantuans.get(position);
        holder.mCount.setText(String.valueOf(position+1));
        holder.mMt.setText("Masa Tanam "+bantuan.getMt());
        holder.mTahun.setText(bantuan.getTahun());
        holder.mKomoditas.setText(bantuan.getKomoditas());
        holder.mNama.setText(bantuan.getNama());
        holder.mTotal.setText(bantuan.getJumlah() +" "+bantuan.getSatuan());
    }

    @Override
    public int getItemCount() {
        if (bantuans == null)
            return 0;
        else
            return bantuans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mMt, mKomoditas, mTahun , mTotal , mNama,mCount;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            mTahun = view.findViewById(R.id.mTahun);
            mTotal = view.findViewById(R.id.mTotal);
            mNama = view.findViewById(R.id.mNama);
            mCount = view.findViewById(R.id.mCount);


        }

    }
}
