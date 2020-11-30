package com.app.kpb2.features.petani.program_bantuan.autp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.program_bantuan.autp.model.Result;

import java.util.List;

public class AutpAdapter extends RecyclerView.Adapter<AutpAdapter.ViewHolder> {
    public List<com.app.kpb2.features.petani.program_bantuan.autp.model.Result> autps;
    Activity context;



    public AutpAdapter(List<Result> data, Activity context) {
        this.autps = data;
        this.context = context;
    }

    @Override
    public AutpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_autp, parent, false);
        AutpAdapter.ViewHolder viewHolder = new AutpAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final AutpAdapter.ViewHolder holder, final int position) {
        final com.app.kpb2.features.petani.program_bantuan.autp.model.Result autp = autps.get(position);
        holder.mMt.setText(autp.getMt());
        holder.mTahun.setText(autp.getTahun());
        holder.mAsuransi.setText(autp.getAsuransi());
    }

    @Override
    public int getItemCount() {
        if (autps == null)
            return 0;
        else
            return autps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mMt, mAsuransi, mTahun;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
            mTahun = view.findViewById(R.id.mTahun);
            mAsuransi = view.findViewById(R.id.mAsuransi);


        }

    }
}
