package com.app.kpb2.features.petani.program_bantuan.alokasi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.kpb2.R;
import com.app.kpb2.features.petani.program_bantuan.alokasi.model.Result;

import java.util.List;

public class AlokasiAdapter extends RecyclerView.Adapter<AlokasiAdapter.ViewHolder> {
    public List<com.app.kpb2.features.petani.program_bantuan.alokasi.model.Result> alokasis;
    Activity context;



    public AlokasiAdapter(List<com.app.kpb2.features.petani.program_bantuan.alokasi.model.Result> data, Activity context) {
        this.alokasis = data;
        this.context = context;
    }

    @Override
    public AlokasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_alokasi, parent, false);
        AlokasiAdapter.ViewHolder viewHolder = new AlokasiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final AlokasiAdapter.ViewHolder holder, final int position) {
        final Result alokasi = alokasis.get(position);
        float total = alokasi.getUrea()+alokasi.getSp36()+alokasi.getNpk()+alokasi.getZa()+alokasi.getOrganik();
        String satuan = " KG" ;
        holder.mMt.setText("Masa Tanam "+alokasi.getMt());
        holder.mKomoditas.setText(alokasi.getKomoditas());
        holder.mTotal.setText(String.valueOf(total)+satuan);
        holder.mUrea.setText(String.valueOf(alokasi.getUrea())+satuan);
        holder.mSp36.setText(String.valueOf(alokasi.getSp36())+satuan);
        holder.mNpk.setText(String.valueOf(alokasi.getNpk())+satuan);
        holder.mZa.setText(String.valueOf(alokasi.getZa())+satuan);
        holder.mOrganik.setText(String.valueOf(alokasi.getOrganik())+satuan);
        holder.mTahun.setText(alokasi.getTahun());
    }

    @Override
    public int getItemCount() {
        if (alokasis == null)
            return 0;
        else
            return alokasis.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mMt, mKomoditas, mTotal, mUrea, mSp36, mNpk, mZa, mOrganik, mTahun;
        ViewHolder(View view) {
            super(view);
            mMt = view.findViewById(R.id.mMt);
            mKomoditas = view.findViewById(R.id.mKomoditas);
            mUrea = view.findViewById(R.id.mUrea);
            mTotal = view.findViewById(R.id.mTotal);
            mTahun = view.findViewById(R.id.mTahun);
            mSp36 = view.findViewById(R.id.mSp36);
            mNpk = view.findViewById(R.id.mNpk);
            mZa = view.findViewById(R.id.mZa);
            mOrganik = view.findViewById(R.id.mOrganik);

        }

    }
}
