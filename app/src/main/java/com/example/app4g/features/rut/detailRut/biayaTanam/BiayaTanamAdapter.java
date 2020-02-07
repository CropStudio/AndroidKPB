package com.example.app4g.features.rut.detailRut.biayaTanam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app4g.R;
import com.example.app4g.Utils.Utils;
import com.example.app4g.features.rut.model.BiayaTanam;

import java.util.List;

public class BiayaTanamAdapter extends RecyclerView.Adapter<BiayaTanamAdapter.ViewHolder> {

    private final List<BiayaTanam> biayaTanams;
    String JenisTnm;

    public BiayaTanamAdapter(List<BiayaTanam> items) {
        biayaTanams = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kalender_tanam, parent, false);

        return new ViewHolder(view);
    }

    public void swap(List<BiayaTanam> datas, String JenisTanaman) {
        if (datas == null || datas.size() == 0)
            return;
        if (biayaTanams != null && biayaTanams.size() > 0)
            biayaTanams.clear();
        biayaTanams.addAll(datas);
        if (this.JenisTnm != JenisTanaman)
            this.JenisTnm = JenisTanaman;
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BiayaTanam model = biayaTanams.get(position);
        holder.mJenis.setText(model.getJenis());
        holder.mHarga.setText(Utils.convertRupiah(model.getHarga()));
        holder.mJumlah.setText(model.getJumlah());

//        holder.mJenisTanaman.setText(JenisTnm);

    }

    @Override
    public int getItemCount() {
        return biayaTanams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mJenis, mHarga, mJumlah;

        public ViewHolder(View view) {
            super(view);
            mJenis = view.findViewById(R.id.mJenis);
            mHarga = view.findViewById(R.id.mHarga);
            mJumlah = view.findViewById(R.id.mJumlah);

        }

    }
}
